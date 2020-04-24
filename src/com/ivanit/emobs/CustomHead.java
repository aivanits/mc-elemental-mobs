package com.ivanit.emobs;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class CustomHead
{
	@SuppressWarnings("deprecation")
	public static ItemStack getHead(ItemStack item, String texture)
	{
		UUID hashAsId = new UUID(texture.hashCode(), texture.hashCode());
		return Bukkit.getUnsafe().modifyItemStack(item,
				"{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + texture + "\"}]}}}"
		);
	}
}
