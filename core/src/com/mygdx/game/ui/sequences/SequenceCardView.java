package com.mygdx.game.ui.sequences;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;

public class SequenceCardView extends View {

    static String[] listImagesIdx = {
            "sequences/cardIdx/1.png",
            "sequences/cardIdx/2.png",
            "sequences/cardIdx/3.png",
            "sequences/cardIdx/4.png"
    };

    ImageView imageViewIdx;
    ImageView imageViewContent;

    public SequenceCardView(float x, float y) {
        super(x, y);

        imageViewIdx = new ImageView(x, y);
        imageViewContent = new ImageView(x, y);

    }

    public void setImageIdx(int idx) {
        imageViewIdx.isVisible = true;
        imageViewIdx.setImgSource(listImagesIdx[idx - 1]);
        imageViewIdx.loadSizeOfTexture();
    }

    public void dropImageIdx() {
        imageViewIdx.isVisible = false;
    }

    public void setImageContent(String source) {
        imageViewContent.setImgSource(source);
        imageViewContent.loadSizeOfTexture();
        setImageIdx(1);
        dropImageIdx();
        imageViewIdx.x = x + imageViewContent.width - imageViewIdx.width - 25;
        imageViewIdx.y = y + imageViewContent.height - imageViewIdx.height - 25;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        imageViewContent.draw(myGdxGame);
        if (imageViewIdx.isVisible) imageViewIdx.draw(myGdxGame);
    }

    @Override
    public boolean isHit(float tx, float ty) {
        return imageViewContent.isHit(tx, ty);
    }
}
