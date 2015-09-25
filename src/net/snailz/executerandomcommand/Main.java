/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.snailz.executerandomcommand;

import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import static org.spigotmc.SpigotConfig.config;

/**
 *
 * @author user
 */
public class Main extends JavaPlugin implements CommandExecutor{
    
    public void onEnable(){
        this.saveDefaultConfig();
    }
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (command.getName().equalsIgnoreCase("RC")){
            if (args[0] == null){
                return false;
            }
            List<String> list = config.getStringList("commands");
            int index = new Random().nextInt(list.size());
            String cmd = list.get(index);
            cmd = cmd.replace("{name}", args[0]);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
            return true;
            
        }
        return false;
    }
    
}
