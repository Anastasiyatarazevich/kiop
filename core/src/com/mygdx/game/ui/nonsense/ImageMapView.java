package com.mygdx.game.ui.nonsense;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;
import com.mygdx.game.utils.nonsense.Nonsense;

import java.util.ArrayList;

import static com.mygdx.game.utils.ApplicationSettings.NONSENSE_PICTURES_DIR;

public class ImageMapView extends ImageView {

    private OnImageMapViewPressedListener onImageMapViewPressedListener;
    MyGdxGame myGdxGame;

    ArrayList<ImageView> listHighlightImages;

    public ImageMapView(float x, float y, float height, MyGdxGame myGdxGame) {
        super(x, y, (int) height);
        this.myGdxGame = myGdxGame;
        this.listHighlightImages = new ArrayList<>();
        setOnClickListener(onClickListener);
    }

    public void setOnImageMapViewPressedListener(OnImageMapViewPressedListener onImageMapViewPressedListener) {
        this.onImageMapViewPressedListener = onImageMapViewPressedListener;
    }

    public void setSelectedSampleIdx(int selectedSampleIdx) {
        setImgSource(NONSENSE_PICTURES_DIR + selectedSampleIdx + ".png");

        width = height / imgTexture.getHeight() * imgTexture.getWidth();
    }

    public void addHighlight(int x, int y, int radius) {
        listHighlightImages.add(new ImageView(
                this.x + x, this.y + y,
                radius * 2, radius * 2,
                "nonsense/nonsenseHighlight.png")
        );
    }

    public void clearMapView() {
        listHighlightImages.clear();
    }

    View.OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClicked() {
            float globalX = myGdxGame.touch.x;
            float globalY = myGdxGame.touch.y;

            int localX = (int) (globalX - x);
            int localY = imgTexture.getHeight() - (int) (globalY - y);

            if (onImageMapViewPressedListener != null) {
                onImageMapViewPressedListener.onPressed(localX, localY);
            }
        }
    };

    public interface OnImageMapViewPressedListener {
        void onPressed(int localX, int localY);
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        super.draw(myGdxGame);
        for (ImageView highlightView : listHighlightImages) {
            highlightView.draw(myGdxGame);
        }
    }
}