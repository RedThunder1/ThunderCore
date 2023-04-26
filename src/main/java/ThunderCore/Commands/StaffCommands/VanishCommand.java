package ThunderCore.Commands.StaffCommands;

import ThunderCore.ThunderCore;
import ThunderCore.Utilities.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {

    private static ArrayList<Player> vanished = new ArrayList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.CONSOLECANTUSE);
            return false;
        }

        if (!ThunderCore.get().isStaff(player)) {
            player.sendMessage(Messages.NOPERMS);
            return true;
        }

        if (vanished.contains(player)) {
            vanished.remove(player);
            player.showPlayer(ThunderCore.get(), player);
            player.sendMessage(ChatColor.GREEN + "You have unvanished!");
            return true;
        }
        vanished.add(player);
        player.hidePlayer(ThunderCore.get(), player);
        player.sendMessage(ChatColor.GREEN + "You have vanished!");
        return true;
    }

    public static ArrayList<Player> getVanished() { return vanished; }
}
