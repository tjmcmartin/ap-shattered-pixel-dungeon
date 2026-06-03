package com.shatteredpixel.shatteredpixeldungeon.ap;

import java.util.List;
import java.util.ArrayList;

public enum APItem {

    WARRIOR (Category.CHARACTER, null),
    MAGE (Category.CHARACTER, null),
    ROGUE (Category.CHARACTER, null),
    HUNTRESS (Category.CHARACTER, null),
    DUELIST (Category.CHARACTER, null),
    CLERIC (Category.CHARACTER, null),
    BERSERKER (Category.SUBCLASS, null),
    GLADIATOR (Category.SUBCLASS, null),
    BATTLEMAGE (Category.SUBCLASS, null),
    WARLOCK (Category.SUBCLASS, null),
    ASSASSIN (Category.SUBCLASS, null),
    FREERUNNER (Category.SUBCLASS, null),
    SNIPER (Category.SUBCLASS, null),
    WARDEN (Category.SUBCLASS, null),
    CHAMPION (Category.SUBCLASS, null),
    MONK (Category.SUBCLASS, null),
    PRIEST (Category.SUBCLASS, null),
    PALADIN (Category.SUBCLASS, null),
    HEROIC_LEAP (Category.ARMOR_ABILITY, null),
    SHOCKWAVE (Category.ARMOR_ABILITY, null),
    ENDURE (Category.ARMOR_ABILITY, null),
    ELEMENTAL_BLAST (Category.ARMOR_ABILITY, null),
    WILD_MAGIC (Category.ARMOR_ABILITY, null),
    WARPING_BEACON (Category.ARMOR_ABILITY, null),
    SMOKE_BOMB (Category.ARMOR_ABILITY, null),
    DEATH_MARK (Category.ARMOR_ABILITY, null),
    SHADOW_CLONE (Category.ARMOR_ABILITY, null),
    SPECTRAL_BLADES (Category.ARMOR_ABILITY, null),
    NATURES_POWER (Category.ARMOR_ABILITY, null),
    SPIRIT_HAWK (Category.ARMOR_ABILITY, null),
    CHALLENGE (Category.ARMOR_ABILITY, null),
    ELEMENTAL_STRIKE (Category.ARMOR_ABILITY, null),
    FEINT (Category.ARMOR_ABILITY, null),
    ASCENDED_FORM (Category.ARMOR_ABILITY, null),
    TRINITY (Category.ARMOR_ABILITY, null),
    POWER_OF_MANY (Category.ARMOR_ABILITY, null),
    PROGRESSIVE_WEAPON (Category.EQUIPMENT, Subcategory.WEAPONRY),
    PROGRESSIVE_ARMOR (Category.EQUIPMENT, Subcategory.WEAPONRY),
    PROGRESSIVE_MISSILE (Category.EQUIPMENT, Subcategory.WEAPONRY),
    ALCHEMISTS_TOOLKIT (Category.EQUIPMENT, Subcategory.ARTIFACT),
    CHALICE_OF_BLOOD (Category.EQUIPMENT, Subcategory.ARTIFACT),
    DRIED_ROSE (Category.EQUIPMENT, Subcategory.ARTIFACT),
    ETHEREAL_CHAINS (Category.EQUIPMENT, Subcategory.ARTIFACT),
    HORN_OF_PLENTY (Category.EQUIPMENT, Subcategory.ARTIFACT),
    MASTER_THIEVES_ARMBAND (Category.EQUIPMENT, Subcategory.ARTIFACT),
    SANDALS_OF_NATURE (Category.EQUIPMENT, Subcategory.ARTIFACT),
    SKELETON_KEY (Category.EQUIPMENT, Subcategory.ARTIFACT),
    TALISMAN_OF_FORESIGHT (Category.EQUIPMENT, Subcategory.ARTIFACT),
    TIMEKEEPERS_HOURGLASS (Category.EQUIPMENT, Subcategory.ARTIFACT),
    UNSTABLE_SPELLBOOK (Category.EQUIPMENT, Subcategory.ARTIFACT),
    WAND_OF_MAGIC_MISSILE (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_LIGHTNING (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_DISINTEGRATION (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_FIREBLAST (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_CORROSION (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_BLAST_WAVE (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_LIVING_EARTH (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_FROST (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_PRISMATIC_LIGHT (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_WARDING (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_TRANSFUSION (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_CORRUPTION (Category.EQUIPMENT, Subcategory.WAND),
    WAND_OF_REGROWTH (Category.EQUIPMENT, Subcategory.WAND),
    RING_OF_ACCURACY (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_ARCANA (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_ELEMENTS (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_ENERGY (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_EVASION (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_FORCE (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_FUROR (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_HASTE (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_STRENGTH (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_SHARPSHOOTING (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_TENACITY (Category.EQUIPMENT, Subcategory.RING),
    RING_OF_WEALTH (Category.EQUIPMENT, Subcategory.RING),
    POTION_OF_STRENGTH (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_HEALING (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_MIND_VISION (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_FROST (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_LIQUID_FLAME (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_TOXIC_GAS (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_HASTE (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_INVISIBILITY (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_LEVITATION (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_PARALYTIC_GAS (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_PURITY (Category.EQUIPMENT, Subcategory.POTION),
    POTION_OF_EXPERIENCE (Category.EQUIPMENT, Subcategory.POTION),
    SCROLL_OF_UPGRADE (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_IDENTIFY (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_REMOVE_CURSE (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_MIRROR_IMAGE (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_RECHARGING (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_TELEPORTATION (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_LULLABY (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_MAGIC_MAPPING (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_RAGE (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_RETRIBUTION (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_TERROR (Category.EQUIPMENT, Subcategory.SCROLL),
    SCROLL_OF_TRANSMUTATION (Category.EQUIPMENT, Subcategory.SCROLL),
    ARCANE_STYLUS (Category.EQUIPMENT, Subcategory.MISC),
    TORCHES (Category.EQUIPMENT, Subcategory.MISC),
    WATERSKIN (Category.EQUIPMENT, Subcategory.MISC),
    RAT_SKULL (Category.TRINKET, null),
    PARCHMENT_SCRAP (Category.TRINKET, null),
    PETRIFIED_SEED (Category.TRINKET, null),
    EXOTIC_CRYSTALS (Category.TRINKET, null),
    MOSSY_CLUMP (Category.TRINKET, null),
    DIMENSIONAL_SUNDIAL (Category.TRINKET, null),
    THIRTEEN_LEAF_CLOVER (Category.TRINKET, null),
    TRAP_MECHANISM (Category.TRINKET, null),
    MIMIC_TOOTH (Category.TRINKET, null),
    WONDROUS_RESIN (Category.TRINKET, null),
    EYE_OF_NEWT (Category.TRINKET, null),
    SALT_CUBE (Category.TRINKET, null),
    VIAL_OF_BLOOD (Category.TRINKET, null),
    SHARD_OF_OBLIVION (Category.TRINKET, null),
    CHAOTIC_CENSER (Category.TRINKET, null),
    FERRET_TUFT (Category.TRINKET, null),
    CRACKED_SPYGLASS (Category.TRINKET, null),
    VELVET_POUCH (Category.EXPANDER, null),
    POTION_BANDOLIER (Category.EXPANDER, null),
    SCROLL_HOLDER (Category.EXPANDER, null),
    MAGICAL_HOLSTER (Category.EXPANDER, null),
    PROGRESSIVE_MAX_LEVEL (Category.LEVEL, null),
    IRON_KEY (Category.KEY, null),
    GOLD_KEY (Category.KEY, null),
    CRYSTAL_KEY (Category.KEY, null);

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

    APItem(Category category, Subcategory subcategory) {
        this.category = category;
        this.subcategory = subcategory;
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
