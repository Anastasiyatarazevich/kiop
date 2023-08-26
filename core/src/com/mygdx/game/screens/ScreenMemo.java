package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionMemo;
import com.mygdx.game.ui.BackgroundPixmapView;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;

import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenMemo implements Screen {

    MyGdxGame myGdxGame;

    SessionMemo testSession;

    SceneHelper sceneGreeting;
    SceneHelper sceneShowingCards;
    SceneHelper scenePassed;

    public ScreenMemo(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        testSession = new SessionMemo();

        sceneGreeting = new SceneHelper();
        sceneShowingCards = new SceneHelper();
        scenePassed = new SceneHelper();

        BackgroundPixmapView backgroundView = new BackgroundPixmapView(COLOR_BG_GRAY);

        TextButton startButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                -1, 155
        );

        sceneGreeting.addActor(backgroundView);
        sceneGreeting.addActor(startButton);

        sceneShowingCards.addActor(backgroundView);

        scenePassed.addActor(backgroundView);
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
                case REMEMBERING_CARDS:
                    if (justTouched) sceneShowingCards.checkHits(myGdxGame);
                    sceneShowingCards.drawScene(myGdxGame);
                    break;
                case GREETING:
                    if (justTouched) sceneGreeting.checkHits(myGdxGame);
                    sceneGreeting.drawScene(myGdxGame);
                    break;
                case PASSED:
                    if (justTouched) scenePassed.checkHits(myGdxGame);
                    scenePassed.drawScene(myGdxGame);
                    break;
            }
        }
    };
}
