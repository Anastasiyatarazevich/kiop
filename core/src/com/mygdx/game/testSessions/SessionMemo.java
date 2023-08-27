package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsMemo;
import com.mygdx.game.testSessions.sessionsStates.StateMemo;
import com.mygdx.game.utils.time.Timer;

public class SessionMemo {

    public ResultsMemo testResults;
    public StateMemo testState;

    private Timer guessingTime;

    public SessionMemo() {
        testResults = new ResultsMemo();
        testState = StateMemo.GREETING;
        guessingTime = new Timer();
    }

    public void startTest() {
        testState = StateMemo.REMEMBERING_CARDS;
    }

    public void hideCards() {
        testState = StateMemo.HIDING_CARDS;
    }

    public void showFullCards() {
        testState = StateMemo.GETTING_CARDS;
    }

    public void endTest() {

    }

}
