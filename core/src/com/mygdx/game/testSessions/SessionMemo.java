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

}
