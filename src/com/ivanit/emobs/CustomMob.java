package com.ivanit.emobs;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class CustomMob
{
	public static HashMap<String, ItemStack> item_configs;
	public static FileConfiguration cfg;
	
//	public static HashMap<String, >
	
	public CustomMob(String mob_cfg_id)
	{
		//	get attributes
		Set<String> attrs = cfg.getConfigurationSection("mobs." + mob_cfg_id).getKeys(false);
	}
}
