package com.ivanit.emobs;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class CustomMob
{
	public static HashMap<String, ItemStack> item_cfg;
	public static FileConfiguration cfg;
	
	void log(String L)
	{
		Bukkit.getLogger().info("[emobs][CustomMob] " + L);
	}
	
	
	
	public static void setConfigs(FileConfiguration in_cfg, HashMap<String, ItemStack> in_item_configs)
	{
		item_cfg = in_item_configs;
		cfg = in_cfg;
	}
	
//	public static HashMap<String, >
	
	public CustomMob(String mob_cfg_id)
	{
		//	get attributes		
		Set<String> attrs = cfg.getConfigurationSection("mobs." + mob_cfg_id).getKeys(false);
	}
}
