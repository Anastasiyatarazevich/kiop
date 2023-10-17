package com.mygdx.game.screens;

import static com.mygdx.game.utils.ApplicationSettings.SCR_WIDTH;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_WHITE;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.BackgroundPixmapView;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.View;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;

import java.util.ArrayList;
import java.util.Random;

public class ScreenMenu implements Screen {

    MyGdxGame myGdxGame;
    ArrayList<View> uiComponentsList;
    SceneHelper sceneMenu;

    int countOfTests = 8;
    int countOfCompletedTests = 0;

    Boolean[] arrayPassed = new Boolean[countOfTests];
    ImageView[] imageViewsMushroomArray = new ImageView[countOfTests];

    String[] arrayDirsBefore = {
            "menu/mushrooms/1",
            "menu/mushrooms/2",
            "menu/mushrooms/2",
            "menu/mushrooms/1",
            "menu/mushrooms/2",
            "menu/mushrooms/1",
            "menu/mushrooms/1",
            "menu/mushrooms/2"
    };

    TextView textViewCountOfTests;

    public ScreenMenu(final MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        uiComponentsList = new ArrayList<>();
        sceneMenu = new SceneHelper();

        final Screen[] arrayScreens = {
                myGdxGame.screenSchulteTable,
                myGdxGame.screenProofreadingTest,
                myGdxGame.screenOverlayShapes,
                myGdxGame.screenTheExtraFourth,
                myGdxGame.screenRavenMatrices,
                myGdxGame.screenMemo,
                myGdxGame.screenNonsense,
                myGdxGame.screenSequences
        };


        Integer[] xArray = getXArray();
        Integer[] yArray = getYArray();

        for (int i = 0; i < countOfTests; i++) {
            imageViewsMushroomArray[i] = new ImageView(xArray[i], yArray[i], arrayDirsBefore[i] + ".png");
            arrayPassed[i] = false;

            final int finalI = i;
            View.OnClickListener imageMushroomClicked = new View.OnClickListener() {
                @Override
                public void onClicked() {
                    if (!arrayPassed[finalI]) {
                        myGdxGame.setScreen(arrayScreens[finalI]);
                    }
                }
            };

            imageViewsMushroomArray[i].setOnClickListener(imageMushroomClicked);
        }

        textViewCountOfTests = new TextView(myGdxGame.fontArialGray32, "Грибы: 0/" + countOfTests, 665, 770);

        ImageView imageViewDialog = new ImageView(370, 650, "menu/raccoon_dialog.png");
        BackgroundPixmapView backgroundView = new BackgroundPixmapView(COLOR_BG_WHITE);

        sceneMenu.addActor(backgroundView);
        for (int i = 0; i < countOfTests; i++) {
            sceneMenu.addActor(imageViewsMushroomArray[i]);
        }

        sceneMenu.addActor(imageViewDialog);
        sceneMenu.addActor(textViewCountOfTests);
    }

    @Override
    public void show() {

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

    RenderHelper.DrawScenes drawScenes = new RenderHelper.DrawScenes() {
        @Override
        public void draw(boolean justTouched) {
            if (justTouched) sceneMenu.checkHits(myGdxGame);
            sceneMenu.drawScene(myGdxGame);
        }
    };

    private Integer[] getXArray() {
        Random random = new Random();
        int horizontalPadding = 100;
        int randomDelta = 60;

        Integer[] xArray = new Integer[countOfTests];
        for (int i = 0; i < countOfTests; i++) {
            int randomX = random.nextInt(randomDelta);
            randomX = (random.nextBoolean()) ? randomX : -randomX;
            xArray[i] = horizontalPadding + (SCR_WIDTH - 2 * horizontalPadding) / countOfTests * i + randomX;
        }

        return xArray;
    }

    private Integer[] getYArray() {
        Random random = new Random();
        int bottomPadding = 80;
        int padding = 250;
        int randomDelta = 30;

        Integer[] yArray = new Integer[countOfTests];
        for (int i = 0; i < countOfTests; i++) {
            int randomY = random.nextInt(randomDelta);
            randomY = (random.nextBoolean()) ? randomY : -randomY;
            yArray[i] = bottomPadding + (i % 2) * padding + randomY;
        }

        return yArray;
    }

    public void setTestPassed(int testIdx) {
        arrayPassed[testIdx] = true;
        imageViewsMushroomArray[testIdx].setImgSource(arrayDirsBefore[testIdx].split("\\.")[0] + "colored.png");
        countOfCompletedTests += 1;

        textViewCountOfTests.setText("Грибы: " + countOfCompletedTests + "/" + countOfTests);

        System.out.println(countOfCompletedTests == countOfTests);
        if (countOfCompletedTests == countOfTests) {
            myGdxGame.setScreen(myGdxGame.screenEnd);
        }
    }
}
