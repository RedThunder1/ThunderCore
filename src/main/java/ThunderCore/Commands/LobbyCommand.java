package ThunderCore.Commands;

import ThunderCore.ThunderCore;
import ThunderCore.Utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LobbyCommand implements CommandExecutor{
    public World lobby;
    public Location spawn;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            ThunderCore.get().console.sendMessage(Messages.CONSOLECANTUSE);
            return false;
        }
        lobby = Bukkit.getWorld("lobby");
        spawn  = new Location(lobby, 0.5, 72, 0.5);
        player.teleport(spawn);
        return true;
    }
}
