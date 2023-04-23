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
import java.util.List;

public class BuildCommand implements CommandExecutor {

    private static ArrayList<Player> build = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.CONSOLECANTUSE);
            return true;
        }

        if (!(ThunderCore.get().isBuilder(player) || ThunderCore.get().isAdmin(player))) {
            player.sendMessage(Messages.NOPERMS);
            return true;
        }

        if (build.contains(player)) {
            build.remove(player);
            player.sendMessage(ChatColor.RED + "Build mode Disabled");
            return true;
        }

        build.add(player);
        player.sendMessage(ChatColor.RED + "Build mode Enabled");
        return true;
    }

    public static List<Player> getBuilders() { return build; }
}
