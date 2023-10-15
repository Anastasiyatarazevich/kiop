package com.mygdx.game.screens;

import static com.mygdx.game.utils.ApplicationSettings.RAVEN_CARD_IMAGES_DIR;
import static com.mygdx.game.utils.ApplicationSettings.RAVEN_CARD_PADDING;
import static com.mygdx.game.utils.ApplicationSettings.RAVEN_CARD_SIZE;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;
import static com.mygdx.game.utils.raven.Cards.targetTextures;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionRaven;
import com.mygdx.game.testSessions.sessionsStates.StateRaven;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.alerts.AlertPauseView;
import com.mygdx.game.ui.raven.MatrixCardsView;
import com.mygdx.game.utils.ApplicationStrings;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.raven.Cards;

public class ScreenRavenMatrices implements Screen {

    MyGdxGame myGdxGame;

    SessionRaven testSession;

    SceneHelper sceneGreeting, sceneCardShowing, scenePassed;

    TextView tableCounterView;
    ImageView imageViewNext;
    ImageView imageViewTargetPicture;
    MatrixCardsView tableCardsView;
    AlertPauseView alertPauseView;

    public static TextureAtlas textureAtlas;

    public ScreenRavenMatrices(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        textureAtlas = new TextureAtlas(RAVEN_CARD_IMAGES_DIR);

        sceneGreeting = new SceneHelper();
        sceneCardShowing = new SceneHelper();
        scenePassed = new SceneHelper();

        testSession = new SessionRaven();

        tableCardsView = new MatrixCardsView(
                900, 270,
                RAVEN_CARD_PADDING, RAVEN_CARD_SIZE,
                Cards.presets[0].getListOfCardsSrc()
        );

        BackgroundPixmapView background = new BackgroundPixmapView(COLOR_BG_GRAY);

        alertPauseView = new AlertPauseView(
                myGdxGame.fontArialBlack64,
                myGdxGame.fontArialBlack32
        );

        TextButton buttonStart = new TextButton(
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

        imageViewTargetPicture = new ImageView(
                100, 182,
                712, 600,
                textureAtlas.findRegion(targetTextures[0])
        );

        imageViewNext = new ImageView(
                1750, 425,
                "icons/icon_next.png"
        );

        TextView textViewTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Найди недостающий фрагмент",
                -1, 950
        );

        TextView textViewPassedTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Молодец, ты справился!",
                -1, 816
        );

        TextButton buttonBack = new TextButton(
                myGdxGame.fontArialBlack64,
                "Вернуться в лес",
                "schulteTable/buttonBackground.png",
                -1, 228
        );

        tableCounterView = new TextView(
                myGdxGame.fontArialGray32,
                "1/" + Cards.presets.length,
                -1, 62
        );

        ImageView imageViewPause = new ImageView(
                1680, 930,
                "icons/icon_pause.png"
        );

        TaskView taskView = new TaskView(
                myGdxGame.fontArialBlackBold64,
                myGdxGame.fontArialGray64,
                ApplicationStrings.RAVEN_TASK_DESCRIPTION
        );

        buttonBack.setOnClickListener(onButtonBackClicked);
        buttonStart.setOnClickListener(onButtonStartClicked);
        imageViewPause.setOnClickListener(onButtonPauseClicked);
        textViewBack.setOnClickListener(onButtonReturnClickListener);
        imageViewNext.setOnClickListener(onImageViewNextClicked);
        tableCardsView.setOnTableCardsClickListener(onTableCardsClicked);
        alertPauseView.setOnButtonResumeClickListener(onButtonResumeClicked);
        alertPauseView.setOnButtonReturnHomeClickListener(onButtonReturnHomeClicked);

        imageViewNext.isVisible = false;

        sceneGreeting.addActor(background);
        sceneGreeting.addActor(buttonStart);
        sceneGreeting.addActor(taskView);
        sceneGreeting.addActor(textViewBack);

        sceneCardShowing.addActor(background);
        sceneCardShowing.addActor(tableCardsView);
        sceneCardShowing.addActor(imageViewNext);
        sceneCardShowing.addActor(imageViewTargetPicture);
        sceneCardShowing.addActor(textViewTitle);
        sceneCardShowing.addActor(tableCounterView);
        sceneCardShowing.addActor(imageViewPause);
        sceneCardShowing.addActor(alertPauseView);

        scenePassed.addActor(background);
        scenePassed.addActor(buttonBack);
        scenePassed.addActor(textViewPassedTitle);
    }

    @Override
    public void show() {
        if (testSession.testState == StateRaven.GREETING) {
            setSceneCardShowing();
        }
    }

    @Override
    public void render(float delta) {
        testSession.checkingTime();
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

    public void setSceneCardShowing() {
        imageViewNext.isVisible = false;
        tableCardsView.setCards(Cards.presets[testSession.currentMatrixIndex].getListOfCardsSrc());
        imageViewTargetPicture.setTextureRegion(textureAtlas.findRegion(targetTextures[testSession.currentMatrixIndex]));
        tableCounterView.setText((testSession.currentMatrixIndex + 1) + "/" + Cards.presets.length);
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

    MatrixCardsView.OnTableCardsClickListener onTableCardsClicked = new MatrixCardsView.OnTableCardsClickListener() {
        @Override
        public void onClick(int cardIdx) {
            imageViewNext.isVisible = true;
            testSession.cardWasSelected(cardIdx);
        }
    };

    View.OnClickListener onButtonStartClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.startTest();
        }
    };

    View.OnClickListener onImageViewNextClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            if (!testSession.nextCards()) {
                testSession.endTest();
                return;
            }
            setSceneCardShowing();
        }
    };

    View.OnClickListener onButtonBackClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
            myGdxGame.screenMenu.setTestPassed(4);
        }
    };

    View.OnClickListener onButtonPauseClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.pauseTest();
            alertPauseView.show();
        }
    };

    AlertPauseView.OnButtonResumeClickListener onButtonResumeClicked = new AlertPauseView.OnButtonResumeClickListener() {
        @Override
        public void onClicked() {
            testSession.resumeTest();
        }
    };

    AlertPauseView.OnButtonReturnHomeClickListener onButtonReturnHomeClicked = new AlertPauseView.OnButtonReturnHomeClickListener() {
        @Override
        public void onClicked() {
            testSession.clearSession();
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
