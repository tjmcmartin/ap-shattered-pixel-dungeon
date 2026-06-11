package com.shatteredpixel.shatteredpixeldungeon.ap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.ap.APItem.Subcategory;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class APManager {

    private static final Set<APLocation> completedChecks = new HashSet<>();
    private static final HashMap<APItem, Integer> receivedItems = new HashMap<>();
    private static final Queue<APItem> pendingItems = new LinkedList<>();
    private static boolean processingItems = false;
    public static int warrior_max_level = 30;
    public static int mage_max_level = 1;
    public static int rogue_max_level = 1;
    public static int huntress_max_level = 1;
    public static int duelist_max_level = 1;
    public static int cleric_max_level = 1;
    public static int max_weapon_tier = 0;
    public static int max_armor_tier = 0;
    public static int max_missile_tier = 0;
    public static int alchemy_level = 0;
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

        pendingItems.add(item);
        GLog.h("[AP] " + Messages.get(APManager.class, "item_sent", item, "player"));

    }
    public static void processPendingItems() {

        if (processingItems) return;

        processingItems = true;

        while (!pendingItems.isEmpty()) {

            APItem item = pendingItems.poll();

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
                    break;
                case EQUIPMENT:
                    Generator.Category cat = Generator.Category.valueOf(item.getSubcategory().name());
                    cat.defaultProbs[item.id] = cat.maxDefaultProbs[item.id];
                    if (cat.defaultProbs2 != null) {
                        cat.defaultProbs2[item.id] = cat.maxDefaultProbs2[item.id];
                    }
                    availableItems.get(item.getSubcategory()).add(item);
                    break;
                case EXPANDER:
                    if (item == APItem.VELVET_POUCH) {
                        new VelvetPouch().collect();
                        Dungeon.LimitedDrops.VELVET_POUCH.drop();
                    }
                    break;
                case ACTION:
                    if (item.id == 1) {
                        alchemy_level++;
                    }
                    break;
                case LEVEL:
                    switch (item.getSubcategory()) {
                        case WARRIOR:
                            warrior_max_level++;
                            break;
                        case MAGE:
                            mage_max_level++;
                            break;
                        case ROGUE:
                            rogue_max_level++;
                            break;
                        case HUNTRESS:
                            huntress_max_level++;
                            break;
                        case DUELIST:
                            duelist_max_level++;
                            break;
                        case CLERIC:
                            cleric_max_level++;
                            break;
                    }
                    Dungeon.hero.earnExp(0, APManager.class);
                    break;
                default:
                    GLog.i("[TODO] The item " + item + " does nothing atm, sorry!");
            }
        }

        processingItems = false;
    }

    public static boolean hasItem (APItem item){
        return receivedItems.containsKey(item);
    }

    public static boolean hasTalent (Talent talent) {
        return false;
    }

    public static int getMaxLevel(HeroClass heroClass) {
        Subcategory subCat = Subcategory.fromString(heroClass.name());
        if (subCat == null) {
            return 30;
        }
        switch (subCat) {
            case WARRIOR:
                return warrior_max_level;
            case MAGE:
                return mage_max_level;
            case ROGUE:
                return rogue_max_level++;
            case HUNTRESS:
                return huntress_max_level++;
            case DUELIST:
                return duelist_max_level++;
            case CLERIC:
                return cleric_max_level++;
            default:
                return 30;
        }
    }

    private static final String RECEIVED_ITEM_NAMES = "received_items_names";
    private static final String RECEIVED_ITEM_COUNTS = "received_item_counts";
    private static final String COMPLETED_CHECKS = "completed_check";
    private static final String WARRIOR_MAX_LEVEL = "warrior_max_level";
    private static final String MAGE_MAX_LEVEL = "mage_max_level";
    private static final String ROGUE_MAX_LEVEL = "rogue_max_level";
    private static final String HUNTRESS_MAX_LEVEL = "huntress_max_level";
    private static final String DUELIST_MAX_LEVEL = "duelist_max_level";
    private static final String ClERIC_MAX_LEVEL = "cleric_max_level";
    private static final String MAX_WEAPON_TIER = "max_weapon_tier";
    private static final String MAX_ARMOR_TIER = "max_armor_tier";
    private static final String MAX_MISSILE_TIER = "max_missile_tier";
    private static final String ALCHEMY_LEVEL = "alchemy_level";

    public static void store(Bundle bundle) {

        processPendingItems();

        String[] itemNames = new String[receivedItems.size()];
        int[] itemCounts = new int[receivedItems.size()];

        int i = 0;
        for (Map.Entry<APItem, Integer> entry : receivedItems.entrySet()) {
            itemNames[i] = entry.getKey().name();
            itemCounts[i] = entry.getValue();
            i++;
        }
        bundle.put(RECEIVED_ITEM_NAMES, itemNames);
        bundle.put(RECEIVED_ITEM_COUNTS, itemCounts);

        String[] locations = new String[completedChecks.size()];
        i = 0;
        for (APLocation location : completedChecks) {
            locations[i] = location.name();
            i++;
        }
        bundle.put(COMPLETED_CHECKS, locations);

        bundle.put(WARRIOR_MAX_LEVEL, warrior_max_level);
        bundle.put(MAGE_MAX_LEVEL, mage_max_level);
        bundle.put(ROGUE_MAX_LEVEL, rogue_max_level);
        bundle.put(HUNTRESS_MAX_LEVEL, huntress_max_level);
        bundle.put(DUELIST_MAX_LEVEL, duelist_max_level);
        bundle.put(ClERIC_MAX_LEVEL, cleric_max_level);

        bundle.put(MAX_WEAPON_TIER, max_weapon_tier);
        bundle.put(MAX_ARMOR_TIER, max_armor_tier);
        bundle.put(MAX_MISSILE_TIER, max_missile_tier);

        bundle.put(ALCHEMY_LEVEL, alchemy_level);

    }

    public static void restore( Bundle bundle) {

        receivedItems.clear();
        completedChecks.clear();

        String[] itemNames = bundle.getStringArray(RECEIVED_ITEM_NAMES);
        int [] itemCounts = bundle.getIntArray(RECEIVED_ITEM_COUNTS);

        for (int i = 0; i < itemNames.length; i++) {
            receivedItems.put(APItem.fromString( itemNames[i] ), itemCounts[i]);

            //TODO recreate availableItems and availableTrinkets

        }

        String[] locations = bundle.getStringArray(COMPLETED_CHECKS);

        for (String location : locations) {
            completedChecks.add(APLocation.fromString( location ));
        }

        warrior_max_level = bundle.getInt(WARRIOR_MAX_LEVEL);
        mage_max_level = bundle.getInt(MAGE_MAX_LEVEL);
        rogue_max_level = bundle.getInt(ROGUE_MAX_LEVEL);
        huntress_max_level = bundle.getInt(HUNTRESS_MAX_LEVEL);
        duelist_max_level = bundle.getInt(DUELIST_MAX_LEVEL);
        cleric_max_level = bundle.getInt(ClERIC_MAX_LEVEL);

        max_weapon_tier = bundle.getInt(MAX_WEAPON_TIER);
        max_armor_tier = bundle.getInt(MAX_ARMOR_TIER);
        max_missile_tier = bundle.getInt(MAX_MISSILE_TIER);

        alchemy_level = bundle.getInt(ALCHEMY_LEVEL);

    }
}
