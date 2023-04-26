package ThunderCore.Utilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;

public class Messages {

    public static final TextComponent DISCORD = Component.text(ChatColor.BLUE + "You can Join our discord at https://discord.gg/syVRwcn or by clicking here!")
            .clickEvent(ClickEvent.openUrl("https://discord.gg/syVRwcn"))
            .hoverEvent(HoverEvent.showText(Component.text("Join our discord!")))
            .toBuilder().build();

    public static final TextComponent NOPERMS = Component.text(ChatColor.RED + "You do not have permissions to use this command!");
    public static final TextComponent CONSOLECANTUSE = Component.text(ChatColor.RED + "Console cant use this command!");
    public static final TextComponent NOTAPLAYER = Component.text(ChatColor.RED + "That is not a Player!");
    public static final TextComponent ERROR = Component.text(ChatColor.RED + "There was an error with this command!");


    public static final TextComponent GAMES = Component.text(ChatColor.GOLD + "The games we currently have is the smp.  Other games are in the works!");
    public static final TextComponent RULES = Component.text(ChatColor.RED + "Please make sure to read our rules in the discord server!");
    public static final TextComponent WELCOME = Component.text(ChatColor.GREEN + "Welcome to ThunderMC! Here we will have many mini games like Smp, Duels, and Kitpvp.");
}
