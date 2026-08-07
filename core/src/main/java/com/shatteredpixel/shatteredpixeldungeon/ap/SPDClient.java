package com.shatteredpixel.shatteredpixeldungeon.ap;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;

import java.util.Objects;

import io.github.archipelagomw.Client;
import io.github.archipelagomw.Print.APPrintPart;
import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ConnectionResultEvent;
import io.github.archipelagomw.events.LocationInfoEvent;
import io.github.archipelagomw.events.PrintJSONEvent;
import io.github.archipelagomw.events.ReceiveItemEvent;
import io.github.archipelagomw.network.ConnectionResult;
import io.github.archipelagomw.parts.NetworkItem;

public class SPDClient extends Client {

    private int team;
    private int slot;
    private String seedName;
    private String saveID;

    public SPDClient() {
        getEventManager().registerListener(this);
        this.setItemsHandlingFlags(0b001);
    }

    public String getSaveID() {
        return saveID;
    }

    @ArchipelagoEventListener
    public void onConnectionResult(ConnectionResultEvent event) {

        System.out.println("Result received: "+event.getResult());

        if (event.getResult() == ConnectionResult.Success) {
            team = event.getTeam();
            slot = event.getSlot();
            seedName = event.getSeedName();
            saveID = seedName + ":" + slot;

            APConnector.onSuccessfulConnect(saveID);
        } else {
            APConnector.onFailedConnect(event.getResult().name());
        }
    }

    @ArchipelagoEventListener
    public void onReceiveItemEvent(ReceiveItemEvent event) {
        System.out.println("Item \"" + event.getItemName() + "\" received");
        APConnector.receiveItem(event.getItemID(), event.getItemName(), event.getPlayerName());
    }

    @ArchipelagoEventListener
    public void onLocationInfoEvent(LocationInfoEvent event) {
        for (NetworkItem location : event.locations) {
            //if the item is local
            if (location.playerName.equals(getMyName())) {
                //print the message for finding your own item
                APManager.out( Messages.get(APConnector.class, "item_sent_self", location.itemName) );
                //give yourself the item now
                APConnector.receiveItem(location.itemID);
            //if the item is not local
            } else {
                //print the message for finding someone else item
                APManager.out( Messages.get(APConnector.class, "item_sent", location.itemName, location.playerName) );
            }
        }
    }

//    @ArchipelagoEventListener
//    public void onPrintJSONEvent(PrintJSONEvent event) {
//        switch (event.type) {
//            case ItemSend:
//                int sender = event.item.playerID;
//
//                System.out.println("player= "+event.player+", receiveing= "+event.apPrint.receiving+", slot= "+slot);
//                if(event.player == event.apPrint.receiving && event.player == slot) {
//                    APManager.out( Messages.get(APConnector.class, "item_sent_self", event.item.itemName) );
//                } else if (event.player == slot) {
//                    APManager.out( Messages.get(APConnector.class, "item_sent", event.item.itemName, "Name") );
//                } else {
//                    APManager.out( Messages.get(APConnector.class, "item_received", event.item.itemName, "Name") );
//                }
//        }
//        System.out.println("msg="+event.apPrint.getPlainText());
//    }


    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();

        //TODO let the player know there was an error
    }

    @Override
    public void onClose(String reason, int attemptingReconnect) {

        //TODO notify the player

        System.out.println(reason);

    }

}
