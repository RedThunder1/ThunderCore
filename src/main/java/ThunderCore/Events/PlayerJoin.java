package ThunderCore.Events;

import ThunderCore.Managers.RankManager.RankManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    public World lobbyWorld = Bukkit.getWorld("lobby");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPlayedBefore()) {
            event.joinMessage(Component.text(ChatColor.AQUA + player.getName() + " has joined the server!"));

        } else {
            event.joinMessage(Component.text(ChatColor.AQUA + player.getName() + " has joined the server for the first time!"));
        }

        Location location = new Location(lobbyWorld, 0.5, 72, 0.5);
        player.teleport(location);
        player.setHealth(20);
        player.setSaturation(20);

        if (RankManager.get().getFakePlayer(player) == null) {
            RankManager.get().createFakePlayer(event.getPlayer(), "member", null);
        }
        player.displayName(Component.text(RankManager.get().getFakePlayer(player).getPlayerRank().getPrefix()));
        player.playerListName(Component.text(RankManager.get().getFakePlayer(player).getPlayerRank().getPrefix() + player.getName()));
    }
}
