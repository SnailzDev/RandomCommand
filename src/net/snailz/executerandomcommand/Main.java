
package net.snailz.executerandomcommand;

import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements CommandExecutor{
    
    public void onEnable(){
        this.saveDefaultConfig();
    }
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (command.getName().equalsIgnoreCase("RC")){
            List<String> list = this.getConfig().getStringList(args[0]);
            Random random = new Random();
            String cmd = list.get(random.nextInt(list.size()));
            cmd = cmd.replace("{name}", args[1]);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
            return true;
            
        }
        return false;
    }
    
}
