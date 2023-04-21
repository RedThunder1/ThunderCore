package ThunderCore.Events;

import ThunderCore.Commands.StaffCommands.BuildCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.quitMessage(Component.text(ChatColor.RED + player.getName() + " has left the server!"));


        BuildCommand.getBuilders().remove(event.getPlayer());
    }

}
