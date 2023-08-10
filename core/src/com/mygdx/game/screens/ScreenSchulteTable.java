package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionSchulteTable;
import com.mygdx.game.testSessions.sessionsStates.StateSchulteTable;
import com.mygdx.game.ui.BackgroundPixmap;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.ui.View;
import com.mygdx.game.ui.shulteTable.TableItemView;
import com.mygdx.game.ui.shulteTable.TableView;
import com.mygdx.game.ui.shulteTable.TimeCounterView;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.schulteHelper.SelectionResponse;

import static com.mygdx.game.utils.ApplicationSettings.*;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenSchulteTable implements Screen {

    MyGdxGame myGdxGame;
    SessionSchulteTable testSession;
    boolean justTouched;

    SceneHelper sceneGreeting;
    SceneHelper sceneTableShowing;
    SceneHelper sceneBreak;
    SceneHelper scenePassed;

    TableView tableView;
    TimeCounterView timeCounterView;

    public ScreenSchulteTable(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        testSession = new SessionSchulteTable();

        sceneGreeting = new SceneHelper();
        sceneTableShowing = new SceneHelper();
        sceneBreak = new SceneHelper();
        scenePassed = new SceneHelper();

        tableView = new TableView(623, 163, SCHULTE_TABLE_SIZE, myGdxGame.fontArialGray64,
                SCHULTE_TABLE_ITEMS_SIZE, onTableItemClicked);
        timeCounterView = new TimeCounterView(590, 921, "Оставшееся время",
                myGdxGame.fontArialGray64, myGdxGame.fontArialBlack64, 30000);

        BackgroundPixmap background = new BackgroundPixmap(COLOR_BG_GRAY);
        TextButton startButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                775, 155);
        startButton.setOnClickListener(onStartButtonClicked);

        scenePassed.addActor(background);
        sceneBreak.addActor(background);
        sceneTableShowing.addActor(background);

        // todo: make better margins
        //sceneTableShowing.addActor(timeCounterView);
        sceneTableShowing.addActors(tableView.getAllViews());
        sceneGreeting.addActor(background);
        sceneGreeting.addActor(startButton);
    }

    @Override
    public void show() {
        if (testSession.testState == StateSchulteTable.GREETING) {
            testSession.startSession(COUNT_OF_SCHULTE_TABLES);
            timeCounterView.startTimer();
        }
    }

    @Override
    public void render(float delta) {
        timeCounterView.updateTimer();
        justTouched = RenderHelper.checkTouch(myGdxGame);
        RenderHelper.draw(myGdxGame, drawScenes);
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

    void setTable() {
        tableView.setTable(testSession.tables.get(testSession.tableIdx).tableMatrix);
    }

    void closeTable(int closingCode) {
        testSession.testState = StateSchulteTable.BREAK;
    }

    RenderHelper.DrawScenes drawScenes = new RenderHelper.DrawScenes() {
        @Override
        public void draw() {
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
            setTable();
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
                        closeTable(0);
                    break;
                case ERROR:
                    // todo: fill some logic
                    break;
                case DUPLICATE:
                    // todo: fill some logic
                    break;
            }
        }
    };
}
