package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.ui.Component;

public class APStatLabel extends Component {


    private final float MAX_GAP = 15;

    private BitmapText text;
    private Image icon;
    private float gap;

    public APStatLabel(String textString, Image iconImage) {
        this(textString, iconImage, 40);
    }
    public APStatLabel(String textString, Image iconImage, float gapPxls) {

        this.gap = Math.min(gapPxls, MAX_GAP);

        text = new BitmapText(textString, PixelScene.pixelFont);
        add( text );

        icon = iconImage;
        add( icon );

        layout();
    }

    @Override
    protected void layout() {
        text.x = x;
        text.y = y + (icon.height() - text.height())/4;

        icon.x = text.x + text.width() + gap;

        icon.y = y;

        PixelScene.align(text);
        PixelScene.align(icon);

        width = text.width() + gap + icon.width();
        height = Math.max(text.height(), icon.height());
    }

    public void setGap(float gapPxls) {
        gap = gapPxls;
        layout();
    }

    public void setText(HeroClass hero, String txt) {
        text.text(txt);
        layout();
    }
}
