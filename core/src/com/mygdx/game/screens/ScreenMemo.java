package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionMemo;
import com.mygdx.game.testSessions.sessionsStates.StateMemo;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.memo.TableMemoCardsView;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.memo.CardsPresetsMemo;

import static com.mygdx.game.utils.ApplicationSettings.*;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenMemo implements Screen {

    MyGdxGame myGdxGame;

    SessionMemo testSession;

    SceneHelper sceneGreeting;
    SceneHelper sceneShowingCards;
    SceneHelper scenePassed;

    TableMemoCardsView tableMemoCardsView;
    TimeCounterView timeCounterView;

    public ScreenMemo(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        testSession = new SessionMemo();

        sceneGreeting = new SceneHelper();
        sceneShowingCards = new SceneHelper();
        scenePassed = new SceneHelper();

        BackgroundPixmapView backgroundView = new BackgroundPixmapView(COLOR_BG_GRAY);
        tableMemoCardsView = new TableMemoCardsView(0, 140, CARD_SIZE, CARD_PADDING);

        TextButton startButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                -1, 155
        );

        TextView textViewRememberTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Запомни как можно больше карточек",
                -1, 1000
        );

        timeCounterView = new TimeCounterView(
                -1, 850, "",
                myGdxGame.fontArialBlack64,
                myGdxGame.fontArialBlack64,
                SECONDS_TO_REMEMBER,
                0
        );

        startButton.setOnClickListener(onButtonStartClicked);

        sceneGreeting.addActor(backgroundView);
        sceneGreeting.addActor(startButton);

        sceneShowingCards.addActor(backgroundView);
        sceneShowingCards.addActor(timeCounterView);
        sceneShowingCards.addActor(tableMemoCardsView);
        sceneShowingCards.addActor(textViewRememberTitle);

        scenePassed.addActor(backgroundView);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (timeCounterView.updateTimer() == 1 && testSession.testState == StateMemo.REMEMBERING_CARDS) {
            tableMemoCardsView.hideCards();
            testSession.hideCards();
        }

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
                case GUESSING_CARDS:
                case HIDING_CARDS:
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

    View.OnClickListener onButtonStartClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            tableMemoCardsView.setCards(CardsPresetsMemo.preset);
            testSession.startTest();
            timeCounterView.startTimer();
        }
    };
}
