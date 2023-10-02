package com.mygdx.game.utils.sequences;

import com.mygdx.game.utils.ArrayShuffler;

import java.util.Arrays;

import static com.mygdx.game.utils.ApplicationSettings.SEQUENCES_COUNT_CARDS;

public class Sequence {

    final private String[] cardsPaths = new String[SEQUENCES_COUNT_CARDS];

    public String[] getCardsPaths() {
        return cardsPaths;
    }

    Sequence(String dirToCards) {
        for (int i = 0; i < cardsPaths.length; i++) {
            cardsPaths[i] = dirToCards + (i + 1) + ".png";
        }
        ArrayShuffler.shuffle(cardsPaths);
    }

}
