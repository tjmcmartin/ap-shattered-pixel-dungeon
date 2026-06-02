package com.shatteredpixel.shatteredpixeldungeon.ap;

import java.util.HashSet;
import java.util.Set;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;

public class APManager {

    private static final Set<APLocation> completedChecks = new HashSet<>();

    public static void checkLocation(APLocation location) {

        if (completedChecks.contains(location)) {
            return;
        }

        completedChecks.add(location);

        //TODO fix once ap side integrated
        GLog.w("[AP] " + Messages.get(APManager.class, "item_sent", "item", "player"));
    }

    public static void receiveItem(APItem item) {
        System.out.println("Received " + item);
    }

}
