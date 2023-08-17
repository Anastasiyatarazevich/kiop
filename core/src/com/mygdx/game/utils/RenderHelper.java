package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.View;


public class RenderHelper extends InputAdapter {

    public static boolean isDragging = false;
    static int x, y;
    static int deltaX, deltaY;

    private static View dragginView;

    public static boolean checkTouch(MyGdxGame myGdxGame) {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.touch = myGdxGame.camera.unproject(myGdxGame.touch);
            return true;
        }
        return false;
    }

    public static void setDraggingView(View view, int startX, int startY) {
        dragginView = view;
        // deltaX = (int) (startX - view.x);
        // deltaY = (int) (startY - view.y);
        deltaX = 0;
        deltaY = 0;
    }

    public static View getDragginView() {
        return dragginView;
    }

    public static void clearDraggingView() {
        dragginView = null;
    }

    public static void draw(MyGdxGame myGdxGame, DrawScenes drawScenes, boolean justTouch) {
        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);


        drawScenes.draw(justTouch);

        if (dragginView != null && isDragging) {
            x = (int) (Gdx.input.getX() - deltaX - dragginView.width / 2);
            y = (int) (Gdx.graphics.getHeight() - (Gdx.input.getY() + deltaY) - dragginView.height / 2);
            dragginView.x = x;
            dragginView.y = y;
            dragginView.draw(myGdxGame);
        }
        myGdxGame.batch.end();
    }


    public interface DrawScenes {
        void draw(boolean justTouched);
    }

}
