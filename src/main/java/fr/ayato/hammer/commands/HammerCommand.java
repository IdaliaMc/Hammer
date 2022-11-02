package fr.ayato.hammer.commands;

import fr.ayato.hammer.hammer.Hammer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class HammerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //check if the player is op
        if (args.length == 1 && ( sender.isOp() || sender.hasPermission("hammer.give") || sender instanceof ConsoleCommandSender)) {
            Player player2 = Bukkit.getPlayer(args[0]);
            player2.getInventory().addItem(Hammer.data());
            player2.updateInventory();
        } else {
            sender.sendMessage("§cLa commande est /hammer <pseudo>");
        }
        return false;
    }
}
