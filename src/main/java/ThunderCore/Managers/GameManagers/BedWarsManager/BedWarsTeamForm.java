package ThunderCore.Managers.GameManagers.BedWarsManager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class BedWarsTeamForm {

    private ArrayList<Player> teamMembers;
    private HashMap<Player, Integer> playerKills;

    private String teamColor;

    public BedWarsTeamForm(ArrayList<Player> teamMembers, String teamColor) {
        this.teamMembers = teamMembers;
        this.teamColor = teamColor;
        playerKills = new HashMap<>();
        for (Player player : teamMembers) {
            playerKills.put(player, 0);
        }
    }

    public ArrayList<Player> getTeamMembers() { return teamMembers; }
    public void setTeamMembers(ArrayList<Player> newPlayers) { teamMembers = newPlayers; }

    public Integer getPlayerKills(Player player) { return playerKills.get(player); }
    public void setPlayerKills(Player player) {
        if (playerKills.get(player) == null) { return; }
        playerKills.put(player, playerKills.get(player) + 1);
    }

    public String getTeamColor() { return teamColor; }
    public void setTeamColor(String newColor) { teamColor = newColor; }

}
