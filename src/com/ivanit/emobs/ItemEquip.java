package com.ivanit.emobs;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemEquip {
	ItemStack item = new ItemStack(Material.AIR);
	float dropChance = 0.0F;
	boolean valid = false;
	
	public ItemEquip()
	{
		valid = false;
	}
	
	public ItemEquip(ItemStack in_item, float in_chance)
	{
		item = in_item;
		dropChance = in_chance;
		valid = true;
	}
	
	public ItemEquip(Material in_mat, float in_chance)
	{
		item = new ItemStack(in_mat, 1);
		dropChance = in_chance;
		valid = true;
	}
}
