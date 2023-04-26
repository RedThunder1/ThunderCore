package ThunderCore.Managers.GameManagers.BedWarsManager;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;
import java.util.Objects;

public record BedWarsMapForm(String name, World mapTemplate, String worldType, List<Location> teamSpawns, List<Location> teamShops, List<Location> centerGenerators, List<Location> upgradeGenerators) {

    public BedWarsMapForm {
        Objects.requireNonNull(name);
        Objects.requireNonNull(mapTemplate);
        Objects.requireNonNull(worldType);
        Objects.requireNonNull(teamSpawns);
        Objects.requireNonNull(teamShops);
        Objects.requireNonNull(centerGenerators);
        Objects.requireNonNull(upgradeGenerators);
    }
}
