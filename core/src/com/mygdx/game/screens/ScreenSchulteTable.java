package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionSchulteTable;
import com.mygdx.game.testSessions.sessionsStates.StateSchulteTable;
import com.mygdx.game.ui.BackgroundPixmap;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.ui.View;
import com.mygdx.game.ui.shulteTable.TableView;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;

import static com.mygdx.game.utils.ApplicationSettings.COUNT_OF_SCHULTE_TABLES;
import static com.mygdx.game.utils.ApplicationSettings.SCHULTE_TABLE_ITEMS_SIZE;
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

    public ScreenSchulteTable(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        testSession = new SessionSchulteTable();

        sceneGreeting = new SceneHelper();
        sceneTableShowing = new SceneHelper();
        sceneBreak = new SceneHelper();
        scenePassed = new SceneHelper();

        tableView = new TableView(623, 163, COUNT_OF_SCHULTE_TABLES, myGdxGame.fontArialGray64, SCHULTE_TABLE_ITEMS_SIZE);

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
        sceneTableShowing.addActors(tableView.getAllViews());
        sceneGreeting.addActor(background);
        sceneGreeting.addActor(startButton);
    }

    @Override
    public void show() {
        if (testSession.testState == StateSchulteTable.GREETING) {
            testSession.startSession(COUNT_OF_SCHULTE_TABLES);
        }
    }

    @Override
    public void render(float delta) {
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
}
