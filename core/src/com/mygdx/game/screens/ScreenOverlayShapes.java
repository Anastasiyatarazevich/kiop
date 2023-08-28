package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionOverlayShapes;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.overlayShapes.ImageColumnView;
import com.mygdx.game.ui.overlayShapes.ImageMapView;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.overlayShapes.ColorsCodes;
import com.mygdx.game.utils.overlayShapes.Shape;

import java.util.ArrayList;

import static com.mygdx.game.utils.ApplicationSettings.*;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenOverlayShapes implements Screen {

    MyGdxGame myGdxGame;

    private final SessionOverlayShapes testSession;

    private final SceneHelper sceneGreeting;
    private final SceneHelper sceneShapesShowing;
    private final SceneHelper scenePassed;

    ImageMapView imageMapView;
    ImageColumnView imageColumnView1, imageColumnView2;

    ArrayList<ImageView> listShapesImages;
    ArrayList<SpriteShapes> spriteListShapesImages;

    public ScreenOverlayShapes(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;


        testSession = new SessionOverlayShapes();

        sceneShapesShowing = new SceneHelper();
        sceneGreeting = new SceneHelper();
        scenePassed = new SceneHelper();

        listShapesImages = new ArrayList<>();
        spriteListShapesImages = new ArrayList<>();

        BackgroundPixmapView background = new BackgroundPixmapView(COLOR_BG_GRAY);

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

        TextView endTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Молодец, ты справился!",
                -1, 900
        );

        TextButton menuButton = new TextButton(
                myGdxGame.fontArialBlack64,
                "Вернуться в лес",
                "schulteTable/buttonBackground.png",
                -1, 300
        );

        menuButton.setOnClickListener(onMenuButtonClicked);

        imageColumnView1 = new ImageColumnView(137, 122, 720, 25);
        imageColumnView2 = new ImageColumnView(1630, 122, 720, 25);

        for (int i = 0; i < testSession.shapeList.size(); i++) {
            if (i < testSession.shapeList.size() / 2)
                imageColumnView1.addImage(COLUMN_BLACK_AND_WHITE_SHAPES_DIR + testSession.shapeList.get(i).getName());
            else
                imageColumnView2.addImage(COLUMN_BLACK_AND_WHITE_SHAPES_DIR + testSession.shapeList.get(i).getName());
        }

        imageMapView = new ImageMapView(122, 1280, 720, testSession.getSelectedSample(),
                onImageMapViewPressed, myGdxGame);

        startButton.setOnClickListener(onStartButtonClicked);

        sceneGreeting.addActor(background);
        sceneGreeting.addActor(startButton);

        sceneShapesShowing.addActor(background);
        sceneShapesShowing.addActor(imageMapView);
        sceneShapesShowing.addActor(textViewTitle);
        sceneShapesShowing.addActor(imageColumnView1);
        sceneShapesShowing.addActor(imageColumnView2);

        scenePassed.addActor(background);
        scenePassed.addActor(menuButton);
        scenePassed.addActor(endTitle);

        // System.out.println("Count of shapes: " + testSession.shapeList.size());

//        for (int i = 0; i < testSession.shapeList.size(); i++) {
//            Shape shape = testSession.shapeList.get(i);
//            listShapesImages.add(new ImageView(shape.getX(), shape.getY(), BLACK_AND_WHITE_SHAPES_DIR + shape.getName()));
//            shape.setX((int) (shape.getX() + imageMapView.x));
//            shape.setY((int) (shape.getY() + imageMapView.y));
//            // shape.setX((int) (imageMapView.x));
//            // shape.setY((int) (imageMapView.y));
//        }


        for (int i = 0; i < testSession.shapeList.size(); i++) {
            Shape shape = testSession.shapeList.get(i);
            spriteListShapesImages.add(new SpriteShapes(shape.getX(), shape.getY(), BLACK_AND_WHITE_SHAPES_DIR + shape.getName(), shape.getRotation()));
            shape.setX((int) (shape.getX() + imageMapView.x));
            shape.setY((int) (shape.getY() + imageMapView.y));
        }

        Gdx.input.setInputProcessor(new MyInputProcessor());
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
                    if (justTouched) sceneShapesShowing.checkHits(myGdxGame);
                    sceneShapesShowing.drawScene(myGdxGame);
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

    View.OnClickListener onMenuButtonClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };

    ImageMapView.OnImageMapViewPressed onImageMapViewPressed = new ImageMapView.OnImageMapViewPressed() {
        @Override
        public void onPressed(int localX, int localY) {
            int code = testSession.colorMap.getColorCode(localX, localY);

//            for (int i = 0; i < testSession.shapeList.size(); i++) {
//                if (ColorsCodes.getImageNameByColorCode(code).equals(
//                        testSession.shapeList.get(i).getName().split("\\.")[0])) {
//                    listShapesImages.get(i).x = testSession.shapeList.get(i).getX();
//                    listShapesImages.get(i).y = testSession.shapeList.get(i).getY();
//                    RenderHelper.setDraggingView(listShapesImages.get(i), (int) myGdxGame.touch.x, (int) (myGdxGame.touch.y));
//                    System.out.println(testSession.shapeList.get(i).getX() + " - " + testSession.shapeList.get(i).getY());
//                    return;
//                }
//            }

            for (int i = 0; i < testSession.shapeList.size(); i++) {
                if (ColorsCodes.getImageNameByColorCode(code).equals(
                        testSession.shapeList.get(i).getName().split("\\.")[0])) {
                    spriteListShapesImages.get(i).x = testSession.shapeList.get(i).getX();
                    spriteListShapesImages.get(i).y = testSession.shapeList.get(i).getY();
                    RenderHelper.setDraggingView(spriteListShapesImages.get(i), (int) myGdxGame.touch.x, (int) (myGdxGame.touch.y));
                    // System.out.println(testSession.shapeList.get(i).getX() + " - " + testSession.shapeList.get(i).getY());
                    return;
                }
            }
        }
    };

    private class MyInputProcessor extends InputAdapter {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (button == Input.Buttons.LEFT) {
                if (screenX >= imageMapView.x && screenX < imageMapView.x + imageMapView.width &&
                        Gdx.graphics.getHeight() - screenY >= imageMapView.y &&
                        Gdx.graphics.getHeight() - screenY < imageMapView.y + imageMapView.height) {
                    RenderHelper.isDragging = true;
                }
            }
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            if (button == Input.Buttons.LEFT) {
                RenderHelper.isDragging = false;

                if (RenderHelper.getDraggingViewSprite() == null) return false;
                String draggingShapeName = ((SpriteShapes) RenderHelper.getDraggingViewSprite()).getImgSource();
                draggingShapeName = draggingShapeName.split("/")[draggingShapeName.split("/").length - 1];
                RenderHelper.clearDraggingView();
                String shapeName = imageColumnView1.getImgSource(screenX, SCR_HEIGHT - screenY);
                if (shapeName.isEmpty())
                    shapeName = imageColumnView2.getImgSource(screenX, SCR_HEIGHT - screenY);

                if (!shapeName.isEmpty()) {
                    shapeName = shapeName.split("/")[shapeName.split("/").length - 1];
                    for (int i = 0; i < testSession.shapeList.size(); i++) {
                        if (testSession.shapeList.get(i).getName().equals(shapeName)) {

                            if (!shapeName.equals(draggingShapeName)) continue;

                            if (!testSession.shapeList.get(i).getIsFound()) {
                                testSession.shapeWasFound(i);

                                if (i < testSession.shapeList.size() / 2) {
                                    String newDir = COLORED_SHAPES_DIR + testSession.shapeList.get(i).getName();
                                    imageColumnView1.imagesList.get(i).setImgSource(newDir);
                                } else {
                                    int newIdx = i - testSession.shapeList.size() / 2;
                                    String newDir = COLORED_SHAPES_DIR + testSession.shapeList.get(i).getName();
                                    imageColumnView2.imagesList.get(newIdx).setImgSource(newDir);
                                }
                                return true;

                            }
                        }
                    }
                }

            }
            return true;
        }
    }

}
