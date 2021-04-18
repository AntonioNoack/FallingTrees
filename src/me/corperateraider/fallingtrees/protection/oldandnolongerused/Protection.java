package me.corperateraider.fallingtrees.protection.oldandnolongerused;

import java.util.ArrayList;

import me.corperateraider.fallingtrees.protection.IProtector;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import us.forseth11.feudal.core.Feudal;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Protection {
	
	private IProtector[] prot;
	
	public boolean isEmpty(){
		return prot.length == 0;
	}
	
	public Protection(){
		
		ArrayList<IProtector> prots = new ArrayList<>();
		Plugin plugin;
		
		try {
			
			// WorldGuard
			plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
			if(plugin instanceof WorldGuardPlugin){
				System.out.println("[FallingTrees] WorldGuard found: will respect it :)");
				prots.add(new WorldGuardProtector((WorldGuardPlugin) plugin));
			}
			
			// Feudal
			plugin = Bukkit.getPluginManager().getPlugin("Feudal");
			if(plugin instanceof Feudal){
				System.out.println("[FallingTrees] Feudal found: will respect it :)");
				prots.add(new FeudalProtector());
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		prot = prots.toArray(new IProtector[prots.size()]);
	}
	
	private boolean first = true;
	
	public boolean canBuild(Player p, Location l, Block b){
		for(int i=0;i<prot.length;i++){
			try {// just in case...
				if(!prot[i].canBuild(p, l, b)) return false;
			} catch(Exception e){
				if(first || Math.random() < .01){
					if(first){
						System.err.println("[FallingTrees] Notice: I will report only a part of all exceptions, because that would be so many!!!");
						first = false;
					} e.printStackTrace();
				}
			}
		} return true;
	}
}
