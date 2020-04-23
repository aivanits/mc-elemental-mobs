package com.ivanit.emobs;

import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.Bukkit;
//import org.bukkit.ChunkSnapshot;
import org.bukkit.command.ConsoleCommandSender;

//import org.bukkit.block;
import java.util.Random;

public class Emobs extends JavaPlugin 
{
	//	Fired when plugin is enabled
	@Override
    public void onEnable() 
	{
		Bukkit.dispatchCommand(console, "say Elemental Mobs plugin enabled");	
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() 
    {

    }
    
    
    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    
    
    @EventHandler
    public void onSpawn(CreatureSpawnEvent e)
    {
    	Bukkit.dispatchCommand(console, "say HOSTILE MOB SPAWNED");	
	    if (isHostileMobSpawn(e))
	    {	    	
	    	Bukkit.dispatchCommand(console, "say HOSTILE MOB SPAWNED");	
	    	// Obtain a number between [0 - 49].
    		//int n = rand.nextInt(50);
	    }
    }
    
    
    
    
    //    helper functions
    
    Random rand = new Random();

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
