package com.ivanit.emobs;

//import com.ivanit.emobs.CommandSpawn;
//import com.ivanit.emobs.ConfigParser;
//import com.ivanit.emobs.SpawnRegister;

//import org.bukkit.event.entity.CreatureSpawnEvent;
//import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.entity.EntityType;
//import org.bukkit.event.EventHandler;
//import org.bukkit.plugin.Plugin;

import org.bukkit.Bukkit;
//import org.bukkit.ChunkSnapshot;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;

//import org.bukkit.configuration.InvalidConfigurationException;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.Location;
//import org.bukkit.entity.*;

//import org.bukkit.block;
//import java.util.Random;

public class Emobs extends JavaPlugin 
{	
	//	vars
    ConfigParser cfgParsed;
    
    //	TODO: implement a proper logger
    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    void log(String L)
	{
		Bukkit.getLogger().info("[emobs] " + L);
	}
    
    FileConfiguration cfg;
	
	//	load config
    @Override
    public void onEnable()
    {
    	// enable plugin, load configuration
    	log("enabling Elemental Mobs");
    	
    	log("saving default config file...");
    	this.saveDefaultConfig();
    	log("loading config file...");
    	cfg = getConfig();
        
        // loads all the custom mobs
        log("parsing config file...");
        cfgParsed = new ConfigParser(cfg);
        cfgParsed.loadCfg();
        
        // start the command listener
        log("starting command listeners...");
        this.getCommand("eMob").setExecutor(new CommandSpawn(cfgParsed));
        this.getCommand("eItem").setExecutor(new CommandGiveItem(cfgParsed));
        
        // create and start the mob spawn listener
        log("starting mob spawn listener...");
        SpawnRegister mySpReg = new SpawnRegister(cfgParsed);
        this.getServer().getPluginManager().registerEvents(mySpReg, this);
        
        log("Finished Loading!");
    }
}
