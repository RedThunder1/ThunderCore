package ThunderCore.Commands.ReportCommand;

import ThunderCore.Managers.ReportManager.ReportManager;
import ThunderCore.Utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.UUID;

public class ReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.CONSOLECANTUSE);
            return false;
        }

        if (args[0].isEmpty()) {
            player.sendMessage(ChatColor.RED + "You must provide a player to report and a reason!");
            return false;
        }

        Player toReport = Bukkit.getPlayer(args[0]);
        if (toReport == null) {
            player.sendMessage(Messages.NOTAPLAYER);
            return false;
        }

        if (args[1].isEmpty()) {
            player.sendMessage(ChatColor.RED + "You must provide a report reason!");
            return false;
        }

        StringBuilder reason = new StringBuilder();
        for (String str : Arrays.copyOfRange(args, 1, args.length)) {
            reason.append(str).append(" ");
        }

        ReportManager.get().createReport(player, toReport, reason.toString(), UUID.randomUUID());
        player.sendMessage(ChatColor.GREEN + "You have successfully reported " + ChatColor.RED + toReport.getName());


        return true;
    }
}
