import asyncio

from Archipelago.Archipelago.worlds.stardew_valley.content.override import override
from Archipelago.CommonClient import CommonContext
from queue import Queue
from typing import Any

class APClient(CommonContext):
    def __init__(self) -> None:
        super().__init__()

        self.pending_items: Queue[int] = Queue()

    async def connect_to_ap(self, address: str, slot_name: str, password: str) -> None:
        self.auth = slot_name
        self.password = password

        await self.connect(address)

    async def send_location_check(self, location_id: int) -> None:
        await self.check_locations({location_id})

    def get_pending_items(self) -> list[int]:
        items = self.pending_items.copy()
        self.pending_items.clear()
        return items


    def on_socket_opened(self) -> None:
        print("[AP] Socket opened")

    def on_socket_closed(self) -> None:
        print("[AP] Socket closed")

    def on_error(self, e: Exception) -> None:
        print(f"[AP] Error: {e}")

    #item event hook

    @override
    def on_package(self, cmd: str, args: dict[str, Any]) -> None:

        print(f"[AP] Package: {cmd}")
        print(args)

        match cmd:
            case "ReceivedItems":
                for item in args["items"]:
                    item_id: int = item.item

                    self.handle_received_item(item_id)

    def handle_received_item(self, item_id: int) -> None:
        print(f"[AP] Received item: {item_id}")

        self.pending_items.put(item_id)