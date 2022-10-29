package fr.ayato.hammer.hammer;

import fr.ayato.hammer.utils.Config;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Hammer {

    public static ItemStack data() {
        ItemStack itemStack = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Config.getItemName());
        meta.setLore(Config.getItemLore());
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
