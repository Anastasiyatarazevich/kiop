package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsMemo;
import com.mygdx.game.testSessions.sessionsStates.StateMemo;
import com.mygdx.game.utils.memo.CardsPresetsMemo;
import com.mygdx.game.utils.time.Timer;

import java.util.ArrayList;

public class SessionMemo {

    public ResultsMemo testResults;
    public StateMemo testState;

    private Timer guessingTime;

    private ArrayList<String> selectedCards;
    public String[] shownCards;

    public SessionMemo() {
        testResults = new ResultsMemo();
        testState = StateMemo.GREETING;
        guessingTime = new Timer();

        shownCards = CardsPresetsMemo.getShuffledPreset();
        selectedCards = new ArrayList<>();
    }

    public void startTest() {
        testState = StateMemo.REMEMBERING_CARDS;
    }

    public void hideCards() {
        testState = StateMemo.HIDING_CARDS;
    }

    public void cardsWereReleased() {
        guessingTime.startTimer();
    }

    public void endTest() {
        testState = StateMemo.PASSED;
        guessingTime.terminateTimer();

        testResults.setSpentTimeInSeconds(guessingTime.getFinalTimeInSeconds());
        testResults.calculateResults(shownCards, selectedCards);
        System.out.println(testResults);
    }

    public void selectCard(String cardSrc) {
        if (!selectedCards.contains(cardSrc)) {
            selectedCards.add(cardSrc);
        } else {
            selectedCards.remove(cardSrc);
        }
    }

}
