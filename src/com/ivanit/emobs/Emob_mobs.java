package com.ivanit.emobs;

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
	public HashMap<String, Custom_mob> mob_configs = new HashMap<>();
	public HashMap<String, ItemStack> item_configs = new HashMap<>();
		
	
	public void loadCfg(FileConfiguration cfg)
	{
		Set<String> moblist = cfg.getConfigurationSection("mobs").getKeys(false);
		for ( String mob : moblist )
		{
			mob_configs.put(mob, Custom_mob_ctor(cfg, mob));
		}
	}


	private Custom_mob Custom_mob_ctor(FileConfiguration cfg, String mob) {
		return Custom_mob(cfg, mob);
	}


	public void spawn_testPig(Location loc)
    {
    	LivingEntity mypig = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
    	    	
    	// mypig.setGlowing(true);
    	mypig.addPassenger(loc.getWorld().spawnEntity(loc, EntityType.PIG));
    	mypig.setCustomName("TEST PIG");
    	mypig.setGliding(true);
    	mypig.getEquipment().setChestplate(null);
    	mypig.getEquipment().setChestplateDropChance((float) 1.0);
    }
}
