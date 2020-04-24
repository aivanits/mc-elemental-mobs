package com.ivanit.emobs;

import com.ivanit.emobs.ItemEquip;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
//import java.util.Base64;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomMob
{
	public static Random rand = new Random();
	    
	//	static: items, config file
	public static HashMap<String, ItemEquip> item_cfg;
	public static FileConfiguration cfg;
	
	// base type
	EntityType base = EntityType.ZOMBIE;
    // spawn chance
	double chance = 0.00F;
	// TODO: biome limits, height limits
	
	
	String mob_ID = "NULL";
	// in game nametag
	String name = "NULL";
	boolean name_vis = false;
    
	// held items
	ItemEquip heldItem = new ItemEquip();
	ItemEquip heldItem_ofh = new ItemEquip();
       
    // armor
	ItemEquip helm = new ItemEquip();
	ItemEquip chest = new ItemEquip();
	ItemEquip leg = new ItemEquip();
	ItemEquip boot = new ItemEquip();
	
	// potion effects
	Set<PotionEffect> effects = new HashSet<>();
	
	// TODO: other data (health, speed, etc?)
	
	
	// logging
	void log(String L)
	{
		Bukkit.getLogger().info("[emobs][CustomMob] " + L);
	}
	
	// static set configurations (across all instances(
	public static void setConfigs(FileConfiguration in_cfg, HashMap<String, ItemEquip> in_item_configs)
	{
		item_cfg = in_item_configs;
		cfg = in_cfg;
	}
	
	
	public ItemEquip tryItem(String item_path)
	{
		String item_name = "mobs." + mob_ID + "." + item_path;
		if (item_cfg.containsKey(item_name))
		{
			return item_cfg.get(item_name);
		}
		else if (Material.matchMaterial(item_name) != null)
		{
			return new ItemEquip(Material.matchMaterial(item_name), 0.5F);
		}
		else
		{
			return new ItemEquip();
		}
	}
	
	
	// create mob from config
	@SuppressWarnings("deprecation")
	public CustomMob(String mob_cfg_id)
	{
		mob_ID = mob_cfg_id;
		//	get attributes
		Set<String> attrs = cfg.getConfigurationSection("mobs." + mob_ID).getKeys(false);
		
		// core data
		if (attrs.contains("base"))
		{
			base = EntityType.fromName(cfg.getString("mobs." + mob_ID + ".base"));
		}
		
		if (attrs.contains("chance"))
		{
			chance = cfg.getDouble("mobs." + mob_ID + ".chance");
		}
		
		// equipment
		if (attrs.contains("item"))
		{
			heldItem = tryItem("item");
		}
		
		if (attrs.contains("itemOfh"))
		{
			heldItem_ofh = tryItem("itemOfh");
		}
		
		if (attrs.contains("helm"))
		{
			helm = tryItem("helm");
		}
		
		if (attrs.contains("chest"))
		{
			chest = tryItem("chest");
		}
		
		if (attrs.contains("leg"))
		{
			leg = tryItem("leg");
		}
		
		if (attrs.contains("boot"))
		{
			boot = tryItem("boot");
		}
		
		
		// potion effects
		if (attrs.contains("potions"))
		{
			Set<String> potions_set = cfg.getConfigurationSection("mobs." + mob_ID + ".potions").getKeys(false);
			for (String effect_string : potions_set  )
			{
				int effect_strength = cfg.getInt("mobs." + mob_ID + ".potions." + effect_string, 1);
				PotionEffectType efct_type = PotionEffectType.getByName(effect_string);
				
				if (efct_type != null)
				{
					effects.add( new PotionEffect(efct_type, Integer.MAX_VALUE, effect_strength));					
				}
			}
		}
	}
	
	
	public void spawn(Location loc)
	{
		if (rand.nextFloat() < chance)
    	{
			LivingEntity mypig = (LivingEntity) loc.getWorld().spawnEntity(loc, base);
						
			for (PotionEffect efct : effects)
			{
				mypig.addPotionEffect(efct);
			}
			
			
			// equipment
			EntityEquipment equip = mypig.getEquipment();
			
			if (heldItem.valid)
			{
				equip.setItemInMainHand(heldItem.item);
				equip.setItemInMainHandDropChance(heldItem.dropChance);
			}
			
			if (heldItem_ofh.valid)
			{
				equip.setItemInOffHand(heldItem_ofh.item);
				equip.setItemInOffHandDropChance(heldItem_ofh.dropChance);
			}
	
			if (helm.valid)
			{
				equip.setHelmet(helm.item);
				equip.setHelmetDropChance(helm.dropChance);
			}
			
			if (chest.valid)
			{
				equip.setChestplate(chest.item);
				equip.setChestplateDropChance(chest.dropChance);
			}
			
			if (leg.valid)
			{
				equip.setLeggings(leg.item);
				equip.setLeggingsDropChance(leg.dropChance);
			}
			
			if (boot.valid)
			{
				equip.setBoots(boot.item);
				equip.setBootsDropChance(boot.dropChance);
			}
    	}
	}
}
