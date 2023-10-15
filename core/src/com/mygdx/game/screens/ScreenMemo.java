package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionMemo;
import com.mygdx.game.testSessions.sessionsStates.StateMemo;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.alerts.AlertPauseView;
import com.mygdx.game.ui.memo.TableMemoCardsView;
import com.mygdx.game.utils.ApplicationStrings;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;

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
    TextButton textButtonCompleteTest;
    TextView textViewRememberTitle;
    AlertPauseView alertPauseView;

    public ScreenMemo(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        testSession = new SessionMemo();

        sceneGreeting = new SceneHelper();
        sceneShowingCards = new SceneHelper();
        scenePassed = new SceneHelper();

        BackgroundPixmapView backgroundView = new BackgroundPixmapView(COLOR_BG_GRAY);

        alertPauseView = new AlertPauseView(
                myGdxGame.fontArialBlack64,
                myGdxGame.fontArialBlack32
        );

        TaskView taskView = new TaskView(
                myGdxGame.fontArialBlackBold64,
                myGdxGame.fontArialGray64,
                ApplicationStrings.MEMO_TASK_DESCRIPTION
        );

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
                660, 155
        );

        TextView textViewBack = new TextView(
                myGdxGame.fontArialBlack64,
                "Назад",
                1040, 180
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

        ImageView imageRaccoon = new ImageView(
                1350, 60,
                "images/sitting_raccoon.png"
        );

        TextButton buttonBackToMenu = new TextButton(
                myGdxGame.fontArialBlack64,
                "Вернуться в лес",
                "schulteTable/buttonBackground.png",
                -1, 480
        );

        TextView textViewMotivator = new TextView(
                myGdxGame.fontArialBlack64,
                "Молодец, ты справился!",
                -1, 680
        );

        ImageView imageViewPause = new ImageView(
                1680, 1030,
                "icons/icon_pause.png"
        );

        startButton.setOnClickListener(onButtonStartClicked);
        buttonBackToMenu.setOnClickListener(onBackButtonClicked);
        imageViewPause.setOnClickListener(onButtonPauseClicked);
        textViewBack.setOnClickListener(onButtonReturnClickListener);
        alertPauseView.setOnButtonResumeClickListener(onButtonResumeClickListener);
        alertPauseView.setOnButtonReturnHomeClickListener(onButtonReturnHomeClickListener);
        textButtonCompleteTest.setOnClickListener(onButtonCompleteTestClicked);
        textButtonCompleteTest.isVisible = false;

        sceneGreeting.addActor(backgroundView);
        sceneGreeting.addActor(startButton);
        sceneGreeting.addActor(taskView);
        sceneGreeting.addActor(textViewBack);

        sceneShowingCards.addActor(backgroundView);
        sceneShowingCards.addActor(timeCounterView);
        sceneShowingCards.addActor(tableMemoCardsView);
        sceneShowingCards.addActor(textViewRememberTitle);
        sceneShowingCards.addActor(textButtonCompleteTest);
        sceneShowingCards.addActor(imageViewPause);
        sceneShowingCards.addActor(alertPauseView);

        scenePassed.addActor(backgroundView);
        scenePassed.addActor(buttonBackToMenu);
        scenePassed.addActor(textViewMotivator);
        scenePassed.addActor(imageRaccoon);
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
            testSession.startTest();
            tableMemoCardsView.setCards(testSession.shownCards, onCardsHided);
            timeCounterView.startTimer();
            timeCounterView.isVisible = true;
            textButtonCompleteTest.isVisible = false;
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
            myGdxGame.screenMenu.setTestPassed(5);
        }
    };

    View.OnClickListener onButtonPauseClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.pauseTest();
            alertPauseView.show();
        }
    };

    AlertPauseView.OnButtonResumeClickListener onButtonResumeClickListener = new AlertPauseView.OnButtonResumeClickListener() {
        @Override
        public void onClicked() {
            testSession.resumeTest();
        }
    };

    AlertPauseView.OnButtonReturnHomeClickListener onButtonReturnHomeClickListener = new AlertPauseView.OnButtonReturnHomeClickListener() {
        @Override
        public void onClicked() {
            testSession.clearSession();
            timeCounterView.timer.resetTimer();
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };

    View.OnClickListener onButtonReturnClickListener = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };
}
