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

public class CommandHandler implements CommandExecutor
{
	static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	ConfigParser myspawner;
	
	void Logger(String L)
	{
		Bukkit.getLogger().info("[emobs][CommandHandler] " + L);
	}
	
	public CommandHandler(ConfigParser in_mobs)
	{
		myspawner = in_mobs;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            // Here we need to give items to our player
        
	        ItemStack diamond = new ItemStack(Material.DIAMOND);
	        diamond.setAmount(1);
	        ItemMeta itemMeta = diamond.getItemMeta();
	        itemMeta.setDisplayName("TEST DIAMOND");
	        diamond.setItemMeta(itemMeta);
	        player.getInventory().addItem(diamond);
	        
	        
	        myspawner.spawn_testPig(player.getLocation());
	        
	        Bukkit.dispatchCommand(console, "say TEST COMMAND RUN");
	        
	        // If the player (or console) uses our command correct, we can return true
	        return true;
        }
        else
        {
        	return false;
        }
    }
}
