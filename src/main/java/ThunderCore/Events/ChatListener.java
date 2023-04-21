package ThunderCore.Events;

import ThunderCore.Managers.RankManager.FakePlayer;
import ThunderCore.Managers.RankManager.RankManager;
import ThunderCore.ThunderCore;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {
    private static boolean chatMuted = false;
    @EventHandler
    public void onMessage(AsyncChatEvent event) {
        Player player = event.getPlayer();
        String message = event.originalMessage().toString();
        FakePlayer fakePlayer = RankManager.get().getFakePlayer(player);

        if (chatMuted) {
            if (!ThunderCore.get().isModerator(player)) {
                player.sendMessage(Component.text(ChatColor.RED + "The chat is currently muted!"));
                event.setCancelled(true);
                return;
            }
        }

        if (fakePlayer.isMuted()) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You are muted! You cannot send messages at this time!");
            return;
        }

        if (message.charAt(0) == '#' && ThunderCore.get().isStaff(player)) {
            String msg1 = message.substring(1);
            event.setCancelled(true);
            for (Player staff : Bukkit.getOnlinePlayers()) {
                if (ThunderCore.get().isStaff(staff)) {
                    staff.sendMessage(ChatColor.RED + "[STAFF CHAT] " + ChatColor.RESET + RankManager.get().getPlayerRank(player).getPrefix() + player.getName() + ChatColor.GOLD + msg1);
                    return;
                }
            }
        }

        event.message(Component.text(RankManager.get().getPlayerRank(player).getPrefix() + player.getName() + ": " + ChatColor.RESET + message));

    }

    public static Boolean getChatMuted() { return chatMuted; }
    public static void setChatMuted(boolean mute) { chatMuted = mute; }

}
