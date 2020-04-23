package com.ivanit.emobs;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class SpawnRegister implements Listener
{
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	MobHandler mymobs = new MobHandler();
	
	
	//  on creature spawn
	@EventHandler
	public void onSpawn(CreatureSpawnEvent e)
	{
		if (e.getSpawnReason() == SpawnReason.NATURAL)
	    {
			Bukkit.dispatchCommand(console, "say NATURAL MOB SPAWNED");
			mymobs.spawn_testPig(e.getLocation());    		
	    }
	
	
	    if (isHostileMobSpawn(e))
	    {	    	
	    	Bukkit.dispatchCommand(console, "say HOSTILE MOB SPAWNED");	
	    	// Obtain a number between [0 - 49].
	    	//int n = rand.nextInt(50);
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
