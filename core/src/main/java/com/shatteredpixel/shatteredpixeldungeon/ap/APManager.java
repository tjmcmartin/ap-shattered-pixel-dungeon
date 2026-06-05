package com.shatteredpixel.shatteredpixeldungeon.ap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.shatteredpixel.shatteredpixeldungeon.ap.APItem.Subcategory;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.Random;

public class APManager {

    private static final Set<APLocation> completedChecks = new HashSet<>();
    private static final HashMap<APItem, Integer> receivedItems = new HashMap<>();
    public static int max_level = 30;
    public static int max_weapon_tier = 1;
    public static int max_armor_tier = 1;
    public static int max_missile_tier = 1;
    public static HashSet<APItem> availableTrinkets = new HashSet<>();
    public static Map<Subcategory, HashSet<APItem>> availableItems = new HashMap<>();

    static {
        for (Subcategory cat : Subcategory.values()) {
            availableItems.put(cat, new HashSet<>());
        }
    }

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

        if (receivedItems.containsKey(item)) {
            receivedItems.put(item, receivedItems.get(item) + 1);
        } else {
            receivedItems.put(item, 1);
        }

        switch (item.getCategory()) {
            case TRINKET:
                availableTrinkets.add(item);
                break;
            case WEAPONRY:
                switch (item.id) {
                    case 0:
                        max_weapon_tier++;
                    case 1:
                        max_armor_tier++;
                    case 2:
                        max_missile_tier++;
                }
            case EQUIPMENT:
                Generator.Category cat = Generator.Category.valueOf(item.getSubcategory().name());
                cat.defaultProbs[item.id] = cat.maxDefaultProbs[item.id];
                if (cat.defaultProbs2 != null) {
                    cat.defaultProbs2[item.id] = cat.maxDefaultProbs2[item.id];
                }
                GLog.i("[AP] Probability of " + cat.classes[item.id].getName() + " because of item " + item.name());
                availableItems.get(item.getSubcategory()).add(item);
                break;
            case EXPANDER:
                if (item == APItem.VELVET_POUCH) {
                    new VelvetPouch().collect();
                    Dungeon.LimitedDrops.VELVET_POUCH.drop();
                }
                break;
            case LEVEL:
                max_level++;
                Dungeon.hero.earnExp(0, APManager.class);
                break;
            default:
                GLog.i("[TODO] The item " + item + " does nothing atm, sorry!");
        }
    }

    public static boolean hasItem(APItem item) {
        return receivedItems.containsKey(item);
    }

}
