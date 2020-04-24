package com.ivanit.emobs;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemEquip {
	ItemStack item = null;
	double dropChance = 0.2;
	
	public ItemEquip(ItemStack in_item, double in_chance)
	{
		item = in_item;
		dropChance = in_chance;
	}
	
	public ItemEquip(Material in_mat, double in_chance)
	{
		item = new ItemStack(in_mat, 1);
		dropChance = in_chance;
	}
}
