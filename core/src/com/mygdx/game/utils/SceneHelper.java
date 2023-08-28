package com.mygdx.game.utils;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.View;

import java.util.ArrayList;

public class SceneHelper {

    ArrayList<View> uiComponentsList;

    public SceneHelper() {
        uiComponentsList = new ArrayList<>();
    }

    public void addActor(View view) {
        uiComponentsList.add(view);
    }

    public void addActors(ArrayList<View> listView) {
        uiComponentsList.addAll(listView);
    }

    public void drawScene(MyGdxGame myGdxGame) {
        for (View component : uiComponentsList) {
            if (component.isVisible) {
                component.draw(myGdxGame);
            }
        }
    }

    public void checkHits(MyGdxGame myGdxGame) {
        for (View component : uiComponentsList) {
            if (component.isVisible) component.isHit(myGdxGame.touch.x, myGdxGame.touch.y);
        }
    }

    // todo: write disposing
    public void showScreen() {

    }
}
