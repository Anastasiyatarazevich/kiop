package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionMemo;
import com.mygdx.game.testSessions.sessionsStates.StateMemo;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.alerts.AlertPause;
import com.mygdx.game.ui.memo.TableMemoCardsView;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;

import static com.mygdx.game.utils.ApplicationSettings.*;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

// TODO: remove the opportunity to select more than COUNT_OF_CARDS_TO_REMEMBER cards

public class ScreenMemo implements Screen {

    MyGdxGame myGdxGame;

    SessionMemo testSession;

    SceneHelper sceneGreeting;
    SceneHelper sceneShowingCards;
    SceneHelper scenePassed;

    TableMemoCardsView tableMemoCardsView;
    TimeCounterView timeCounterView;
    TextButton textButtonCompleteTest;
    TextView textViewRememberTitle;

    public ScreenMemo(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        testSession = new SessionMemo();

        sceneGreeting = new SceneHelper();
        sceneShowingCards = new SceneHelper();
        scenePassed = new SceneHelper();

        BackgroundPixmapView backgroundView = new BackgroundPixmapView(COLOR_BG_GRAY);

        tableMemoCardsView = new TableMemoCardsView(
                0, 140,
                MEMO_CARD_SIZE,
                MEMO_CARD_PADDING,
                onTableItemClickListener,
                onCardsReleasedListener
        );

        TextButton startButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                -1, 155
        );

        textViewRememberTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Запомни как можно больше карточек",
                -1, 1050
        );

        timeCounterView = new TimeCounterView(
                -1, 850, "",
                myGdxGame.fontArialBlack64,
                myGdxGame.fontArialBlack64,
                SECONDS_TO_REMEMBER,
                0
        );

        textButtonCompleteTest = new TextButton(
                myGdxGame.fontArialBlack64,
                "Готово",
                "schulteTable/buttonBackground.png",
                -1, 62
        );

        TextButton backButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Вернуться в лес",
                "schulteTable/buttonBackground.png",
                -1, 228
        );

        TextView motivatorTextView = new TextView(
                myGdxGame.fontArialBlack64,
                "Молодец, ты справился!",
                -1, 816
        );

        startButton.setOnClickListener(onButtonStartClicked);
        backButton.setOnClickListener(onBackButtonClicked);
        textButtonCompleteTest.setOnClickListener(onButtonCompleteTestClicked);
        textButtonCompleteTest.isVisible = false;

        sceneGreeting.addActor(backgroundView);
        sceneGreeting.addActor(startButton);

        sceneShowingCards.addActor(backgroundView);
        sceneShowingCards.addActor(timeCounterView);
        sceneShowingCards.addActor(tableMemoCardsView);
        sceneShowingCards.addActor(textViewRememberTitle);
        sceneShowingCards.addActor(textButtonCompleteTest);

        scenePassed.addActor(backgroundView);
        scenePassed.addActor(backButton);
        scenePassed.addActor(motivatorTextView);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (timeCounterView.isTimerExpired() && testSession.testState == StateMemo.REMEMBERING_CARDS) {
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
            tableMemoCardsView.setCards(testSession.shownCards, onCardsHided);
            testSession.startTest();
            timeCounterView.startTimer();
        }
    };

    TableMemoCardsView.OnCardsHidedListener onCardsHided = new TableMemoCardsView.OnCardsHidedListener() {
        @Override
        public void onHided() {
            timeCounterView.isVisible = false;
            tableMemoCardsView.addUnknownCards();
            textViewRememberTitle.setText("Найди показанные ранее карточки");
        }
    };

    TableMemoCardsView.OnCardsReleasedListener onCardsReleasedListener = new TableMemoCardsView.OnCardsReleasedListener() {
        @Override
        public void onReleased() {
            testSession.cardsWereReleased();
        }
    };

    TableMemoCardsView.OnTableItemClickListener onTableItemClickListener = new TableMemoCardsView.OnTableItemClickListener() {
        @Override
        public void onClicked(String cardSrc) {
            textButtonCompleteTest.isVisible = true;
            testSession.selectCard(cardSrc.split("/")[2].split("\\.")[0]);
        }
    };

    View.OnClickListener onButtonCompleteTestClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.endTest();
        }
    };

    View.OnClickListener onBackButtonClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };

    AlertPause.OnButtonResumeClickListener onButtonResumeClickListener = new AlertPause.OnButtonResumeClickListener() {
        @Override
        public void onClicked() {

        }
    };
}
