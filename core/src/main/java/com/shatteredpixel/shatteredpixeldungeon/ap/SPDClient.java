package com.shatteredpixel.shatteredpixeldungeon.ap;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;

import java.util.Objects;

import io.github.archipelagomw.Client;
import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ConnectionResultEvent;
import io.github.archipelagomw.network.ConnectionResult;

public class SPDClient extends Client {

    private int team;
    private int slot;
    private String seedName;
    private String saveID;

    public SPDClient() {
        getEventManager().registerListener(this);
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
