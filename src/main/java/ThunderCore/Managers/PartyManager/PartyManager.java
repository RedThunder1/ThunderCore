package ThunderCore.Managers.PartyManager;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PartyManager {

    private static ArrayList<PartyForm> parties;
    private static PartyManager partyManager;
    public static PartyManager get() { return partyManager; }

    public PartyManager() {
        partyManager = this;
        parties = new ArrayList<>();
    }

    public void createParty(PartyForm partyForm) { parties.add(partyForm); }

    public void removeParty(PartyForm partyForm) { parties.remove(partyForm); }

    public PartyForm getPartyByLeader(Player player) {
        for(PartyForm partyForm : parties) {
            if(String.valueOf(partyForm.getLeader()).equals(String.valueOf(player))) {
                return partyForm;
            }
        }
        return null;
    }

    public PartyForm getPartyByMember(Player player) {
        for(PartyForm partyForm : parties) {
            if(partyForm.getMembers().contains(player)) {
                return partyForm;
            }
        }
        return null;
    }

    public PartyForm checkInvited(Player player) {
        for(PartyForm partyForm : parties) {
            if(partyForm.getInvited().contains(player)) {
                return partyForm;
            }
        }
        return null;
    }

    public boolean checkIfMember(Player player) {
        for(PartyForm partyForm : parties) {
            if(partyForm.getMembers().contains(player)) {
                return true;
            }
            if (partyForm.getInvited().contains(player)) {
                return true;
            }
            if (partyForm.getLeader().equals(player)) {
                return true;
            }
        }
        return false;
    }
}
