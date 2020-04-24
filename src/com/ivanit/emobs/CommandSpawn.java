package com.ivanit.emobs;

//import com.ivanit.emobs.ConfigParser;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
//import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;

public class CommandSpawn implements CommandExecutor
{
	static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	ConfigParser mymobs;
	
	void Logger(String L)
	{
		Bukkit.getLogger().info("[emobs][CommandSpawn] " + L);
	}
	
	public CommandSpawn(ConfigParser in_mobs)
	{
		mymobs = in_mobs;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (args.length < 1)
            {
            	player.sendMessage("not enough arguments, please specify a mob");
            	return false;
            }
            
            String mobName = args[0];
            if (mymobs.mobList.contains(mobName))
            {
            	CustomMob mob = mymobs.mob_configs.get(mobName);
            	Location loc = player.getLocation();
            	mob.spawn(loc);
            	player.sendMessage("[emobs] spawned: " + mobName);
    	        return true;
            }
            else
            {
            	player.sendMessage("invalid mob name");
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
