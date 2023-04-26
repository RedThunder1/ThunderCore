package ThunderCore.Managers.GameManagers.BedWarsManager.GameManager;


import ThunderCore.Managers.FileManager.FileManager;
import ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsGameForm;
import ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsGenerator.CenterGenerator;
import ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsGenerator.SpawnGenerator;
import ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsGenerator.UpgradeGenerator;
import ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsManager;
import ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsMapForm;
import ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsTeamForm;
import ThunderCore.ThunderCore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BedWarsTeamGameManager implements Listener {

    private boolean gameOver = false;
    private boolean frozen = true;
    private BedWarsGameForm gameForm;
    public BedWarsTeamGameManager(BedWarsGameForm gameForm) {
        this.gameForm = gameForm;
        BedWarsMapForm map = gameForm.getMap();
        World gameWorld = map.mapTemplate();
        gameForm.setId(gameWorld.getName() + (BedWarsManager.get().getActiveGames().size() + 1));

        //Copy world
        FileManager.copyWorld(gameWorld, gameForm.getId());

        List<Location> spawns = gameForm.getMap().teamSpawns();
        List<BedWarsTeamForm> teams = gameForm.getTeams();


        //Initialize Shops


        //Initialize Generators

        //SpawnGenerators
        SpawnGenerator redGen = new SpawnGenerator(spawns.get(0));
        SpawnGenerator yellowGen = new SpawnGenerator(spawns.get(1));
        SpawnGenerator greenGen = new SpawnGenerator(spawns.get(2));
        SpawnGenerator blueGen = new SpawnGenerator(spawns.get(3));
        ArrayList<SpawnGenerator> spawnGenerators = new ArrayList<>();
        spawnGenerators.add(redGen);
        spawnGenerators.add(yellowGen);
        spawnGenerators.add(greenGen);
        spawnGenerators.add(blueGen);

        //UpgradeGenerators
        ArrayList<UpgradeGenerator> upgradeGenerators = new ArrayList<>();
        upgradeGenerators.add(new UpgradeGenerator(map.upgradeGenerators().get(0)));
        upgradeGenerators.add(new UpgradeGenerator(map.upgradeGenerators().get(1)));
        upgradeGenerators.add(new UpgradeGenerator(map.upgradeGenerators().get(2)));
        upgradeGenerators.add(new UpgradeGenerator(map.upgradeGenerators().get(3)));

        //CenterGenerators
        ArrayList<CenterGenerator> centerGenerators = new ArrayList<>();
        centerGenerators.add(new CenterGenerator(map.centerGenerators().get(0)));
        centerGenerators.add(new CenterGenerator(map.centerGenerators().get(1)));
        centerGenerators.add(new CenterGenerator(map.centerGenerators().get(2)));
        centerGenerators.add(new CenterGenerator(map.centerGenerators().get(3)));

        //Spawn Players
        int spawn = 0;
        for (BedWarsTeamForm teamForm : teams) {
            List<Player> players = teamForm.getTeamMembers();
            for (Player teamMember : players) {
                teamMember.teleport(spawns.get(spawn));
            }
            spawn++;
        }

        //Initialize Scoreboards and other Hud elements (Once I figure that out)



        //Countdown to game start
        int[] countdown = {5};
        new BukkitRunnable() {
            @Override
            public void run() {
                if (countdown[0] <= 0) {
                    frozen = false;
                    cancel();
                }
                for (BedWarsTeamForm teams : gameForm.getTeams()) {
                    for (Player players : teams.getTeamMembers()) {
                        Title title = Title.title(Component.text(ChatColor.GREEN + String.valueOf(countdown[0])), Component.empty(), Title.Times.times(Duration.ofMillis(100), Duration.ofMillis(600), Duration.ofMillis(100)));
                        players.playNote(players.getLocation(), Instrument.CHIME, Note.natural(1, Note.Tone.A));
                        players.showTitle(title);
                    }
                }
                countdown[0]--;
            }
        }.runTaskTimer(ThunderCore.get(), 0, 20);


        for (SpawnGenerator spawnGenerator : spawnGenerators) {
            spawnGenerator.startSpawning();
        }
        for (UpgradeGenerator upgradeGenerator : upgradeGenerators) {
            upgradeGenerator.startSpawning();
        }
        for (CenterGenerator centerGenerator : centerGenerators) {
            centerGenerator.startSpawning();
        }

        //8 Minute games
        int[] time = {480};
        new BukkitRunnable() {
            @Override
            public void run() {
                if (gameOver) {
                    cancel();
                }
                if (time[0] <= 0) {
                    endGame();
                    cancel();
                }
                time[0]--;

            }
        }.runTaskTimer(ThunderCore.get(), 0, 20);
    }

    public void endGame() {
        gameOver = true;



    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        if (!player.getWorld().getName().equals(gameForm.getId())) { return; }
        player.setGameMode(GameMode.SPECTATOR);
        EntityDamageEvent ede = player.getLastDamageCause();
        assert ede != null;
        EntityDamageEvent.DamageCause damageCause = ede.getCause();
        if (damageCause == EntityDamageEvent.DamageCause.VOID) {

        } else if (damageCause == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {

        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        //If player joins back have them rejoin
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld().getName().equals(gameForm.getId()) && frozen) {
            event.setCancelled(true);
        }
    }

}
