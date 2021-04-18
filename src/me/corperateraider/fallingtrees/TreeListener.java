package me.corperateraider.fallingtrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import me.corperateraider.fallingtrees.protection.BlockBreakTestPlayer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class TreeListener extends Thread implements Listener {
	
	public static int max = 200;
	public static boolean enabled = true;
	public static boolean protectPlayerHeads = true;
	
	private HashMap<World, HashMap<Long, Player>> players = new HashMap<>();
	private HashSet<World> worlds = new HashSet<>();
	
	public static ArrayList<TreeRunnable> nTRs = new ArrayList<TreeRunnable>();
	public static ArrayList<TreeRunnable> del = new ArrayList<TreeRunnable>();
	
	@Override
	public void run(){
		if(!enabled)return;
		//melde dich fürs nächste Mal nochmal an :)
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this, 1);
		
		//lösche die ungültigen Threads
		for(TreeRunnable nTR : del){
			nTRs.remove(nTR);
		} del = new ArrayList<TreeRunnable>();
		
		
		// schaue, wo sich die Spieler aufhalten
		if(protectPlayerHeads){
			worlds.clear();
			for(TreeRunnable nTR : nTRs){
				worlds.add(nTR.w);
			}
			
			for(World w:worlds){
				HashMap<Long, Player> pls = players.get(w);
				if(pls != null){
					pls.clear();
					for(Player p:w.getPlayers()){
						Location loc = p.getEyeLocation();
						pls.put(lkey(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()), p);
					}
				}
			}
		}
		
		
		//führe alle gültigen Threads aus
		long time = System.currentTimeMillis();
		for(TreeRunnable nTR : nTRs){
			nTR.tick(time);
		}
	}
	
	public FallingTrees plugin;
	public TreeListener(FallingTrees fallingTrees){
		plugin = fallingTrees;
		fallingTrees.getServer().getPluginManager().registerEvents(this, fallingTrees);
	}
	
	@EventHandler
	public void ini(BlockBreakEvent event) throws InterruptedException {
		Player p = event.getPlayer();
		if(!(p instanceof BlockBreakTestPlayer)){
			
			World w = event.getBlock().getWorld();
			if(!plugin.disabledIn.contains(w.getName())){
				
				Block a = event.getBlock();
				
				Block a1 = a, a2 = w.getBlockAt(a.getX(), a.getY()+1, a.getZ());
				
				if(!p.isSneaking() && (
						plugin.noProtection || (
								plugin.protection.canBuild(p, a1.getLocation(), a1) &&
								plugin.protection.canBuild(p, a2.getLocation(), a2)
						)
					) && 
					isRealLog(a1) && 
					isRealLog(a2) && 
					LumberEnchanting.had(event.getPlayer().getItemInHand())){
					
					HashMap<Long, Player> players;
					if(protectPlayerHeads){
						players = this.players.get(w);
						if(players == null) this.players.put(w, players = new HashMap<>());
					} else players = null;
					
					TreeRunnable r = new TreeRunnable(a, w, p, players);
					
					if(r.isTree()){
						nTRs.add(r);
					}
				}
			}
		}
	}
	
	
	boolean isRealLog(Block b){return b.getType()==Material.LOG || (plugin.LOG_2 && b.getType()==Material.LOG_2);}
	
	static ArrayList<Runnable> threads = new ArrayList<>();
	
	public static float fSpeed = 1;
	
	public static double
		maxAlpha = 110,
		maxMilliSecs = 15000,
		maxLogsRow = 8, maxLeavesRow = 4,
		minLeaves0 = 1, minLogs0 = 3, minLogsPerLeave = .03;
	
	class TreeRunnable {
		
		float alpha;
		Block a;
		World w;
		Player p;
		ArrayList<FallingBlock> bs = new ArrayList<>();
		final HashMap<Location, Boolean> buildable = new HashMap<>();
		final HashMap<Long, Player> players;
		final double pureRandomDirection = Math.random()*6.28, neigung = Math.random()*.8-.4;
		final int xq, yq, zq, h;
		int t;
		
		final long startTime;
		
		public boolean ok(Block b){
			if(players != null){// es soll auf die Spieler acht gegeben werden
				Player player = players.get(lkey(b.getX(), b.getY(), b.getZ()));
				if(player != null) return false;
			}
			
			if(plugin.noProtection) return true;
			Location loc = b.getLocation();
			Boolean ok = buildable.get(b);
			if(ok == null) buildable.put(loc, ok = plugin.protection.canBuild(p, loc, b));
			return ok;
		}
		
		class FallingBlock {
			int x0, y0, z0, id, meta, x1, y1, z1;
			Block old;
			
			public FallingBlock(int x, int y, int z, int id, int meta, Block b){
				x0=x1=x;
				y0=y1=y;
				z0=z1=z;
				this.id=id;
				this.meta=meta;
				old=b;
			}
			
			@SuppressWarnings("deprecation")
			public void change(DrehMatrix m, int mode, int dir){
				int x=m.x(x0,y0,z0),
					y=m.y(x0,y0,z0),
					z=m.z(x0,y0,z0);
				if((x!=x1 || y!=y1 || z!=z1) && old.getTypeId() == id){
					Block b2 = w.getBlockAt(xq+x, yq+y, zq+z);
					if(b2.isEmpty() && ok(b2) && old.getTypeId() == id){
						
						// teste, ob man hier bauen darf
						
						if(id == 17 || id == 162){
							if(mode==1){
								meta = (meta&3) + dir;
							} else {
								if(meta>4){
									meta &= 3;
								}
							}
						}
						
						b2.setTypeIdAndData(id, (byte) meta, false);
						old.setType(Material.AIR);
						old = b2;
						x1=x;y1=y;z1=z;
					}
				}
			}
		}
		
		public void tick(long time){
			if(alpha > maxAlpha || time-startTime>maxMilliSecs){
				TreeListener.del.add(this);
			} else {
				float dif = 50f * this.t++ / this.h * fSpeed;
				if (dif == 0.0F) {dif = 0.00001f;}
				this.alpha += dif;
				DrehMatrix m = new DrehMatrix().rotY(-pureRandomDirection).rotX(alpha*.017).rotZ(neigung * Math.min(1, Math.abs(alpha))).rotY(pureRandomDirection);//
				m.finalize();
				int mode = Math.round(alpha/90) & 1, dir = m.dir();
				for(FallingBlock b:bs){
					b.change(m, mode, dir);
				}
			}
		}
		
		public boolean isTree() {
			int lo = 0;
			int le = 0;
			for(FallingBlock b:bs){
				if(b.id==17 || b.id==162){
					lo++;
				} else {
					le++;
				}
			} return lo >= minLogs0 + minLogsPerLeave * le && le >= minLeaves0;
		}

		@SuppressWarnings("deprecation")
		public TreeRunnable(Block block, World world, Player player, HashMap<Long, Player> players) throws InterruptedException {
			
			a = block;
			w = world;
			p = player;
			
			this.players = players;
			
			startTime = System.currentTimeMillis();
			
			ArrayList<Possibly> all = new ArrayList<>();
			HashMap<Integer, Possibly> keys = new HashMap<>();
			
			Possibly p = new Possibly(block, block.getX(), block.getY(), block.getZ(), 0, 0, true);
			all.add(p);
			keys.put(key(p.x, p.y, p.z), p);
			
			int int1 = 0;
			while(int1<all.size()){
				all.get(int1++).find(w, keys, all);
			}
			
			t = 0;
			xq=a.getX();
			yq=a.getY();
			zq=a.getZ();
			for(Possibly pos:all){
				Block b = pos.block;
				bs.add(new FallingBlock(b.getX()-xq, b.getY()-yq, b.getZ()-zq, b.getTypeId(), b.getData(), b));
			}
			
			h = (int) ((bs.size() * 50 + 6) / fSpeed) + 1;//keine Ahnung...
			
			alpha = 0;
		}
		
		class Possibly {
			Block block;
			boolean log;
			int dist, dist2, x, y, z;
			Possibly(Block b, int x, int y, int z, int dist, int dist2, boolean log){
				this.x=x;this.y=y;this.z=z;this.dist=dist;this.dist2=dist2;this.log=log;
				block = b;
			}
			
			private void find(World w, HashMap<Integer, Possibly> a, ArrayList<Possibly> b){
				int key;
				for(int i=-1;i<2;i++){
					for(int j=-1;j<2;j++){
						for(int k=-1;k<2;k++){
							if(!(i==0 && j==0 && k==0) && (j>=0 || Math.random()<.3) && !a.containsKey(key=key(x+i,y+j,z+k))){
								test(w,x+i,y+j,z+k,key,a,b);
							}
						}
					}
				}
			}
			
			private void test(World w, int x, int y, int z, int key, HashMap<Integer, Possibly> a, ArrayList<Possibly> all){
				Block b = w.getBlockAt(x,y,z);
				if(ok(b)){
					Material m = b.getType();
					Possibly n = null;
					if(log){
						if(isLog(m) && dist2<maxLogsRow){
							n = new Possibly(b, x, y, z, dist+1, dist2+1, true);
						} else if(isLeaves(m)){
							n = new Possibly(b, x, y, z, 0, dist2+2, false);
						}
					} else {
						if(isLog(m) && dist<2 && dist2<maxLogsRow){
							n = new Possibly(b, x, y, z, dist+1, dist2+2, true);
						} else if(isLeaves(m) && dist<maxLeavesRow){
							n = new Possibly(b, x, y, z, dist+1, dist2+2,false);
						}
					}
					if(n != null){
						a.put(key,n);
						all.add(n);
					}
				}
			}
		}
	}
	
	private final boolean isLeaves(Material m) {return m==Material.LEAVES || (plugin.LOG_2 && m==Material.LEAVES_2) || m==Material.VINE;}
	private final boolean isLog(Material b){return b==Material.LOG || (plugin.LOG_2 && b==Material.LOG_2);}
	
	static int key(int x, int y, int z){
		return ((x&1023)<<20)+((y&1023)<<10)+(z&1023);
	}
	
	static long lkey(int x, int y, int z){// nicht perfekt, doch das braucht es ja auch nicht sein: die Falsch-Schutz-Rate ist durchaus klein
		return (((long)(x & 0xfffff)) << 40)+(((long)(y & 0xfffff)) << 20)+((long)(z & 0xfffff));
	}
}
