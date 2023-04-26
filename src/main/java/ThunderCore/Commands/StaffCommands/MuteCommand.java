package ThunderCore.Commands.StaffCommands;

import ThunderCore.Managers.RankManager.FakePlayer;
import ThunderCore.Managers.RankManager.RankManager;
import ThunderCore.ThunderCore;
import ThunderCore.Utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MuteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.CONSOLECANTUSE);
            return false;
        }

        if (!ThunderCore.get().isModerator(player)) {
            player.sendMessage(Messages.NOPERMS);
            return true;
        }

        if (args[0].isEmpty()) {
            player.sendMessage(ChatColor.RED + "You must provide a player to mute!");
            return false;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            player.sendMessage(Messages.NOTAPLAYER);
            return false;
        }
        Player muted = Bukkit.getPlayer(args[0]);
        FakePlayer mutedFakePlayer = RankManager.get().getFakePlayer(muted);
        assert muted != null;
        if (RankManager.get().getPlayerRank(player).getPermLevel() <= RankManager.get().getPlayerRank(muted).getPermLevel()) {
            player.sendMessage(ChatColor.RED + "You cannot mute that player!");
            return true;
        }

        if (label.equals("mute")) {
            if (mutedFakePlayer.isMuted()) {
                player.sendMessage(ChatColor.RED + muted.getName() + " is already muted");
                return true;
            }
            player.sendMessage(ChatColor.RED + muted.getName() + " has been MUTED!");
            mutedFakePlayer.setMuted(true);
        } else {
            if (!mutedFakePlayer.isMuted()) {
                player.sendMessage(ChatColor.RED + muted.getName() + " isn't muted");
                return true;
            }
            player.sendMessage(ChatColor.GREEN + muted.getName() + " has been UNMUTED!");
            mutedFakePlayer.setMuted(false);
        }
        return true;
    }
}
