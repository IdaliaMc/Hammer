package fr.ayato.hammer.listeners;


import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.listeners.FactionsBlockListener;
import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.ayato.hammer.utils.Config;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BreakBlockListener implements Listener {

    public boolean isBannedBlock(Block block) {
        List<Material> isBannedBlock = new ArrayList<>();
        isBannedBlock.add(Material.BEDROCK);
        isBannedBlock.add(Material.BARRIER);
        isBannedBlock.add(Material.MOB_SPAWNER);
        isBannedBlock.add(Material.ENDER_CHEST);
        isBannedBlock.add(Material.ENDER_PORTAL);
        isBannedBlock.add(Material.ENDER_PORTAL_FRAME);
        isBannedBlock.add(Material.PORTAL);
        return isBannedBlock.contains(block.getType());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void blockBreackEvent(BlockBreakEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getItemInHand();
        Player p = e.getPlayer();
        if (item != null && item.getType() == Material.DIAMOND_PICKAXE && item.hasItemMeta() && item.getItemMeta().hasLore() && item.getItemMeta().getLore().equals(Config.getItemLore())) {
            Faction faction = FPlayers.getInstance().getByPlayer(p).getFaction();
            Block b = e.getBlock();
            World w = b.getWorld();
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    for (int z = -1; z < 2; z++) {
                        Block block = w.getBlockAt(b.getLocation().getBlockX() + x, b.getLocation().getBlockY() + y, b.getLocation().getBlockZ() + z);
                        try {
                            if (!isBannedBlock(block)) {
                                if (FactionsBlockListener.playerCanBuildDestroyBlock(p, block.getLocation(), "break", true)) {
                                    block.breakNaturally();
                                }
                            } else {
                                e.setCancelled(true);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        }
    }
}
