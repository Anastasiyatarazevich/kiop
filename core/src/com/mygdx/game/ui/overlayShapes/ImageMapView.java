package com.mygdx.game.ui.overlayShapes;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;

import static com.mygdx.game.utils.ApplicationSettings.OVERLAY_PICTURES_DIR;

// that view shows black and white picture of overlaid shapes and also
// returns local coordinates of user press
public class ImageMapView extends ImageView {

    private OnImageMapViewPressed onImageMapViewPressed;
    MyGdxGame myGdxGame;

    public ImageMapView(float y, float width, float height, int selectedSample, OnImageMapViewPressed onImageMapViewPressed, MyGdxGame myGdxGame) {
        super(y, width, height, OVERLAY_PICTURES_DIR + selectedSample + ".png");
        this.onImageMapViewPressed = onImageMapViewPressed;
        this.myGdxGame = myGdxGame;
        setOnClickListener(onClickListener);
    }



    public interface OnImageMapViewPressed{
        void onPressed(int localX, int localY);
    }

    View.OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClicked() {
            float globalX = myGdxGame.touch.x;
            float globalY = myGdxGame.touch.y;

            int localX = (int) (globalX - x);
            // anchor point is in left-bottom corner but pixels array starts with left-top corner
            int localY = imgTexture.getHeight() - (int) (globalY - y);

            onImageMapViewPressed.onPressed(localX, localY);
        }
    };
}
