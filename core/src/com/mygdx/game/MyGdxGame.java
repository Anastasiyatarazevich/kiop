package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.screens.ScreenMenu;
import com.mygdx.game.screens.ScreenOverlayShapes;
import com.mygdx.game.screens.ScreenProofreadingTest;
import com.mygdx.game.screens.ScreenSchulteTable;
import com.mygdx.game.utils.ApplicationSettings;
import com.mygdx.game.utils.SessionHelper;
import com.mygdx.game.utils.WebHelper;

public class MyGdxGame extends Game {

    public OrthographicCamera camera;
    public SpriteBatch batch;
    public Vector3 touch;

    public ScreenProofreadingTest screenProofreadingTest;
    public ScreenOverlayShapes screenOverlayShapes;
    public ScreenSchulteTable screenSchulteTable;
    public ScreenMenu screenMenu;

    public BitmapFont commonFont;
    public BitmapFont fontArialBlack64;
    public BitmapFont fontArialBlackBold64;
    public BitmapFont fontArialGray64;
    public BitmapFont fontArialGray32;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        touch = new Vector3();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ApplicationSettings.SCR_WIDTH, ApplicationSettings.SCR_HEIGHT);

        // todo: rebuild fonts
        commonFont = new BitmapFont(Gdx.files.internal("fonts/ArialGray64.fnt"));
        fontArialBlack64 = new BitmapFont(Gdx.files.internal("fonts/ArialBlack64.fnt"));
        fontArialBlackBold64 = new BitmapFont(Gdx.files.internal("fonts/ArialBlackBold64.fnt"));
        fontArialGray64 = new BitmapFont(Gdx.files.internal("fonts/ArialGray64.fnt"));
        fontArialGray32 = new BitmapFont(Gdx.files.internal("fonts/ArialGray32.fnt"));

        screenProofreadingTest = new ScreenProofreadingTest(this);
        screenOverlayShapes = new ScreenOverlayShapes(this);
        screenSchulteTable = new ScreenSchulteTable(this);
        screenMenu = new ScreenMenu(this);

        setScreen(screenMenu);
        //WebHelper.getRequest();

        Gdx.app.debug("param", SessionHelper.params);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
