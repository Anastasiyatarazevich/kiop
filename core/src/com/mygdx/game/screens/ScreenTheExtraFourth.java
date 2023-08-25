package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionTheExtraFourth;
import com.mygdx.game.ui.BackgroundPixmap;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.ui.View;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;

import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenTheExtraFourth implements Screen {

    MyGdxGame myGdxGame;
    SessionTheExtraFourth testSession;

    SceneHelper scenePassed;
    SceneHelper sceneGreeting;
    SceneHelper sceneCardShowing;

    public ScreenTheExtraFourth(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        testSession = new SessionTheExtraFourth();

        scenePassed = new SceneHelper();
        sceneGreeting = new SceneHelper();
        sceneCardShowing = new SceneHelper();

        BackgroundPixmap background = new BackgroundPixmap(COLOR_BG_GRAY);

        TextButton startButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                775, 155
        );

        startButton.setOnClickListener(onStartButtonClicked);

        sceneGreeting.addActor(background);
        sceneGreeting.addActor(startButton);

        sceneCardShowing.addActor(background);

        scenePassed.addActor(background);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        boolean justTouched = RenderHelper.checkTouch(myGdxGame);
        RenderHelper.draw(myGdxGame, drawScenes, justTouched);
    }

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

    RenderHelper.DrawScenes drawScenes = new RenderHelper.DrawScenes() {
        @Override
        public void draw(boolean justTouched) {
            switch (testSession.testState) {
                case PASSED:
                    if (justTouched) scenePassed.checkHits(myGdxGame);
                    scenePassed.drawScene(myGdxGame);
                    break;
                case CARDS_SHOWING:
                    if (justTouched) sceneCardShowing.checkHits(myGdxGame);
                    sceneCardShowing.drawScene(myGdxGame);
                    break;
                case GREETING:
                    if (justTouched) sceneGreeting.checkHits(myGdxGame);
                    sceneGreeting.drawScene(myGdxGame);
                    break;
            }
        }
    };

    View.OnClickListener onStartButtonClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.startTest();
        }
    };
}
