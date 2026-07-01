package com.shatteredpixel.shatteredpixeldungeon.scenes;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.shatteredpixel.shatteredpixeldungeon.APDataSaver;
import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.ap.APManager;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.ExitButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.StyledButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.TitleBackground;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndGameInProgress;
import com.watabou.glscripts.Script;
import com.watabou.glwrap.Blending;
import com.watabou.glwrap.Quad;
import com.watabou.glwrap.Texture;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.TextInput;
import com.watabou.utils.RectF;

import java.io.IOException;

public class ConnectScene extends PixelScene {

    private RenderedTextBlock addressTitle;
    private RenderedTextBlock slotNameTitle;
    private RenderedTextBlock passwordTitle;
    private TextInput addressField;
    private TextInput slotNameField;
    private TextInput passwordField;
    private StyledButton btnConnect;
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

        stage.getViewport().update((int) h, (int) w, true);

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
            public void enterPressed() {
                addressField.nextField();
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

            @Override
            public void enterPressed() {
                slotNameField.nextField();
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
            public void upPressed() {
                passwordField.prevField();
            }

            @Override
            public void enterPressed() {
                connect();
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

        btnConnect = new StyledButton(Chrome.Type.GREY_BUTTON_TR, Messages.get(this, "connect")) {
            @Override
            protected void onClick() {
                connect();
            }
        };
        btnConnect.icon(Icons.get(Icons.ENTER));
        add(btnConnect);

        btnConnect.setRect(w/2 - passwordField.width()/2, passwordField.bottom() + gap, passwordField.width(), 20);

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
        if (btnExit.active) {
            ShatteredPixelDungeon.switchScene(TitleScene.class);
        } else {
            super.onBackPressed();
        }
    }

    //TODO change this to communicate with ap server
    private void connect() {

        String port = addressField.getText();
        String name = slotNameField.getText();
        String password = passwordField.getText();

        //Connect to ap server here

        //reset APManager attributes
        APManager.reset();
        Generator.resetDefaults();

        APManager.setPlayerInfo(port, name);

        int slot = APDataSaver.findSlot(port, name);
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

}
