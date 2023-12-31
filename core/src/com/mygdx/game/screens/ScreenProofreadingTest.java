package com.mygdx.game.screens;


import static com.mygdx.game.utils.ApplicationSettings.PROOF_READING_COLUMNS;
import static com.mygdx.game.utils.ApplicationSettings.PROOF_READING_ITEMS_SIZE;
import static com.mygdx.game.utils.ApplicationSettings.PROOF_READING_ROWS;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionProofTest;
import com.mygdx.game.testSessions.results.ResultsProofReadingTest;
import com.mygdx.game.testSessions.sessionsStates.StateProofTable;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.alerts.AlertPauseView;
import com.mygdx.game.ui.proofReading.TableViewProof;
import com.mygdx.game.ui.shulteTable.FindTextView;
import com.mygdx.game.ui.shulteTable.TableItemView;
import com.mygdx.game.utils.ApplicationStrings;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.schulteHelper.SelectionResponse;

public class ScreenProofreadingTest implements Screen {

    MyGdxGame myGdxGame;

    SessionProofTest testSession;

    SceneHelper sceneTableShowing, sceneGreeting, scenePassed;

    AlertPauseView alertPauseView;
    TableViewProof tableView;
    FindTextView findText;
    TextView textViewMotivator;

    ResultsProofReadingTest proofReadingTestResults;

    public ScreenProofreadingTest(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        sceneTableShowing = new SceneHelper();
        sceneGreeting = new SceneHelper();
        scenePassed = new SceneHelper();

        testSession = new SessionProofTest();

        BackgroundPixmapView background = new BackgroundPixmapView(COLOR_BG_GRAY);

        alertPauseView = new AlertPauseView(
                myGdxGame.fontArialBlack64,
                myGdxGame.fontArialBlack32
        );

        tableView = new TableViewProof(
                100, 120,
                PROOF_READING_ROWS,
                PROOF_READING_COLUMNS,
                myGdxGame.fontArialGray64,
                PROOF_READING_ITEMS_SIZE,
                onTableItemClicked
        );

        findText = new FindTextView(
                636, 980,
                "Найди: ",
                myGdxGame.fontArialBlack64,
                " "
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

        ImageView imageRaccoon = new ImageView(
                1350, 60,
                "images/sitting_raccoon.png"
        );

        TextButton backButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Вернуться в лес",
                "schulteTable/buttonBackground.png",
                -1, 480
        );

        textViewMotivator = new TextView(
                myGdxGame.fontArialBlack64,
                "Молодец, ты справился!",
                -1, 680
        );

        ImageView imageViewPause = new ImageView(
                1765, 960,
                "icons/icon_pause.png"
        );

        TaskView taskView = new TaskView(
                myGdxGame.fontArialBlackBold64,
                myGdxGame.fontArialGray64,
                ApplicationStrings.PROOF_READING_TASK_DESCRIPTION
        );

        backButton.setOnClickListener(onBackButtonClicked);
        startButton.setOnClickListener(onStartButtonClicked);
        imageViewPause.setOnClickListener(onButtonPauseClicked);
        textViewBack.setOnClickListener(onButtonReturnClickListener);
        alertPauseView.setOnButtonReturnHomeClickListener(onButtonReturnHomeClickListener);
        alertPauseView.setOnButtonResumeClickListener(onButtonResumeClickListener);

        sceneGreeting.addActor(background);
        sceneGreeting.addActor(startButton);
        sceneGreeting.addActor(taskView);
        sceneGreeting.addActor(textViewBack);

        scenePassed.addActor(background);
        scenePassed.addActor(backButton);
        scenePassed.addActor(textViewMotivator);
        scenePassed.addActor(imageRaccoon);

        sceneTableShowing.addActor(background);
        sceneTableShowing.addActors(tableView.getAllViews());
        sceneTableShowing.addActor(findText);
        sceneTableShowing.addActor(imageViewPause);
        sceneTableShowing.addActor(alertPauseView);
    }

    @Override
    public void show() {
        testSession.testState = StateProofTable.GREETING;
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
        testSession.setCounter(0);
        tableView.dispose();
//        this.dispose();
    }

    @Override
    public void dispose() {
        //todo: add dispose method
//        tableView.dispose();
    }

    void closeGame() {
        testSession.testState = StateProofTable.PASSED;
        proofReadingTestResults = new ResultsProofReadingTest();
        testSession.endTest(proofReadingTestResults);
        System.out.println(proofReadingTestResults);
    }

    void setTable() {
        tableView.setTable(testSession.table.tableMatrix);
    }


    RenderHelper.DrawScenes drawScenes = new RenderHelper.DrawScenes() {
        @Override
        public void draw(boolean justTouched) {
            switch (testSession.testState) {
                case TABLE_SHOWING:
                    if (justTouched) sceneTableShowing.checkHits(myGdxGame);
                    sceneTableShowing.drawScene(myGdxGame);
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

    TableItemView.OnTableItemClicked onTableItemClicked = new TableItemView.OnTableItemClicked() {
        @Override
        public void onClicked(TableItemView tableItemView) {
            if (!(tableItemView.getIsClicked())) {
                SelectionResponse clickResponse = testSession.checkSelection(tableItemView.value);
                switch (clickResponse) {
                    case SUCCESS:
                        tableItemView.setItemSelected();
                        System.out.println("Correct! " + testSession.getCounter() + " " + testSession.getOccurrence());
                        if (testSession.getCounter() == testSession.getOccurrence()) {
                            closeGame();

                        }
                        break;
                }
                tableItemView.setClicked(true);
            }
        }
    };

    View.OnClickListener onStartButtonClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.startTest();
            findText.updateFindText(testSession.targetLetter);
            setTable();
        }
    };

    View.OnClickListener onBackButtonClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
            myGdxGame.screenMenu.setTestPassed(1);
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
            testSession.clearTest();
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
