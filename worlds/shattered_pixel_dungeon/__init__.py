from typing import Optional

from BaseClasses import Region
from worlds.AutoWorld import World
from .names import item_names

from .options import SPDOptions
from .items import SPDItem, generate_item_table, generate_item_data_table, max_level_item_data, \
    progressive_equipment_item_data
from .locations import generate_location_table, SPDLocation, CHARACTERS, DUNGEON_REGIONS, ENEMIES, location_start_id, \
    character_offset, level_clears_offset, MAX_LOOT_LOCATIONS, loot_item_offset, SPECIAL_ROOMS, special_room_offset, \
    LOCKED_ROOMS, locked_room_offset, MAX_SHOP_LOCATIONS, shop_character_offset, shop_offset


class ShatteredPixelDungeon(World):
    """Description"""
    game = "Shattered Pixel Dungeon"

    options_dataclass = SPDOptions
    options: SPDOptions

    active_items: dict[str, int] = {}

    location_name_to_id: dict[str, int] = generate_location_table()
    item_name_to_id: dict[str, int] = generate_item_table()

    def generate_early(self):
        self.item_data_table = generate_item_data_table()

        for name, data in self.item_data_table.items():
            if name in max_level_item_data:
                self.active_items[name] = 30
            elif name in progressive_equipment_item_data:
                self.active_items[name] = 5
            elif name == item_names.progressive_alchemy:
                self.active_items[name] = 9
            else:
                self.active_items[name] = 1

    def add_location(self, region: Region, name: str, id: Optional[int]) -> SPDLocation:
        loc: SPDLocation = SPDLocation(self.player, name, id, region)
        region.locations.append(loc)
        return loc

    def create_regions(self) -> None:

        #all the regions
        regions: list[Region] = []

        #create starting region
        menu_region: Region = Region("Menu", self.player, self.multiworld)
        #TODO add global shop locations here
        #add the main menu region
        regions.append(menu_region)

        #for each character in play
        for i, character in enumerate(CHARACTERS):

            #create a region for that character
            character_region: Region = Region(character, self.player, self.multiworld)
            regions.append(character_region)

            #connect it to the main menu region
            menu_region.connect(character_region)

            #create all the locations for loot items that spawn in particular special rooms
            for j, room in enumerate(SPECIAL_ROOMS):
                loc = self.add_location( character_region, f"{character} {room}", location_start_id + i*character_offset + special_room_offset + j )

            #create all the locations for loot items that spawn in particular locked rooms
            for j, room in enumerate(LOCKED_ROOMS):
                loc = self.add_location( character_region, f"{character} {room}", location_start_id + i*character_offset + locked_room_offset + j )

            #counters for apid construction
            floor: int = 0
            enemy_count: int = 0
            shop_item_count: int = 0
            loot_item_count: int = 0


            #keep track of the previous region
            prev_region: Region = character_region

            #for each region in the dungeon
            for j, area in enumerate(DUNGEON_REGIONS):

                #create an ap region for that region
                area_region: Region = Region(f"{character} {area}", self.player, self.multiworld)

                #connect it to the previous region
                prev_region.connect(area_region)
                #add the region
                regions.append(area_region)

                #create all the shop location items
                for loc_num in range(MAX_SHOP_LOCATIONS[j]):
                    loc = self.add_location( area_region, f"{character} {area} Shop {loc_num+1}", location_start_id + i*shop_character_offset + shop_offset + shop_item_count )
                    shop_item_count += 1

                #create all the random loot item locations
                for loc_num in range(MAX_LOOT_LOCATIONS[j]):
                    loc = self.add_location( area_region, f"{character} {area} Item {loc_num+1}", location_start_id + i*character_offset + loot_item_offset + loot_item_count )
                    loot_item_count += 1

                #for each floor on that region
                for _ in range(5):
                    #TODO get the ruleset for this floor, and use it for all the items

                    #for each new enemy that appears on that floor
                    for enemy in ENEMIES[floor]:
                        #create a location for it
                        loc = self.add_location( area_region, f"{character} {enemy} Kill", location_start_id + i*character_offset + enemy_count)
                        #increment the number of enemy locations added
                        enemy_count += 1

                    #create a location for clearing the floor
                    loc = self.add_location( area_region, f"{character} Clear Floor {floor+1}", location_start_id + i*character_offset + level_clears_offset + floor)

                    #increment the floor
                    floor += 1

                #update the previous region
                prev_region = area_region

        #add all the regions (and locations) to the multiworld
        self.multiworld.regions.extend(regions)


    def create_item(self, name: str) -> SPDItem:
        return SPDItem(name, self.item_data_table[name].type, self.item_data_table[name].id, self.player)

    def create_items(self) -> None:
        item_pool: list[SPDItem] = []

        locations_left: int = len(self.get_locations())

        for item, count in self.active_items.items():
            for _ in range(count):
                item_pool.append(self.create_item(item))
                locations_left -= 1

        #TODO change this to be more dynamic later
        while locations_left > 0:
            item_pool.append(self.create_item(item_names.poison_trap))
            locations_left -= 1

        self.multiworld.itempool.extend(item_pool)


    def set_rules(self) -> None:
        #TODO create rules based on logic (anything that isn't in create_regions)
        pass

    #Include generate_basic() here for non-logic stuff, if any shows up

