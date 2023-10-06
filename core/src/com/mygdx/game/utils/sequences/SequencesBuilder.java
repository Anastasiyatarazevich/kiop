package com.mygdx.game.utils.sequences;

import com.mygdx.game.utils.ArrayShuffler;

public class SequencesBuilder {

    public static Sequence[] arraySequences = {
            new Sequence("sequences/cards/1/"),
            new Sequence("sequences/cards/2/"),
            new Sequence("sequences/cards/3/"),
            new Sequence("sequences/cards/4/"),
            new Sequence("sequences/cards/5/"),
    };

    public static void shuffle() {
        ArrayShuffler.shuffle(arraySequences);
    }

}
