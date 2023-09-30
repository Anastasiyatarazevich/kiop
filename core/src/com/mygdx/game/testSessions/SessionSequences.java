package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsSequences;
import com.mygdx.game.testSessions.sessionsStates.StateSequences;
import com.mygdx.game.utils.time.Timer;

import javax.swing.plaf.nimbus.State;

public class SessionSequences {

    public StateSequences testState;
    public ResultsSequences testResults;

    Timer timerFullSession;

    public SessionSequences() {
        testResults = new ResultsSequences();
        timerFullSession = new Timer();
        testState = StateSequences.GREETING;
    }

    public void startTest() {
        testState = StateSequences.FINDING_SEQUENCES;
    }

    public void pauseTest() {
        timerFullSession.pauseTimer();
    }

    public void resumeTest() {
        timerFullSession.resetTimer();
    }

    public void endTest() {
        testState = StateSequences.PASSED;
    }

    public void clearSession() {

    }

}
