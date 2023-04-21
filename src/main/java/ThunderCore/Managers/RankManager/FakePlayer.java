package ThunderCore.Managers.RankManager;

import java.util.List;
import java.util.UUID;

public class FakePlayer {

    private Ranks rank;
    private final UUID uuid;
    private List<String> subperms;
    private boolean muted;

    public FakePlayer(Ranks rank, UUID uuid, List<String> subperms, boolean muted) {
        this.rank = rank;
        this.uuid = uuid;
        this.subperms = subperms;
        this.muted = muted;
    }

    public Ranks getPlayerRank() { return rank; }

    public boolean isMuted() { return muted;}
    public void setMuted(boolean mute) { muted = mute; }

    public void setRank(Ranks ranks) { this.rank = ranks; }

    public UUID getUUID() { return uuid; }

    public List<String> getSubperms() {
        return subperms;
    }
    public void setSubperms(List<String> subperms) {
        this.subperms = subperms;
    }

}
