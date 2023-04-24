package ThunderCore.Managers.SkyWarsManager;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SkyWarsGameForm {

    private ArrayList<SkyWarsTeamForm> teams;
    private SkyWarsMapForm map;
    private String mode;

    public SkyWarsGameForm(ArrayList<SkyWarsTeamForm> teams, String mode, SkyWarsMapForm map) {
        this.teams = teams;
        this.map = map;
        this.mode = mode;
    }

    public SkyWarsTeamForm getTeamByColor(String color) {
        for (SkyWarsTeamForm teamForm : teams) {
            if (teamForm.getTeamColor().equals(color)) {
                return teamForm;
            }
        }
        return null;
    }

    public SkyWarsTeamForm getTeamByPlayer(Player player) {
        for (SkyWarsTeamForm teamForm : teams) {
            if (teamForm.getTeamMembers().contains(player)) {
                return teamForm;
            }
        }
        return null;
    }
    public ArrayList<SkyWarsTeamForm> getTeams() { return teams; }
    public void setTeams(ArrayList<SkyWarsTeamForm> newTeams) { teams = newTeams; }
    public void removeTeam(SkyWarsTeamForm removedTeam) { teams.remove(removedTeam); }


    public SkyWarsMapForm getMap() { return map; }

    public String getMode() { return mode; }


}
