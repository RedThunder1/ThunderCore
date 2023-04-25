package ThunderCore.Commands.StaffCommands;

import ThunderCore.Events.ChatListener;
import ThunderCore.ThunderCore;
import ThunderCore.Utilities.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MuteChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.CONSOLECANTUSE);
            return false;
        }
        if (!ThunderCore.get().isAdmin(player)) {
            player.sendMessage(Messages.NOPERMS);
            return true;
        }
        if (ChatListener.getChatMuted()) {
            player.sendMessage(ChatColor.GREEN + "Chat has been unmuted!");
            ChatListener.setChatMuted(false);
            return true;
        }
        player.sendMessage(ChatColor.RED + "Chat has been muted!");
        ChatListener.setChatMuted(true);
        return true;
    }
}
