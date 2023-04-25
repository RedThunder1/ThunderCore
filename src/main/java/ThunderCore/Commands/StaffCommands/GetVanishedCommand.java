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
import java.util.Arrays;

public class GetVanishedCommand implements CommandExecutor {

    private ArrayList<Player> vanished = VanishCommand.getVanished();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.CONSOLECANTUSE);
            return false;
        }

        if (!(ThunderCore.get().isModerator(player))) {
            player.sendMessage(Messages.NOPERMS);
            return true;
        }

        if (vanished.isEmpty()) {
            player.sendMessage(ChatColor.RED + "There are no vanished players!");
            return true;
        }

        StringBuilder vanishedPlayers = new StringBuilder();
        for (Player vPlayer : vanished) {
            vanishedPlayers.append(vPlayer.getName()).append(", ");
        }
        player.sendMessage(ChatColor.GREEN + vanishedPlayers.toString() + "are vanished!");
        return true;
    }
}
