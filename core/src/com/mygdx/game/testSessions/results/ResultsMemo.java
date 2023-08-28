package com.mygdx.game.testSessions.results;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mygdx.game.utils.ApplicationSettings.COUNT_OF_CARDS_TO_REMEMBER;

public class ResultsMemo {

    private int countOfCorrectCards;
    private int countOfErrors;
    private int spentTimeInSeconds;

    public void setSpentTimeInSeconds(int spentTimeInSeconds) {
        this.spentTimeInSeconds = spentTimeInSeconds;
    }

    public int getCountOfCorrectCards() {
        return countOfCorrectCards;
    }

    public int getCountOfErrors() {
        return countOfErrors;
    }

    public int getSpentTimeInSeconds() {
        return spentTimeInSeconds;
    }

    public void calculateResults(String[] shownCards, ArrayList<String> selectedCards) {

        int correcntCards = 0;

        for (int i = 0; i < COUNT_OF_CARDS_TO_REMEMBER; i++) {
            String card = shownCards[i];
            if (selectedCards.contains(card)) correcntCards += 1;
        }

        countOfCorrectCards = correcntCards;
        countOfErrors = COUNT_OF_CARDS_TO_REMEMBER - correcntCards;
    }

    @Override
    public String toString() {
        return "Result of memo{" +
                "spent time (sec) = " + spentTimeInSeconds +
                ", count of errors = " + countOfErrors +
                ", count of remembered = " + countOfCorrectCards +
                '}';
    }
}
