package com.shatteredpixel.shatteredpixeldungeon.ap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.shatteredpixel.shatteredpixeldungeon.APDataSaver;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.ap.APItem.Subcategory;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

public class APManager {

    private static final EnumSet<APLocation> completedChecks = EnumSet.noneOf(APLocation.class);
    public static int checksCount = 0;
    public static int totalChecks;
    private static final EnumMap<APItem, Integer> receivedItems = new EnumMap<>(APItem.class);
    private static final Queue<APItem> pendingItems = new LinkedList<>();
    private static boolean processingItems = false;
    public static int wins;
    public static int requiredWins;

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
    public static EnumSet<APItem> availableTrinkets = EnumSet.noneOf(APItem.class);
    public static EnumMap<Subcategory, EnumSet<APItem>> availableItems = new EnumMap<>(Subcategory.class);
    public static EnumMap<Subcategory, EnumSet<APItem>> availableSubclasses = new EnumMap<>(Subcategory.class);
    public static EnumMap<Subcategory, EnumSet<APItem>> availableArmorAbilities = new EnumMap<>(Subcategory.class);
    public static EnumMap<APLocation.shopLocationType, List<Integer>> shopLocations = new EnumMap<>(APLocation.shopLocationType.class);
    public static EnumMap<APLocation.lootLocationType, List<Integer>> lootLocations = new EnumMap<>(APLocation.lootLocationType.class);
    public static EnumMap<HeroClass, Integer> kills = new EnumMap<>(HeroClass.class);
    public static EnumMap<HeroClass, Integer> levelClears = new EnumMap<>(HeroClass.class);
    public static EnumMap<HeroClass, Map<String, Integer>> regionLoot = new EnumMap<>(HeroClass.class);
    public static EnumMap<HeroClass, Map<String, Integer>> regionShops = new EnumMap<>(HeroClass.class);

    public static void reset() {

        receivedItems.clear();
        completedChecks.clear();
        checksCount = 0;

        wins = 0;

        warrior_max_level = 30;
        mage_max_level = 1;
        rogue_max_level = 1;
        huntress_max_level = 1;
        duelist_max_level = 1;
        cleric_max_level = 1;
        max_weapon_tier = 0;
        max_armor_tier = 0;
        max_missile_tier = 0;
        alchemy_level = 0;

        availableItems.clear();
        for (Subcategory cat : Subcategory.values()) {
            availableItems.put(cat, EnumSet.noneOf(APItem.class));
            availableSubclasses.put(cat, EnumSet.noneOf(APItem.class));
            availableArmorAbilities.put(cat, EnumSet.noneOf(APItem.class));
        }

        shopLocations.clear();
        for (APLocation.shopLocationType region : APLocation.shopLocationType.values()) {
            shopLocations.put(region, new ArrayList<>());
            for (int i = 0; i < region.total - 1; i++) {
                shopLocations.get(region).add(region.startApid + i);
            }
            Collections.shuffle(shopLocations.get(region));
        }

        lootLocations.clear();
        for (APLocation.lootLocationType region : APLocation.lootLocationType.values()) {
            lootLocations.put(region, new ArrayList<>());
            for (int i = 0; i < region.total; i++) {
                lootLocations.get(region).add(region.startApid + i);
            }
            Collections.shuffle(lootLocations.get(region));
        }

        kills.clear();
        levelClears.clear();
        for (Map<String, Integer> map : regionLoot.values()) {
            map.clear();
        }
        regionLoot.clear();
        for (Map<String, Integer> map : regionShops.values()) {
            map.clear();
        }
        regionShops.clear();
        for (HeroClass clazz : HeroClass.values()) {
            kills.put(clazz, 0);
            levelClears.put(clazz, 0);
            regionLoot.put(clazz, new HashMap<>());
            regionShops.put(clazz, new HashMap<>());

            for (Dungeon.Region region : Dungeon.Region.values()) {
                regionLoot.get(clazz).put(region.name(), 0);
                regionShops.get(clazz).put(region.name(), 0);
            }
        }
    }

    public static void checkLocation(APLocation location) {
        checkLocation(location, false);
    }
    public static void checkLocation(APLocation location, boolean statUpdateNeeded) {

        if (completedChecks.contains(location)) {
            return;
        }

        completedChecks.add(location);
        checksCount++;

        if(statUpdateNeeded) {
            String name = location.name().toLowerCase();
            String region = Dungeon.Region.byDepth().name();
            HeroClass heroClass = Dungeon.hero.heroClass;
            if ( name.contains("room") || name.contains("item") ) {
                regionLoot.get(heroClass).put(region, regionLoot.get(heroClass).get(region) + 1);
            } else if ( name.contains("shop") && !name.contains("global") ) {
                regionShops.get(heroClass).put(region, regionShops.get(heroClass).get(region) + 1);
            } else {
                GLog.n( "[WARNING] Stat for " + location.name() + " not stored properly");
            }
        }


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
                    Generator.Category trinket = Generator.Category.TRINKET;
                    trinket.defaultProbs[item.id] = trinket.maxDefaultProbs[item.id];
                    break;
                case SUBCLASS:
                    availableSubclasses.get(item.getSubcategory()).add(item);
                    break;
                case ARMOR_ABILITY:
                    availableArmorAbilities.get(item.getSubcategory()).add(item);
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
                case TRAP:
                    switch (item.getSubcategory()) {
                        case DEBUFF:
                            if (item.debuff.equals(Poison.class)) {
                                int duration = Math.max( 4, Random.IntRange(2, 3) + Dungeon.depth/3 );
                                Buff.affect(Dungeon.hero, (Class<Poison>) item.debuff).set( duration );
                            } else {
                                Buff.affect(Dungeon.hero, item.debuff);
                            }

                            break;
                        case TRAP_EFFECT:
                            try {
                                Reflection.newInstance(item.trap).set(Dungeon.hero.pos).activate();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                    }
                    break;
                default:
                    GLog.i("[TODO] The item " + item + " does nothing atm, sorry!");
            }
        }

        processingItems = false;
    }

    public static boolean isUnchecked(APLocation loc) {
        return !completedChecks.contains(loc);
    }

    public static boolean hasItem (APItem item){
        return receivedItems.containsKey(item);
    }

    public static boolean hasTalent (Talent talent) {
        APItem item = APItem.fromString( talent.name().toUpperCase() );
        return hasItem(item);
    }

    public static boolean hasSubclasses(Hero hero) {
        Subcategory subCat = Subcategory.fromString( hero.heroClass.name().toUpperCase() );
        return !availableSubclasses.get(subCat).isEmpty();
    }

    public static boolean hasSubclass(HeroSubClass subCls) {
        APItem item = APItem.fromString( subCls.name().toUpperCase() );
        return hasItem(item);
    }

    public static ArrayList<HeroSubClass> getSubclasses(Hero hero) {
        HeroSubClass[] heroSubClasses = hero.heroClass.subClasses();
        ArrayList<HeroSubClass> unlockedSubclasses = new ArrayList<>();
        for (HeroSubClass subClass : heroSubClasses) {
            if (hasSubclass(subClass)) {
                unlockedSubclasses.add(subClass);
            }
        }

        return unlockedSubclasses;
    }

    public static boolean hasArmorAbilities(Hero hero) {
        Subcategory subCat = Subcategory.fromString( hero.heroClass.name().toUpperCase() );
        return !availableArmorAbilities.get(subCat).isEmpty();
    }

    public static boolean hasArmorAbility(ArmorAbility abil) {
        APItem item = APItem.fromString( abil.name().toUpperCase() );
        return hasItem(item);
    }

    public static ArrayList<ArmorAbility> getArmorAbilities(Hero hero) {
        ArmorAbility[] armorAbilities = hero.heroClass.armorAbilities();
        ArrayList<ArmorAbility> unlockedArmorAbilities = new ArrayList<>();
        for (ArmorAbility abil : armorAbilities) {
            if (hasArmorAbility(abil)) {
                unlockedArmorAbilities.add(abil);
            }
        }
        return unlockedArmorAbilities;
    }

    public static boolean hasEquipmentType(Generator.Category genCat) {
        APItem.Subcategory cat = APItem.Subcategory.fromString( genCat.name() );
        return !availableItems.get(cat).isEmpty();
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

    public static APLocation getNextShopLocation(int depth) {
        List<Integer> locations = shopLocations.get( APLocation.shopLocationType.byDepth(depth) );
        return APLocation.fromId( locations.remove(locations.size()-1) );
    }

    public static APLocation getNextLootLocation(int depth) {
        List<Integer> locations = lootLocations.get( APLocation.lootLocationType.byDepth(depth) );
        return APLocation.fromId( locations.remove(locations.size()-1) );
    }

    public static String getKills(HeroClass hero) {
        return kills.get(hero).toString();
    }

    public static String getClears(HeroClass hero) {
        return levelClears.get(hero).toString();
    }

    public static String getRegionLootStat(HeroClass hero, String region) {
        return regionLoot.get(hero).get(region).toString();
    }

    public static String getRegionShopStat(HeroClass hero, String region) {
        return regionShops.get(hero).get(region).toString();
    }

    private static final String RECEIVED_ITEM_NAMES = "received_items_names";
    private static final String RECEIVED_ITEM_COUNTS = "received_item_counts";
    private static final String COMPLETED_CHECKS = "completed_check";
    private static final String CHECKS_COUNT = "checks_count";
    private static final String TOTAL_CHECKS = "total_checks";

    private static final String PLAYER_NAME = "player_name";
    private static final String PORT = "port";
    private static final String WINS = "wins";
    private static final String REQUIRED_WINS = "required_wins";

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

    private static final String KILL_KEYS = "kill_keys";
    private static final String KILL_VALUES = "kill_values";
    private static final String LEVEL_CLEAR_KEYS = "level_clear_keys";
    private static final String LEVEL_CLEAR_VALUES = "level_clear_values";
    private static final String REGION_LOOT_KEYS = "region_loot_keys";
    private static final String REGION_LOOT_REGIONS = "region_loot_regions";
    private static final String REGION_LOOT_VALUES = "region_loot_values";
    private static final String REGION_SHOP_KEYS = "region_shop_keys";
    private static final String REGION_SHOP_REGIONS = "region_shop_regions";
    private static final String REGION_SHOP_VALUES = "region_shop_values";


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
        bundle.put(CHECKS_COUNT, checksCount);
        bundle.put(TOTAL_CHECKS, totalChecks);

        bundle.put(WINS, wins);
        bundle.put(REQUIRED_WINS, requiredWins);

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

        String[] killKeys = new String[kills.size()];
        int[] killValues = new int[kills.size()];

        i = 0;
        for (Map.Entry<HeroClass, Integer> entry : kills.entrySet()) {
            killKeys[i] = entry.getKey().name();
            killValues[i] = entry.getValue();
            i++;
        }
        bundle.put(KILL_KEYS, killKeys);
        bundle.put(KILL_VALUES, killValues);

        String[] levelClearKeys = new String[levelClears.size()];
        int[] levelClearValues = new int[levelClears.size()];

        i = 0;
        for (Map.Entry<HeroClass, Integer> entry : levelClears.entrySet()) {
            levelClearKeys[i] = entry.getKey().name();
            levelClearValues[i] = entry.getValue();
            i++;
        }
        bundle.put(LEVEL_CLEAR_KEYS, levelClearKeys);
        bundle.put(LEVEL_CLEAR_VALUES, levelClearValues);

        String[] regionLootKeys = new String[regionLoot.size()];
        String[] regionLootRegions = new String[Dungeon.Region.values().length];
        int[] regionLootValues = new int[Dungeon.Region.values().length];

        i = 0;
        int j;
        for (Map.Entry<HeroClass, Map<String, Integer>> outerEntry : regionLoot.entrySet()) {
            j = 0;
            for (Map.Entry<String, Integer> entry : outerEntry.getValue().entrySet()) {
                regionLootRegions[j] = entry.getKey();
                regionLootValues[j] = entry.getValue();
                j++;
            }
            regionLootKeys[i] = outerEntry.getKey().name();
            i++;
        }
        bundle.put(REGION_LOOT_KEYS, regionLootKeys);
        bundle.put(REGION_LOOT_REGIONS, regionLootRegions);
        bundle.put(REGION_LOOT_VALUES, regionLootValues);

        String[] regionShopKeys = new String[regionShops.size()];
        String[] regionShopRegions = new String[Dungeon.Region.values().length];
        int[] regionShopValues = new int[Dungeon.Region.values().length];

        i = 0;
        for (Map.Entry<HeroClass, Map<String, Integer>> outerEntry : regionShops.entrySet()) {
            j = 0;
            for (Map.Entry<String, Integer> entry : outerEntry.getValue().entrySet()) {
                regionShopRegions[j] = entry.getKey();
                regionShopValues[j] = entry.getValue();
                j++;
            }
            regionShopKeys[i] = outerEntry.getKey().name();
            i++;
        }
        bundle.put(REGION_SHOP_KEYS, regionShopKeys);
        bundle.put(REGION_SHOP_REGIONS, regionShopRegions);
        bundle.put(REGION_SHOP_VALUES, regionShopValues);

    }

    public static void preview(APDataSaver.Info info, Bundle bundle) {

        info.checkedLocations = bundle.getInt(CHECKS_COUNT);
        info.totalLocations = bundle.getInt(TOTAL_CHECKS);

        info.wins = bundle.getInt(WINS);
        info.requiredWins = bundle.getInt(REQUIRED_WINS);
    }

    public static void restore( Bundle bundle) {

        reset();

        String[] itemNames = bundle.getStringArray(RECEIVED_ITEM_NAMES);
        int [] itemCounts = bundle.getIntArray(RECEIVED_ITEM_COUNTS);

        for (int i = 0; i < itemNames.length; i++) {
            APItem item = APItem.fromString( itemNames[i] );
            receivedItems.put(item, itemCounts[i]);

            switch (item.getCategory()) {
                case TRINKET:
                    availableTrinkets.add(item);
                    break;
                case EQUIPMENT:
                    Generator.Category cat = Generator.Category.valueOf(item.getSubcategory().name());
                    cat.defaultProbs[item.id] = cat.maxDefaultProbs[item.id];
                    if (cat.defaultProbs2 != null) {
                        cat.defaultProbs2[item.id] = cat.maxDefaultProbs2[item.id];
                    }
                    availableItems.get(item.getSubcategory()).add(item);
                    break;
            }
        }

        String[] locations = bundle.getStringArray(COMPLETED_CHECKS);

        for (String location : locations) {
            completedChecks.add(APLocation.fromString( location ));
        }
        checksCount = bundle.getInt(CHECKS_COUNT);
        totalChecks = bundle.getInt(TOTAL_CHECKS);

        wins = bundle.getInt(WINS);
        requiredWins = bundle.getInt(REQUIRED_WINS);

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

        String[] killKeys = bundle.getStringArray(KILL_KEYS);
        int[] killValues = bundle.getIntArray(KILL_VALUES);

        for(int i = 0; i <killKeys.length; i++) {
            kills.put( HeroClass.valueOf(killKeys[i]) , killValues[i] );
        }

        String[] levelClearKeys = bundle.getStringArray(LEVEL_CLEAR_KEYS);
        int[] levelClearValues = bundle.getIntArray(LEVEL_CLEAR_VALUES);

        for (int i = 0; i < levelClearKeys.length; i++) {
            levelClears.put( HeroClass.valueOf(levelClearKeys[i]) , levelClearValues[i] );
        }

        String[] regionLootKeys = bundle.getStringArray(REGION_LOOT_KEYS);
        String[] regionLootRegions = bundle.getStringArray(REGION_LOOT_REGIONS);
        int[] regionLootValues = bundle.getIntArray(REGION_LOOT_VALUES);

        for (String key : regionLootKeys) {
            HeroClass clazz = HeroClass.valueOf(key);
            regionLoot.put(clazz, new HashMap<>());
            for (int i = 0; i < regionLootRegions.length; i++) {
                regionLoot.get(clazz).put(regionLootRegions[i], regionLootValues[i]);
            }
        }

        String[] regionShopKeys = bundle.getStringArray(REGION_SHOP_KEYS);
        String[] regionShopRegions = bundle.getStringArray(REGION_SHOP_REGIONS);
        int[] regionShopValues = bundle.getIntArray(REGION_SHOP_VALUES);

        for (String key : regionShopKeys) {
            HeroClass clazz = HeroClass.valueOf(key);
            regionLoot.put(clazz, new HashMap<>());
            for (int i = 0; i < regionShopRegions.length; i++) {
                regionLoot.get(clazz).put(regionShopRegions[i], regionShopValues[i]);
            }
        }

    }
}
