package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionSchulteTable;
import com.mygdx.game.testSessions.sessionsStates.StateSchulteTable;
import com.mygdx.game.ui.BackgroundPixmap;
import com.mygdx.game.ui.View;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;

import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenSchulteTable implements Screen {

    MyGdxGame myGdxGame;
    SessionSchulteTable testSession;
    boolean justTouched;

    SceneHelper sceneGreeting;
    SceneHelper sceneTableShowing;
    SceneHelper sceneBreak;
    SceneHelper scenePassed;

    public ScreenSchulteTable(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        testSession = new SessionSchulteTable();

        sceneGreeting = new SceneHelper();
        sceneTableShowing = new SceneHelper();
        sceneBreak = new SceneHelper();
        scenePassed = new SceneHelper();

        BackgroundPixmap background = new BackgroundPixmap(COLOR_BG_GRAY);

        scenePassed.addActor(background);
        sceneBreak.addActor(background);
        sceneTableShowing.addActor(background);
        sceneGreeting.addActor(background);
    }

    @Override
    public void show() {
        if (testSession.testState == StateSchulteTable.GREETING)
            testSession.startSession();
    }

    @Override
    public void render(float delta) {
        justTouched = RenderHelper.checkTouch(myGdxGame);
        RenderHelper.draw(myGdxGame, drawScenes);
    }

    RenderHelper.DrawScenes drawScenes = new RenderHelper.DrawScenes() {
        @Override
        public void draw() {
            switch (testSession.testState) {
                case BREAK:
                    if (justTouched) sceneBreak.checkHits(myGdxGame);
                    sceneBreak.drawScene(myGdxGame);
                    break;
                case PASSED:
                    if (justTouched) scenePassed.checkHits(myGdxGame);
                    scenePassed.drawScene(myGdxGame);
                    break;
                case TABLE_SHOWING:
                    if (justTouched) sceneTableShowing.checkHits(myGdxGame);
                    sceneTableShowing.drawScene(myGdxGame);
                    break;
                case GREETING:
                    if (justTouched) sceneGreeting.checkHits(myGdxGame);
                    sceneGreeting.drawScene(myGdxGame);
                    break;
            }
        }
    };

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
