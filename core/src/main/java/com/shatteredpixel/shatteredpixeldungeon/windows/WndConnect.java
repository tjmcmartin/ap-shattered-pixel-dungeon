package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;

public class WndConnect extends Window {


    protected static final int WIDTH_MIN    = 120;
    protected static final int WIDTH_MAX    = 220;
    protected static final int GAP	= 2;

    private static RenderedTextBlock text;
    private static final String textBody = "Attempting to connect to server";
    private static String dots= "";
    private static IconTitle titlebar;

    private static float timer = 0;

    public WndConnect() {

        super();

        int width = WIDTH_MIN;

        titlebar = new IconTitle( new ItemSprite(ItemSpriteSheet.AP_ITEM), "Archipelago Connection" );
        titlebar.setRect(0, 0, width, 0);
        add(titlebar);

        text = PixelScene.renderTextBlock(6);
        text.setHightlighting(false);
        text.text(textBody);
        text.setPos(0, titlebar.bottom() + 2*GAP);
        add(text);

        while(PixelScene.landscape()
                && text.bottom() > targetHeight()
                && width < WIDTH_MAX) {
            width += 20;
            titlebar.setRect(0, 0, width, 0);
            text.setPos( 0, titlebar.bottom() + 2*GAP);
            text.maxWidth(width);

        }

        bringToFront(titlebar);

        resize( width, (int)text.bottom() + 2);


    }

    @Override
    public void onBackPressed() {}

    @Override
    public synchronized void update() {
        super.update();

        timer += Game.elapsed;

        if (timer >= 0.5f) {
            timer -= 0.5f;
            updateDots();
        }

    }

    private void updateDots() {
        dots += ".";
        if (dots.length() > 3)  dots = "";
        text.text(textBody + dots);
    }

    protected float targetHeight() {
        return PixelScene.MIN_HEIGHT_L - 10;
    }
}
