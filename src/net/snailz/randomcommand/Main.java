
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
            int listsize = list.size() - 1;
            for (int i = 0; i < listsize; i++) {
                String item = list.get(i);
                if (!item.contains("_")){
                    Random random = new Random();
                    String cmd = list.get(random.nextInt(list.size()));
                    cmd = cmd.replace("{name}", args[1]);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    break;
                }
                String[] item_string = item.split(":");
                int randomint = (int) (Math.random() * 100);
                int percent = Integer.parseInt(item_string[0]);
                if (percent < randomint){
                    String cmd = item_string[1];
                    cmd = cmd.replace("{name}", args[1]);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    break;
                }
            }
            return true;
            
        }
        return false;
    }
    
}


