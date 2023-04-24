package ThunderCore.Managers.SkyWarsManager.GameManager;


import ThunderCore.Managers.SkyWarsManager.SkyWarsGameForm;
import ThunderCore.Managers.SkyWarsManager.SkyWarsManager;
import ThunderCore.Managers.SkyWarsManager.SkyWarsTeamForm;
import ThunderCore.ThunderCore;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SkyWarsTeamGameManager implements Listener {

    private boolean gameOver = false;
    private SkyWarsGameForm gameForm;
    public SkyWarsTeamGameManager(SkyWarsGameForm gameForm) {
        this.gameForm = gameForm;
        World gameWorld = gameForm.getMap().getMapTemplate();
        new WorldCreator(gameWorld.getName() + (SkyWarsManager.get().getActiveGames().size() + 1)).createWorld();
        List<Location> spawns = gameForm.getMap().getTeamSpawns();
        List<SkyWarsTeamForm> teams = gameForm.getTeams();

        //Initialize Shops


        //Initialize Generators


        //Initialize Scoreboards and other Hud elements (Once I figure that out)


        //Spawn Players
        int spawn = 0;
        for (SkyWarsTeamForm teamForm : teams) {
            List<Player> players = teamForm.getTeamMembers();
            for (Player teamMember : players) {
                teamMember.teleport(spawns.get(spawn));
            }
            spawn++;
        }
        //8 Minute games
        int[] time = {480};
        new BukkitRunnable() {
            @Override
            public void run() {
                if (gameOver) {
                    cancel();
                }
                if (time[0] == 0) {
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
        player.setGameMode(GameMode.SPECTATOR);
        EntityDamageEvent ede = player.getLastDamageCause();
        assert ede != null;
        EntityDamageEvent.DamageCause damageCause = ede.getCause();
        if (damageCause == EntityDamageEvent.DamageCause.VOID) {

        } else if (damageCause == EntityDamageEvent.DamageCause.VOID) {

        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

    }



}
