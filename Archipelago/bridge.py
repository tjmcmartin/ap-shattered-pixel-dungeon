import socket
from ap_client import APClient

from typing import TYPE_CHECKING

if TYPE_CHECKING:
    # This block only runs during static type analysis
    from _typeshed import ReadableBuffer, WriteableBuffer


ap: APClient = APClient()

def send(conn: socket.socket, message: str) -> None:
    conn.sendall(f"{message}\n".encode())

def handle_check_location(location_id: int) -> int:
    #TODO fix this
    return 1001;

async def handle_message(conn: socket.socket, raw: str) -> None:
     
    parts: list[str] = raw.split("|")
    command: str = parts[0]

    match command:
        case "CHECK_LOCATION":
            location_id: int = int(parts[1])

            item_id: int = await ap.send_location_check(location_id)

            
            send(conn, f"RECEIVE_ITEM|{item_id}")
            print("Response sent")
        case _:
            print(f"Unknown command: {raw}")

def main() -> None:
    """Start the local bridge server."""

    server: socket.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind(("localhost", 38281))
    server.listen(1)

    print("Waiting for SPD connection...")

    connection, _ = server.accept()

    while True:
        raw: str = connection.recv(1024).decode().strip()

        if not raw:
            break

        handle_message(connection, raw)


if __name__ == "__main__":
    main()