package fr.ayato.hammer.utils;

import fr.ayato.hammer.Main;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Config {

    public static Main getInstance() {
        return JavaPlugin.getPlugin(Main.class);
    }

    public static String getItemName() {
        return getInstance().getConfig().getString("name");
    }

    public static List<String> getItemLore() {
        return getInstance().getConfig().getStringList("lore");
    }

    public static boolean getUnbreakable() { return getInstance().getConfig().getBoolean("unbreakable"); }
}
