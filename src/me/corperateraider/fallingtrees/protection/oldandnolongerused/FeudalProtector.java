package me.corperateraider.fallingtrees.protection.oldandnolongerused;

import me.corperateraider.fallingtrees.protection.IProtector;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import us.forseth11.feudal.api.FeudalAPI;
import us.forseth11.feudal.core.Feudal;
import us.forseth11.feudal.kingdoms.Kingdom;
import us.forseth11.feudal.kingdoms.Land;
import us.forseth11.feudal.user.User;

/**
 * unsure whether it works
 * */
public class FeudalProtector implements IProtector {

	// kingdom.hasProtection betrifft nur Kisten und TNT
	// die Unterscheidung nach Setzen/Graben interessiert uns nicht, weil wir meist beides machen
	
	final boolean
		shieldProtection,
		landProtection,
		apiNull;
	
	final FeudalAPI api;
	
	public FeudalProtector(){
		shieldProtection = 
				Feudal.getConfiguration().getConfig().getBoolean("kingdom.shieldProtection.blockBreak") ||
				Feudal.getConfiguration().getConfig().getBoolean("kingdom.shieldProtection.blockPlace");
		landProtection = 
				Feudal.getConfiguration().getConfig().getBoolean("kingdom.landProtection.blockBreak") ||
				Feudal.getConfiguration().getConfig().getBoolean("kingdom.landProtection.blockPlace");
		
		api = Feudal.getAPI();
		apiNull = api==null;
		if(apiNull) System.out.println("[FallingTrees] Warning concerning Feudal: the API is null!");
	}
	
	/**
	 * musste auch zum Großteil übernommen werden, weil alles privat oder unbrauchbar ist
	 * */
	public boolean canBuild(Player player, Location loc, Block block) {
		Kingdom kingdom;
		if(apiNull) // kein Zugriff auf die API: kann einen Fehler in der Zukunft verursachen...
			kingdom = Feudal.getLandKingdom(new Land(loc));
		else
			kingdom = api.getKingdom(loc);
		
		return kingdom == null || hasPermission(kingdom, player) || !(landProtection || (shieldProtection && kingdom.isShielded()));
	}
	
	/**
	 * musste leider übernommen werden, weil es eine private Funktion ist
	 **/
	public boolean hasPermission(Kingdom kingdom, Player player){
		if(player.hasPermission("feudal.admin.passProtection")) return true;
		User u = Feudal.getUser(player.getUniqueId().toString());// auch kein Zugriff auf die API...
		if(u == null) return false;
		if(u.getKingdomUUID().equals(kingdom.getUUID())) return !kingdom.isVacation();
		return false;
	}
}
