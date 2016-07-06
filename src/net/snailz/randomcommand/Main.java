
package net.snailz.randomcommand;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor{
/*
public static boolean isInteger(String str) {
    if (str == null) {
        return false;
    }
    int length = str.length();
    if (length == 0) {
        return false;
    }
    int i = 0;
    if (str.charAt(0) == '-') {
        if (length == 1) {
            return false;
        }
        i = 1;
    }
    for (; i < length; i++) {
        char c = str.charAt(i);
        if (c < '0' || c > '9') {
            return false;
        }
    }
    return true;
}
*/
 
    @Override
    public void onEnable(){
        int configver = 1;
        this.saveDefaultConfig();
        int CVU = this.getConfig().getInt("configver");
        if (configver != CVU){
            getLogger().severe("NEW CONFIG NEEDED. COPY ALL COMMAND LISTS, DELETE CONFIG, AND RESTART SERVER!");
            Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("RandomCommand");
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (command.getName().equalsIgnoreCase("rc")){
            if (args.length == 0){
                sender.sendMessage(ChatColor.GREEN + "RandomCommand Help");
                sender.sendMessage(ChatColor.GREEN + "/rc <list> <playername>");
                sender.sendMessage(ChatColor.GREEN + "Use: execute a random command from a list that you put as the 1st argument with {name} replaced with the player name that you put as the 2nd argument");
                return true;
            }
            List<String> list = this.getConfig().getStringList(args[0]);
            if (this.getConfig().getBoolean("use%") == false){
                    Random random = new Random();
                    String cmd = list.get(random.nextInt(list.size()));
                    String CSC = this.getConfig().getString("commandsplitcharacter");
                    if (cmd.toLowerCase().contains(CSC)){
                        String[] cmds = cmd.split(CSC);
                        int cmdslength = cmds.length;
                        for (int x = 0;x < cmdslength; x++){
                            String currentcmd = cmds[x];
                            if (currentcmd.contains("{name}") && args.length <= 1){
                                sender.sendMessage(ChatColor.YELLOW + "You must add a player name to the command for this list!");
                                sender.sendMessage(ChatColor.YELLOW + "Format: /rc <listname> [playername]");
                            }
                        }
                        for (int x = 0;x < cmdslength; x++){
                            if (cmds[x].contains("{name}")){
                                cmds[x].replace("{name}", args[1]);
                            }
                        }
                        for (int x = 0;x < cmdslength; x++){
                            if (args.length == 3){
                                int wait = Integer.parseInt(args[2]);
                                if (wait > 0){
                                    try{
                                        TimeUnit.SECONDS.sleep(wait);
                                    } catch(InterruptedException ex){
                                        Thread.currentThread().interrupt();
                                    }
                                }
                            }
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmds[x]);
                        }
                        return true;
                    }
                    else{
                    if (cmd.contains("{name}")){
                            if (args.length == 1){
                                sender.sendMessage(ChatColor.YELLOW + "You must add a player name to the command for this list!");
                                sender.sendMessage(ChatColor.YELLOW + "Format: /rc <listname> [playername]");
                                return true;
                            }
                            cmd = cmd.replace("{name}", args[1]);
                        }
                    if (args.length == 3){
                        int wait = Integer.parseInt(args[2]);
                            if (wait > 0){
                                try{
                                    TimeUnit.SECONDS.sleep(wait);
                                } catch(InterruptedException ex){
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    return true;
                    }
            }
            if (this.getConfig().getBoolean("use%") == true){
            int randomint = (int) (Math.random() * 100);
            for (String cmd : list){
                String[] cmd_list = cmd.split(":");
                int percent = Integer.parseInt(cmd_list[0]);
                if (percent > randomint){
                    String CSC = this.getConfig().getString("commandsplitcharacter");
                    if (cmd.toLowerCase().contains(CSC)){
                        String[] cmds = cmd_list[1].split(CSC);
                        int cmdslength = cmds.length;
                        for (int x = 0;x < cmdslength; x++){
                            String currentcmd = cmds[x];
                            if (currentcmd.contains("{name}") && args.length <= 1){
                                sender.sendMessage(ChatColor.YELLOW + "You must add a player name to the command for this list!");
                                sender.sendMessage(ChatColor.YELLOW + "Format: /rc <listname> [playername]");
                            }
                        }
                        for (int x = 0;x < cmdslength; x++){
                            if (cmds[x].contains("{name}")){
                                cmds[x].replace("{name}", args[1]);
                            }
                        }
                        for (int x = 0;x < cmdslength; x++){
                            if (args.length == 3){
                                int wait = Integer.parseInt(args[2]);
                                if (wait > 0){
                                    try{
                                        TimeUnit.SECONDS.sleep(wait);
                                    } catch(InterruptedException ex){
                                        Thread.currentThread().interrupt();
                                    }
                                }
                            }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmds[x]);
                        }
                        return true;
                    }
                    else{
                        if (cmd_list[1].contains("{name}")){
                            if (args.length == 1){
                                sender.sendMessage(ChatColor.YELLOW + "You must add a player name to the command for this list!");
                                sender.sendMessage(ChatColor.YELLOW + "Format: /rc <listname> [playername]");
                                return true;
                            }
                            cmd_list[1] = cmd_list[1].replace("{name}", args[1]);
                        }
                        if (args.length == 3){
                        int wait = Integer.parseInt(args[2]);
                            if (wait > 0){
                                try{
                                    TimeUnit.SECONDS.sleep(wait);
                                } catch(InterruptedException ex){
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd_list[1]);
                        return true;
                    }
                }
            }
            }
            
        }
        if (command.getName().equalsIgnoreCase("rcreload")){
            this.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Config Reloaded");
            return true;
        }
/*
        if (command.getName().equalsIgnoreCase("rcongig")){
            if (args[0].equalsIgnoreCase("add")){
                String[] commands = Arrays.copyOfRange(args, 2, args.length-1);
                for (int x = 0; x == commands.length-1; x++){
                     commands[x] = commands[x] + " ";
                }
                String commands2 = commands.toString();
                sender.sendMessage(commands2);
                if (args[1] != "none"){
                    if (isInteger(args[1]) == true){
                        commands2 = args[1] + ":" + commands2;
                    }
                    else{
                        sender.sendMessage(ChatColor.YELLOW + "You must add a percentage or none as your second arguement!");
                    }
                }
                this.getConfig().set(args[0], commands2);
                this.saveConfig();
                return true;
                
            }
            if (args[0].equalsIgnoreCase("remove")){
                
            }
        }
*/
        return false;
    }
    
}


