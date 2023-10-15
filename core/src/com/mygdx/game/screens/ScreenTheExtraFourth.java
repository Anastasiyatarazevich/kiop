package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionTheExtraFourth;
import com.mygdx.game.testSessions.sessionsStates.StateTheExtraFourth;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.alerts.AlertPauseView;
import com.mygdx.game.ui.theExtraFourth.TableCardsView;
import com.mygdx.game.utils.ApplicationStrings;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.theExtraFourth.CardsPresetsTheExtraFourth;
import org.w3c.dom.Text;

import static com.mygdx.game.utils.ApplicationSettings.*;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenTheExtraFourth implements Screen {

    MyGdxGame myGdxGame;
    SessionTheExtraFourth testSession;

    SceneHelper scenePassed;
    SceneHelper sceneGreeting;
    SceneHelper sceneCardShowing;

    AlertPauseView alertPauseView;
    TableCardsView tableCardsView;
    TextView tableCounterView;
    ImageView imageViewNext;

    public ScreenTheExtraFourth(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        testSession = new SessionTheExtraFourth();

        scenePassed = new SceneHelper();
        sceneGreeting = new SceneHelper();
        sceneCardShowing = new SceneHelper();

        tableCardsView = new TableCardsView(
                -1, 160,
                FOURTH_CARD_PADDING, FOURTH_CARD_SIZE,
                CardsPresetsTheExtraFourth.presets[testSession.currentQuadIdx].getListOfCardsSrc()
        );

        tableCounterView = new TextView(
                myGdxGame.fontArialGray32,
                "1/" + CardsPresetsTheExtraFourth.presets.length,
                -1, 62
        );

        ImageView imageViewPause = new ImageView(
                1680, 970,
                "icons/icon_pause.png"
        );

        imageViewNext = new ImageView(
                1550, 425,
                "icons/icon_next.png"
        );

        alertPauseView = new AlertPauseView(
                myGdxGame.fontArialBlack64,
                myGdxGame.fontArialBlack32
        );

        BackgroundPixmapView background = new BackgroundPixmapView(COLOR_BG_GRAY);

        TextButton buttonStart = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                775, 155
        );

        TextView textViewTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Выбери лишнюю карточку",
                -1, 985
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

        TaskView taskView = new TaskView(
                myGdxGame.fontArialBlackBold64,
                myGdxGame.fontArialGray64,
                ApplicationStrings.EXTRA_FOURTH_TASK_DESCRIPTION
        );

        imageViewNext.isVisible = false;
        imageViewNext.setOnClickListener(onImageViewNextClicked);
        tableCardsView.setOnTableCardsClickListener(onTableCardsClicked);
        buttonStart.setOnClickListener(onButtonStartClicked);
        buttonBack.setOnClickListener(onButtonBackClicked);
        imageViewPause.setOnClickListener(onButtonPauseClicked);
        alertPauseView.setOnButtonReturnHomeClickListener(onButtonReturnHomeClicked);
        alertPauseView.setOnButtonResumeClickListener(onButtonResumeClicked);

        sceneGreeting.addActor(background);
        sceneGreeting.addActor(buttonStart);
        sceneGreeting.addActor(taskView);

        sceneCardShowing.addActor(background);
        sceneCardShowing.addActor(tableCardsView);
        sceneCardShowing.addActor(textViewTitle);
        sceneCardShowing.addActor(tableCounterView);
        sceneCardShowing.addActor(imageViewNext);
        sceneCardShowing.addActor(imageViewPause);
        sceneCardShowing.addActor(alertPauseView);

        scenePassed.addActor(background);
        scenePassed.addActor(buttonBack);
        scenePassed.addActor(textViewPassedTitle);
    }

    @Override
    public void show() {
        if (testSession.testState == StateTheExtraFourth.GREETING) {
            setTestScene();
        }
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

    public void setTestScene() {
        imageViewNext.isVisible = false;
        tableCardsView.setCards(CardsPresetsTheExtraFourth.presets[testSession.currentQuadIdx].getListOfCardsSrc());
        tableCounterView.setText((testSession.currentQuadIdx + 1) + "/" + CardsPresetsTheExtraFourth.presets.length);
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

    View.OnClickListener onButtonStartClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.startTest();
            setTestScene();
        }
    };

    TableCardsView.OnTableCardsClickListener onTableCardsClicked = new TableCardsView.OnTableCardsClickListener() {
        @Override
        public void onClick(int cardIdx) {
            imageViewNext.isVisible = true;
            testSession.cardWasSelected(cardIdx);
        }
    };

    View.OnClickListener onImageViewNextClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            if (!testSession.nextCards()) {
                return;
            }
            setTestScene();
        }
    };

    View.OnClickListener onButtonBackClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.screenMenu.setTestPassed(3);
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
}
