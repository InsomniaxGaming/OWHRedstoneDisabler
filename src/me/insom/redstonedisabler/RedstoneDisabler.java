package me.insom.redstonedisabler;

import me.insom.redstonedisabler.listeners.RedstoneListener;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag;

public class RedstoneDisabler extends org.bukkit.plugin.java.JavaPlugin{
	
	WorldGuardPlugin worldGuard = null;
	WGCustomFlagsPlugin customFlags = null;
	
	StateFlag flag = new StateFlag("redstone", true);
	
	public void onEnable(){
		
		worldGuard = (WorldGuardPlugin)this.getServer().getPluginManager().getPlugin("WorldGuard"); 
		customFlags = (WGCustomFlagsPlugin)this.getServer().getPluginManager().getPlugin("WGCustomFlags");
		
		if (worldGuard == null || customFlags == null) // Check that we have both plugins, so the server doesn't explode
		{
			this.getPluginLoader().disablePlugin(this);
	    }
		
		customFlags.addCustomFlag(flag);
		
		// register redstone listener
		this.getServer().getPluginManager().registerEvents(new RedstoneListener(this), this);
		
	}
	
	public WorldGuardPlugin getWorldGuard()
	{
		return worldGuard;
	}
	
	public WGCustomFlagsPlugin getCustomFlagsPlugin()
	{
		return customFlags;
	}
	
	public StateFlag getRedstoneFlag(){
		return flag;
	}
	
	public void onDisable(){}

}
