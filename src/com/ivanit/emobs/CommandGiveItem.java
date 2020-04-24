package com.ivanit.emobs;

import com.ivanit.emobs.ConfigParser;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandGiveItem implements CommandExecutor
{
	ConfigParser config;
	
	void Logger(String L)
	{
		Bukkit.getLogger().info("[emobs][CommandGiveItem] " + L);
	}
	
	public CommandGiveItem(ConfigParser in_items)
	{
		config = in_items;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (args.length < 1)
            {
            	player.sendMessage("not enough arguments, please specify an item");
            	return false;
            }
            
            String itemName = args[0];
            if (config.itemList.contains(itemName))
            {
            	ItemStack item = config.item_configs.get(itemName).item;
            	player.getInventory().addItem(item);
            	player.sendMessage("[emobs] gave item: " + itemName);
    	        return true;
            }
            else
            {
            	player.sendMessage("invalid item name");
            	return false;
            }
        }
        else
        {
        	sender.sendMessage("needs to be run by a player");
        	return false;
        }
    }
}


