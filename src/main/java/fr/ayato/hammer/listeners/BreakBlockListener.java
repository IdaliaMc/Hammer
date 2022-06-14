package fr.ayato.hammer.listeners;

import com.massivecraft.factions.FPlayers;
import fr.ayato.hammer.utils.Config;
import org.bukkit.Material;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BreakBlockListener implements Listener {
    Map<UUID, BlockFace> lastBlockFace = new HashMap<>();


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) this.lastBlockFace.put(
                e.getPlayer().getUniqueId(),
                e.getBlockFace()
        );
    }

    private void processBlock(ItemStack i, Block b, Player p) {
        if (b.isLiquid()) return;
        if (b.getType() == Material.BEDROCK ||
                b.getType() == Material.BARRIER ||
                b.getType() == Material.MOB_SPAWNER ||
                b.getType() == Material.ENDER_CHEST ||
                b.getType() == Material.ENDER_PORTAL ||
                b.getType() == Material.ENDER_PORTAL_FRAME ||
                b.getType() == Material.PORTAL
        ) return;
        b.breakNaturally(i);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void blockBreackEvent(BlockBreakEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getItemInHand();
        if (item != null && (item.getType() == Material.DIAMOND_PICKAXE || !FPlayers.getInstance().getByPlayer(player).isInEnemyTerritory()) && item.hasItemMeta() && item.getItemMeta().hasLore() && item.getItemMeta().getLore().equals(Config.getItemLore())) {
            Player p = e.getPlayer();
            ItemStack i = e.getPlayer().getItemInHand();
            if (this.lastBlockFace.get(p.getUniqueId()) == null || this.lastBlockFace.get(p.getUniqueId()) == BlockFace.DOWN || this.lastBlockFace.get(p.getUniqueId()) == BlockFace.UP) {
                processBlock(i, e.getBlock().getRelative(BlockFace.NORTH), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.NORTH_EAST), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.EAST), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.SOUTH_EAST), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.SOUTH), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.SOUTH_WEST), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.WEST), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.NORTH_WEST), p);
            }
            if (this.lastBlockFace.get(p.getUniqueId()) == BlockFace.EAST || this.lastBlockFace.get(p.getUniqueId()) == BlockFace.WEST) {
                processBlock(i, e.getBlock().getRelative(BlockFace.UP), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.DOWN), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.NORTH), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.SOUTH), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.UP), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.DOWN), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.UP), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.DOWN), p);
            }
            if (this.lastBlockFace.get(p.getUniqueId()) == BlockFace.NORTH || this.lastBlockFace.get(p.getUniqueId()) == BlockFace.SOUTH) {
                processBlock(i, e.getBlock().getRelative(BlockFace.UP), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.DOWN), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.EAST), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.WEST), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.UP), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.DOWN), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.UP), p);
                processBlock(i, e.getBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.DOWN), p
                );
            }
        }
    }
}
