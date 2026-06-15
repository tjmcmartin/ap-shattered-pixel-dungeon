package com.shatteredpixel.shatteredpixeldungeon.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.ExitButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.TitleBackground;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.glscripts.Script;
import com.watabou.glwrap.Blending;
import com.watabou.glwrap.Quad;
import com.watabou.glwrap.Texture;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextInput;
import com.watabou.utils.GameMath;
import com.watabou.utils.RectF;

public class ConnectScene extends PixelScene {

    private RenderedTextBlock addressTitle;
    private RenderedTextBlock slotNameTitle;
    private RenderedTextBlock passwordTitle;
    private TextInput addressField;
    private TextInput slotNameField;
    private TextInput passwordField;
    private IconButton btnExit;
    private Stage stage;

    @Override
    public void create() {

        super.create();

        float w = Camera.main.width;
        float h = Camera.main.height;
        RectF insets = getCommonInsets();

        SpriteBatch.overrideVertexType = Mesh.VertexDataType.VertexArray;
        Viewport viewport = new Viewport() {};
        viewport.setWorldSize(w, h);
        viewport.setScreenBounds(0, 0, Game.width, Game.height);
        viewport.setCamera(new OrthographicCamera());
        stage = new Stage(viewport);
        Game.inputHandler.addInputProcessor(stage);

//        stage.getViewport().update((int) h, (int) w, true);

        TitleBackground BG = new TitleBackground((int) h, (int) w);
        add(BG);

        w -= insets.left + insets.right;
        h -= insets.top + insets.bottom;

        float START_Y = insets.top;
        float gap = 15;
        float bigGap = 25;

        addressTitle = PixelScene.renderTextBlock( Messages.get(this, "address"), 24 );
        addressTitle.hardlight(Window.TITLE_COLOR);
        PixelScene.align(addressTitle);
        add(addressTitle);
        addressTitle.setPos(w/2 - addressTitle.width()/2, START_Y + addressTitle.height());

        addressField = new TextInput(Chrome.get(Chrome.Type.TOAST_WHITE), false, 50, true, stage) {
            @Override
            public void downPressed() {
                addressField.nextField();
            }
            @Override
            public void upPressed() {
                addressField.prevField();
            }
        };
        addressField.setText("");
        addressField.setMaxLength(30);
        add(addressField);
        addressField.setRect(w/2 - addressTitle.width()/2, addressTitle.bottom()+gap, addressTitle.width(), 16);

        slotNameTitle = PixelScene.renderTextBlock( Messages.get(this, "slot_name"), 24 );
        slotNameTitle.hardlight(Window.TITLE_COLOR);
        PixelScene.align(slotNameTitle);
        add(slotNameTitle);
        slotNameTitle.setPos(w/2 - slotNameTitle.width()/2, addressField.bottom() + slotNameTitle.height() + bigGap);

        slotNameField = new TextInput(Chrome.get(Chrome.Type.TOAST_WHITE), false, 50, false, stage) {
            @Override
            public void downPressed() {
                slotNameField.nextField();
            }
            @Override
            public void upPressed() {
                slotNameField.prevField();
            }
        };
        slotNameField.setText("FILLER");
        slotNameField.setMaxLength(30);
        add(slotNameField);
        slotNameField.setRect(w/2 - slotNameTitle.width()/2, slotNameTitle.bottom()+gap, slotNameTitle.width(), 16);

        passwordTitle = PixelScene.renderTextBlock( Messages.get(this, "password"), 24 );
        passwordTitle.hardlight(Window.TITLE_COLOR);
        PixelScene.align(passwordTitle);
        add(passwordTitle);
        passwordTitle.setPos(w/2 - passwordTitle.width()/2, slotNameField.bottom() + passwordTitle.height() + bigGap);

        passwordField = new TextInput(Chrome.get(Chrome.Type.TOAST_WHITE), false, 50, false, stage) {
            @Override
            public void downPressed() {
                passwordField.nextField();
            }
            @Override
            public void upPressed() {
                passwordField.prevField();
            }
        };
        passwordField.setText("FILLER");
        passwordField.setMaxLength(30);
        add(passwordField);
        passwordField.setRect(w/2 - passwordTitle.width()/2, passwordTitle.bottom()+gap, passwordTitle.width(), 16);

        btnExit = new ExitButton();
        int ofs = PixelScene.landscape() ? 0 : 4;
        btnExit.setPos(Camera.main.width - btnExit.width() - ofs, ofs);
        add(btnExit);
        btnExit.active = true;

        fadeIn();

    }

    @Override
    public void update() {
        super.update();

        stage.act(Game.elapsed);
    }

    @Override
    public void draw() {
        super.draw();

        Quad.releaseIndices();
        Script.unuse();
        Texture.clear();

        stage.draw();

        Quad.bindIndices();
        Blending.useDefault();
    }

    @Override
    protected void onBackPressed() {
        ShatteredPixelDungeon.switchScene(TitleScene.class);
    }

}
