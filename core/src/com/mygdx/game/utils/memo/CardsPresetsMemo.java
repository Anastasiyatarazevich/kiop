package com.mygdx.game.utils.memo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mygdx.game.utils.ApplicationSettings.COUNT_OF_ADDITION_CARDS;
import static com.mygdx.game.utils.ApplicationSettings.COUNT_OF_CARDS_TO_REMEMBER;

public class CardsPresetsMemo {

    public static String[] preset = {
            "card1", "card2", "card3", "card4",
            "card5", "card6", "card7", "card8",
            "card9", "card10", "card11", "card12",
            "card13", "card14", "card15", "card16",
            "card17", "card18", "card19", "card20",
            "card21", "card22", "card23", "card24",
            "card25", "card26", "card27", "card28",
            "card29", "card30", "card31", "card32"
    };

    public static String[] getShuffledPreset() {
        String[] outputArray = new String[COUNT_OF_CARDS_TO_REMEMBER + COUNT_OF_ADDITION_CARDS];
        List<String> list = new ArrayList<>(Arrays.asList(preset));

        Collections.shuffle(list);

        for (int i = 0; i < COUNT_OF_CARDS_TO_REMEMBER + COUNT_OF_ADDITION_CARDS; i++) {
            outputArray[i] = list.get(i);
        }
        return outputArray;
    }

}
