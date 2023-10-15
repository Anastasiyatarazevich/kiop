package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionSchulteTable;
import com.mygdx.game.testSessions.sessionsStates.StateSchulteTable;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.alerts.AlertPauseView;
import com.mygdx.game.ui.shulteTable.TableCounterView;
import com.mygdx.game.ui.shulteTable.TableItemView;
import com.mygdx.game.ui.shulteTable.TableView;
import com.mygdx.game.utils.ApplicationSettings;
import com.mygdx.game.utils.ApplicationStrings;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.schulteHelper.SelectionResponse;

import static com.mygdx.game.utils.ApplicationSettings.*;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenSchulteTable implements Screen {

    MyGdxGame myGdxGame;
    SessionSchulteTable testSession;

    SceneHelper sceneGreeting;
    SceneHelper sceneTableShowing;
    SceneHelper sceneBreak;
    SceneHelper scenePassed;

    TableView tableView;
    TextView motivatorTextView;
    TextView smallTableCounterView;
    TimeCounterView timeCounterView;
    TableCounterView tableCounterView;

    AlertPauseView alertPauseView;

    public ScreenSchulteTable(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        testSession = new SessionSchulteTable();

        sceneGreeting = new SceneHelper();
        sceneTableShowing = new SceneHelper();
        sceneBreak = new SceneHelper();
        scenePassed = new SceneHelper();

        tableView = new TableView(
                623, 120,
                SCHULTE_TABLE_SIZE,
                myGdxGame.fontArialGray64,
                SCHULTE_TABLE_ITEMS_SIZE,
                onTableItemClicked
        );

        timeCounterView = new TimeCounterView(
                623, 921,
                "Прошло времени",
                myGdxGame.fontArialBlack64,
                myGdxGame.fontArialBlackBold64
        );

        tableCounterView = new TableCounterView(
                636, 660,
                "Осталось", "таблицы",
                myGdxGame.fontArialBlack64,
                myGdxGame.fontArialBlackBold64,
                COUNT_OF_SCHULTE_TABLES
        );

        smallTableCounterView = new TextView(
                myGdxGame.fontArialGray32,
                "1/" + COUNT_OF_SCHULTE_TABLES,
                -1, 43
        );

        motivatorTextView = new TextView(
                myGdxGame.fontArialBlack64,
                "Молодец, ты справился!",
                -1, 816
        );

        BackgroundPixmapView background = new BackgroundPixmapView(COLOR_BG_GRAY);

        TaskView taskView = new TaskView(
                myGdxGame.fontArialBlackBold64,
                myGdxGame.fontArialGray64,
                ApplicationStrings.SCHULTE_TABLE_TASK_DESCRIPTION
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

        TextButton continueButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Продолжить",
                "schulteTable/buttonBackground.png",
                -1, 228
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

        ImageView imageViewPause = new ImageView(1741, 917, "icons/icon_pause.png");

        alertPauseView = new AlertPauseView(myGdxGame.fontArialBlack64, myGdxGame.fontArialBlack32);

        buttonBackToMenu.setOnClickListener(onBackButtonClicked);
        startButton.setOnClickListener(onStartButtonClicked);
        continueButton.setOnClickListener(onContinueButtonClicked);
        imageViewPause.setOnClickListener(onPauseClicked);
        textViewBack.setOnClickListener(onButtonReturnClickListener);

        alertPauseView.setOnButtonResumeClickListener(onButtonResumeClickListener);
        alertPauseView.setOnButtonReturnHomeClickListener(onButtonReturnHomeClickListener);

        sceneGreeting.addActor(background);
        sceneGreeting.addActor(startButton);
        sceneGreeting.addActor(taskView);
        sceneGreeting.addActor(textViewBack);

        scenePassed.addActor(background);
        scenePassed.addActor(buttonBackToMenu);
        scenePassed.addActor(textViewMotivator);
        scenePassed.addActor(imageRaccoon);

        sceneBreak.addActor(background);
        sceneBreak.addActor(motivatorTextView);
        sceneBreak.addActor(tableCounterView);
        sceneBreak.addActor(continueButton);

        sceneTableShowing.addActor(background);
        sceneTableShowing.addActor(timeCounterView);
        sceneTableShowing.addActors(tableView.getAllViews());
        sceneTableShowing.addActor(smallTableCounterView);
        sceneTableShowing.addActor(imageViewPause);
        sceneTableShowing.addActor(alertPauseView);
    }

    @Override
    public void show() {
        if (testSession.testState == StateSchulteTable.GREETING) {
            testSession.startSession(COUNT_OF_SCHULTE_TABLES);
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

    void setTableScene() {
        smallTableCounterView.setText((testSession.tableIdx + 1) + "/" + COUNT_OF_SCHULTE_TABLES);
        timeCounterView.timer.resetTimer();
        timeCounterView.startTimer();
        tableView.setTable(testSession.tables.get(testSession.tableIdx).tableMatrix);
    }

    void closeTable() {
        if (testSession.tableIdx == COUNT_OF_SCHULTE_TABLES - 1) {
            testSession.endTest();
            System.out.println(testSession.testResults);
            return;
        }
        testSession.testState = StateSchulteTable.BREAK;
        tableCounterView.decreaseCountOfTimes();
    }

    RenderHelper.DrawScenes drawScenes = new RenderHelper.DrawScenes() {
        @Override
        public void draw(boolean justTouched) {
            switch (testSession.testState) {
                case BREAK:
                    if (justTouched) sceneBreak.checkHits(myGdxGame);
                    sceneBreak.drawScene(myGdxGame);
                    break;
                case PASSED:
                    if (justTouched) scenePassed.checkHits(myGdxGame);
                    scenePassed.drawScene(myGdxGame);
                    break;
                case TABLE_SHOWING:
                    if (justTouched) sceneTableShowing.checkHits(myGdxGame);
                    sceneTableShowing.drawScene(myGdxGame);
                    break;
                case GREETING:
                    if (justTouched) sceneGreeting.checkHits(myGdxGame);
                    sceneGreeting.drawScene(myGdxGame);
                    break;
            }
        }
    };

    View.OnClickListener onStartButtonClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.startTest();
            setTableScene();
        }
    };


    TableItemView.OnTableItemClicked onTableItemClicked = new TableItemView.OnTableItemClicked() {
        @Override
        public void onClicked(TableItemView tableItemView) {
            SelectionResponse clickResponse = testSession.checkSelection(Integer.parseInt(tableItemView.value));
            switch (clickResponse) {
                case SUCCESS:
                    tableItemView.setItemSelected();
                    if (!testSession.tables.get(testSession.tableIdx).nextItem())
                        closeTable();
                    break;
                case ERROR:
                    testSession.testResults.addError();
                    break;
                case DUPLICATE:
                    testSession.testResults.addDuplicate();
                    break;
            }
        }
    };

    View.OnClickListener onContinueButtonClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.nextTable();
            setTableScene();
            testSession.testState = StateSchulteTable.TABLE_SHOWING;
        }
    };

    View.OnClickListener onBackButtonClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
            myGdxGame.screenMenu.setTestPassed(0);
        }
    };

    View.OnClickListener onPauseClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            alertPauseView.show();
            testSession.pauseTest();
            timeCounterView.timer.pauseTimer();
        }
    };

    AlertPauseView.OnButtonReturnHomeClickListener onButtonReturnHomeClickListener = new AlertPauseView.OnButtonReturnHomeClickListener() {
        @Override
        public void onClicked() {
            testSession.clearSession();
            timeCounterView.timer.resetTimer();
            tableCounterView.resetCounter();
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };

    AlertPauseView.OnButtonResumeClickListener onButtonResumeClickListener = new AlertPauseView.OnButtonResumeClickListener() {
        @Override
        public void onClicked() {
            testSession.resumeTest();
            timeCounterView.timer.resumeTimer();
        }
    };

    View.OnClickListener onButtonReturnClickListener = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };
}
