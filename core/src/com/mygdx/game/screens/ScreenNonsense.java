package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionNonsense;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.alerts.AlertPauseView;
import com.mygdx.game.ui.nonsense.ImageMapView;
import com.mygdx.game.utils.ApplicationStrings;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.nonsense.Nonsense;

import static com.mygdx.game.testSessions.sessionsStates.StateNonsense.GREETING;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenNonsense implements Screen {

    MyGdxGame myGdxGame;

    SessionNonsense testSession;

    SceneHelper scenePassed;
    SceneHelper sceneGreeting;
    SceneHelper sceneFindingNonsense;

    AlertPauseView alertPauseView;
    ImageMapView imageMapView;
    TextView textViewNonsenseCount;

    public ScreenNonsense(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        testSession = new SessionNonsense();

        sceneFindingNonsense = new SceneHelper();
        sceneGreeting = new SceneHelper();
        scenePassed = new SceneHelper();

        BackgroundPixmapView backgroundView = new BackgroundPixmapView(COLOR_BG_GRAY);

        alertPauseView = new AlertPauseView(
                myGdxGame.fontArialBlack64,
                myGdxGame.fontArialBlack32
        );

        imageMapView = new ImageMapView(
                70, 70, 941,
                myGdxGame
        );

        ImageView imagePause = new ImageView(
                1768, 1020,
                "icons/icon_pause.png"
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

        TextView textViewTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Найди все нелепицы",
                941, 1035
        );

        TextButton buttonCompleteTest = new TextButton(
                myGdxGame.fontArialBlack64,
                "Готово",
                "schulteTable/buttonBackground.png",
                1139, 70
        );

        TextView textViewNonsenseCountTitle = new TextView(
                myGdxGame.fontArialGray64,
                "Найдено: ",
                1125, 525
        );

        textViewNonsenseCount = new TextView(
                myGdxGame.fontArialBlackBold64,
                "0",
                1450, 525
        );

        TextButton buttonBackToMenu = new TextButton(
                myGdxGame.fontArialBlack64,
                "Вернуться в лес",
                "schulteTable/buttonBackground.png",
                -1, 228
        );

        TextView textViewMotivator = new TextView(
                myGdxGame.fontArialBlack64,
                "Молодец, ты справился!",
                -1, 816
        );

        TaskView taskView = new TaskView(
                myGdxGame.fontArialBlackBold64,
                myGdxGame.fontArialGray64,
                ApplicationStrings.NONSENSE_TASK_DESCRIPTION
        );

        imagePause.setOnClickListener(onImagePauseClicked);
        buttonStart.setOnClickListener(onButtonStartClicked);
        buttonBackToMenu.setOnClickListener(onButtonBackClicked);
        buttonCompleteTest.setOnClickListener(onButtonCompleteClicked);
        textViewBack.setOnClickListener(onButtonReturnClickListener);
        testSession.setOnNonsenseFoundListener(onNonsenseFoundListener);
        imageMapView.setOnImageMapViewPressedListener(onImageMapViewPressedListener);
        alertPauseView.setOnButtonResumeClickListener(onButtonResumeClickListener);
        alertPauseView.setOnButtonReturnHomeClickListener(onButtonReturnHomeClickListener);

        sceneGreeting.addActor(backgroundView);
        sceneGreeting.addActor(buttonStart);
        sceneGreeting.addActor(taskView);
        sceneGreeting.addActor(textViewBack);

        scenePassed.addActor(backgroundView);
        scenePassed.addActor(buttonBackToMenu);
        scenePassed.addActor(textViewMotivator);

        sceneFindingNonsense.addActor(backgroundView);
        sceneFindingNonsense.addActor(textViewTitle);
        sceneFindingNonsense.addActor(buttonCompleteTest);
        sceneFindingNonsense.addActor(imagePause);
        sceneFindingNonsense.addActor(imageMapView);
        sceneFindingNonsense.addActor(textViewNonsenseCountTitle);
        sceneFindingNonsense.addActor(textViewNonsenseCount);
        sceneFindingNonsense.addActor(alertPauseView);
    }

    @Override
    public void show() {
        if (testSession.testState == GREETING) {
            testSession.startSession();
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

    public void setFindingNonsenseScene() {
        imageMapView.setSelectedSampleIdx(testSession.getSelectedSample());
        textViewNonsenseCount.setText("0");
    }

    RenderHelper.DrawScenes drawScenes = new RenderHelper.DrawScenes() {
        @Override
        public void draw(boolean justTouched) {
            switch (testSession.testState) {
                case FINDING_NONSENSES:
                    if (justTouched) sceneFindingNonsense.checkHits(myGdxGame);
                    sceneFindingNonsense.drawScene(myGdxGame);
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
            setFindingNonsenseScene();
            testSession.startTest();
        }
    };

    View.OnClickListener onImagePauseClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.pauseTest();
            alertPauseView.show();
        }
    };

    View.OnClickListener onButtonCompleteClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.endTest();
        }
    };

    View.OnClickListener onButtonBackClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
            myGdxGame.screenMenu.setTestPassed(6);
        }
    };

    ImageMapView.OnImageMapViewPressedListener onImageMapViewPressedListener = new ImageMapView.OnImageMapViewPressedListener() {
        @Override
        public void onPressed(int localX, int localY) {
            int colorCode = testSession.colorMap.getColorCode(localX, localY);
            testSession.executeColorCode(colorCode);
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
            imageMapView.clearMapView();
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };

    SessionNonsense.OnNonsenseFoundListener onNonsenseFoundListener = new SessionNonsense.OnNonsenseFoundListener() {
        @Override
        public void onFound(Nonsense nonsense) {
            imageMapView.addHighlight(nonsense.getX(), nonsense.getY(), nonsense.getPointerRadius());
            textViewNonsenseCount.setText(String.valueOf(testSession.countOfFoundNonsenses));
        }
    };

    View.OnClickListener onButtonReturnClickListener = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };
}
