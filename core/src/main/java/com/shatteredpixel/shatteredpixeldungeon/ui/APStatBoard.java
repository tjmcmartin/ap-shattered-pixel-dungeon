package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Camera;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.Image;
import com.watabou.noosa.ui.Component;

public class APStatBoard extends Component {

    private final float MAX_WIDTH = 150;
    private final float MAX_HEIGHT = 200;
    private APStatLabel levelClears;
    private APStatLabel firstKills;
    private APRegionStatLabel sewersStats;
    private APRegionStatLabel prisonStats;
    private APRegionStatLabel cavesStats;
    private APRegionStatLabel cityStats;
    private APRegionStatLabel hallsStats;

    public APStatBoard(float w, float h) {

        levelClears = new APStatLabel("0/25", Icons.get(Icons.STAIRS));
        add(levelClears);
        firstKills = new APStatLabel("0/25", new Image( new RatSprite() ));
        add(firstKills);

        sewersStats = new APRegionStatLabel("Sewers");
        add(sewersStats);
        prisonStats = new APRegionStatLabel("Prison");
        add(prisonStats);
        cavesStats = new APRegionStatLabel("Caves");
        add(cavesStats);
        cityStats = new APRegionStatLabel("City");
        add(cityStats);
        hallsStats = new APRegionStatLabel("Halls");
        add(hallsStats);

        setSize( Math.min(w, MAX_WIDTH) , Math.min(h, MAX_HEIGHT) );
    }

    @Override
    protected void layout() {

        float gap = getExtraY()/3;

        float labelGap = (width - (levelClears.width() + firstKills.width()) )/6;
        levelClears.setGap(labelGap);
        firstKills.setGap(labelGap);

        float labelOffset = (width - (levelClears.width() + firstKills.width()) )/4;
        levelClears.setPos(x + labelOffset, y);
        firstKills.setPos(x + width/2 + labelOffset, y);

        float offset = getOffset();
        sewersStats.setPos(x + offset, levelClears.bottom() + gap);
        prisonStats.setPos(x + width/2 + offset, levelClears.bottom() + gap);
        cavesStats.setPos(x + offset, sewersStats.bottom() + gap);
        cityStats.setPos(x + width/2 + offset, sewersStats.bottom() + gap);
        hallsStats.setPos(x + offset, cavesStats.bottom() + gap);
    }

    private float getExtraY() {
        return height - (levelClears.height() + sewersStats.height() + cavesStats.height() + hallsStats.height());
    }

    private float getOffset() {
        return Math.min( (width - (sewersStats.width() + prisonStats.width()) )/4 , (width - (cavesStats.width() + cityStats.width()) )/4 );
    }
}
