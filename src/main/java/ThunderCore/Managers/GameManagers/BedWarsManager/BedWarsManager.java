package ThunderCore.Managers.GameManagers.BedWarsManager;

import ThunderCore.Managers.GameManagers.BedWarsManager.GameManager.BedWarsDuelGameManager;
import ThunderCore.Managers.GameManagers.BedWarsManager.GameManager.BedWarsTeamGameManager;
import ThunderCore.Managers.ThunderManager;
import ThunderCore.ThunderCore;
import com.google.gson.Gson;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;


public class BedWarsManager implements ThunderManager {

    private final Gson gson = new Gson();

    private World lobbyTemplate;
    private ArrayList<BedWarsMapForm> teamMaps = new ArrayList<>();
    private ArrayList<BedWarsMapForm> duelMaps = new ArrayList<>();
    private final ArrayList<BedWarsGameForm> activeGames = new ArrayList<>();
    private final ArrayList<StartingGameRecord> startingGames = new ArrayList<>();
    private static BedWarsManager skyWarsManager;
    public static BedWarsManager get() { return skyWarsManager; }

    public BedWarsManager() {
        skyWarsManager = this;
        initializeMaps();
    }

    public void removeActiveGames(BedWarsGameForm removeGame) { activeGames.remove(removeGame); }
    public ArrayList<BedWarsGameForm> getActiveGames() { return activeGames; }

    public void removeStartingGames(StartingGameRecord startingGame) { startingGames.remove(startingGame); }
    public ArrayList<StartingGameRecord> getStartingGames() { return startingGames; }

    public void startGame(BedWarsGameForm gameForm) {
        activeGames.add(gameForm);
        switch (gameForm.getMode()) {
            case "quads", "trios", "duos", "solo" -> new BedWarsTeamGameManager(gameForm);
            case "duel" -> new BedWarsDuelGameManager(gameForm);
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
