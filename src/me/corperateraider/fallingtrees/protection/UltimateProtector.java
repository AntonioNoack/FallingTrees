package me.corperateraider.fallingtrees.protection;

import java.util.HashMap;

import me.corperateraider.fallingtrees.FallingTrees;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class UltimateProtector implements IProtector {
	
	private HashMap<Player, BlockBreakTestPlayer> players = new HashMap<>();

	@Override public boolean canBuild(Player p, Location l, Block b) {
		
		BlockBreakTestPlayer testingPlayer = players.get(p);
		if(testingPlayer == null) players.put(p, testingPlayer = new BlockBreakTestPlayer(p));
		
		BlockBreakEvent synthetic = new BlockBreakEvent(b, testingPlayer);
		FallingTrees.instance.getServer().getPluginManager().callEvent(synthetic);
		
		return !synthetic.isCancelled();
	}

}
