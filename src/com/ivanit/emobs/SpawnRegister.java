package com.ivanit.emobs;

import org.bukkit.Bukkit;
//import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class SpawnRegister implements Listener
{
	ConfigParser mymobs;
	
	void log(String L)
	{
		Bukkit.getLogger().info("[emobs][SpawnRegister] " + L);
	}
	
	
	public SpawnRegister(ConfigParser in_mobs)
	{
		mymobs = in_mobs;
	}
	
	
	//  on creature spawn
	@EventHandler
	public void onSpawn(CreatureSpawnEvent e)
	{
		if (e.getSpawnReason() == SpawnReason.NATURAL)
	    {
		    if (isHostileMobSpawn(e))
		    {
		    	for (String mob : mymobs.mobList)
		    	{
		    		// try to spawn each valid mob
		    		// the called function will handle spawn chance
		    		mymobs.mob_configs.get(mob).spawn(e.getLocation());
		    	}
		    }
	    }
    }
	  
	  
	  
	//    helper function
	public boolean isHostileMobSpawn(CreatureSpawnEvent e)
	{
		if (e.getSpawnReason() == SpawnReason.NATURAL)
	    {
	    	if (
			(e.getEntityType() == EntityType.ZOMBIE) 
	    		|| (e.getEntityType() == EntityType.SKELETON)
	    		|| (e.getEntityType() == EntityType.CREEPER)
	    		|| (e.getEntityType() == EntityType.SPIDER)
	    	) {
	    		return true;
	    	}
	    }
	
		return false;
	}
}
