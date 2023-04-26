package ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsGenerator;

import ThunderCore.ThunderCore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


public class SpawnGenerator {

    private boolean spawnItems = true;
    private float defaultIronSpawnRate = 2f;
    private float defaultGoldSpawnRate = 10f;
    private final World world;
    private final Location location;
    public SpawnGenerator(Location location) {
        world = location.getWorld();
        this.location = location;

    }

    public void startSpawning() {
        float[] ironSpawnRate = {2f};
        float[] goldSpawnRate = {10f};
        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        ItemStack gold = new ItemStack(Material.GOLD_INGOT);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(spawnItems)) {
                    cancel();
                }
                if (ironSpawnRate[0] <= 0) {
                    world.dropItemNaturally(location, iron);
                    ironSpawnRate[0] = defaultIronSpawnRate;
                } else {
                    ironSpawnRate[0]--;
                }
                if (goldSpawnRate[0] <= 0) {
                    world.dropItemNaturally(location, gold);
                    goldSpawnRate[0] = defaultGoldSpawnRate;
                } else {
                    goldSpawnRate[0]--;
                }
            }
        }.runTaskTimer(ThunderCore.get(), 0, 20);
    }


    public void endSpawn() { spawnItems = false; }
    public void upgradeIronSpawnRate(float upgradeRate) { defaultIronSpawnRate -= upgradeRate; }
    public void upgradeGoldSpawnRate(float upgradeRate) { defaultGoldSpawnRate -= upgradeRate; }

}
