package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;

public class RenderHelper {

    public static boolean checkTouch(MyGdxGame myGdxGame) {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.touch = myGdxGame.camera.unproject(myGdxGame.touch);
            return true;
        }
        return false;
    }

    public static void draw(MyGdxGame myGdxGame, DrawScenes drawScenes, boolean justTouch) {
        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);

        drawScenes.draw(justTouch);

        myGdxGame.batch.end();
    }

    public interface DrawScenes {
        void draw(boolean justTouched);
    }

}
