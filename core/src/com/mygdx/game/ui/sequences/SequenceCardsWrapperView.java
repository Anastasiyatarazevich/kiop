package com.mygdx.game.ui.sequences;

import com.mygdx.game.ui.View;

import static com.mygdx.game.utils.ApplicationSettings.SEQUENCES_COUNT_CARDS;

public class SequenceCardsWrapperView extends View {

    SequenceCardView[] listCardsView = new SequenceCardView[SEQUENCES_COUNT_CARDS];

    public SequenceCardsWrapperView(float x, float y) {
        super(x, y);
    }

    public void loadImages(String[] listImagesSources, int imageWidth, int imageHeight) {

        width = imageWidth * SEQUENCES_COUNT_CARDS;
        height = imageHeight;

        for (int i = 0; i < SEQUENCES_COUNT_CARDS; i++) {
            listCardsView[i] = new SequenceCardView(x, y);
            listCardsView[i].set
        }
    }

}
