package com.shatteredpixel.shatteredpixeldungeon.ap;

import com.badlogic.gdx.Gdx;
import com.shatteredpixel.shatteredpixeldungeon.APDataSaver;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ConnectScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.HeroSelectScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.StartScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.watabou.noosa.Scene;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import io.github.archipelagomw.events.ConnectionResultEvent;

public class APConnector {

    private static SPDClient client = new SPDClient();

    public static void connect(String port, String name, String password) {
        client.setGame("Shattered Pixel Dungeon");
        client.setName(name);
        client.setPassword(password);

        try {
            client.connect(port);
        } catch (URISyntaxException e) {
            System.out.println("Invalid url");
            e.printStackTrace();
        }
    }

    public static SPDClient getClient() {
        return client;
    }

    public static void onSuccessfulConnect(String saveID) {
        if (!(ShatteredPixelDungeon.scene() instanceof ConnectScene)) return;

        //reset APManager attributes
        APManager.reset();
        Generator.resetDefaults();

        int slot = APDataSaver.findSlot(saveID);
        int total = APDataSaver.getTotalSlots();

        //If there is no slot that matches
        if (slot == -1) {

            System.out.println("Creating a new slot");

            //If there is an open slot
            if (total < GamesInProgress.MAX_SLOTS) {
                //Start a new game in that new slot
                GamesInProgress.selectedClass = null;
                GamesInProgress.curSlot = GamesInProgress.firstEmpty();

                ShatteredPixelDungeon.switchScene(HeroSelectScene.class);
            }
            //If there isn't an open slot
            else {
                System.out.println("You must replace a slot!");
                //Have the player chose a slot to replace
                ShatteredPixelDungeon.switchScene(StartScene.class);
            }
        //If there is a matching slot
        } else {

            System.out.println("Loading existing save...");

            //Load the slot for that data
            try {
                APDataSaver.load(slot);
            } catch (IOException e) {
                ShatteredPixelDungeon.reportException(e);
            }

            GamesInProgress.curSlot = slot;

            //If the slot has a game in progress
            if (GamesInProgress.gameExists(slot)) {
                System.out.println("Loading in-progress run...");
                Dungeon.hero = null;
                Dungeon.daily = Dungeon.dailyReplay = false;
                ActionIndicator.clearAction();
                InterlevelScene.mode = InterlevelScene.Mode.CONTINUE;
                ShatteredPixelDungeon.switchScene(InterlevelScene.class);
            }
            //If the game doesn't exist
            else {
                System.out.println("Start a new run!");
                //Let the user start a new run
                GamesInProgress.selectedClass = null;

                ShatteredPixelDungeon.switchScene(HeroSelectScene.class);
            }
        }
    }

    public static void onFailedConnect(String reason) {
        Scene scene = ShatteredPixelDungeon.scene();
        if (!(scene instanceof ConnectScene)) return;

        String msg = formatReason(reason);

        Gdx.app.postRunnable(() -> {
            ((ConnectScene) scene).onFailedConnect(msg);
        });
    }

    private static String formatReason(String reason) {
        return reason.replaceAll("(?<=[a-z])(?=[A-Z])", "_").toLowerCase();
    }

    public static void sendCheck(int id) {

        client.scoutLocations(new ArrayList<>(id));

        client.checkLocation(id);
    }

    public static void receiveItem(Long itemID, String itemName, String sendingPlayer) {
        APManager.out( Messages.get(APConnector.class, "item_received", itemName, sendingPlayer) );
        receiveItem(itemID);
    }

    public static void receiveItem(Long itemID) {
        APItem item = APItem.fromId( itemID.intValue() );
        System.out.println("id="+itemID+", item:"+item);
        APManager.receiveItem(item);
    }

}
