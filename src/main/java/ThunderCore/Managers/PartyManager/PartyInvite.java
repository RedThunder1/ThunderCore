package ThunderCore.Managers.PartyManager;

import ThunderCore.ThunderCore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class PartyInvite {

    private PartyForm party;
    private final TextComponent accept = Component.text(ChatColor.GREEN + "ACCEPT")
            .clickEvent(ClickEvent.runCommand("party join"))
            .toBuilder().build();
    private final TextComponent deny = Component.text(ChatColor.RED + "DENY")
            .clickEvent(ClickEvent.runCommand("party deny"))
            .toBuilder().build();

    public void invitePlayer(Player inviter, Player invited) {
        invited.sendMessage(ChatColor.GOLD + "You have been invited to a party by " + inviter.getName() + "! " + accept + ChatColor.GOLD + " || " + ChatColor.RESET + deny);
        party = PartyManager.get().getPartyByLeader(inviter);
        if (party == null) {
            ArrayList<Player> invitedList = new ArrayList<>();
            invitedList.add(invited);
            party = new PartyForm(inviter, null, invitedList);
            PartyManager.get().createParty(party);
        }

        if (party.getMembers().size() == 3) {
            inviter.sendMessage(ChatColor.RED + "The party is full! You cannot invite more players!");
            return;
        }

        if (party.getInvited().contains(invited)) {
            inviter.sendMessage(ChatColor.RED + "You have already invited " + invited.getName());
            return;
        }

        if (PartyManager.get().checkIfMember(invited)) {
            inviter.sendMessage(ChatColor.RED + invited.getName() + " is already in a party!");
            return;
        }

        final int[] time = {60};
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(party.getInvited().contains(invited))) {
                    cancel();
                }
                if (time[0] == 0) {
                    party.getInvited().remove(invited);
                    invited.sendMessage(ChatColor.RED + "Your party invite has expired!");
                    cancel();
                }
                time[0]--;
            }
        }.runTaskTimer(ThunderCore.get(), 0, 20);
    }
}
