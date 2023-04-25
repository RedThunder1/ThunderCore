package ThunderCore.Managers.RankManager;

import java.util.List;
import java.util.UUID;

public class FakePlayer {

    private Ranks rank;
    private final UUID uuid;
    private List<String> subperms;
    private boolean muted;
    private boolean inGame;

    public FakePlayer(Ranks rank, UUID uuid, List<String> subperms, boolean muted, boolean inGame) {
        this.rank = rank;
        this.uuid = uuid;
        this.subperms = subperms;
        this.muted = muted;
        this.inGame = inGame;
    }

    public Ranks getPlayerRank() { return rank; }

    public boolean isMuted() { return muted;}
    public void setMuted(boolean mute) { muted = mute; }
    public boolean isInGame() { return inGame; }
    public void setInGame(boolean inGame) { this.inGame = inGame; }

    public void setRank(Ranks ranks) { this.rank = ranks; }

    public UUID getUUID() { return uuid; }

    public List<String> getSubperms() {
        return subperms;
    }
    public void setSubperms(List<String> subperms) {
        this.subperms = subperms;
    }

}
