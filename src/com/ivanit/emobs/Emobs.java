package com.ivanit.emobs;

import com.ivanit.emobs.CommandHandler;
import com.ivanit.emobs.MobHandler;
import com.ivanit.emobs.SpawnRegister;

import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;

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
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    MobHandler mymobs = new MobHandler();
    
    FileConfiguration cfg = getConfig();
	//    String cfgFileName = "emobs.yml";
	//	private File customConfigFile;
	//    FileConfiguration cfg;
	
	//	load config
    @Override
    public void onEnable()
    {
    	Bukkit.getLogger().info("enabling Elemental Mobs");
    	cfg.addDefault("testConfigVal", true);
        cfg.options().copyDefaults(true);
        saveConfig();
        
//        mymobs.cfg = cfg;
//        mymobs.loadCfg();
        
        this.getCommand("mypig").setExecutor(new CommandHandler());
        
//        SpawnRegister mySpReg = new SpawnRegister();
//        mySpReg.mymobs = mymobs;
        
//        this.getServer().getPluginManager().registerEvents(mySpReg, this);
    }
}
