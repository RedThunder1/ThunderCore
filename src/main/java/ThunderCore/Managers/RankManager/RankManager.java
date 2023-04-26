package ThunderCore.Managers.RankManager;

import ThunderCore.Managers.FileManager.FileManager;
import ThunderCore.Managers.ThunderManager;
import ThunderCore.ThunderCore;
import com.google.gson.Gson;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RankManager implements ThunderManager {

    private final Gson gson = new Gson();

    private static RankManager rankManager;
    public static RankManager get() {
        return rankManager;
    }

    private final ArrayList<Ranks> playerRanks;
    public ArrayList<FakePlayer> fakePlayers;
    private final ArrayList<String> subPerms;

    public RankManager() {
        rankManager = this;
        playerRanks = new ArrayList<>();
        fakePlayers = new ArrayList<>();
        subPerms = new ArrayList<>();
        playerRanks.add(new Ranks("owner", 4, ChatColor.DARK_RED + "[" + ChatColor.BOLD + "Owner" + ChatColor.RESET + ChatColor.DARK_RED + "] "));
        playerRanks.add(new Ranks("co-owner", 4, ChatColor.RED + "[" + ChatColor.BOLD + "Co Owner" + ChatColor.RESET + ChatColor.RED + "] "));
        playerRanks.add(new Ranks("developer", 3, ChatColor.RED + "[" + ChatColor.BOLD + "Developer" + ChatColor.RESET + ChatColor.RED + "] "));
        playerRanks.add(new Ranks("admin", 3, ChatColor.GOLD + "[" + ChatColor.BOLD + "Admin" + ChatColor.RESET + ChatColor.GOLD + "] "));
        playerRanks.add(new Ranks("mod", 2, ChatColor.YELLOW + "[" + ChatColor.BOLD + "Mod" + ChatColor.RESET + ChatColor.YELLOW + "] "));
        playerRanks.add(new Ranks("builder", 2, ChatColor.BLUE + "[" + ChatColor.BOLD + "Builder" + ChatColor.RESET + ChatColor.BLUE + "] "));
        playerRanks.add(new Ranks("trial-mod", 1, ChatColor.BLUE + "[" + ChatColor.BOLD + "Trial Mod" + ChatColor.RESET + ChatColor.BLUE + "] "));
        playerRanks.add(new Ranks("spartan", 0, ChatColor.GREEN + "[" + ChatColor.BOLD + "Spartan" + ChatColor.RESET + ChatColor.GREEN + "] "));
        playerRanks.add(new Ranks("member", 0, ChatColor.AQUA + "[" + ChatColor.BOLD + "Member" + ChatColor.RESET + ChatColor.AQUA + "] "));
        subPerms.add("build");
        subPerms.add("heal");
        subPerms.add("mute");
        subPerms.add("vanish");
        subPerms.add("unmute");
        subPerms.add("kick");
        subPerms.add("ban");
        subPerms.add("unban");
        subPerms.add("worldtp");
        subPerms.add("mutechat");
        subPerms.add("sudo");
        subPerms.add("skullgive");
        subPerms.add("createworld");
        subPerms.add("deleteworld");
        subPerms.add("worldlist");
        subPerms.add("stop");
        subPerms.add("restart");
        subPerms.add("setrank");
        subPerms.add("setsubperms");
    }

    public ArrayList<Ranks> getPlayerRanks() {
        return playerRanks;
    }

    public ArrayList<String> getSubPerms() { return subPerms; }

    public Ranks getRankByName(String name) {
        for (Ranks pRanks : playerRanks) {
            if (pRanks.getName().equals(name)) {
                return pRanks;
            }
        }
        return null;
    }

    public Ranks getPlayerRank(Player player) {
        for (FakePlayer fakePlayer : fakePlayers) {
            if (fakePlayer.getUUID().equals(player.getUniqueId())) {
                return fakePlayer.getPlayerRank();
            }
        }
        return null;
    }

    public FakePlayer getFakePlayer(Player player) {
        for (FakePlayer fakePlayer : fakePlayers) {
            if (fakePlayer.getUUID().equals(player.getUniqueId())) {
                return fakePlayer;
            }
        }
        return null;
    }

    public void createFakePlayer(Player player, String rank, List<String> subperms) {
        ThunderCore.get().greenMsg("Created a fake player!");
        fakePlayers.add(new FakePlayer(getRankByName(rank), player.getUniqueId(), subperms, false, false));
    }

    public boolean checkSubPerm(String s) {
        for (String subPerm : subPerms) {
            if (s.equals(subPerm)) {
                return true;
            }
        }
        return false;
    }

    //TODO: Load player ranks here, we already initialize them in the constructor
    @Override
    public void load() {
        try {
            File folder = new File("ThunderCore/FakePlayers/");
            File[] listOfFiles = folder.listFiles();
            for (File file : Objects.requireNonNull(listOfFiles)) {
                String fileContent = FileManager.readFile(file);
                this.fakePlayers.add(gson.fromJson(fileContent, FakePlayer.class));
            }
        } catch(NullPointerException e) {
            ThunderCore.get().yellowMsg("THERE ARE NO PLAYER FILES!");
        }
        ThunderCore.get().greenMsg("Ranks loaded!");
    }

    @Override
    public void save() {
        for (FakePlayer fakePlayer : this.fakePlayers) {
            String id = fakePlayer.getUUID().toString();
            FileManager.writeFile(new File("ThunderCore/FakePlayers/" + id + ".json"), gson.toJson(fakePlayer));
        }
        ThunderCore.get().greenMsg("Saved Player Ranks!");
    }

}
