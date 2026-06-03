package com.shatteredpixel.shatteredpixeldungeon.ap;

import java.util.List;
import java.util.ArrayList;

public enum APItem {

    WARRIOR (Category.CHARACTER, null, 0),
    MAGE (Category.CHARACTER, null, 1),
    ROGUE (Category.CHARACTER, null, 2),
    HUNTRESS (Category.CHARACTER, null, 3),
    DUELIST (Category.CHARACTER, null, 4),
    CLERIC (Category.CHARACTER, null, 5),
    BERSERKER (Category.SUBCLASS, null, 0),
    GLADIATOR (Category.SUBCLASS, null, 1),
    BATTLEMAGE (Category.SUBCLASS, null, 2),
    WARLOCK (Category.SUBCLASS, null, 3),
    ASSASSIN (Category.SUBCLASS, null, 4),
    FREERUNNER (Category.SUBCLASS, null, 5),
    SNIPER (Category.SUBCLASS, null, 6),
    WARDEN (Category.SUBCLASS, null, 7),
    CHAMPION (Category.SUBCLASS, null, 8),
    MONK (Category.SUBCLASS, null, 9),
    PRIEST (Category.SUBCLASS, null, 10),
    PALADIN (Category.SUBCLASS, null, 11),
    HEROIC_LEAP (Category.ARMOR_ABILITY, null, 0),
    SHOCKWAVE (Category.ARMOR_ABILITY, null, 1),
    ENDURE (Category.ARMOR_ABILITY, null, 2),
    ELEMENTAL_BLAST (Category.ARMOR_ABILITY, null, 3),
    WILD_MAGIC (Category.ARMOR_ABILITY, null, 4),
    WARPING_BEACON (Category.ARMOR_ABILITY, null, 5),
    SMOKE_BOMB (Category.ARMOR_ABILITY, null, 6),
    DEATH_MARK (Category.ARMOR_ABILITY, null, 7),
    SHADOW_CLONE (Category.ARMOR_ABILITY, null, 8),
    SPECTRAL_BLADES (Category.ARMOR_ABILITY, null, 9),
    NATURES_POWER (Category.ARMOR_ABILITY, null, 10),
    SPIRIT_HAWK (Category.ARMOR_ABILITY, null, 11),
    CHALLENGE (Category.ARMOR_ABILITY, null, 12),
    ELEMENTAL_STRIKE (Category.ARMOR_ABILITY, null, 13),
    FEINT (Category.ARMOR_ABILITY, null, 14),
    ASCENDED_FORM (Category.ARMOR_ABILITY, null, 15),
    TRINITY (Category.ARMOR_ABILITY, null, 16),
    POWER_OF_MANY (Category.ARMOR_ABILITY, null, 17),
    PROGRESSIVE_WEAPON (Category.EQUIPMENT, Subcategory.WEAPONRY, 0),
    PROGRESSIVE_ARMOR (Category.EQUIPMENT, Subcategory.WEAPONRY, 1),
    PROGRESSIVE_MISSILE (Category.EQUIPMENT, Subcategory.WEAPONRY, 2),
    ALCHEMISTS_TOOLKIT (Category.EQUIPMENT, Subcategory.ARTIFACT, 0),
    CHALICE_OF_BLOOD (Category.EQUIPMENT, Subcategory.ARTIFACT, 1),
    DRIED_ROSE (Category.EQUIPMENT, Subcategory.ARTIFACT, 3),
    ETHEREAL_CHAINS (Category.EQUIPMENT, Subcategory.ARTIFACT, 4),
    HORN_OF_PLENTY (Category.EQUIPMENT, Subcategory.ARTIFACT, 6),
    MASTER_THIEVES_ARMBAND (Category.EQUIPMENT, Subcategory.ARTIFACT, 7),
    SANDALS_OF_NATURE (Category.EQUIPMENT, Subcategory.ARTIFACT, 8),
    SKELETON_KEY (Category.EQUIPMENT, Subcategory.ARTIFACT, 9),
    TALISMAN_OF_FORESIGHT (Category.EQUIPMENT, Subcategory.ARTIFACT, 10),
    TIMEKEEPERS_HOURGLASS (Category.EQUIPMENT, Subcategory.ARTIFACT, 11),
    UNSTABLE_SPELLBOOK (Category.EQUIPMENT, Subcategory.ARTIFACT, 12),
    WAND_OF_MAGIC_MISSILE (Category.EQUIPMENT, Subcategory.WAND, 0),
    WAND_OF_LIGHTNING (Category.EQUIPMENT, Subcategory.WAND, 1),
    WAND_OF_DISINTEGRATION (Category.EQUIPMENT, Subcategory.WAND, 2),
    WAND_OF_FIREBLAST (Category.EQUIPMENT, Subcategory.WAND, 3),
    WAND_OF_CORROSION (Category.EQUIPMENT, Subcategory.WAND, 4),
    WAND_OF_BLAST_WAVE (Category.EQUIPMENT, Subcategory.WAND, 5),
    WAND_OF_LIVING_EARTH (Category.EQUIPMENT, Subcategory.WAND, 6),
    WAND_OF_FROST (Category.EQUIPMENT, Subcategory.WAND, 7),
    WAND_OF_PRISMATIC_LIGHT (Category.EQUIPMENT, Subcategory.WAND, 8),
    WAND_OF_WARDING (Category.EQUIPMENT, Subcategory.WAND, 9),
    WAND_OF_TRANSFUSION (Category.EQUIPMENT, Subcategory.WAND, 10),
    WAND_OF_CORRUPTION (Category.EQUIPMENT, Subcategory.WAND, 11),
    WAND_OF_REGROWTH (Category.EQUIPMENT, Subcategory.WAND, 12),
    RING_OF_ACCURACY (Category.EQUIPMENT, Subcategory.RING, 0),
    RING_OF_ARCANA (Category.EQUIPMENT, Subcategory.RING, 1),
    RING_OF_ELEMENTS (Category.EQUIPMENT, Subcategory.RING, 2),
    RING_OF_ENERGY (Category.EQUIPMENT, Subcategory.RING, 3),
    RING_OF_EVASION (Category.EQUIPMENT, Subcategory.RING, 4),
    RING_OF_FORCE (Category.EQUIPMENT, Subcategory.RING, 5),
    RING_OF_FUROR (Category.EQUIPMENT, Subcategory.RING, 6),
    RING_OF_HASTE (Category.EQUIPMENT, Subcategory.RING, 7),
    RING_OF_MIGHT (Category.EQUIPMENT, Subcategory.RING, 8),
    RING_OF_SHARPSHOOTING (Category.EQUIPMENT, Subcategory.RING, 9),
    RING_OF_TENACITY (Category.EQUIPMENT, Subcategory.RING, 10),
    RING_OF_WEALTH (Category.EQUIPMENT, Subcategory.RING, 11),
    POTION_OF_STRENGTH (Category.EQUIPMENT, Subcategory.POTION, 0),
    POTION_OF_HEALING (Category.EQUIPMENT, Subcategory.POTION, 1),
    POTION_OF_MIND_VISION (Category.EQUIPMENT, Subcategory.POTION, 2),
    POTION_OF_FROST (Category.EQUIPMENT, Subcategory.POTION, 3),
    POTION_OF_LIQUID_FLAME (Category.EQUIPMENT, Subcategory.POTION, 4),
    POTION_OF_TOXIC_GAS (Category.EQUIPMENT, Subcategory.POTION, 5),
    POTION_OF_HASTE (Category.EQUIPMENT, Subcategory.POTION, 6),
    POTION_OF_INVISIBILITY (Category.EQUIPMENT, Subcategory.POTION, 7),
    POTION_OF_LEVITATION (Category.EQUIPMENT, Subcategory.POTION, 8),
    POTION_OF_PARALYTIC_GAS (Category.EQUIPMENT, Subcategory.POTION, 9),
    POTION_OF_PURITY (Category.EQUIPMENT, Subcategory.POTION, 10),
    POTION_OF_EXPERIENCE (Category.EQUIPMENT, Subcategory.POTION, 11),
    SCROLL_OF_UPGRADE (Category.EQUIPMENT, Subcategory.SCROLL, 0),
    SCROLL_OF_IDENTIFY (Category.EQUIPMENT, Subcategory.SCROLL, 1),
    SCROLL_OF_REMOVE_CURSE (Category.EQUIPMENT, Subcategory.SCROLL, 2),
    SCROLL_OF_MIRROR_IMAGE (Category.EQUIPMENT, Subcategory.SCROLL, 3),
    SCROLL_OF_RECHARGING (Category.EQUIPMENT, Subcategory.SCROLL, 4),
    SCROLL_OF_TELEPORTATION (Category.EQUIPMENT, Subcategory.SCROLL, 5),
    SCROLL_OF_LULLABY (Category.EQUIPMENT, Subcategory.SCROLL, 6),
    SCROLL_OF_MAGIC_MAPPING (Category.EQUIPMENT, Subcategory.SCROLL, 7),
    SCROLL_OF_RAGE (Category.EQUIPMENT, Subcategory.SCROLL, 8),
    SCROLL_OF_RETRIBUTION (Category.EQUIPMENT, Subcategory.SCROLL, 9),
    SCROLL_OF_TERROR (Category.EQUIPMENT, Subcategory.SCROLL, 10),
    SCROLL_OF_TRANSMUTATION (Category.EQUIPMENT, Subcategory.SCROLL, 11),
    ARCANE_STYLUS (Category.EQUIPMENT, Subcategory.MISC, 0),
    TORCHES (Category.EQUIPMENT, Subcategory.MISC, 3),
    WATERSKIN (Category.EQUIPMENT, Subcategory.MISC, 2),
    RAT_SKULL (Category.TRINKET, null, 0),
    PARCHMENT_SCRAP (Category.TRINKET, null, 1),
    PETRIFIED_SEED (Category.TRINKET, null, 2),
    EXOTIC_CRYSTALS (Category.TRINKET, null, 3),
    MOSSY_CLUMP (Category.TRINKET, null, 4),
    DIMENSIONAL_SUNDIAL (Category.TRINKET, null, 5),
    THIRTEEN_LEAF_CLOVER (Category.TRINKET, null, 6),
    TRAP_MECHANISM (Category.TRINKET, null, 7),
    MIMIC_TOOTH (Category.TRINKET, null, 8),
    WONDROUS_RESIN (Category.TRINKET, null, 9),
    EYE_OF_NEWT (Category.TRINKET, null, 10),
    SALT_CUBE (Category.TRINKET, null, 11),
    VIAL_OF_BLOOD (Category.TRINKET, null, 12),
    SHARD_OF_OBLIVION (Category.TRINKET, null, 13),
    CHAOTIC_CENSER (Category.TRINKET, null, 14),
    FERRET_TUFT (Category.TRINKET, null, 15),
    CRACKED_SPYGLASS (Category.TRINKET, null, 16),
    VELVET_POUCH (Category.EXPANDER, null, 0),
    POTION_BANDOLIER (Category.EXPANDER, null, 1),
    SCROLL_HOLDER (Category.EXPANDER, null, 2),
    MAGICAL_HOLSTER (Category.EXPANDER, null, 3),
    PROGRESSIVE_MAX_LEVEL (Category.LEVEL, null, 0),
    IRON_KEY (Category.KEY, null, 0),
    GOLD_KEY (Category.KEY, null, 1),
    CRYSTAL_KEY (Category.KEY, null, 2);

    public enum Category {
        CHARACTER,
        SUBCLASS,
        ARMOR_ABILITY,
        EQUIPMENT,
        TRINKET,
        KEY,
        EXPANDER,
        SKILL,
        LEVEL,
        ACTION;

        public static Category fromString(String name) {
            for (Category cat : values()) {
                if (cat.name().equals(name)) {
                    return cat;
                }
            }
            return null;
        }
    }

    public enum Subcategory {
        WEAPONRY,
        ARTIFACT,
        WAND,
        RING,
        POTION,
        SCROLL,
        MISC;

        public static Subcategory fromString(String name) {
            for (Subcategory cat : values()) {
                if (cat.name().equals(name)) {
                    return cat;
                }
            }
            return null;
        }

    }

    private final Category category;
    private final Subcategory subcategory;
    public final int id;

    APItem(Category category, Subcategory subcategory, int id) {
        this.category = category;
        this.subcategory = subcategory;
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public static APItem fromString(String name) {
        for (APItem item : values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public static List<APItem> getByCategory(Category category) {
        List<APItem> result = new ArrayList<>();

        for (APItem item : values()) {
            if (item.category == category) {
                result.add(item);
            }
        }

        return result;
    }

    public static List<APItem> getBySubcategory(Subcategory subcategory) {
        List<APItem> result = new ArrayList<>();

        for (APItem item : values()) {
            if (item.subcategory == subcategory) {
                result.add(item);
            }
        }

        return result;
    }

    public static String classToEnum(String className) {

        return className
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toUpperCase();
    }

}
