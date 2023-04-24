package ThunderCore.Commands.StaffCommands.Worlds;

import ThunderCore.ThunderCore;
import ThunderCore.Utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TpWorldCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.CONSOLECANTUSE);
            return false;
        }

        if (!(player.isOp() || ThunderCore.get().isStaff(player))) {
            player.sendMessage(Messages.NOPERMS);
            return false;
        }

        if (args == null || args.length == 0) {
            player.sendMessage(ChatColor.RED + "Please provide a world to teleport to!");
            return false;
        }

        String w = args[0];
        if (Bukkit.getWorld(w) == null) {
            player.sendMessage(ChatColor.RED + "That is not an available world!");
            return false;
        }

        if (args.length > 1) {
            String p = args[1];
            if (Bukkit.getPlayer(p) != null) {
                World world = Bukkit.getWorld(w);
                Player player1 = Bukkit.getPlayer(p);
                assert player1 != null;
                assert world != null;
                int x = world.getSpawnLocation().getBlockX();
                int y = world.getSpawnLocation().getBlockY();
                int z = world.getSpawnLocation().getBlockZ();
                Location loc = new Location(world, x, y, z);
                player1.teleport(loc);

                return false;

            } else {
                player.sendMessage(ChatColor.RED + "That is not an online player!");
            }
            return false;
        }

        World world = Bukkit.getWorld(w);
        assert world != null;
        int x = world.getSpawnLocation().getBlockX();
        int y = world.getSpawnLocation().getBlockY();
        int z = world.getSpawnLocation().getBlockZ();
        Location loc = new Location(world, x, y, z);
        player.teleport(loc);

        return true;
    }
}
