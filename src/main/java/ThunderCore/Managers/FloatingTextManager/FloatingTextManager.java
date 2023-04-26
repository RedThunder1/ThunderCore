package ThunderCore.Managers.FloatingTextManager;

import ThunderCore.Managers.ThunderManager;

import java.util.ArrayList;

public class FloatingTextManager implements ThunderManager {

    private ArrayList<FloatingTextRecord> floatingText = new ArrayList<>();

    private FloatingTextManager floatingTextManager;
    public FloatingTextManager get() { return floatingTextManager; }

    public FloatingTextManager() {
        floatingTextManager = this;
    }



}
