package ThunderCore.Managers.PartyManager;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PartyForm {

    private Player leader;
    private ArrayList<Player> members;
    private ArrayList<Player> invited;

    public PartyForm(Player leader, ArrayList<Player> members, ArrayList<Player> invited) {
        this.leader = leader;
        this.members = members;
        this.invited = invited;
    }

    public Player getLeader() { return leader; }
    public void setLeader(Player leader) { this.leader = leader; }

    public ArrayList<Player> getMembers() { return members; }
    public void setMembers(ArrayList<Player> members) { this.members = members; }

    public ArrayList<Player> getInvited() { return invited; }
    public void setInvited(ArrayList<Player> invited) { this.invited = invited; }


    public void removeMember(Player player) {
        for (Player p : members) {
            if (p == player) {
                members.remove(player);
                return;
            }
        }
    }

    public void removeInvited(Player player) {
        for (Player p : invited) {
            if (p == player) {
                invited.remove(player);
                return;
            }
        }
    }
}
