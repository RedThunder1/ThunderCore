package ThunderCore.Utilities;

import ThunderCore.ThunderCore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.ChatColor;

public class AnnouncementMessages implements Runnable{

    public static final TextComponent GAMES = Component.text(ChatColor.GOLD + "The current planned game is Skywars.  Once finished, other games will be developed.");
    public static final TextComponent RULES = Component.text(ChatColor.RED + "Please make sure to read our rules in the discord server!");
    public static final TextComponent WELCOME = Component.text(ChatColor.GREEN + "Welcome to ThunderMC! Here we will have many mini games like Smp, Duels, and Kitpvp.");
    public static final TextComponent DISCORD = Component.text(ChatColor.BLUE + "You can Join our discord at https://discord.gg/syVRwcn or by clicking here!")
            .clickEvent(ClickEvent.openUrl("https://discord.gg/syVRwcn"))
            .hoverEvent(HoverEvent.showText(Component.text("Join our discord!")))
            .toBuilder().build();
    private final ThunderCore plugin;
    public AnnouncementMessages(ThunderCore plugin) {this.plugin = plugin;}

    public int messageNumber = 0;

    @Override
    public void run() {
        messageNumber++;
        switch (messageNumber) {
            case 1 -> plugin.getServer().broadcast(DISCORD);
            case 2 -> plugin.getServer().broadcast(RULES);
            case 3 -> plugin.getServer().broadcast(GAMES);
            case 4 -> {
                plugin.getServer().broadcast(WELCOME);
                messageNumber = 0;
            }
            default -> messageNumber = 0;
        }
    }

}
