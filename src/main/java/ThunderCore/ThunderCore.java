package ThunderCore;

import ThunderCore.Commands.LobbyCommand;
import ThunderCore.Commands.PartyCommand;
import ThunderCore.Commands.StaffCommands.*;
import ThunderCore.Commands.StaffCommands.Worlds.CreateWorldCommand;
import ThunderCore.Commands.StaffCommands.Worlds.DeleteWorldCommand;
import ThunderCore.Commands.StaffCommands.Worlds.TpWorldCommand;
import ThunderCore.Events.ChatListener;
import ThunderCore.Events.PlayerJoin;
import ThunderCore.Events.PlayerLeave;
import ThunderCore.Events.WorldProtection;
import ThunderCore.Managers.RankManager.FakePlayer;
import ThunderCore.Managers.RankManager.RankManager;
import ThunderCore.Managers.SkyWarsManager.SkyWarsManager;
import ThunderCore.Managers.ThunderManager;
import ThunderCore.Utilities.AnnouncementMessages;
import ThunderCore.Utilities.Time;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThunderCore extends JavaPlugin {
    public ConsoleCommandSender console = this.getServer().getConsoleSender();
    public String thunderName = ChatColor.BOLD + "" + ChatColor.YELLOW + "THUNDER" + ChatColor.BLUE + "CORE: " + ChatColor.RESET;
    private static ThunderCore plugin;
    public static ThunderCore get() {return plugin;}
    private final ArrayList<ThunderManager> managers = new ArrayList<>();
    //TODO:
    // Priority:
    //      Skywars mini game
    //      Commands
    //      Party system
    // Secondary:
    //      Replace ChatColor since its depreciated
    //      Test for bugs once a server is set up
    //      Other Games
    //      Friend system
    //      Timed Mute
    // Bugs to fix:
    //      Chat message not displayed properly
    //      Some commands don't do anything (lobby, worldtp)


    @Override
    public void onEnable() {
        plugin = this;
        loadManagers();
        loadEvents();
        loadRunnables();
        loadWorlds();
        greenMsg("ENABLED!");
    }

    @Override
    public void onLoad() { greenMsg("LOADED!"); }

    @Override
    public void onDisable() {
        for(ThunderManager thunderManager : managers) {
            thunderManager.save();
        }
        redMsg("DISABLED!");
    }

    public void loadCommands() {
        String[] banAlias = {"ipban"};
        String[] muteAlias = {"unmute"};
        String[] lobbyAlias = {"hub"};
        Objects.requireNonNull(this.getCommand("lobby")).setExecutor(new LobbyCommand());
        Objects.requireNonNull(this.getCommand("lobby")).setAliases(List.of(lobbyAlias));
        Objects.requireNonNull(this.getCommand("ban")).setExecutor(new BanCommand());
        Objects.requireNonNull(this.getCommand("ban")).setAliases(List.of(banAlias));
        Objects.requireNonNull(this.getCommand("mutechat")).setExecutor(new MuteChatCommand());
        Objects.requireNonNull(this.getCommand("vanish")).setExecutor(new VanishCommand());
        Objects.requireNonNull(this.getCommand("build")).setExecutor(new BuildCommand());
        Objects.requireNonNull(this.getCommand("mute")).setExecutor(new MuteCommand());
        Objects.requireNonNull(this.getCommand("mute")).setAliases(List.of(muteAlias));
        Objects.requireNonNull(this.getCommand("getvanished")).setExecutor(new GetVanishedCommand());
        Objects.requireNonNull(this.getCommand("party")).setExecutor(new PartyCommand());
        Objects.requireNonNull(this.getCommand("worldcreate")).setExecutor(new CreateWorldCommand());
        Objects.requireNonNull(this.getCommand("worlddelete")).setExecutor(new DeleteWorldCommand());
        Objects.requireNonNull(this.getCommand("worldtp")).setExecutor(new TpWorldCommand());
        greenMsg("Commands LOADED!");
    }

    public void loadEvents() {
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoin(), this);
        pluginManager.registerEvents(new PlayerLeave(), this);
        pluginManager.registerEvents(new WorldProtection(), this);
        pluginManager.registerEvents(new ChatListener(), this);
        greenMsg("Events LOADED!");
    }

    public void loadManagers() {
        managers.add(new RankManager());
        managers.add(new SkyWarsManager());
        greenMsg("Managers have been INITIALIZED");
        for(ThunderManager thunderManager : managers) {
            thunderManager.load();
        }
        greenMsg("Managers LOADED!");
    }

    public void loadRunnables() {
        BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.runTaskTimer(this, new AnnouncementMessages(this), 0, Time.TEN_MIN);
        greenMsg("Runnables LOADED!");
    }

    public void loadWorlds() {
        ArrayList<String> worlds = new ArrayList<>();
        worlds.add("world");
        worlds.add("lobby");
        greenMsg("Worlds Initialized!");
        for (String world : worlds) {
            WorldCreator worldCreator = new WorldCreator(world);
            worldCreator.createWorld();
        }
        greenMsg("Worlds LOADED!");
    }

    public void greenMsg(String text) { console.sendMessage(Component.text(thunderName + ChatColor.GREEN + text)); }

    public void redMsg(String text) { console.sendMessage(Component.text(thunderName + ChatColor.RED + text)); }

    public void yellowMsg(String text) { console.sendMessage(Component.text(thunderName + ChatColor.YELLOW + text)); }

    public boolean isStaff(Player player) {
        if (RankManager.get().fakePlayers.contains(RankManager.get().getFakePlayer(player))) {
            FakePlayer fakePlayer = RankManager.get().getFakePlayer(player);
            return fakePlayer.getPlayerRank().getPermLevel() >= 1;
        } else return player.isOp();
    }
    public boolean isModerator(Player player) {
        if (RankManager.get().fakePlayers.contains(RankManager.get().getFakePlayer(player))) {
            FakePlayer fakePlayer = RankManager.get().getFakePlayer(player);
            return fakePlayer.getPlayerRank().getPermLevel() >= 2;
        } else return player.isOp();
    }
    public boolean isAdmin(Player player) {
        if (RankManager.get().fakePlayers.contains(RankManager.get().getFakePlayer(player))) {
            FakePlayer fakePlayer = RankManager.get().getFakePlayer(player);
            return fakePlayer.getPlayerRank().getPermLevel() >= 3;
        } else return player.isOp();
    }
    public boolean isOwner(Player player) {
        if (RankManager.get().fakePlayers.contains(RankManager.get().getFakePlayer(player))) {
            FakePlayer fakePlayer = RankManager.get().getFakePlayer(player);
            return fakePlayer.getPlayerRank().getPermLevel() >= 4;
        } else return player.isOp();
    }
    public boolean isBuilder(Player player) {
        if (RankManager.get().fakePlayers.contains(RankManager.get().getFakePlayer(player))) {
            FakePlayer fakePlayer = RankManager.get().getFakePlayer(player);
            return fakePlayer.getPlayerRank().equals(RankManager.get().getRankByName("builder"));
        } else return isAdmin(player);
    }

}
