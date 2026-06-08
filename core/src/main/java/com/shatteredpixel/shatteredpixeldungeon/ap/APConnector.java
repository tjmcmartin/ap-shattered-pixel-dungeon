package com.shatteredpixel.shatteredpixeldungeon.ap;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class APConnector {

    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void connect() {
        try {
            socket = new Socket("localhost", 38281);

            out = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            out.println("HELLO");

            startListeningThread();

            System.out.println("APConnector connected to Python bridge");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendCheck(long locationID) {
        if (!isConnected()) {
            System.out.println("APConnector not ready");
            return;
        }
        out.println("CHECK_LOCATION|" + locationID);
        System.out.println("Check sent to bridge.");
    }

    public static void startListeningThread() {
        new Thread(() -> {
            try {
                while (true) {
                    String line = in.readLine();

                    if (line == null) {
                        break;
                    }

                    System.out.println("Received message: " + line);
                    handleMessage(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void handleMessage(String line) {
        String[] parts = line.split("\\|");

        String command = parts[0];

        switch (command) {
            case "RECEIVE_ITEM":
                int itemId = Integer.parseInt(parts[1]);

                System.out.println("itemId=" + itemId + ", item=" + APItem.fromId(itemId));

                APManager.receiveItem(APItem.fromId(itemId));

                break;
            default:
                System.out.println("Unknown command: " + line);
        }

    }

    public static boolean isConnected() {
        return socket != null && socket.isConnected() && out != null;
    }

}
