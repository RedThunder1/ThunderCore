package ThunderCore.Managers.SkyWarsManager;

import ThunderCore.Managers.GSONManager.GSONManager;
import ThunderCore.Managers.SkyWarsManager.GameManager.SkyWarsDuelGameManager;
import ThunderCore.Managers.SkyWarsManager.GameManager.SkyWarsTeamGameManager;
import ThunderCore.Managers.ThunderManager;
import ThunderCore.ThunderCore;
import com.google.gson.Gson;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


public class SkyWarsManager implements ThunderManager {

    private final Gson gson = new Gson();

    private ArrayList<SkyWarsMapForm> mapTemplates;
    private ArrayList<SkyWarsMapForm> teamMaps = new ArrayList<>();
    private ArrayList<SkyWarsMapForm> duelMaps = new ArrayList<>();
    private ArrayList<SkyWarsGameForm> activeGames = new ArrayList<>();
    private static SkyWarsManager skyWarsManager;
    public static SkyWarsManager get() {
        return skyWarsManager;
    }

    public SkyWarsManager() {
        skyWarsManager = this;
        /*
        if (!(mapTemplates.isEmpty())) {
            for (SkyWarsMapForm skyWarsMapForm : mapTemplates) {
                if (skyWarsMapForm.getWorldType().equals("duel")) {
                    duelMaps.add(skyWarsMapForm);
                } else {
                    teamMaps.add(skyWarsMapForm);
                }
            }
        }
         */
    }

    public void removeActiveGames(SkyWarsGameForm removeGame) { activeGames.remove(removeGame); }
    public ArrayList<SkyWarsGameForm> getActiveGames() { return activeGames; }

    @Override
    public void load() {
        try {
            File folder = new File("ThunderCore/SkyWarsMaps/");
            File[] listOfFiles = folder.listFiles();
            for (File file : Objects.requireNonNull(listOfFiles)) {
                String fileContent = GSONManager.readFile(file);
                mapTemplates.add(gson.fromJson(fileContent, SkyWarsMapForm.class));
            }
            mapTemplates = gson.fromJson(Objects.requireNonNull(GSONManager.readFile(new File("PlayerRanks.json"))), mapTemplates.getClass());
        } catch(NullPointerException e) {
            ThunderCore.get().yellowMsg("THERE ARE NO SKYWARS MAP FILES!");
        }
        ThunderCore.get().greenMsg("Ranks loaded!");
    }

    @Override
    public void save() {
        for (SkyWarsMapForm mapForm : mapTemplates) {
            String id = mapForm.getName();
            GSONManager.writeFile(new File("ThunderCore/SkyWarsMaps/" + id + ".json"), gson.toJson(mapForm));
        }
        ThunderCore.get().greenMsg("Saved SkyWarsMaps!");
    }

    public void startGame(SkyWarsGameForm gameForm) {
        activeGames.add(gameForm);
        switch (gameForm.getMode()) {
            case "team":
                new SkyWarsTeamGameManager(gameForm);
                break;
            case "duel":
                new SkyWarsDuelGameManager(gameForm);
                break;
            default:
                ThunderCore.get().redMsg("There was an error initializing aSkyWars game!");
                break;
        }
    }

}
