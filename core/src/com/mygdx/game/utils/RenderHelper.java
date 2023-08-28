package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.SpriteShapes;
import com.mygdx.game.ui.View;

import static com.mygdx.game.utils.ApplicationSettings.SCR_HEIGHT;


public class RenderHelper extends InputAdapter {

    public static boolean isDragging = false;
    static int x, y;
    static int deltaX, deltaY;

    static SpriteShapes spriteShapes;
    private static View draggingView;
    private static SpriteShapes draggingViewSprite;

    public static boolean checkTouch(MyGdxGame myGdxGame) {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.touch = myGdxGame.camera.unproject(myGdxGame.touch);
            return true;
        }
        return false;
    }

    public static void setDraggingView(View view, int startX, int startY) {
        draggingView = view;
        // deltaX = (int) (startX - view.x);
        // deltaY = (int) (startY - view.y);
        deltaX = (int) (view.width / 2);
        deltaY = (int) (view.height / 2);
    }


    public static void setDraggingView(SpriteShapes spriteShapes, int startX, int startY) {
        draggingViewSprite = spriteShapes;
        // deltaX = (int) (startX - view.x);
        // deltaY = (int) (startY - view.y);
        deltaX = (int) (spriteShapes.getWidth() / 2);
        deltaY = (int) (spriteShapes.getWidth() / 2);
    }


    public static View getDraggingView() {
        return draggingView;
    }

    public static SpriteShapes getDraggingViewSprite() {
        return draggingViewSprite;
    }

    public static void clearDraggingView() {
        draggingView = null;
        draggingViewSprite = null;
    }


    public static void draw(MyGdxGame myGdxGame, DrawScenes drawScenes, boolean justTouch) {
        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.enableBlending();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);

        drawScenes.draw(justTouch);
        if (draggingViewSprite != null && isDragging) {

            x = (int) (Gdx.input.getX() - deltaX);
            y = (int) (SCR_HEIGHT - (Gdx.input.getY() + deltaY));
            draggingViewSprite.setPos(x, y);
            draggingViewSprite.draw(myGdxGame);
        }

        myGdxGame.batch.end();
    }


    public interface DrawScenes {
        void draw(boolean justTouched);
    }

}
