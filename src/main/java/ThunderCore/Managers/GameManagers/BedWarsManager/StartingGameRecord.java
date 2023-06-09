package ThunderCore.Managers.GameManagers.BedWarsManager;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public record StartingGameRecord(ArrayList<Player> players, String mode, BedWarsMapForm map, UUID id, World lobby) {
    public StartingGameRecord {
        Objects.requireNonNull(players);
        Objects.requireNonNull(mode);
        Objects.requireNonNull(map);
        Objects.requireNonNull(id);
        Objects.requireNonNull(lobby);
    }
}
