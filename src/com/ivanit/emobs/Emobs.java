package com.ivanit.emobs;

import com.ivanit.emobs.Emob_manual;
import com.ivanit.emobs.Emob_mobs;

import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
//import org.bukkit.ChunkSnapshot;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.Location;
import org.bukkit.entity.*;

//import org.bukkit.block;
//import java.util.Random;

public class Emobs extends JavaPlugin 
{	
	//	vars
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    Emob_mobs myspawner = new Emob_mobs();
    
    String cfgFileName = "emobs.yml";
	private File customConfigFile;
    FileConfiguration cfg;
	
	//	load config
    @Override
    public void onEnable()
    {
        createCustomConfig();
        
        myspawner.cfg = getCfg();
        myspawner.loadCfg();
    }

    public FileConfiguration getCfg()
    {
        return this.cfg;
    }

    private void createCustomConfig()
    {
        customConfigFile = new File(getDataFolder(), cfgFileName);
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource(cfgFileName, false);
         }

        cfg = new YamlConfiguration();
        try
        {
            cfg.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    
    
        
    
    //    on creature spawn
    @EventHandler
    public void onSpawn(CreatureSpawnEvent e)
    {
    	Bukkit.dispatchCommand(console, "say HOSTILE MOB SPAWNED");	
    	
    	if (e.getSpawnReason() == SpawnReason.NATURAL)
	    {
    		myspawner.spawn_testPig(e.getLocation());    		
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
