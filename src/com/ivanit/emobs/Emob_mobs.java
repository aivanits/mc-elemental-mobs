package com.ivanit.emobs;

import com.ivanit.emobs.CustomMob;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.configuration.file.FileConfiguration;

public class Emob_mobs
{
	public static FileConfiguration cfg;
	
	public HashMap<String, CustomMob> mob_configs = new HashMap<>();
	public HashMap<String, ItemStack> item_configs = new HashMap<>();
		
	
	public void loadCfg()
	{
		//	parse all the custom items	
		Set<String> itemList = cfg.getConfigurationSection("items").getKeys(false);
		for ( String item_str : itemList )
		{
			ItemStack temp_item = cfg.getItemStack("items." + item_str);
			item_configs.put(item_str, temp_item);
		}
			
		//	go over the mob list
		CustomMob.item_configs = item_configs;
		Set<String> moblist = cfg.getConfigurationSection("mobs").getKeys(false);
		for ( String mob_str : moblist )
		{
			//	call custom mob constructor to parse the data		
			CustomMob mymob = new CustomMob(mob_str);
			mob_configs.put(mob_str, mymob);
		}
	}


	public void spawn_testPig(Location loc)
    {
    	LivingEntity mypig = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
    	    	
    	// mypig.setGlowing(true);
    	mypig.addPassenger(loc.getWorld().spawnEntity(loc, EntityType.PIG));
    	mypig.setCustomName("TEST PIG");
    	mypig.setGliding(true);
    	mypig.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
    	mypig.getEquipment().setChestplateDropChance((float) 1.0);
    }
}
