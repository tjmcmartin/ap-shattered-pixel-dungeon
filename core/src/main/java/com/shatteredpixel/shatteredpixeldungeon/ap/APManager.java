package com.shatteredpixel.shatteredpixeldungeon.ap;

import java.util.HashSet;
import java.util.Set;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.Random;

public class APManager {

    private static final Set<APLocation> completedChecks = new HashSet<>();
    private static final Set<APItem> receivedItems = new HashSet<>();
    public static int max_level = 1;
    public static int max_weapon_tier = 1;
    public static int max_armor_tier = 1;
    public static int max_missile_tier = 1;

    public static void checkLocation(APLocation location) {

        if (completedChecks.contains(location)) {
            return;
        }

        completedChecks.add(location);


        APItem randItem = APItem.values()[Random.Int(APItem.values().length)];


        //TODO fix once ap side integrated
        GLog.w("[AP] " + Messages.get(APManager.class, "item_sent", randItem, "player"));

        receiveItem(randItem);
    }

    public static void receiveItem(APItem item) {

        receivedItems.add(item);

        switch (item.getCategory()) {
            case EQUIPMENT:
                switch (item.getSubcategory()) {
                    case POTION:

                        break;
                    case SCROLL:
                        //add the scroll
                        break;
                }
                break;
            case LEVEL:
                max_level++;
            default:
                GLog.i("[TODO] The item " + item + " does nothing atm, sorry!");
        }
    }

    public static boolean hasItem(APItem item) {
        return receivedItems.contains(item);
    }

    public static boolean hasEquipmentType(APItem.Subcategory cat) {
        for (APItem item : receivedItems) {
            if(item.getSubcategory() == cat) {
                return true;
            }
        }
        return false;
    }

}
