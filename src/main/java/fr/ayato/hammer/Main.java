package fr.ayato.hammer;

import fr.ayato.hammer.commands.HammerCommand;
import fr.ayato.hammer.listeners.BreakBlockListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println(ChatColor.WHITE + "--------------------");
        System.out.println(ChatColor.AQUA + "[" + ChatColor.YELLOW + "Idalia" + ChatColor.AQUA + "]" + ChatColor.GREEN + " Hammer is now enabled !");
        System.out.println(ChatColor.WHITE + "--------------------");
        saveDefaultConfig();
        getCommand("hammer").setExecutor(new HammerCommand());
        getServer().getPluginManager().registerEvents(new BreakBlockListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.WHITE + "--------------------");
        System.out.println(ChatColor.AQUA + "[" + ChatColor.YELLOW + "Idalia" + ChatColor.AQUA + "]" + ChatColor.RED + " Hammer is now disabled !");
        System.out.println(ChatColor.WHITE + "--------------------");
    }
}
