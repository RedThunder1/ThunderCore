package ThunderCore.Commands.StaffCommands.Worlds;

import ThunderCore.ThunderCore;
import ThunderCore.Utilities.Messages;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CreateWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            createWorld(args, sender);
            return true;
        }

        if (!(player.isOp() || ThunderCore.get().isAdmin(player))) {
            player.sendMessage(Messages.NOPERMS);
            return true;
        }

        if (args == null) {
            player.sendMessage(ChatColor.RED + "Please provide a name and a world type like Normal, Flat, Void");
            return false;
        }

        if (args[1] == null) {
            player.sendMessage(ChatColor.RED + "Please provide a world type like Normal, Flat, Void");
            return false;
        }

        createWorld(args, sender);
        return false;
    }

    public void createWorld(String[] args, CommandSender sender) {
        String name = args[0];
        String type = args[1].toLowerCase();
        WorldCreator wc = new WorldCreator(name);

        switch (type) {
            case "normal" -> {
                wc.environment(World.Environment.NORMAL);
                wc.type(WorldType.NORMAL);
                wc.createWorld();
                sender.sendMessage(ChatColor.GREEN + "You have created a new normal world named " + name + "!");
            }
            case "flat" -> {
                wc.environment(World.Environment.NORMAL);
                wc.type(WorldType.FLAT);
                wc.createWorld();
                sender.sendMessage(ChatColor.GREEN + "You have created a new flat world named " + name + "!");
            }
            case "void" -> {
                wc.generator(new VoidWorldGenerator());
                wc.createWorld();
                sender.sendMessage(ChatColor.GREEN + "You have created a new empty world named " + name + "!");
            }
            default -> sender.sendMessage(ChatColor.RED + "World types can only be normal, flat, or void!");
        }
    }
}
