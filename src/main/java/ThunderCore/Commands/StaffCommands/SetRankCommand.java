package ThunderCore.Commands.StaffCommands;

import ThunderCore.Managers.RankManager.FakePlayer;
import ThunderCore.Managers.RankManager.RankManager;
import ThunderCore.Managers.RankManager.Ranks;
import ThunderCore.ThunderCore;
import ThunderCore.Utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetRankCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            for(Ranks playerRank : RankManager.get().getPlayerRanks()) {
                if(args[1].equalsIgnoreCase(playerRank.getName())) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Messages.NOTAPLAYER);
                        return false;
                    }
                    FakePlayer fakePlayer = RankManager.get().getFakePlayer(Bukkit.getPlayer(args[0]));
                    fakePlayer.setRank(playerRank);
                    return true;
                }
            }
            sender.sendMessage(ChatColor.RED + "That is not an available rank!");
            return false;
        }

        if (!ThunderCore.get().isOwner(player)) {
            player.sendMessage(Messages.NOPERMS);
            return true;
        }

        if (args == null) {
            player.sendMessage(ChatColor.RED + "You must provide a player and a rank!");
            return false;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            player.sendMessage(Messages.NOTAPLAYER);
            return false;
        }

        Player rankedPlayer = Bukkit.getPlayer(args[0]);

        if (args[1].isEmpty()) {
            player.sendMessage(ChatColor.RED + "You must provide a rank!");
            return false;
        }

        for(Ranks playerRank : RankManager.get().getPlayerRanks()) {
            if(args[1].equalsIgnoreCase(playerRank.getName())) {
                FakePlayer fakePlayer = RankManager.get().getFakePlayer(rankedPlayer);
                fakePlayer.setRank(playerRank);
                return true;
            }
        }
        player.sendMessage(ChatColor.RED + "That is not an available rank!");
        return false;
    }
}
