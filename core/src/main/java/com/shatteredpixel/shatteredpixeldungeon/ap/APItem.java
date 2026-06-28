package com.shatteredpixel.shatteredpixeldungeon.ap;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.AlarmTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.BlazingTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.BurningTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.ChillingTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.ConfusionTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.CorrosionTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.CursingTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.DisarmingTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.DistortionTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.FlockTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.FrostTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GeyserTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GrippingTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.OozeTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.PitfallTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.ShockingTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.StormTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.SummoningTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.ToxicTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.WarpingTrap;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public enum APItem {

    /* APID Map
    ****************** EQUIPMENT 1000-1099 *******************
    * 1000-1019 potions
    * 1020-1039 scrolls
    * 1040-1059 rings
    * 1060-1079 wands
    * 1080-1099 artifacts
    *
    ********** CHARACTER UNLOCKS/UPGRADES 1100-1349 **********
    * 1100-1109 characters
    * 1110-1129 subclasses
    * 1130-1149 armor abilities
    * 1150-1159 progressive level ups
    * 1160-1349 ability unlocks
    *
    ******************* WEAPONRY 1350-1449 *******************
    * 1350-1389 progressive weapon tier
    * 1390-1409 progressive armor tier
    * 1410-1449 progressive throwing weapon tier
    *
    ******************* ACTIONS 1550-1599 ********************
    * 1550 Progressive Alchemy
    * 1560 Searching
    *
    **************** MISC EQUIPMENT 1600-1799 ****************
    * 1600-1619 Trinkets
    * 1620-1629 Dungeon Quests
    * 1630-1639 Key types
    * 1640-1649 Inventory Expanders
    * 1650 Arcane Syluses
    * 1660 Torches
    * 1670 Waterskin
    *
    ************** FILLER CONSUMABLES 1800-1849 **************
    *
    ******************** TRAPS 1850-1999 *********************
    *
    */

//    WARRIOR (Category.CHARACTER, null, 0, 1100),
    MAGE (Category.CHARACTER, null, null, 1, 1101),
    ROGUE (Category.CHARACTER, null, null, 2, 1102),
    HUNTRESS (Category.CHARACTER, null, null, 3, 1103),
    DUELIST (Category.CHARACTER, null, null, 4, 1104),
    CLERIC (Category.CHARACTER, null, null, 5, 1105),
    BERSERKER (Category.SUBCLASS, Subcategory.WARRIOR, null, 0, 1110),
    GLADIATOR (Category.SUBCLASS, Subcategory.WARRIOR, null, 1, 1111),
    BATTLEMAGE (Category.SUBCLASS, Subcategory.MAGE, null, 2, 1112),
    WARLOCK (Category.SUBCLASS, Subcategory.MAGE, null, 3, 1113),
    ASSASSIN (Category.SUBCLASS, Subcategory.ROGUE, null, 4, 1114),
    FREERUNNER (Category.SUBCLASS, Subcategory.ROGUE, null, 5, 1115),
    SNIPER (Category.SUBCLASS, Subcategory.HUNTRESS, null, 6, 1116),
    WARDEN (Category.SUBCLASS, Subcategory.HUNTRESS, null, 7, 1117),
    CHAMPION (Category.SUBCLASS, Subcategory.DUELIST, null, 8, 1118),
    MONK (Category.SUBCLASS, Subcategory.DUELIST, null, 9, 1119),
    PRIEST (Category.SUBCLASS, Subcategory.CLERIC, null, 10, 1120),
    PALADIN (Category.SUBCLASS, Subcategory.CLERIC, null, 11, 1121),
    HEROIC_LEAP (Category.ARMOR_ABILITY, Subcategory.WARRIOR, null, 0, 1130),
    SHOCKWAVE (Category.ARMOR_ABILITY, Subcategory.WARRIOR, null, 1, 1131),
    ENDURE (Category.ARMOR_ABILITY, Subcategory.WARRIOR, null, 2, 1132),
    ELEMENTAL_BLAST (Category.ARMOR_ABILITY, Subcategory.MAGE, null, 3, 1133),
    WILD_MAGIC (Category.ARMOR_ABILITY, Subcategory.MAGE, null, 4, 1134),
    WARPING_BEACON (Category.ARMOR_ABILITY, Subcategory.MAGE, null, 5, 1135),
    SMOKE_BOMB (Category.ARMOR_ABILITY, Subcategory.ROGUE, null, 6, 1136),
    DEATH_MARK (Category.ARMOR_ABILITY, Subcategory.ROGUE, null, 7, 1137),
    SHADOW_CLONE (Category.ARMOR_ABILITY, Subcategory.ROGUE, null, 8, 1138),
    SPECTRAL_BLADES (Category.ARMOR_ABILITY, Subcategory.HUNTRESS, null, 9, 1139),
    NATURES_POWER (Category.ARMOR_ABILITY, Subcategory.HUNTRESS, null, 10, 1140),
    SPIRIT_HAWK (Category.ARMOR_ABILITY, Subcategory.HUNTRESS, null, 11, 1141),
    CHALLENGE (Category.ARMOR_ABILITY, Subcategory.DUELIST, null, 12, 1142),
    ELEMENTAL_STRIKE (Category.ARMOR_ABILITY, Subcategory.DUELIST, null, 13, 1143),
    FEINT (Category.ARMOR_ABILITY, Subcategory.DUELIST, null, 14, 1144),
    ASCENDED_FORM (Category.ARMOR_ABILITY, Subcategory.CLERIC, null, 15, 1145),
    TRINITY (Category.ARMOR_ABILITY, Subcategory.CLERIC, null, 16, 1146),
    POWER_OF_MANY (Category.ARMOR_ABILITY, Subcategory.CLERIC, null, 17, 1147),
    HERTY_MEAL (Category.TALENT, null, null, 0, 1150),
    VETERANS_INTUITION (Category.TALENT, null, null, 1, 1151),
    PROVOKE_ANGER (Category.TALENT, null, null, 2, 1152),
    IRON_WILL (Category.TALENT, null, null, 3, 1153),
    IRON_STOMACH (Category.TALENT, null, null, 4, 1154),
    LIQUID_WILLPOWER (Category.TALENT, null, null, 5, 1155),
    RUNIC_TRANSFERENCE (Category.TALENT, null, null, 6, 1156),
    LETHAL_MOMENTUM (Category.TALENT, null, null, 7, 1157),
    IMPROVISED_PROJECTILES (Category.TALENT, null, null, 8, 1158),
    HOLD_FAST (Category.TALENT, null, null, 9, 1159),
    STRONGMAN (Category.TALENT, null, null, 10, 1160),
    ENDLESS_RAGE (Category.TALENT, null, null, 11, 1161),
    DEATHLESS_FURY (Category.TALENT, null, null, 12, 1162),
    ENRAGED_CATALYST (Category.TALENT, null, null, 13, 1163),
    CLEAVE (Category.TALENT, null, null, 14, 1164),
    LETHAL_DEFENSE (Category.TALENT, null, null, 15, 1165),
    ENHANCED_COMBO (Category.TALENT, null, null, 16, 1166),
    BODY_SLAM (Category.TALENT, null, null, 17, 1167),
    IMPACT_WAVE (Category.TALENT, null, null, 18, 1168),
    DOUBLE_JUMP (Category.TALENT, null, null, 19, 1169),
    EXAPNDING_WAVE (Category.TALENT, null, null, 20, 1170),
    STRIKING_WAVE (Category.TALENT, null, null, 21, 1171),
    SHOCK_FORCE (Category.TALENT, null, null, 22, 1172),
    SUSTAINED_RETRIBUTION (Category.TALENT, null, null, 23, 1173),
    SHRUG_IT_OFF (Category.TALENT, null, null, 24, 1174),
    EVEN_THE_ODDS (Category.TALENT, null, null, 25, 1175),
    EMPOWERING_MEAL (Category.TALENT, null, null, 26, 1176),
    SCHOLARS_INTUITION (Category.TALENT, null, null, 27, 1177),
    LINGERING_MAGIC (Category.TALENT, null, null, 28, 1178),
    BACKUP_BARRIER (Category.TALENT, null, null, 29, 1179),
    ENERGIZING_MEAL (Category.TALENT, null, null, 30, 1180),
    INSCRIBED_POWER (Category.TALENT, null, null, 31, 1181),
    WAND_PRESERVATION (Category.TALENT, null, null, 32, 1182),
    ARCANE_VISION (Category.TALENT, null, null, 33, 1183),
    SHIELD_BATTERY (Category.TALENT, null, null, 34, 1184),
    DESPERATE_POWER (Category.TALENT, null, null, 35, 1185),
    ALLY_WARP (Category.TALENT, null, null, 36, 1186),
    EMPOWERED_STRIKE (Category.TALENT, null, null, 37, 1187),
    MYSTICAL_CHARGE (Category.TALENT, null, null, 38, 1188),
    EXCESS_CHARGE (Category.TALENT, null, null, 39, 1189),
    SOUL_EATER (Category.TALENT, null, null, 40, 1190),
    SOUL_SIPHON (Category.TALENT, null, null, 41, 1191),
    NECROMANCERS_MINIONS (Category.TALENT, null, null, 42, 1192),
    BLAST_RADIUS (Category.TALENT, null, null, 43, 1193),
    ELEMENTAL_POWER (Category.TALENT, null, null, 44, 1194),
    REACTIVE_BARRIER (Category.TALENT, null, null, 45, 1195),
    WILD_POWER (Category.TALENT, null, null, 46, 1196),
    FIRE_EVERYTHING (Category.TALENT, null, null, 47, 1197),
    CONSERVED_MAGIC (Category.TALENT, null, null, 48, 1198),
    TELEFRAG (Category.TALENT, null, null, 49, 1199),
    REMOTE_BEACON (Category.TALENT, null, null, 50, 1200),
    LONGRANGE_WARP (Category.TALENT, null, null, 51, 1201),
    CACHED_RATIONS (Category.TALENT, null, null, 42, 1202),
    THIEFS_INTUITION (Category.TALENT, null, null, 43, 1203),
    SUCKER_PUNCH (Category.TALENT, null, null, 44, 1204),
    PROTECTIVE_SHADOWS (Category.TALENT, null, null, 45, 1205),
    MYSTICAL_MEAL (Category.TALENT, null, null, 46, 1206),
    INSCRIBED_STEAL (Category.TALENT, null, null, 47, 1207),
    WIDE_SEARCH (Category.TALENT, null, null, 48, 1208),
    SILENT_STEPS (Category.TALENT, null, null, 49, 1209),
    ROGUES_FORESIGHT (Category.TALENT, null, null, 50, 1210),
    ENHANCED_RINGS (Category.TALENT, null, null, 51, 1211),
    LIGHT_CLOAK (Category.TALENT, null, null, 52, 1212),
    ENHANCED_LETHALITY (Category.TALENT, null, null, 53, 1213),
    ASSASSINS_REACH (Category.TALENT, null, null, 54, 1214),
    BOUNTY_HUNTER (Category.TALENT, null, null, 55, 1215),
    EVASIVE_ARMOR (Category.TALENT, null, null, 56, 1216),
    PROJECTILE_MOMENTUM (Category.TALENT, null, null, 57, 1217),
    SPEEDY_STEALTH (Category.TALENT, null, null, 58, 1218),
    HASTY_RETREAT (Category.TALENT, null, null, 59, 1219),
    BODY_REPLACEMENT (Category.TALENT, null, null, 60, 1220),
    SHADOW_STEP (Category.TALENT, null, null, 61, 1221),
    FEAR_THE_REAPER (Category.TALENT, null, null, 62, 1222),
    DEATHLY_DURABILITY (Category.TALENT, null, null, 63, 1223),
    DOUBLE_MARK (Category.TALENT, null, null, 64, 1224),
    SHADOW_BLADE (Category.TALENT, null, null, 65, 1225),
    CLONED_ARMOR (Category.TALENT, null, null, 66, 1226),
    PERFECT_COPY (Category.TALENT, null, null, 67, 1227),
    NATURES_BOUNTY (Category.TALENT, null, null, 68, 1228),
    SURVIVALISTS_INTUITION (Category.TALENT, null, null, 69, 1229),
    FOLLOWUP_STRIKE (Category.TALENT, null, null, 70, 1230),
    NATURES_AID (Category.TALENT, null, null, 71, 1231),
    INVIGORATING_MEAL (Category.TALENT, null, null, 72, 1232),
    LIQUID_NATURE (Category.TALENT, null, null, 73, 1233),
    REJUVENATING_STEPS (Category.TALENT, null, null, 74, 1234),
    HEIGHTENED_SENSES (Category.TALENT, null, null, 75, 1235),
    DURABLE_PROJECTILES (Category.TALENT, null, null, 76, 1236),
    POINT_BLANK (Category.TALENT, null, null, 77, 1237),
    SEER_SHOT (Category.TALENT, null, null, 78, 1238),
    FARSIGHT (Category.TALENT, null, null, 79, 1239),
    SHARED_ENCHANTMENT (Category.TALENT, null, null, 80, 1240),
    SHARED_UPGRADES (Category.TALENT, null, null, 81, 1241),
    DURABLE_TIPS (Category.TALENT, null, null, 82, 1242),
    BARKSKIN (Category.TALENT, null, null, 83, 1243),
    SHIELDING_DEW (Category.TALENT, null, null, 84, 1244),
    FAN_OF_BLADES (Category.TALENT, null, null, 85, 1245),
    PROJECTING_BLADES (Category.TALENT, null, null, 86, 1246),
    SPIRIT_BLADES (Category.TALENT, null, null, 87, 1247),
    GROWING_POWER (Category.TALENT, null, null, 88, 1248),
    NATURES_WRATH (Category.TALENT, null, null, 89, 1249),
    WILD_MOMENTUM (Category.TALENT, null, null, 90, 1250),
    EAGLE_EYE (Category.TALENT, null, null, 91, 1251),
    GO_FOR_THE_EYES (Category.TALENT, null, null, 92, 1252),
    SWIFT_SPIRIT (Category.TALENT, null, null, 93, 1253),
    STRENGTHENING_MEAL (Category.TALENT, null, null, 94, 1254),
    ADVENTURERS_INTUTIONS (Category.TALENT, null, null, 95, 1255),
    PATIENT_STRIKE (Category.TALENT, null, null, 96, 1256),
    AGGRESSIVE_BARRIER (Category.TALENT, null, null, 97, 1257),
    FOCUSED_MEAL (Category.TALENT, null, null, 98, 1258),
    LIQUID_AGILITY (Category.TALENT, null, null, 99, 1259),
    WEAPON_RECHARGING (Category.TALENT, null, null, 100, 1260),
    LETHAL_HASTE (Category.TALENT, null, null, 101, 1261),
    SWIFT_EQUIP (Category.TALENT, null, null, 102, 1262),
    PRECISE_ASSAULT (Category.TALENT, null, null, 103, 1263),
    DEADLY_FOLLOWUP (Category.TALENT, null, null, 104, 1264),
    VARIED_CHARGE (Category.TALENT, null, null, 105, 1265),
    TWIN_UPGRADES (Category.TALENT, null, null, 106, 1266),
    COMBINED_LETHALITY (Category.TALENT, null, null, 107, 1267),
    UNENCUMBERED_SPIRIT (Category.TALENT, null, null, 108, 1268),
    MONASTIC_VIGOR (Category.TALENT, null, null, 109, 1269),
    COMBINED_ENERGY (Category.TALENT, null, null, 110, 1270),
    CLOSE_THE_GAP (Category.TALENT, null, null, 111, 1271),
    INVIGORATING_VICTORY (Category.TALENT, null, null, 112, 1272),
    ELIMINATION_MATCH (Category.TALENT, null, null, 113, 1273),
    ELEMENTAL_REACH (Category.TALENT, null, null, 114, 1274),
    STRIKING_FORCE (Category.TALENT, null, null, 115, 1275),
    DIRECTED_POWER (Category.TALENT, null, null, 116, 1276),
    FEIGNED_RETREAT (Category.TALENT, null, null, 117, 1277),
    EXPOSE_WEAKNESS (Category.TALENT, null, null, 118, 1278),
    COUNTER_ABILITY (Category.TALENT, null, null, 119, 1279),
    SATIATED_SPELLS (Category.TALENT, null, null, 120, 1280),
    HOLY_INTUITION (Category.TALENT, null, null, 121, 1281),
    SEARING_LIGHT (Category.TALENT, null, null, 122, 1282),
    SHIELD_OF_LIGHT (Category.TALENT, null, null, 123, 1283),
    ENLIGHTENING_MEAL (Category.TALENT, null, null, 124, 1284),
    RECALL_INSCRIPTION (Category.TALENT, null, null, 125, 1285),
    SUNRAY (Category.TALENT, null, null, 126, 1286),
    DIVINE_SENSE (Category.TALENT, null, null, 127, 1287),
    BLESS (Category.TALENT, null, null, 128, 1288),
    CLEANSE (Category.TALENT, null, null, 129, 1289),
    LIGHT_READING (Category.TALENT, null, null, 130, 1290),
    HOLY_LANCE (Category.TALENT, null, null, 131, 1291),
    HALLOWED_GROUND (Category.TALENT, null, null, 132, 1292),
    MNEMONIC_PRAYER (Category.TALENT, null, null, 133, 1293),
    LAY_ON_HANDS (Category.TALENT, null, null, 134, 1294),
    AURA_OF_PROTECTION (Category.TALENT, null, null, 135, 1295),
    WALL_OF_LIGHT (Category.TALENT, null, null, 136, 1296),
    DIVINE_INTERVENTION (Category.TALENT, null, null, 137, 1297),
    JUDGEMENT (Category.TALENT, null, null, 138, 1298),
    FLASH (Category.TALENT, null, null, 139, 1299),
    BODY_FORM (Category.TALENT, null, null, 140, 1300),
    MIND_FORM (Category.TALENT, null, null, 141, 1301),
    SPIRIT_FORM (Category.TALENT, null, null, 142, 1302),
    BEAMING_RAY (Category.TALENT, null, null, 143, 1303),
    LIFE_LINK (Category.TALENT, null, null, 144, 1304),
    STASIS (Category.TALENT, null, null, 145, 1305),
    HEROIC_ENERGY (Category.TALENT, null, null, 146, 1306),
    PROGRESSIVE_WEAPON (Category.WEAPONRY, null, null, 0, 1350),
    PROGRESSIVE_ARMOR (Category.WEAPONRY, null, null, 1, 1390),
    PROGRESSIVE_MISSILE (Category.WEAPONRY, null, null, 2, 1410),
    ALCHEMISTS_TOOLKIT (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 0, 1080),
    CHALICE_OF_BLOOD (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 1, 1081),
    DRIED_ROSE (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 3, 1083),
    ETHEREAL_CHAINS (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 4, 1084),
    HORN_OF_PLENTY (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 6, 1086),
    MASTER_THIEVES_ARMBAND (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 7, 1087),
    SANDALS_OF_NATURE (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 8, 1088),
    SKELETON_KEY (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 9, 1089),
    TALISMAN_OF_FORESIGHT (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 10, 1090),
    TIMEKEEPERS_HOURGLASS (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 11, 1091),
    UNSTABLE_SPELLBOOK (Category.EQUIPMENT, Subcategory.ARTIFACT, null, 12, 1092),
    WAND_OF_MAGIC_MISSILE (Category.EQUIPMENT, Subcategory.WAND, null, 0, 1060),
    WAND_OF_LIGHTNING (Category.EQUIPMENT, Subcategory.WAND, null, 1, 1061),
    WAND_OF_DISINTEGRATION (Category.EQUIPMENT, Subcategory.WAND, null, 2, 1062),
    WAND_OF_FIREBLAST (Category.EQUIPMENT, Subcategory.WAND, null, 3, 1063),
    WAND_OF_CORROSION (Category.EQUIPMENT, Subcategory.WAND, null, 4, 1064),
    WAND_OF_BLAST_WAVE (Category.EQUIPMENT, Subcategory.WAND, null, 5, 1065),
    WAND_OF_LIVING_EARTH (Category.EQUIPMENT, Subcategory.WAND, null, 6, 1066),
    WAND_OF_FROST (Category.EQUIPMENT, Subcategory.WAND, null, 7, 1067),
    WAND_OF_PRISMATIC_LIGHT (Category.EQUIPMENT, Subcategory.WAND, null, 8, 1068),
    WAND_OF_WARDING (Category.EQUIPMENT, Subcategory.WAND, null, 9, 1069),
    WAND_OF_TRANSFUSION (Category.EQUIPMENT, Subcategory.WAND, null, 10, 1070),
    WAND_OF_CORRUPTION (Category.EQUIPMENT, Subcategory.WAND, null, 11, 1071),
    WAND_OF_REGROWTH (Category.EQUIPMENT, Subcategory.WAND, null, 12, 1072),
    RING_OF_ACCURACY (Category.EQUIPMENT, Subcategory.RING, null, 0, 1040),
    RING_OF_ARCANA (Category.EQUIPMENT, Subcategory.RING, null, 1, 1041),
    RING_OF_ELEMENTS (Category.EQUIPMENT, Subcategory.RING, null, 2, 1042),
    RING_OF_ENERGY (Category.EQUIPMENT, Subcategory.RING, null, 3, 1043),
    RING_OF_EVASION (Category.EQUIPMENT, Subcategory.RING, null, 4, 1044),
    RING_OF_FORCE (Category.EQUIPMENT, Subcategory.RING, null, 5, 1045),
    RING_OF_FUROR (Category.EQUIPMENT, Subcategory.RING, null, 6, 1046),
    RING_OF_HASTE (Category.EQUIPMENT, Subcategory.RING, null, 7, 1047),
    RING_OF_MIGHT (Category.EQUIPMENT, Subcategory.RING, null, 8, 1048),
    RING_OF_SHARPSHOOTING (Category.EQUIPMENT, Subcategory.RING, null, 9, 1049),
    RING_OF_TENACITY (Category.EQUIPMENT, Subcategory.RING, null, 10, 1050),
    RING_OF_WEALTH (Category.EQUIPMENT, Subcategory.RING, null, 11, 1051),
    POTION_OF_STRENGTH (Category.EQUIPMENT, Subcategory.POTION, null, 0, 1000),
    POTION_OF_HEALING (Category.EQUIPMENT, Subcategory.POTION, null, 1, 1001),
    POTION_OF_MIND_VISION (Category.EQUIPMENT, Subcategory.POTION, null, 2, 1002),
    POTION_OF_FROST (Category.EQUIPMENT, Subcategory.POTION, null, 3, 1003),
    POTION_OF_LIQUID_FLAME (Category.EQUIPMENT, Subcategory.POTION, null, 4, 1004),
    POTION_OF_TOXIC_GAS (Category.EQUIPMENT, Subcategory.POTION, null, 5, 1005),
    POTION_OF_HASTE (Category.EQUIPMENT, Subcategory.POTION, null, 6, 1006),
    POTION_OF_INVISIBILITY (Category.EQUIPMENT, Subcategory.POTION, null, 7, 1007),
    POTION_OF_LEVITATION (Category.EQUIPMENT, Subcategory.POTION, null, 8, 1008),
    POTION_OF_PARALYTIC_GAS (Category.EQUIPMENT, Subcategory.POTION, null, 9, 1009),
    POTION_OF_PURITY (Category.EQUIPMENT, Subcategory.POTION, null, 10, 1010),
    POTION_OF_EXPERIENCE (Category.EQUIPMENT, Subcategory.POTION, null, 11, 1011),
    SCROLL_OF_UPGRADE (Category.EQUIPMENT, Subcategory.SCROLL, null, 0, 1020),
    SCROLL_OF_IDENTIFY (Category.EQUIPMENT, Subcategory.SCROLL, null, 1, 1021),
    SCROLL_OF_REMOVE_CURSE (Category.EQUIPMENT, Subcategory.SCROLL, null, 2, 1022),
    SCROLL_OF_MIRROR_IMAGE (Category.EQUIPMENT, Subcategory.SCROLL, null, 3, 1023),
    SCROLL_OF_RECHARGING (Category.EQUIPMENT, Subcategory.SCROLL, null, 4, 1024),
    SCROLL_OF_TELEPORTATION (Category.EQUIPMENT, Subcategory.SCROLL, null, 5, 1025),
    SCROLL_OF_LULLABY (Category.EQUIPMENT, Subcategory.SCROLL, null, 6, 1026),
    SCROLL_OF_MAGIC_MAPPING (Category.EQUIPMENT, Subcategory.SCROLL, null, 7, 1027),
    SCROLL_OF_RAGE (Category.EQUIPMENT, Subcategory.SCROLL, null, 8, 1028),
    SCROLL_OF_RETRIBUTION (Category.EQUIPMENT, Subcategory.SCROLL, null, 9, 1029),
    SCROLL_OF_TERROR (Category.EQUIPMENT, Subcategory.SCROLL, null, 10, 1030),
    SCROLL_OF_TRANSMUTATION (Category.EQUIPMENT, Subcategory.SCROLL, null, 11, 1031),
    ARCANE_STYLUS (Category.MISC, null, null, 0, 1650),
    TORCHES (Category.MISC, null, null, 3, 1660),
    WATERSKIN (Category.MISC, null, null, 2, 1670),
    RAT_SKULL (Category.TRINKET, null, null, 0, 1600),
    PARCHMENT_SCRAP (Category.TRINKET, null, null, 1, 1601),
    PETRIFIED_SEED (Category.TRINKET, null, null, 2, 1602),
    EXOTIC_CRYSTALS (Category.TRINKET, null, null, 3, 1603),
    MOSSY_CLUMP (Category.TRINKET, null, null, 4, 1604),
    DIMENSIONAL_SUNDIAL (Category.TRINKET, null, null, 5, 1605),
    THIRTEEN_LEAF_CLOVER (Category.TRINKET, null, null, 6, 1606),
    TRAP_MECHANISM (Category.TRINKET, null, null, 7, 1607),
    MIMIC_TOOTH (Category.TRINKET, null, null, 8, 1608),
    WONDROUS_RESIN (Category.TRINKET, null, null, 9, 1609),
    EYE_OF_NEWT (Category.TRINKET, null, null, 10, 1610),
    SALT_CUBE (Category.TRINKET, null, null, 11, 1611),
    VIAL_OF_BLOOD (Category.TRINKET, null, null, 12, 1612),
    SHARD_OF_OBLIVION (Category.TRINKET, null, null, 13, 1613),
    CHAOTIC_CENSER (Category.TRINKET, null, null, 14, 1614),
    FERRET_TUFT (Category.TRINKET, null, null, 15, 1615),
    CRACKED_SPYGLASS (Category.TRINKET, null, null, 16, 1616),
    VELVET_POUCH (Category.EXPANDER, null, null, 0, 1640),
    POTION_BANDOLIER (Category.EXPANDER, null, null, 1, 1641),
    SCROLL_HOLDER (Category.EXPANDER, null, null, 2, 1642),
    MAGICAL_HOLSTER (Category.EXPANDER, null, null, 3, 1643),
    WARRIOR_PROGRESSIVE_MAX_LEVEL (Category.LEVEL, Subcategory.WARRIOR, null, 0, 1150),
    MAGE_PROGRESSIVE_MAX_LEVEL (Category.LEVEL, Subcategory.MAGE, null, 1, 1151),
    ROGUE_PROGRESSIVE_MAX_LEVEL (Category.LEVEL, Subcategory.ROGUE, null, 2, 1152),
    HUNTRESS_PROGRESSIVE_MAX_LEVEL (Category.LEVEL, Subcategory.HUNTRESS, null, 3, 1153),
    DUELIST_PROGRESSIVE_MAX_LEVEL (Category.LEVEL, Subcategory.DUELIST, null, 4, 1154),
    CLERIC_PROGRESSIVE_MAX_LEVEL (Category.LEVEL, Subcategory.CLERIC, null, 5, 1155),
    SEARCH (Category.ACTION, null, null, 0, 1560),
    PROGRESSIVE_ALCHEMY (Category.ACTION, null, null, 1, 1550),
    SAD_GHOST_QUEST (Category.QUEST, null, null, 0, 1620),
    WANDMAKER_QUEST (Category.QUEST, null, null, 1, 1621),
    BLACKSMITH_QUEST (Category.QUEST, null, null, 2, 1622),
    AMBITIOUS_IMP_QUEST (Category.QUEST, null, null, 3, 1623),
    IRON_KEY (Category.KEY, null, null, 0, 1630),
    GOLD_KEY (Category.KEY, null, null, 1, 1631),
    CRYSTAL_KEY (Category.KEY, null, null, 2, 1632),
    CAUSTIC_OOZE_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, OozeTrap.class, 0, 1900),
    POISON_TRAP (Category.TRAP, Subcategory.DEBUFF, Poison.class, 1, 1901),
    BLIND_TRAP (Category.TRAP, Subcategory.DEBUFF, Blindness.class, 2, 1902),
    HEX_TRAP (Category.TRAP, Subcategory.DEBUFF, Hex.class, 3, 1903),
    WEAKEN_TRAP (Category.TRAP, Subcategory.DEBUFF, Weakness.class, 4, 1904),
    DEGRADE_TRAP (Category.TRAP, Subcategory.DEBUFF, Degrade.class, 5, 1905),
    ROOT_TRAP (Category.TRAP, Subcategory.DEBUFF, Roots.class, 6, 1906),
    VULNERABLE_TRAP (Category.TRAP, Subcategory.DEBUFF, Vulnerable.class, 7, 1907),
    ICE_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, ChillingTrap.class, 8, 1908),
    FIRE_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, BurningTrap.class, 9, 1909),
    SHOCKING_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, ShockingTrap.class, 10, 1910),
    ALARM_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, AlarmTrap.class, 11, 1911),
    TOXIC_GAS_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, ToxicTrap.class, 12, 1912),
    GRIPPING_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, GrippingTrap.class, 13, 1913),
    SUMMONING_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, SummoningTrap.class, 14, 1914),
    CONFUSION_GAS_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, ConfusionTrap.class, 15, 1915),
    CORROSIVE_GAS_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, CorrosionTrap.class, 16, 1916),
    FLOCK_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, FlockTrap.class, 17, 1917),
    DISTORTION_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, DistortionTrap.class, 18, 1918),
    DISARMING_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, DisarmingTrap.class, 19, 1919),
    WARPING_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, WarpingTrap.class, 20, 1920),
    GEYSER_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, GeyserTrap.class, 21, 1921),
    PITFALL_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, PitfallTrap.class, 22, 1922),
    CURSING_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, CursingTrap.class, 23, 1923),
    BLAZING_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, BlazingTrap.class, 24, 1924),
    FROST_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, FrostTrap.class, 25, 1925),
    STORM_TRAP (Category.TRAP, Subcategory.TRAP_EFFECT, StormTrap.class, 26, 1926);

    public enum Category {
        TRAP,
        CHARACTER,
        SUBCLASS,
        ARMOR_ABILITY,
        EQUIPMENT,
        TRINKET,
        KEY,
        EXPANDER,
        QUEST,
        TALENT,
        LEVEL,
        MISC,
        WEAPONRY,
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
        ARTIFACT,
        WAND,
        RING,
        POTION,
        SCROLL,
        WARRIOR,
        MAGE,
        ROGUE,
        HUNTRESS,
        DUELIST,
        CLERIC,
        DEBUFF,
        TRAP_EFFECT;

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
    public final Class<?> effect;
    public Class<? extends Buff> debuff = null;
    public Class<? extends Trap> trap = null;
    public final int id;
    private final int apid;

    APItem(Category category, Subcategory subcategory, Class<?> effect, int id, int apid) {
        this.category = category;
        this.subcategory = subcategory;
        this.effect = effect;
        if (effect != null &&Buff.class.isAssignableFrom(effect)) this.debuff = (Class<? extends Buff>) effect;
        else if (effect != null && Trap.class.isAssignableFrom(effect)) this.trap = (Class<? extends Trap>) effect;
        this.id = id;
        this.apid = apid;
    }

    public Category getCategory() {
        return category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public int getApid() { return apid; }

    public static final Map<Integer, APItem> BY_ID = new HashMap<>();

    static {
        for (APItem item : values()) {
            BY_ID.put(item.apid, item);
        }
    }

    public static APItem fromString(String name) {
        for (APItem item : values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public static APItem fromId(int id) {
        return BY_ID.get(id);
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
