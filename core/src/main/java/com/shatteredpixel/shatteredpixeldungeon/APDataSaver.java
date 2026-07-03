package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.ap.APConnector;
import com.shatteredpixel.shatteredpixeldungeon.ap.APManager;
import com.shatteredpixel.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;

import java.io.IOException;
import java.util.HashMap;

public class APDataSaver {
    private static final String DATA_FILE = "archipelago.dat";
    private static final String SAVE_ID = "save_id";
    private static final String PLAYER_NAME = "player_name";
    private static HashMap<Integer, Info> slotStates = new HashMap<>();
    public static String modDataFile(int slot) {
        return GamesInProgress.gameFolder(slot) + "/" + DATA_FILE;
    }

    public static void save(int slot) {

        setSlotInfo(slot);

        try {
            Bundle bundle = new Bundle();

            APManager.store(bundle);

            bundle.put(SAVE_ID, APConnector.getClient().getSaveID());
            bundle.put(PLAYER_NAME, APConnector.getClient().getMyName());

            FileUtils.bundleToFile( modDataFile(slot), bundle);

        } catch (IOException e) {
            ShatteredPixelDungeon.reportException(e);
        }
    }

    public static void load(int slot) throws IOException {

        Bundle bundle = FileUtils.bundleFromFile( modDataFile(slot) );

        APManager.restore(bundle);

    }

    public static int findSlot(String saveID) {
        System.out.println("Searching for a slot that matches id "+saveID);
        for (int i = 1; i <= GamesInProgress.MAX_SLOTS; i++) {
            Info slot = getSlotInfo(i);
            System.out.println(
                    (slot == null)? "slot was null" :
                    "slot= "+i+", id="+slot.saveID
            );
            if (slot != null && saveID.equals(slot.saveID)) {
                System.out.println("Match found!");
                return i;
            }
        }
        System.out.println("No matches were found!");
        return -1;
    }

    public static int getTotalSlots() {
        int total = 0;
        for (int i = 1; i <= GamesInProgress.MAX_SLOTS; i++) {
            Info current = getSlotInfo(i);
            if (current != null) total++;
        }
        return total;
    }

    public static Info getSlotInfo(int slot) {

        if (slotStates.containsKey( slot )) {
            return slotStates.get(slot);
        } else if (!GamesInProgress.gameExists(slot)) {
            slotStates.put(slot, null);
            return null;
        } else {
            Info info;
            try {
                Bundle bundle = FileUtils.bundleFromFile(modDataFile(slot));

                info = new Info();
                APManager.preview(info, bundle);
                info.saveID = bundle.getString(SAVE_ID);
                info.name = bundle.getString(PLAYER_NAME);

            } catch (IOException e) {
                info = null;
            } catch (Exception e) {
                ShatteredPixelDungeon.reportException(e);
                info = null;
            }

            slotStates.put(slot, info);
            return info;

        }
    }

    public static void setSlotInfo(int slot) {
        Info info = new Info();

        info.saveID = APConnector.getClient().getSaveID();

        info.name = APConnector.getClient().getMyName();

        info.checkedLocations = APManager.checksCount;
        info.totalLocations = APManager.totalChecks;

        info.wins = APManager.wins;
        info.requiredWins = APManager.requiredWins;

        slotStates.put(slot, info);
    }

    public static class Info {
        public String saveID;

        public String name;

        public int checkedLocations;
        public int totalLocations;

        public int wins;
        public int requiredWins;
    }
}
