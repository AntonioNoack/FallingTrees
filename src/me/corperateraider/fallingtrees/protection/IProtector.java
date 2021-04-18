package me.corperateraider.fallingtrees.protection;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface IProtector {
	boolean canBuild(Player p, Location l, Block b);
}
