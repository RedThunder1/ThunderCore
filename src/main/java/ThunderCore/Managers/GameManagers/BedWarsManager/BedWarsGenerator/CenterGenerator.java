package ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsGenerator;

import ThunderCore.ThunderCore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class CenterGenerator {

    private boolean spawnItems = true;
    private float defaultSpawnRate = 30f;
    private final World world;
    private final Location location;
    public CenterGenerator(Location location) {
        world  = location.getWorld();
        this.location = location;
    }

    public void startSpawning() {
        float[] spawnRate = {30f};
        ItemStack emerald = new ItemStack(Material.EMERALD);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(spawnItems)) {
                    cancel();
                }
                if (spawnRate[0] <= 0) {
                    world.dropItemNaturally(location, emerald);
                    spawnRate[0] = defaultSpawnRate;
                } else {
                    spawnRate[0]--;
                }
            }
        }.runTaskTimer(ThunderCore.get(), 0, 20);
    }


    public void endSpawn() { spawnItems = false; }
    public void upgradeSpawnRate(float upgradeRate) { defaultSpawnRate -= upgradeRate; }

}
