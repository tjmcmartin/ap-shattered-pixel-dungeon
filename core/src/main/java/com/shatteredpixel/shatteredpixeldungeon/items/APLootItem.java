package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.ap.APLocation;
import com.shatteredpixel.shatteredpixeldungeon.ap.APManager;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class APLootItem extends Item {
    {
        image = ItemSpriteSheet.AP_ITEM;
    }

    private APLocation location;

    public APLootItem() {

    }
    public APLootItem(boolean forShop) {
        this(Dungeon.depth, forShop);
    }
    public APLootItem(int depth, boolean forShop) {
        if (forShop) location = APManager.getNextShopLocation(depth);
        else location = APManager.getNextLootLocation(depth);
    }
    public APLootItem(APLocation location) {
        this.location = location;
    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        return new ArrayList<>();
    }

    @Override
    public boolean doPickUp(Hero hero, int pos) {
        GameScene.pickUp(this, pos);
        hero.spendAndNext( pickupDelay() );
        Sample.INSTANCE.play(Assets.Sounds.ITEM);

        APManager.checkLocation(location, true);

        return true;
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }

    @Override
    public String name() {
        //TODO change this once ap server connection is implemented
        return "AP_Item at " + location.name();
    }

    @Override
    public String desc() {
        //TODO change this once ap server connection is implemented
        return "Item Description goes here";
    }

    @Override
    public int value() {
        //TODO change this once ap server connection is implemented
        return 30;
    }

    private static final String LOCATION = "location";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(LOCATION, location);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        location = bundle.getEnum(LOCATION, APLocation.class);
    }
}
