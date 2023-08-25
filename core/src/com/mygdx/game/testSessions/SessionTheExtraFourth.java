package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsTheExtraFourth;
import com.mygdx.game.testSessions.sessionsStates.StateTheExtraFourth;
import com.mygdx.game.utils.time.Timer;

public class SessionTheExtraFourth {

    public StateTheExtraFourth testState;
    public ResultsTheExtraFourth testResults;

    Timer fullTime;

    public SessionTheExtraFourth() {
        testState = StateTheExtraFourth.GREETING;
        testResults = new ResultsTheExtraFourth();
    }

    public void startTest() {
        testState = StateTheExtraFourth.CARDS_SHOWING;
    }

}
