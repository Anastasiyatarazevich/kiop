package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsTheExtraFourth;
import com.mygdx.game.testSessions.sessionsStates.StateTheExtraFourth;
import com.mygdx.game.utils.theExtraFourth.CardsPresetsTheExtraFourth;
import com.mygdx.game.utils.time.Timer;

public class SessionTheExtraFourth {

    public StateTheExtraFourth testState;
    public ResultsTheExtraFourth testResults;

    Timer fullTime;
    public int currentQuadIdx;
    private int selectedCardIdx;

    public SessionTheExtraFourth() {
        testResults = new ResultsTheExtraFourth();
        fullTime = new Timer();
        testState = StateTheExtraFourth.GREETING;
        selectedCardIdx = -1;
    }

    public void startTest() {
        testState = StateTheExtraFourth.CARDS_SHOWING;
        fullTime.startTimer();
        currentQuadIdx = 0;
        CardsPresetsTheExtraFourth.shufflePreset();
    }

    public void pauseTest() {
        fullTime.pauseTimer();
    }

    public void resumeTest() {
        fullTime.resumeTimer();
    }

    public void endTest() {
        testState = StateTheExtraFourth.PASSED;
        fullTime.terminateTimer();
        testResults.setTime(fullTime.getFinalTimeInSeconds());
        testResults.computeWorkEffectiveMark();
        System.out.println(testResults);
    }

    public void clearSession() {
        currentQuadIdx = 0;
        fullTime.resetTimer();
        testResults.resetResults();
        testState = StateTheExtraFourth.GREETING;
    }

    public void cardWasSelected(int idxOfCard) {
        selectedCardIdx = idxOfCard;
        testResults.addSelection();
    }

    public boolean nextCards() {
        if (selectedCardIdx != CardsPresetsTheExtraFourth.presets[currentQuadIdx].getIdxOfExtra()) {
            testResults.addError();
        }
        currentQuadIdx += 1;
        selectedCardIdx = -1;

        if (currentQuadIdx == CardsPresetsTheExtraFourth.presets.length) {
            endTest();
            return false;
        }

        return true;
    }

}
