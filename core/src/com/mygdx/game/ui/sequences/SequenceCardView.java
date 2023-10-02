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
        imageViewIdx.setImgSource(listImagesIdx[idx - 1]);
        imageViewIdx.loadSizeOfTexture();
    }

    public void setImageContent(String source) {
        imageViewContent.setImgSource(source);
        imageViewContent.loadSizeOfTexture();
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        imageViewContent.draw(myGdxGame);
        imageViewIdx.draw(myGdxGame);
    }

    @Override
    public boolean isHit(float tx, float ty) {
        return imageViewContent.isHit(tx, ty);
    }
}
