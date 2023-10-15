package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.screens.*;
import com.mygdx.game.utils.ApplicationSettings;
import com.mygdx.game.utils.SessionHelper;

public class MyGdxGame extends Game {

    public OrthographicCamera camera;
    public SpriteBatch batch;
    public Vector3 touch;

    public ScreenProofreadingTest screenProofreadingTest;
    public ScreenTheExtraFourth screenTheExtraFourth;
    public ScreenRavenMatrices screenRavenMatrices;
    public ScreenOverlayShapes screenOverlayShapes;
    public ScreenSchulteTable screenSchulteTable;
    public ScreenSequences screenSequences;
    public ScreenNonsense screenNonsense;
    public ScreenMemo screenMemo;
    public ScreenMenu screenMenu;
    public ScreenStart screenStart;
    public ScreenEnd screenEnd;

    public BitmapFont fontArialBlack64;
    public BitmapFont fontArialBlack32;
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

        fontArialBlack64 = new BitmapFont(Gdx.files.internal("fonts/ArialBlack64.fnt"));
        fontArialBlack32 = new BitmapFont(Gdx.files.internal("fonts/ArialBlack32.fnt"));
        fontArialBlackBold64 = new BitmapFont(Gdx.files.internal("fonts/ArialBlackBold64.fnt"));
        fontArialGray64 = new BitmapFont(Gdx.files.internal("fonts/ArialGray64.fnt"));
        fontArialGray32 = new BitmapFont(Gdx.files.internal("fonts/ArialGray32.fnt"));

        screenProofreadingTest = new ScreenProofreadingTest(this);
        screenTheExtraFourth = new ScreenTheExtraFourth(this);
        screenRavenMatrices = new ScreenRavenMatrices(this);
        screenOverlayShapes = new ScreenOverlayShapes(this);
        screenSchulteTable = new ScreenSchulteTable(this);
        screenSequences = new ScreenSequences(this);
        screenNonsense = new ScreenNonsense(this);
        screenMemo = new ScreenMemo(this);
        screenMenu = new ScreenMenu(this);
        screenStart = new ScreenStart(this);
        screenEnd = new ScreenEnd(this);

        setScreen(screenStart);
        //WebHelper.getRequest();

        Gdx.app.debug("param", SessionHelper.params);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
