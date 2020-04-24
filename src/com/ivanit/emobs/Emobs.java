package com.ivanit.emobs;

import com.ivanit.emobs.CommandHandler;
import com.ivanit.emobs.MobHandler;
import com.ivanit.emobs.SpawnRegister;

import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
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
	//	TODO: implement a proper logger
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    MobHandler mymobs;
    
    void log(String L)
	{
		Bukkit.getLogger().info("[emobs] " + L);
	}
    
    FileConfiguration cfg;
	//    String cfgFileName = "emobs.yml";
	//	private File customConfigFile;
	//    FileConfiguration cfg;
	
	//	load config
    @Override
    public void onEnable()
    {
    	// enable plugin, load configuration
    	log("enabling Elemental Mobs");
    	cfg = getConfig();
    	
    	this.saveDefaultConfig();
    	
//    	cfg.addDefault("testConfigVal", true);
//    	cfg.addDefault("items.test1", true);
//    	cfg.addDefault("items.test2", true);
//    	cfg.addDefault("mobs.test1", true);
//    	cfg.addDefault("mobs.test1.attr1", true);
//    	cfg.addDefault("mobs.test1.attr2", true);
//    	cfg.addDefault("mobs.test2", true);
//    	cfg.addDefault("mobs.test2.attr1", true);
//    	cfg.addDefault("mobs.test2.attr2", true);
    	
    	cfg.options().copyDefaults(true);
        saveConfig();
        Bukkit.getLogger().info("loading config file...");
        
        // loads all the custom mobs
        mymobs = new MobHandler(cfg);
        mymobs.loadCfg();
        
        // start the command listener
        Bukkit.getLogger().info("starting command listener...");
        this.getCommand("mypig").setExecutor(new CommandHandler(mymobs));
        
        // create and start the mob spawn listener
        Bukkit.getLogger().info("starting mob spawn listener...");
        SpawnRegister mySpReg = new SpawnRegister(mymobs);
        this.getServer().getPluginManager().registerEvents(mySpReg, this);
        
        Bukkit.getLogger().info("Finished Loading!");
    }
}
