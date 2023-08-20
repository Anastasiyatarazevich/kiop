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
import com.mygdx.game.ui.BackgroundPixmap;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.View;
import com.mygdx.game.ui.proofReading.TableViewProof;
import com.mygdx.game.ui.shulteTable.FindTextView;
import com.mygdx.game.ui.shulteTable.TableItemView;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.schulteHelper.SelectionResponse;

public class ScreenProofreadingTest implements Screen {

    MyGdxGame myGdxGame;

    SceneHelper sceneTableShowing, sceneGreeting, scenePassed;
    TableViewProof tableView;
    FindTextView findText;
    TextView goBackText;

    SessionProofTest testSession;

    ResultsProofReadingTest proofReadingTestResults;

    public ScreenProofreadingTest(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        sceneTableShowing = new SceneHelper();
        sceneGreeting = new SceneHelper();
        scenePassed = new SceneHelper();

        testSession = new SessionProofTest();

        tableView = new TableViewProof(100, 120, PROOF_READING_ROWS, PROOF_READING_COLUMNS, myGdxGame.fontArialGray64,
                PROOF_READING_ITEMS_SIZE, onTableItemClicked);
        findText = new FindTextView(636, 921, "Найди: ", myGdxGame.fontArialBlack64, " ");
        goBackText = new TextView(myGdxGame.fontArialBlack64, "Молодец, ты справился!", -1, 816);


        BackgroundPixmap background = new BackgroundPixmap(COLOR_BG_GRAY);

        TextButton startButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                775, 155
        );

        TextButton backButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Вернуться в лес!",
                "schulteTable/buttonBackground.png",
                775, 228
        );

        backButton.setOnClickListener(onBackButtonClicked);

        startButton.setOnClickListener(onStartButtonClicked);

        scenePassed.addActor(background);
        scenePassed.addActor(backButton);
        scenePassed.addActor(goBackText);

        sceneTableShowing.addActor(background);
        sceneTableShowing.addActors(tableView.getAllViews());
        sceneTableShowing.addActor(findText);

        sceneGreeting.addActor(background);
        sceneGreeting.addActor(startButton);
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
        System.out.println("Результаты корректурной пробы: " + proofReadingTestResults);
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
        }
    };


    TableItemView.OnTableItemClicked onTableItemClicked = new TableItemView.OnTableItemClicked() {
        @Override
        public void onClicked(TableItemView tableItemView) {
            if (!(tableItemView.getIsClicked())) {
                SelectionResponse clickResponse = testSession.checkSelection(tableItemView.value);
                System.out.println(clickResponse);
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
//                tableItemView.isClicked = true;
            }
        }
    };
}
