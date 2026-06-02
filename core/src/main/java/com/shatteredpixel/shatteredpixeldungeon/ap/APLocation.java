package com.shatteredpixel.shatteredpixeldungeon.ap;

public enum APLocation {
    DEFEAT_Rat,
    DEFEAT_Snake,
    DEFEAT_Gnoll,
    DEFEAT_Swarm,
    DEFEAT_Crab,
    DEFEAT_Slime,
    DEFEAT_Skeleton,
    DEFEAT_Thief,
    DEFEAT_DM100,
    DEFEAT_Guard,
    DEFEAT_Necromancer,
    DEFEAT_Bat,
    DEFEAT_Brute,
    DEFEAT_Shaman,
    DEFEAT_Spinner,
    DEFEAT_DM200,
    DEFEAT_Ghoul,
    DEFEAT_Elemental,
    DEFEAT_Warlock,
    DEFEAT_Monk,
    DEFEAT_Golem,
    DEFEAT_RipperDemon,
    DEFEAT_DemonSpawner,
    DEFEAT_Succubus,
    DEFEAT_Eye,
    DEFEAT_Scorpio,
    CLEAR_FLOOR_1,
    CLEAR_FLOOR_2,
    CLEAR_FLOOR_3,
    CLEAR_FLOOR_4,
    CLEAR_FLOOR_5,
    CLEAR_FLOOR_6,
    CLEAR_FLOOR_7,
    CLEAR_FLOOR_8,
    CLEAR_FLOOR_9,
    CLEAR_FLOOR_10,
    CLEAR_FLOOR_11,
    CLEAR_FLOOR_12,
    CLEAR_FLOOR_13,
    CLEAR_FLOOR_14,
    CLEAR_FLOOR_15,
    CLEAR_FLOOR_16,
    CLEAR_FLOOR_17,
    CLEAR_FLOOR_18,
    CLEAR_FLOOR_19,
    CLEAR_FLOOR_20,
    CLEAR_FLOOR_21,
    CLEAR_FLOOR_22,
    CLEAR_FLOOR_23,
    CLEAR_FLOOR_24,
    CLEAR_FLOOR_25;

    public static APLocation fromString(String name) {
        for (APLocation location : values()) {
            if (location.name().equals(name)) {
                return location;
            }
        }
        return null;
    }
}
