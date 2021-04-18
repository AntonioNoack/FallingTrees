package me.corperateraider.fallingtrees.protection.oldandnolongerused;

import me.corperateraider.fallingtrees.protection.IProtector;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class WorldGuardProtector implements IProtector {
	
	private WorldGuardPlugin wg;
	
	public WorldGuardProtector(WorldGuardPlugin wg){
		this.wg = wg;
	}

	@Override public boolean canBuild(Player p, Location l, Block b) {
		return wg.canBuild(p, l);
	}
}
