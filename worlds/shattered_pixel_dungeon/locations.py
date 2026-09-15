from typing import NamedTuple, Optional, Final

from BaseClasses import Location, Region

location_start_id: Final[int] = 2000
character_offset: Final[int] = 100
level_clears_offset: Final[int] = 50

shop_offset: Final[int] = 600
shop_character_offset = 50

global_shop_offset: Final[int] = 900

special_room_offset: Final[int] = 1000
locked_room_offset: Final[int] = 1025
loot_item_offset: Final[int] = 1050


CHARACTERS: Final[list[str]] = [
    "Warrior", "Mage",
    "Rogue", "Huntress",
    "Duelist", "Cleric"
]

DUNGEON_REGIONS: Final[list[str]] = [
    "Sewers",
    "Prison",
    "Caves",
    "City",
    "Halls"
]

MAX_LOOT_LOCATIONS: Final[list[int]] = [
    15, 13, 10, 7, 5
]

MAX_SHOP_LOCATIONS: Final[list[int]] = [
    0, 20, 15, 10, 5
]

ENEMIES: Final[list[list[str]]] = [
    ["Rat", "Snake"],
    ["Gnoll"],
    ["Swarm", "Crab"],
    ["Slime"],
    [],

    ["Skeleton", "Thief"],
    ["DM-100", "Guard"],
    ["Necromancer"],
    [],
    [],

    ["Bat", "Brute", "Shaman"],
    ["Spinner"],
    ["DM-200"],
    [],
    [],

    ["Ghoul", "Elemental", "Warlock"],
    ["Monk"],
    ["Golem"],
    [],
    [],

    ["Ripper Demon", "Succubus", "Eye"],
    [],
    ["Scorpio"],
    [],
    []
]

SPECIAL_ROOMS: Final[list[str]] = [
    "Traps Room",
    "Magical Fire Room",
    "Pool Room",
    "Sentry Room",
    "Storage Room",
    "Toxic Gas Room"
    "Pit Room",
]

LOCKED_ROOMS: Final[list[str]] = [
    "Armory Room",
    "Library Room",
    "Treasury Room"
]


class SPDLocation(Location):
    game = "Shattered Pixel Dungeon"


class SPDLocationData(NamedTuple):
    region: str
    id: Optional[int] = None


def add_to_data_table(data_table: dict[str, int], name: str, id: Optional[int]) -> None:
    if id is not None:
        data_table[name] = id


def generate_location_table() -> dict[str, int]:
    data_table: dict[str, int] = {}

    for i, character in enumerate(CHARACTERS):
        for j, room in enumerate(SPECIAL_ROOMS):
            add_to_data_table(data_table, f"{character} {room}",
                              location_start_id + i * character_offset + special_room_offset + j)

        for j, room in enumerate(LOCKED_ROOMS):
            add_to_data_table(data_table, f"{character} {room}",
                              location_start_id + i * character_offset + locked_room_offset + j)

        floor: int = 0
        enemy_count: int = 0
        shop_item_count: int = 0
        loot_item_count: int = 0

        for j, area in enumerate(DUNGEON_REGIONS):

            for loc_num in range(MAX_SHOP_LOCATIONS[j]):
                add_to_data_table(data_table, f"{character} {area} Shop {loc_num + 1}",
                                  location_start_id + i * shop_character_offset + shop_offset + shop_item_count)
                shop_item_count += 1

            for loc_num in range(MAX_LOOT_LOCATIONS[j]):
                add_to_data_table(data_table, f"{character} {area} Item {loc_num + 1}",
                                  location_start_id + i * character_offset + loot_item_offset + loot_item_count)
                loot_item_count += 1

            for _ in range(5):

                for enemy in ENEMIES[floor]:
                    add_to_data_table(data_table, f"{character} {enemy} Kill",
                                      location_start_id + i * character_offset + enemy_count)
                    enemy_count += 1

                add_to_data_table(data_table, f"{character} Clear Floor {floor + 1}",
                                  location_start_id + i * character_offset + level_clears_offset + floor)

                floor += 1

    return data_table