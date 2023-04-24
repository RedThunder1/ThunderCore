package ThunderCore.Managers.SkyWarsManager;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

public class SkyWarsMapForm {

    private final String name;
    private final World mapTemplate;
    private final String worldType;
    private final List<Location> teamSpawns;

    private final List<Location> teamShops;

    private final List<Location> centerGenerators;


    public SkyWarsMapForm(String name, World mapTemplate, String worldType, List<Location> teamSpawns, List<Location> teamShops, List<Location> centerGenerators) {
        this.name = name;
        this.mapTemplate = mapTemplate;
        this.worldType = worldType;
        this.teamSpawns = teamSpawns;
        this.teamShops = teamShops;
        this.centerGenerators = centerGenerators;
    }

    public String getName() { return name; }
    public World getMapTemplate() { return mapTemplate; }
    public String getWorldType() { return worldType; }
    public List<Location> getTeamSpawns() { return teamSpawns; }
    public List<Location> getTeamShops() { return teamShops; }
    public List<Location> getCenterGenerators() { return centerGenerators; }

}
