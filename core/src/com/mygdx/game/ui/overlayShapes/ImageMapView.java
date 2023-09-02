package com.mygdx.game.ui.overlayShapes;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;

import static com.mygdx.game.utils.ApplicationSettings.OVERLAY_PICTURES_DIR;

// that view shows black and white picture of overlaid shapes and also
// returns local coordinates of user press
public class ImageMapView extends ImageView {

    private OnImageMapViewPressedListener onImageMapViewPressedListener;
    MyGdxGame myGdxGame;

    public ImageMapView(float y, float width, float height, int selectedSample, OnImageMapViewPressedListener onImageMapViewPressedListener, MyGdxGame myGdxGame) {
        super(y, width, height, OVERLAY_PICTURES_DIR + selectedSample + ".png");
        this.onImageMapViewPressedListener = onImageMapViewPressedListener;
        this.myGdxGame = myGdxGame;
        setOnClickListener(onClickListener);
    }

    public void setSelectedSampleIdx(int selectedSampleIdx) {
        setImgSource(OVERLAY_PICTURES_DIR + selectedSampleIdx + ".png");
    }

    public interface OnImageMapViewPressedListener {
        void onPressed(int localX, int localY);
    }

    View.OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClicked() {
            float globalX = myGdxGame.touch.x;
            float globalY = myGdxGame.touch.y;

            int localX = (int) (globalX - x);
            int localY = imgTexture.getHeight() - (int) (globalY - y);

            onImageMapViewPressedListener.onPressed(localX, localY);
        }
    };
}
