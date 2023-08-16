package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.overlayShapes.Shape;


public class RenderHelper extends InputAdapter {

    public static boolean isDragging;
    static int x, y;
    static int codeOfShape;

    public static boolean checkTouch(MyGdxGame myGdxGame) {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.touch = myGdxGame.camera.unproject(myGdxGame.touch);
            return true;
        }
        return false;
    }


    public static void dragAndDrop(Shape shape, int code) {
        codeOfShape = code;
        if (isDragging) {
            System.out.println("Dragging");
        }
    }

    public static void draw(MyGdxGame myGdxGame, DrawScenes drawScenes, boolean justTouch) {
        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);


        drawScenes.draw(justTouch);

        if (isDragging) {
            x = Gdx.input.getX();
            y = Gdx.graphics.getHeight() - Gdx.input.getY();
            switch (codeOfShape) {
                case 8:
                    myGdxGame.batch.draw(new Texture("/Users/anastasiatarazevich/AndroidStudioProjects/kiop/assets/overlayShapes/coloredShapes/beet.png"), x, y);
                    break;
            }
        }
        myGdxGame.batch.end();
    }


    public interface DrawScenes {
        void draw(boolean justTouched);
    }

}
