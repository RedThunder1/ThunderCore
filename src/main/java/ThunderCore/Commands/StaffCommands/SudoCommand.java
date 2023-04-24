package ThunderCore.Commands.StaffCommands;

import ThunderCore.ThunderCore;
import ThunderCore.Utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import java.util.Arrays;

public class SudoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.CONSOLECANTUSE);
            return true;
        }

        if (!(ThunderCore.get().isAdmin(player))) {
            player.sendMessage(Messages.NOPERMS);
            return true;
        }

        if (args[0].isBlank()) {
            player.sendMessage(ChatColor.RED + "You must provide a player and message!");
            return false;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            player.sendMessage(Messages.NOTAPLAYER);
            return true;
        }
        Player p = Bukkit.getPlayer(args[0]);
        assert p != null;
        int pLenght = p.getName().length();

        if (args[1].isBlank()) {
            player.sendMessage(ChatColor.RED + "You must provide a message!");
            return false;
        }
        int length = args.length;
        String msg = Arrays.toString(args).substring(pLenght, length);
        char command = args[1].charAt(0);

        if (command == ('/')) {
            p.performCommand(msg);
            return true;
        }
        for (Player members : Bukkit.getOnlinePlayers()) {
            members.sendMessage("<" + p.getName() + "> " + msg);
        }
        return false;
    }
}

