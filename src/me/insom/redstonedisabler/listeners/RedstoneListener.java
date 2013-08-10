package me.insom.redstonedisabler.listeners;

import me.insom.redstonedisabler.OWHRedstoneDisabler;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockRedstoneEvent;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;

public class RedstoneListener implements org.bukkit.event.Listener{
	
	OWHRedstoneDisabler myPlugin = null;
	StateFlag flag = null;
	
	public RedstoneListener(OWHRedstoneDisabler instance)
	{
		myPlugin = instance;
		flag = myPlugin.getRedstoneFlag();
	}
	
	@EventHandler
	public void onBlockRedstone(BlockRedstoneEvent e)
	{
		Block block = e.getBlock();
		RegionManager manager = myPlugin.getWorldGuard().getRegionManager(block.getWorld());
		
		//First, check global region
		if(!(myPlugin.getWorldGuard().getGlobalRegionManager().allows(flag, block.getLocation())))
		{
			e.setNewCurrent(0);
			return;
		}

		ApplicableRegionSet set = manager.getApplicableRegions(block.getLocation());
		
		//check any regions the player is in
		if(set != null ){
			if(!set.allows(flag))
			{
				e.setNewCurrent(0);
			}
		}
	}
}
