package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShopkeeperSprite;
import com.watabou.noosa.Image;
import com.watabou.noosa.ui.Component;

public class APRegionStatLabel extends Component {

    private RenderedTextBlock title;
    private APStatLabel apLootItems;
    private APStatLabel apShopItems;

    public APRegionStatLabel(String region) {
        this(region, 10);
    }
    public APRegionStatLabel(String region, int size) {

        title = PixelScene.renderTextBlock(region, size);
        add(title);

        apLootItems = new APStatLabel("0/25", new ItemSprite(ItemSpriteSheet.AP_ITEM));
        add(apLootItems);

        apShopItems = new APStatLabel("0/25", new Image( new ShopkeeperSprite() ));
        add(apShopItems);

        layout();

    }

    @Override
    protected void layout() {

        float gap = 5;

        title.setPos(x, y);
        apLootItems.setPos(x, title.bottom()+gap);
        apShopItems.setPos(x, apLootItems.bottom());

        width = Math.max(title.width(), Math.max(apLootItems.width(), apShopItems.width()) );
        height = title.height() + gap + apLootItems.height() + apShopItems.height();

        title.setPos(x + (width - title.width())/2 , y);
        PixelScene.align(title);
    }
}
