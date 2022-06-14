package fr.ayato.hammer.commands;

import fr.ayato.hammer.hammer.Hammer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HammerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            player.getInventory().addItem(Hammer.data());
            player.updateInventory();
        } else {
            sender.sendMessage("§cLa commande est /hammer <pseudo>");
        }
        return false;
    }
}
