package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.ap.APLocation;
import com.shatteredpixel.shatteredpixeldungeon.ap.APManager;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class APLootItem extends Item {
    {
        image = ItemSpriteSheet.AP_ITEM;
    }

    private APLocation location;

    public APLootItem() {
        this(Dungeon.depth);
    }
    public APLootItem(int depth) {
        location = APManager.getNextShopLocation(depth);
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

        APManager.checkLocation(location);

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
        return 300;
    }
}
