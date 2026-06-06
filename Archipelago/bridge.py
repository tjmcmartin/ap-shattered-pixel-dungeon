import socket

from typing import TYPE_CHECKING

if TYPE_CHECKING:
    # This block only runs during static type analysis
    from _typeshed import ReadableBuffer

def main() -> None:
    """Start the local bridge server."""

    server: socket.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind(("localhost", 38281))
    server.listen(1)

    print("Waiting for SPD connection...")

    connection, address = server.accept()

    print(f"Connected from {address}")

    while True:
        message: bytes = connection.recv(1024)
        parts: list[str] = message.decode().strip().split()
        command: str = parts[0]

        if not message:
            print("Connection ended")
            break

        print(f"Command: {command} id={id}")

        if command == "CHECK_LOCATION":
            location_id: int = int(parts[1])

            #TODO this is where archipelago will decide item
            item_id = 1001

            
            connection.sendall(f"RECEIVE_ITEM {item_id}\n".encode())
            print("Response sent")

if __name__ == "__main__":
    main()