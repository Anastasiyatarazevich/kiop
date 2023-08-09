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

public class MyGdxGame extends Game {

    public OrthographicCamera camera;
    public SpriteBatch batch;
    public Vector3 touch;

    public ScreenProofreadingTest screenProofreadingTest;
    public ScreenOverlayShapes screenOverlayShapes;
    public ScreenSchulteTable screenSchulteTable;
    public ScreenMenu screenMenu;

    public BitmapFont commonFont;
    public BitmapFont smallFont;
    public BitmapFont largeFont;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        touch = new Vector3();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ApplicationSettings.SCR_WIDTH, ApplicationSettings.SCR_HEIGHT);

        commonFont = new BitmapFont(Gdx.files.internal("fonts/ArialGray64.fnt"));

        screenProofreadingTest = new ScreenProofreadingTest(this);
        screenOverlayShapes = new ScreenOverlayShapes(this);
        screenSchulteTable = new ScreenSchulteTable(this);
        screenMenu = new ScreenMenu(this);

        setScreen(screenMenu);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
