package com.mygdx.game.utils.theExtraFourth;

import java.util.Arrays;
import java.util.Collections;

public class CardsPresetsTheExtraFourth {

    public static Quad[] presets = {
            new Quad("card4", "card2", "card3", "card5", 1),
            new Quad("card7", "card8", "card6", "card4", 2),
            new Quad("card5", "card13", "card8", "card4", 0),
            new Quad("card14", "card16", "card21", "card15", 2),
            new Quad("card29", "card30", "card31", "card32", 2),
            new Quad("card22", "card6", "card24", "card23", 0),
            new Quad("card31", "card10", "card9", "card30", 1),
            new Quad("card17", "card20", "card18", "card28", 0),
            new Quad("card1", "card11", "card21", "card12", 0),
            new Quad("card19", "card25", "card26", "card27", 2)
    };

    public static void shufflePreset() {
        Collections.shuffle(Arrays.asList(presets));
    }

}
