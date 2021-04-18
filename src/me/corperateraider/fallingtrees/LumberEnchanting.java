package me.corperateraider.fallingtrees;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LumberEnchanting implements Listener {
	
	public static boolean enabled = true;
	public static String loreName = "§r§7Lumberjack I";
	public static double chance = 0.1;
	public static boolean had(ItemStack is) {
		if(!enabled) return true;
		if(is==null || !is.hasItemMeta() || !is.getItemMeta().hasLore())return false;
		for(String s:is.getItemMeta().getLore()){if(s.equals(loreName))return true;}
		return false;
	}
	
	@EventHandler
	public void enchant(org.bukkit.event.enchantment.EnchantItemEvent e){
		if(!enabled || Math.random()>chance)return;
		final ItemStack is = e.getItem();
		switch(is.getType()){
		case WOOD_AXE:
		case STONE_AXE:
		case IRON_AXE:
		case GOLD_AXE:
		case DIAMOND_AXE:
			Bukkit.getScheduler().scheduleSyncDelayedTask(FallingTrees.instance, new Runnable(){

				@Override
				public void run() {
					ItemMeta m = is.getItemMeta();
					if(m.hasLore()){
						ArrayList<String> lore = new ArrayList<>();
						lore.addAll(m.getLore());
						lore.add(loreName);
						m.setLore(lore);
						is.setItemMeta(m);
					} else {
						ArrayList<String> lore = new ArrayList<>();
						lore.add(loreName);
						m.setLore(lore);
						is.setItemMeta(m);
					}
				}
				
			});
			break;
		default:
		}
	}
}
