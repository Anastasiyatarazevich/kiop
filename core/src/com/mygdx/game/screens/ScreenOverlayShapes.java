package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionOverlayShapes;
import com.mygdx.game.ui.BackgroundPixmap;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.View;
import com.mygdx.game.ui.overlayShapes.ImageColumnView;
import com.mygdx.game.ui.overlayShapes.ImageMapView;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.overlayShapes.Shape;

import static com.mygdx.game.utils.ApplicationSettings.COLORED_SHAPES_DIR;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenOverlayShapes implements Screen {

    MyGdxGame myGdxGame;

    private SessionOverlayShapes testSession;

    private SceneHelper sceneGreeting;
    private SceneHelper sceneShpaesShowing;
    private SceneHelper scenePassed;


    public ScreenOverlayShapes(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        testSession = new SessionOverlayShapes();

        sceneShpaesShowing = new SceneHelper();
        sceneGreeting = new SceneHelper();
        scenePassed = new SceneHelper();

        BackgroundPixmap background = new BackgroundPixmap(COLOR_BG_GRAY);

        TextView textViewTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Перетащи овощи",
                -1, 907
        );

        TextButton startButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                775, 155
        );

        ImageColumnView imageColumnView1 = new ImageColumnView(140, 122, 720, 40);
        ImageColumnView imageColumnView2 = new ImageColumnView(1760, 122, 720, 40);

        for (int i = 0; i < testSession.shapeList.size(); i++) {
            if (i < testSession.shapeList.size() / 2)
                imageColumnView1.addImage(COLORED_SHAPES_DIR + testSession.shapeList.get(i).getName());
            else
                imageColumnView2.addImage(COLORED_SHAPES_DIR + testSession.shapeList.get(i).getName());
        }

        ImageMapView imageMapView = new ImageMapView(122, 1280, 720, testSession.getSelectedSample(),
                onImageMapViewPressed, myGdxGame);

        startButton.setOnClickListener(onStartButtonClicked);

        sceneGreeting.addActor(background);
        sceneGreeting.addActor(startButton);

        sceneShpaesShowing.addActor(background);
        sceneShpaesShowing.addActor(imageMapView);
        sceneShpaesShowing.addActor(textViewTitle);
        sceneShpaesShowing.addActor(imageColumnView1);
        sceneShpaesShowing.addActor(imageColumnView2);

        scenePassed.addActor(background);

        System.out.println("Count of shapes: " + testSession.shapeList.size());
    }

    @Override
    public void show() {
        /*if (testSession.testState == StateOverlayShapes.GREETING) {
            testSession.startSession();
        }*/
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

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
                case SHAPES_SHOWING:
                    if (justTouched) sceneShpaesShowing.checkHits(myGdxGame);
                    sceneShpaesShowing.drawScene(myGdxGame);
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
        }
    };

    ImageMapView.OnImageMapViewPressed onImageMapViewPressed = new ImageMapView.OnImageMapViewPressed() {
        @Override
        public void onPressed(int localX, int localY) {
            int code = testSession.colorMap.getColorCode(localX, localY);
            System.out.println("Color code: " + code);
        }
    };
}
