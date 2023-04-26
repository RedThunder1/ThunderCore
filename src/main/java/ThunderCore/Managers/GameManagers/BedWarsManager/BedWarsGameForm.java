package ThunderCore.Managers.GameManagers.BedWarsManager;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BedWarsGameForm {

    private ArrayList<BedWarsTeamForm> teams;
    private BedWarsMapForm map;
    private String mode;
    private String id;

    public BedWarsGameForm(ArrayList<BedWarsTeamForm> teams, String mode, BedWarsMapForm map, String id) {
        this.teams = teams;
        this.map = map;
        this.mode = mode;
        this.id = id;
    }

    public BedWarsTeamForm getTeamByColor(String color) {
        for (BedWarsTeamForm teamForm : teams) {
            if (teamForm.getTeamColor().equals(color)) {
                return teamForm;
            }
        }
        return null;
    }

    public BedWarsTeamForm getTeamByPlayer(Player player) {
        for (BedWarsTeamForm teamForm : teams) {
            if (teamForm.getTeamMembers().contains(player)) {
                return teamForm;
            }
        }
        return null;
    }
    public ArrayList<BedWarsTeamForm> getTeams() { return teams; }
    public void setTeams(ArrayList<BedWarsTeamForm> newTeams) { teams = newTeams; }
    public void removeTeam(BedWarsTeamForm removedTeam) { teams.remove(removedTeam); }


    public BedWarsMapForm getMap() { return map; }

    public String getMode() { return mode; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id;}


}
