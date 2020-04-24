package com.ivanit.emobs;

import com.ivanit.emobs.CustomMob;
import com.ivanit.emobs.CustomHead;
import com.ivanit.emobs.ItemEquip;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
//import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
//import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.configuration.file.FileConfiguration;

public class ConfigParser
{
	public static FileConfiguration cfg;
	
	public HashMap<String, CustomMob> mob_configs = new HashMap<>();
	public HashMap<String, ItemEquip> item_configs = new HashMap<>();
	
	public Set<String> itemList = Collections.emptySet();
	public Set<String> mobList = Collections.emptySet();
	public Set<String> headList = Collections.emptySet();
	
	
	void log(String L)
	{
		Bukkit.getLogger().info("[emobs][MobHandler] " + L);
	}
	
	public ConfigParser(FileConfiguration in_cfg)
	{
		cfg = in_cfg;
	}
		
	public void loadCfg()
	{
		log("loading custom items...");
		//	parse all the custom items	
		//	getKeys(deep = false) means we only get the top level keys under "items"	
		
		if (cfg.isConfigurationSection("items"))
		{
			itemList = cfg.getConfigurationSection("items").getKeys(false);
			for ( String item_str : itemList )
			{
				if (cfg.isConfigurationSection("items." + item_str))
				{
					ItemEquip temp_item = new ItemEquip(
							cfg.getItemStack("items." + item_str),
							(float) cfg.getDouble("items." + item_str, 0.1)
						);
					
					item_configs.put(item_str, temp_item);
				}
			}
		}
		
		
		log("loading custom heads...");
		if (cfg.isConfigurationSection("heads"))
		{
			headList = cfg.getConfigurationSection("heads").getKeys(false);
			for ( String head_str : headList )
			{
				if (cfg.isConfigurationSection("heads." + head_str))
				{
					// 
					ItemEquip head_item = new ItemEquip(
							CustomHead.getHead(
								cfg.getItemStack("heads." + head_str + ".itemData"),
								cfg.getString("heads." + head_str + ".texture")),
							(float) cfg.getDouble("heads." + head_str + ".dropChance", 0.2)
					);
				
					item_configs.put(head_str, head_item);
				}
			}
		}
		
		
		log("passing item config...");	
		CustomMob.setConfigs(cfg, item_configs);

		//	go over the mob list
		log("loading custom mobs config...");	
		
		if (cfg.isConfigurationSection("mobs"))
		{
			mobList = cfg.getConfigurationSection("mobs").getKeys(false);
			
			log("parsing custom mob data...");
			for ( String mob_str : mobList )
			{
				if (cfg.isConfigurationSection("mobs." + mob_str))
				{
					log( String.format("	loading mob:  %s", mob_str));
					//	call custom mob constructor to parse the data
					CustomMob mymob = new CustomMob(mob_str);
					mob_configs.put(mob_str, mymob);
				}
			}
		}
		
		// done!
		log(String.format("loaded %d custom mobs, %d custom heads, and %d custom items",
				mobList.size(), headList.size(), itemList.size() ) );
	}
	



	public void spawn_testPig(Location loc)
    {
    	LivingEntity mypig = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
    	    	
    	// mypig.setGlowing(true);
    	mypig.addPassenger(loc.getWorld().spawnEntity(loc, EntityType.PIG));
    	mypig.setCustomName("TEST PIG");
    	mypig.addPotionEffect(new PotionEffect( PotionEffectType.GLOWING, Integer.MAX_VALUE, 1 ) );
    	mypig.setGliding(true);
    	mypig.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
    	mypig.getEquipment().setChestplateDropChance((float) 1.0);
    }
}
