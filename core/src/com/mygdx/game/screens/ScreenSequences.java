package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionSequences;
import com.mygdx.game.testSessions.sessionsStates.StateSequences;
import com.mygdx.game.ui.BackgroundPixmapView;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.ui.View;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;

import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenSequences implements Screen {

    MyGdxGame myGdxGame;

    SessionSequences testSession;

    SceneHelper sceneGreeting;
    SceneHelper sceneShowingSequences;
    SceneHelper scenePassed;

    public ScreenSequences(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        testSession = new SessionSequences();

        sceneGreeting = new SceneHelper();
        scenePassed = new SceneHelper();
        sceneShowingSequences = new SceneHelper();

        BackgroundPixmapView backgroundView = new BackgroundPixmapView(COLOR_BG_GRAY);

        TextButton buttonStart = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                -1, 155
        );

        buttonStart.setOnClickListener(onButtonStartClicked);

        sceneGreeting.addActor(backgroundView);
        sceneGreeting.addActor(buttonStart);

        sceneShowingSequences.addActor(backgroundView);

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
                case FINDING_SEQUENCES:
                    if (justTouched) sceneShowingSequences.checkHits(myGdxGame);
                    sceneShowingSequences.drawScene(myGdxGame);
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

    void startTest() {
        testSession.startTest();
    }

    View.OnClickListener onButtonStartClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            startTest();
        }
    };
}
