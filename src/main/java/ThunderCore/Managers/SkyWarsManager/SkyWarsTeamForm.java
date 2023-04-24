package ThunderCore.Managers.SkyWarsManager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class SkyWarsTeamForm {

    private ArrayList<Player> teamMembers;
    private HashMap<Player, Integer> playerKills;

    private String teamColor;

    public SkyWarsTeamForm(ArrayList<Player> teamMembers, String teamColor) {
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
    public void setPlayerKills(Player player, int kills) {
        if (playerKills.get(player) == null) { return; }
        playerKills.put(player, kills);
    }

    public String getTeamColor() { return teamColor; }
    public void setTeamColor(String newColor) { teamColor = newColor; }

}
