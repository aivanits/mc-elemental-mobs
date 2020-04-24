package com.ivanit.emobs;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
//import java.util.Base64;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomMob
{
	public static HashMap<String, ItemStack> item_cfg;
	public static FileConfiguration cfg;
	
	ItemStack helm;
	ItemStack chest;
	ItemStack leg;
	ItemStack boot;
	
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
