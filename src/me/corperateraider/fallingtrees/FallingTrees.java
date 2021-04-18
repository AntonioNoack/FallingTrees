package me.corperateraider.fallingtrees;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import me.corperateraider.fallingtrees.protection.UltimateProtector;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FallingTrees extends JavaPlugin {
	
	public static FallingTrees instance;
	public UltimateProtector protection;
	public boolean noProtection = false;
	public boolean LOG_2 = false;
	public HashSet<String> disabledIn = new HashSet<>();

	@Override
	public void onEnable(){
		
		instance = this;
		
		protection = new UltimateProtector();
		
		try {
			Material.LOG_2.name();
			LOG_2 = true;
		} catch (NoSuchFieldError e){}
		
		MathHelper.init();
		try {
			
			/////////////////////////////////////////////////
			if(!getDataFolder().exists()){
				System.out.println();getDataFolder().mkdir();
			}
			File f = new File(getDataFolder()+"/config.txt");
			if(!f.exists())
				f.createNewFile();
			/////////////////////////////////////////////////
			
			getConfig().load(getDataFolder()+"/config.txt");
			if(!getConfig().contains("sendData2mcstatsorg")){
				getConfig().set("sendData2mcstatsorg", true);
				System.out.println("FallingTrees plugin set the send Data2mcstats.org configuration to true. Please change, if you doesnt want to send this data. We only send that we exist.");
			} else if(this.getConfig().getBoolean("sendData2mcstatsorg")){
				try {
					MetricsLite metrics = new MetricsLite(this);
					metrics.start();
				} catch (IOException e) {
					//failed to connect to mcstats.org :(
				}
			}
			FileConfiguration confi = getConfig();
			
			TreeListener.maxMilliSecs = set(confi, "stopFallingAfterMilliSeconds", TreeListener.maxMilliSecs);
			TreeListener.fSpeed = (float) (set(confi, "speedFactor", TreeListener.fSpeed));
			TreeListener.maxAlpha = set(confi, "maxAlpha", TreeListener.maxAlpha);
			TreeListener.maxLogsRow = set(confi, "maxLogsInARow", TreeListener.maxLogsRow);
			TreeListener.maxLeavesRow = set(confi, "maxLeavesInARow", TreeListener.maxLeavesRow);
			TreeListener.minLeaves0 = set(confi, "minLeaves", TreeListener.minLeaves0);
			TreeListener.minLogs0 = set(confi, "minLogs", TreeListener.minLogs0);
			TreeListener.minLogsPerLeave = set(confi, "minLogsPerLeave", TreeListener.minLogsPerLeave);
			TreeListener.protectPlayerHeads = set(confi, "protectPlayerHeads", TreeListener.protectPlayerHeads);
			LumberEnchanting.enabled = set(confi, "enchantment.needed", false);
			LumberEnchanting.chance = set(confi, "enchantment.chance", LumberEnchanting.chance);
			LumberEnchanting.loreName = set(confi, "enchantment.name", LumberEnchanting.loreName);
			List<String> disabledIns = set(confi, "disabledWorlds", new ArrayList<String>());
			if(disabledIns != null) disabledIn.addAll(disabledIns);
			
			confi.save(getDataFolder()+"/config.txt");
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
		new TreeListener(this).run();
		getServer().getPluginManager().registerEvents(new LumberEnchanting(), this);
		TreeListener.enabled = true;
	}
	
	double set(FileConfiguration confi, String name, double def){
		if(confi.isSet(name)){
			return confi.getDouble(name);
		} else {
			confi.set(name, def);
			return def;
		}
	}
	
	int set(FileConfiguration confi, String name, int def){
		if(confi.isSet(name)){
			return confi.getInt(name);
		} else {
			confi.set(name, def);
			return def;
		}
	}
	
	List<String> set(FileConfiguration confi, String name, List<String> def){
		if(confi.isSet(name)){
			return confi.getStringList(name);
		} else {
			confi.set(name, def);
			return def;
		}
	}
	
	String set(FileConfiguration confi, String name, String def){
		if(confi.isSet(name)){
			return confi.getString(name);
		} else {
			confi.set(name, def);
			return def;
		}
	}
	
	boolean set(FileConfiguration confi, String name, boolean def){
		if(confi.isSet(name)){
			return confi.getBoolean(name);
		} else {
			confi.set(name, def);
			return def;
		}
	}
	
	@Override
	public void onDisable(){
		TreeListener.enabled = false;
		TreeListener.nTRs.clear();
	}
}

/**
 * a class for fast sin and cos
 * */
class MathHelper {
	
	static float[] sin = new float[65536], cos = new float[65536];
	
	static void init(){
		for(int i=0;i<65536;i++){
			sin[i] = cos[(i+49152) & 0xffff] = (float) Math.sin(i);
		}
	}
	
	public static float sin(float x){
		float v = sin[(int)(abs(x)*10430.37835f) & 0xffff];
		return x < 0 ? -v : +v;
	}
	
	public static float cos(float x){
		return cos[(int)(abs(x)*10430.37835f) & 0xffff];
	}
	
	private static float abs(float r){
		return r<0?-r:r;
	}
}