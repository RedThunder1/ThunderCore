package ThunderCore.Commands;

import ThunderCore.Managers.PartyManager.PartyForm;
import ThunderCore.Managers.PartyManager.PartyInvite;
import ThunderCore.Managers.PartyManager.PartyManager;
import ThunderCore.Utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PartyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.CONSOLECANTUSE);
            return true;
        }
        PartyForm party = PartyManager.get().getPartyByLeader(player);
        switch (args[0]) {
            case "invite" -> {

                if (args[1].isEmpty()) {
                    player.sendMessage(ChatColor.RED + "You must provide a player to invite!");
                    return false;
                }
                if (Bukkit.getPlayer(args[1]) == null) {
                    player.sendMessage(ChatColor.RED + "That is not a player!");
                    return true;
                }
                Player invited = Bukkit.getPlayer(args[1]);
                assert invited != null;
                new PartyInvite().invitePlayer(player, invited);
            }
            case "kick" -> {
                if (party == null) {
                    player.sendMessage(ChatColor.RED + "You cannot kick a player as you are either not in a party or not the leader!");
                    return true;
                }
                if (args[1].isEmpty()) {
                    player.sendMessage(ChatColor.RED + "You must provide a player to Kick!");
                    return false;
                }
                if (Bukkit.getPlayer(args[1]) == null) {
                    player.sendMessage(ChatColor.RED + "That is not a player!");
                    return true;
                }
                Player kicked = Bukkit.getPlayer(args[1]);
                assert kicked != null;
                if (!party.getMembers().contains(kicked)) {
                    player.sendMessage(ChatColor.RED + "That player is not in your party!");
                    return true;
                }
                party.removeMember(kicked);
                kicked.sendMessage(ChatColor.RED + "You were kicked from the server!");
            }
            case "disband" -> {
                if (party == null) {
                    player.sendMessage(ChatColor.RED + "You cannot disband a party as you are either not in one or not the leader!");
                    return true;
                }
                for (Player p : party.getMembers()) {
                    p.sendMessage(ChatColor.RED + "The party has been disbanded");
                }
                PartyManager.get().removeParty(party);
            }
            case "leave" -> {

                return true;
            }
            case "join" -> {
                PartyForm invitedPartyForm = PartyManager.get().checkInvited(player);
                if (invitedPartyForm == null) {
                    player.sendMessage(ChatColor.RED + "You have not been invited to a party!");
                    return true;
                }

                invitedPartyForm.getMembers().add(player);
                invitedPartyForm.getInvited().remove(player);
                for (Player p : invitedPartyForm.getMembers()) {
                    p.sendMessage(ChatColor.GREEN + player.getName() + " has joined the party!");
                }

                return true;
            }
            case "deny" -> {
                PartyForm invitedPartyForm = PartyManager.get().checkInvited(player);
                if (invitedPartyForm == null) {
                    player.sendMessage(ChatColor.RED + "You have not been invited to a party!");
                    return true;
                }

                //Remove player from party form

                return true;
            }
            case "help" -> {
                player.sendMessage(ChatColor.GOLD + "The available commands for Party are:\n/party invite <player> \n/party kick <player> \n/party disband \n/party leave \n/party join \n/party deny");
                return true;
            }
            default -> {
                player.sendMessage(ChatColor.RED + "That is not an available party command!");
                return false;
            }
        }
        return true;
    }
}
