package com.mygdx.game.screens;

import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.View;

import java.util.ArrayList;

public class ScreenMenu implements Screen {

    MyGdxGame myGdxGame;
    ArrayList<View> uiComponentsList;

    public ScreenMenu(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        uiComponentsList = new ArrayList<>();

        TextView textViewTest1 = new TextView(myGdxGame.fontArialGray64, "1. Таблица Шульте", 100, 700);
        TextView textViewTest2 = new TextView(myGdxGame.fontArialGray64, "2. Коррекатурная проба", 100, 600);
        TextView textViewTest3 = new TextView(myGdxGame.fontArialGray64, "3. Наложенные фигуры", 100, 500);
        TextView textViewTest4 = new TextView(myGdxGame.fontArialGray64, "4. Четвёртый лишний", 100, 400);
        TextView textViewTest5 = new TextView(myGdxGame.fontArialGray64, "5. Матрицы Равена", 100, 300);
        TextView textViewTest6 = new TextView(myGdxGame.fontArialGray64, "6. Мемо", 100, 200);

        textViewTest1.setOnClickListener(textView3clicked);
        textViewTest2.setOnClickListener(textView2clicked);
        textViewTest3.setOnClickListener(textView1clicked);
        textViewTest4.setOnClickListener(textView4Clicked);
        textViewTest5.setOnClickListener(textView5Clicked);
        textViewTest6.setOnClickListener(textView6Clicked);

        uiComponentsList.add(textViewTest1);
        uiComponentsList.add(textViewTest2);
        uiComponentsList.add(textViewTest3);
        uiComponentsList.add(textViewTest4);
        uiComponentsList.add(textViewTest5);
        uiComponentsList.add(textViewTest6);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.touch = myGdxGame.camera.unproject(myGdxGame.touch);
            for (View component : uiComponentsList) {
                if (component.isVisible) component.isHit(myGdxGame.touch.x, myGdxGame.touch.y);
            }
        }

        ScreenUtils.clear(COLOR_BG_GRAY);
        myGdxGame.camera.update();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);

        for (View component : uiComponentsList) {
            component.draw(myGdxGame);
        }

        myGdxGame.batch.end();
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

    View.OnClickListener textView1clicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenOverlayShapes);
        }
    };

    View.OnClickListener textView2clicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenProofreadingTest);
        }
    };

    View.OnClickListener textView3clicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenSchulteTable);
        }
    };

    View.OnClickListener textView4Clicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenTheExtraFourth);
        }
    };

    View.OnClickListener textView5Clicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenRavenMatrices);
        }
    };

    View.OnClickListener textView6Clicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMemoTest);
        }
    };
}
