package ThunderCore.Commands.StaffCommands.Worlds;

import ThunderCore.ThunderCore;
import ThunderCore.Utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DeleteWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            if (args == null) {
                sender.sendMessage(ChatColor.RED + "Please provide a world to delete!");
                return false;
            }
            String name = args[0];
            if (Bukkit.getWorld(name) == null) {
                sender.sendMessage(ChatColor.RED + "That is not a world!");
                return false;
            }
            Bukkit.unloadWorld(name, false);
            if (Objects.requireNonNull(Bukkit.getWorld(name)).getWorldFolder().delete()) {
                sender.sendMessage(ChatColor.RED + "The world " + name + " was deleted!");
            }
            return false;
        }


        if (!(player.isOp() || ThunderCore.get().isAdmin(player))) {
            player.sendMessage(Messages.NOPERMS);
            return false;
        }

        if (args == null) {
            player.sendMessage(ChatColor.RED + "Please provide a world to delete!");
            return false;
        }

        String name = args[0];
        if (Bukkit.getWorld(name) == null) {
            player.sendMessage(ChatColor.RED + "That is not a world!");
            return true;
        }

        Bukkit.unloadWorld(name, false);
        if (Objects.requireNonNull(Bukkit.getWorld(name)).getWorldFolder().delete()) {
            sender.sendMessage(ChatColor.RED + "The world " + name + " was deleted!");
        }

        return true;
    }
}
