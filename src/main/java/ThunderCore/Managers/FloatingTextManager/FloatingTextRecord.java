package ThunderCore.Managers.FloatingTextManager;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public record FloatingTextRecord(ArmorStand armorStand, Component text, Location location) {
}
