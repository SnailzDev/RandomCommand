
package net.snailz.randomcommand;

import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements CommandExecutor{
    
    @Override
    public void onEnable(){
        this.saveDefaultConfig();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (command.getName().equalsIgnoreCase("RC")){
            List<String> list = this.getConfig().getStringList(args[0]);
            if (this.getConfig().getBoolean("use%") == false){
                    Random random = new Random();
                    String cmd = list.get(random.nextInt(list.size()));
                    cmd = cmd.replace("{name}", args[1]);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    return true;
            }
            if (this.getConfig().getBoolean("use%") == true){
            int randomint = (int) (Math.random() * 100);
            for (String cmd : list){
                cmd = cmd.replace("{name}", args[1]);
                String[] cmd_list = cmd.split(":");
                int percent = Integer.parseInt(cmd_list[0]);
                if (percent > randomint){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd_list[1]);
                    return true;
                }
            }
            }
            
        }
        return false;
    }
    
}


