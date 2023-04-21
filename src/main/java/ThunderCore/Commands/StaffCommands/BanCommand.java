package ThunderCore.Commands.StaffCommands;

import ThunderCore.ThunderCore;
import ThunderCore.Utilities.Messages;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Date;

public class BanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.CONSOLECANTUSE);
            return true;
        }

        if (!ThunderCore.get().isModerator(player)) {
            player.sendMessage(Messages.NOPERMS);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "You must provide a player to ban, ban time, and a ban reason!");
            return false;
        }
        if (args[1].isEmpty()) {
            player.sendMessage(ChatColor.RED + "You must provide a ban time!");
            return false;
        }

        if (args[2].isEmpty()) {
            player.sendMessage(ChatColor.RED + "You must provide a ban reason!");
            return false;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            player.sendMessage(Messages.NOTAPLAYER);
            return false;
        }

        Player toBan = Bukkit.getPlayer(args[0]);
        assert toBan != null;
        String time = args[1];
        StringBuilder reason = new StringBuilder();
        for (String str : Arrays.copyOfRange(args, 2, args.length)) {
            reason.append(str).append(" ");
        }
        int day = 0;
        int hour = 0;
        int minute = 0;
        int end = 0;
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) == 'd') {
                if (i == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect arguments! no amount of days provided!");
                    return false;
                }

                try {
                    String check = time.substring(end,i-1);
                    day = Integer.parseInt(check);
                    end = i;
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "Incorrect arguments! Incorrect day time provided!");
                    return false;
                }

            } else if (time.charAt(i) == 'h') {
                if (i == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect arguments! no amount of hours provided!");
                    return false;
                }

                try {
                    String check = time.substring(end,i-1);
                    hour = Integer.parseInt(check);
                    end = i;
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "Incorrect arguments! Incorrect hour time provided!");
                    return false;
                }
            } else if (time.charAt(i) == 'm') {
                if (i == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect arguments! no amount of minutes provided!");
                    return false;
                }

                try {
                    String check = time.substring(end,i-1);
                    minute = Integer.parseInt(check);
                    end = i;
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "Incorrect arguments! Incorrect minute time provided!");
                    return false;
                }

            }
        }

        int utilMinute = 60 * 1000;
        int utilHour = 60 * utilMinute;
        int utilDay = 24 * utilHour;

        if (day == 0) {
            day = 1;
        } else {
            day *= utilDay;
        }

        if (hour == 0) {
            hour = 1;
        } else {
            hour *= utilHour;
        }

        if (minute == 0) {
            minute = 1;
        } else {
            minute *= utilMinute;
        }


        String bumper = StringUtils.repeat("\n", 35);

        if (label.equals("ipban")) {
            Bukkit.getBanList(BanList.Type.IP).addBan(toBan.getName(), bumper + reason.toString() + bumper, new Date(System.currentTimeMillis() + (long) day * hour * minute), null);
            toBan.kick(Component.text(reason.toString()));
        } else {
            Bukkit.getBanList(BanList.Type.NAME).addBan(toBan.getName(), bumper + reason.toString() + bumper, new Date(System.currentTimeMillis() + (long) day * hour * minute), null);
            toBan.kick(Component.text(reason.toString()));
        }



        return false;
    }
}
