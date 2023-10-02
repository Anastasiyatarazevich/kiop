package com.mygdx.game.ui.sequences;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.View;

import static com.mygdx.game.utils.ApplicationSettings.SEQUENCES_COUNT_CARDS;

public class SequenceCardsWrapperView extends View {

    final private int padding = 30;

    public SequenceCardView[] listCardsView = new SequenceCardView[SEQUENCES_COUNT_CARDS];

    OnSequenceItemClicked onSequenceItemClicked;

    public SequenceCardsWrapperView(float x, float y) {
        super(x, y);
    }

    public void setOnSequenceItemClicked(OnSequenceItemClicked onSequenceItemClicked) {
        this.onSequenceItemClicked = onSequenceItemClicked;
    }

    public void loadImages(String[] listImagesSources, int imageWidth, int imageHeight) {

        if (listImagesSources == null) return;

        width = imageWidth * SEQUENCES_COUNT_CARDS;
        height = imageHeight;

        for (int i = 0; i < SEQUENCES_COUNT_CARDS; i++) {
            int newX = (i != 0) ? padding * i : 0;
            newX += (int) x + (imageWidth * i);
            listCardsView[i] = new SequenceCardView(newX, y);
            listCardsView[i].setImageContent(listImagesSources[i]);
        }
    }

    public void dropIdxes() {
        for (SequenceCardView card : listCardsView) {
            card.dropImageIdx();
        }
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        for (SequenceCardView card : listCardsView) {
            card.draw(myGdxGame);
        }
    }

    @Override
    public boolean isHit(float tx, float ty) {
        for (int i = 0; i < listCardsView.length; i++) {
            if (listCardsView[i].isHit(tx, ty)) {
                if (onSequenceItemClicked != null) {
                    onSequenceItemClicked.onItemClicked(i);
                }
                return true;
            }
        }
        return false;
    }

    public interface OnSequenceItemClicked {
        void onItemClicked(int itemIdx);
    }
}
