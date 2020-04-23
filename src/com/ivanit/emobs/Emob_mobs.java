package com.ivanit.emobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;




public class Emob_mobs
{
	
	
	
	public class custom_mob {
		ItemStack
	}
	
	
	
	
	
	
	ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
	
	
	
	
	
	
	
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
