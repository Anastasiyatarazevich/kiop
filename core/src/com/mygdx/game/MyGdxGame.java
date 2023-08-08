package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.ScreenMenu;
import com.mygdx.game.screens.ScreenOverlayShapes;
import com.mygdx.game.screens.ScreenProofreadingTest;
import com.mygdx.game.screens.ScreenSchulteTable;
import com.mygdx.game.utils.ApplicationSettings;

public class MyGdxGame extends ApplicationAdapter {

    public OrthographicCamera camera;
    public SpriteBatch batch;

    public ScreenProofreadingTest screenProofreadingTest;
    public ScreenOverlayShapes screenOverlayShapes;
    public ScreenSchulteTable screenSchulteTable;
    public ScreenMenu screenMenu;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ApplicationSettings.SCR_WIDTH, ApplicationSettings.SCR_HEIGHT);


    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
