package ThunderCore.Managers.RankManager;

public class Ranks {

    private final String name;
    private final int permlevel;
    private final String prefix;

    public Ranks(String name, int permlevel, String prefix) {
        this.name = name;
        this.permlevel = permlevel;
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }
    public int getPermLevel() {
        return permlevel;
    }
    public String getPrefix() {
        return prefix;
    }

}
