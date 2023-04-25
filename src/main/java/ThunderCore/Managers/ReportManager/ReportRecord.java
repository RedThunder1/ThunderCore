package ThunderCore.Managers.ReportManager;

import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public record ReportRecord(Player reporter, Player reported, String reason, UUID id) {
    public ReportRecord {
        Objects.requireNonNull(reporter);
        Objects.requireNonNull(reported);
        Objects.requireNonNull(reason);
        Objects.requireNonNull(id);
    }
}
