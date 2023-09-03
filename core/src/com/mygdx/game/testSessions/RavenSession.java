package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsRaven;
import com.mygdx.game.testSessions.sessionsStates.StateRaven;
import com.mygdx.game.utils.raven.Cards;
import com.mygdx.game.utils.time.Timer;

public class RavenSession {

    public StateRaven testState;
    public ResultsRaven testResults;

    Timer fullTime;

    public int currentMatrixIndex;
    private int selectedCardIndex;

    public RavenSession() {
        testResults = new ResultsRaven();
        fullTime = new Timer();
        testState = StateRaven.GREETING;
        selectedCardIndex = -1;
    }

    public void startTest() {
        testState = StateRaven.CARDS_SHOWING;
        fullTime.startTimer();
        currentMatrixIndex = 0;
    }

    public void pauseTest() {
        fullTime.pauseTimer();
    }

    public void resumeTest() {
        fullTime.resumeTimer();
    }

    public void endTest() {
        testState = StateRaven.PASSED;
        fullTime.terminateTimer();
        testResults.setSpentTimeInSeconds(fullTime.getFinalTimeInSeconds());
        System.out.println(testResults.toString());
    }

    public void clearSession() {
        fullTime.resetTimer();
        testResults.clearResults();
        testState = StateRaven.GREETING;
        currentMatrixIndex = 0;
    }

    public void checkingTime() {
        if (fullTime.updateTime() > 240 && testState == StateRaven.CARDS_SHOWING) {
            endTest();
        }
    }

    public void cardWasSelected(int idxOfCard) {
        selectedCardIndex = idxOfCard;
    }

    public boolean nextCards() {
        if (selectedCardIndex != Cards.presets[currentMatrixIndex].getIdxOfExtra()) {
            // todo: error case in raven
        } else {
            testResults.addPoint();
        }
        currentMatrixIndex += 1;
        selectedCardIndex = -1;
        if (currentMatrixIndex == Cards.presets.length) {
            endTest();
            return false;
        }

        return true;
    }

}
