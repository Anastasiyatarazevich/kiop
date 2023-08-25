package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsTheExtraFourth;
import com.mygdx.game.testSessions.sessionsStates.StateTheExtraFourth;
import com.mygdx.game.utils.theExtraFourth.CardsPresets;
import com.mygdx.game.utils.time.Timer;

public class SessionTheExtraFourth {

    public StateTheExtraFourth testState;
    public ResultsTheExtraFourth testResults;

    Timer fullTime;
    public int currentQuadIdx;

    public SessionTheExtraFourth() {
        testResults = new ResultsTheExtraFourth();
        fullTime = new Timer();
        testState = StateTheExtraFourth.GREETING;

    }

    public void startTest() {
        testState = StateTheExtraFourth.CARDS_SHOWING;
        fullTime.startTimer();
        currentQuadIdx = 0;
        CardsPresets.shufflePreset();
    }

}
