package com.mygdx.game.screens;

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

        TextView textViewTest1 = new TextView(myGdxGame.commonFont, "Test1", 100, 200);
        TextView textViewTest2 = new TextView(myGdxGame.commonFont, "Test2", 100, 300);
        TextView textViewTest3 = new TextView(myGdxGame.commonFont, "Test3", 100, 400);

        textViewTest1.setOnClickListener(textView1clicked);
        textViewTest2.setOnClickListener(textView2clicked);
        textViewTest3.setOnClickListener(textView3clicked);

        uiComponentsList.add(textViewTest1);
        uiComponentsList.add(textViewTest2);
        uiComponentsList.add(textViewTest3);
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

        ScreenUtils.clear(0, 0, 0, 1);
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
}
