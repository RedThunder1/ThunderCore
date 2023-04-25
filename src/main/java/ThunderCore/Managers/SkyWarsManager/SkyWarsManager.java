package ThunderCore.Managers.SkyWarsManager;

import ThunderCore.Managers.SkyWarsManager.GameManager.SkyWarsDuelGameManager;
import ThunderCore.Managers.SkyWarsManager.GameManager.SkyWarsTeamGameManager;
import ThunderCore.Managers.ThunderManager;
import ThunderCore.ThunderCore;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;


public class SkyWarsManager implements ThunderManager {

    private final Gson gson = new Gson();

    private World lobbyTemplate;
    private ArrayList<SkyWarsMapForm> teamMaps = new ArrayList<>();
    private ArrayList<SkyWarsMapForm> duelMaps = new ArrayList<>();
    private final ArrayList<SkyWarsGameForm> activeGames = new ArrayList<>();
    private final ArrayList<StartingGameRecord> startingGames = new ArrayList<>();
    private static SkyWarsManager skyWarsManager;
    public static SkyWarsManager get() { return skyWarsManager; }

    public SkyWarsManager() {
        skyWarsManager = this;
        initializeMaps();
    }

    public void removeActiveGames(SkyWarsGameForm removeGame) { activeGames.remove(removeGame); }
    public ArrayList<SkyWarsGameForm> getActiveGames() { return activeGames; }

    public void removeStartingGames(StartingGameRecord startingGame) { startingGames.remove(startingGame); }
    public ArrayList<StartingGameRecord> getStartingGames() { return startingGames; }

    public void startGame(SkyWarsGameForm gameForm) {
        activeGames.add(gameForm);
        switch (gameForm.getMode()) {
            case "quads", "trios", "duos", "solo" -> new SkyWarsTeamGameManager(gameForm);
            case "duel" -> new SkyWarsDuelGameManager(gameForm);
            default -> ThunderCore.get().redMsg("There was an error initializing aSkyWars game!");
        }
    }


    public void joinGame(Player player, String mode) {
        if (startingGames.isEmpty()) {
            initializeNewGame(player, mode);
        }
        for (StartingGameRecord startingGame : startingGames) {
            if (startingGame.mode().equals(mode)) {
                startingGame.players().add(player);
                //tp player to lobby
                return;
            }
        }
        initializeNewGame(player, mode);
    }

    public void initializeNewGame(Player player, String mode) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        /*
        Get random map here once maps are made


        startingGames.add(new StartingGameRecord(players, mode, map, UUID.randomUUID()));
        return;
        */
    }

    private void initializeMaps() {
        //Maps will be hardcoded
        //lobbyTemplate = Bukkit.getWorld("lobbyTemplate");
    }
}
