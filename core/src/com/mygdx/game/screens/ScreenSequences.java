package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionSequences;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.alerts.AlertPauseView;
import com.mygdx.game.ui.sequences.SequenceCardsWrapperView;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;

import static com.mygdx.game.utils.ApplicationSettings.SEQUENCES_COUNT_CARDS;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenSequences implements Screen {

    MyGdxGame myGdxGame;

    SessionSequences testSession;

    SceneHelper sceneGreeting;
    SceneHelper sceneShowingSequences;
    SceneHelper scenePassed;

    SequenceCardsWrapperView sequenceCardsWrapperView;
    TextButton buttonReady;
    AlertPauseView alertPauseView;
    ImageAlphaView imageAlertErrorView;

    public ScreenSequences(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        testSession = new SessionSequences();

        sceneGreeting = new SceneHelper();
        scenePassed = new SceneHelper();
        sceneShowingSequences = new SceneHelper();

        BackgroundPixmapView backgroundView = new BackgroundPixmapView(COLOR_BG_GRAY);

        sequenceCardsWrapperView = new SequenceCardsWrapperView(115, 320);

        imageAlertErrorView = new ImageAlphaView(515, 70, "sequences/alertIncorrect.png");

        alertPauseView = new AlertPauseView(
                myGdxGame.fontArialBlack64,
                myGdxGame.fontArialBlack32
        );

        TextButton buttonStart = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                -1, 155
        );

        buttonReady = new TextButton(
                myGdxGame.fontArialBlack64,
                "Готово",
                "schulteTable/buttonBackground.png",
                1535, 91
        );

        TextView textViewDrop = new TextView(
                myGdxGame.fontArialBlack64,
                "Сбрость",
                115, 115
        );

        TextView textViewTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Восстанови последовательность",
                115, 950
        );

        TextButton buttonBack = new TextButton(
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

        ImageView imageViewPause = new ImageView(
                1680, 940,
                "icons/icon_pause.png"
        );

        alertPauseView.setOnButtonResumeClickListener(onButtonResumeClickListener);
        alertPauseView.setOnButtonReturnHomeClickListener(onButtonReturnHomeClickListener);
        sequenceCardsWrapperView.setOnSequenceItemClicked(onSequenceItemClicked);
        buttonStart.setOnClickListener(onButtonStartClicked);
        buttonReady.setOnClickListener(onButtonReadyClicked);
        textViewDrop.setOnClickListener(onButtonDropClicked);
        buttonBack.setOnClickListener(onButtonBackClicked);
        imageViewPause.setOnClickListener(onButtonPauseClicked);

        sceneGreeting.addActor(backgroundView);
        sceneGreeting.addActor(buttonStart);

        sceneShowingSequences.addActor(backgroundView);
        sceneShowingSequences.addActor(sequenceCardsWrapperView);
        sceneShowingSequences.addActor(textViewTitle);
        sceneShowingSequences.addActor(buttonReady);
        sceneShowingSequences.addActor(textViewDrop);
        sceneShowingSequences.addActor(imageViewPause);
        sceneShowingSequences.addActor(imageAlertErrorView);
        sceneShowingSequences.addActor(alertPauseView);

        scenePassed.addActor(backgroundView);
        scenePassed.addActor(buttonBack);
        scenePassed.addActor(motivatorTextView);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        testSession.checkTimer();
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
        buttonReady.isVisible = false;
        testSession.startTest();
        sequenceCardsWrapperView.loadImages(testSession.getCardSequence(), 400, 400);
    }

    SequenceCardsWrapperView.OnSequenceItemClicked onSequenceItemClicked = new SequenceCardsWrapperView.OnSequenceItemClicked() {
        @Override
        public void onItemClicked(int itemIdx) {
            int newIdx = testSession.addNewIdx(itemIdx);
            if (newIdx != -1) {
                sequenceCardsWrapperView.listCardsView[itemIdx].setImageIdx(newIdx);
            }
            if (testSession.listSequenceIdx.size() == SEQUENCES_COUNT_CARDS) {
                buttonReady.isVisible = true;
            }
        }
    };

    View.OnClickListener onButtonStartClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            startTest();
        }
    };

    View.OnClickListener onButtonDropClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            if (imageAlertErrorView.getAlpha() != 0f) {
                imageAlertErrorView.fadeAway(800);
            }
            buttonReady.isVisible = false;
            sequenceCardsWrapperView.dropIdxes();
            testSession.dropIdxes();
        }
    };

    View.OnClickListener onButtonReadyClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            if (!testSession.nextSequence()) {
                imageAlertErrorView.loadSizeOfTexture();
                imageAlertErrorView.fadeIn(800);
                return;
            }
            buttonReady.isVisible = false;
            sequenceCardsWrapperView.dropIdxes();
            testSession.dropIdxes();
            sequenceCardsWrapperView.loadImages(testSession.getCardSequence(), 400, 400);
        }
    };

    View.OnClickListener onButtonBackClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
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
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };
}
