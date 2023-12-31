package com.mygdx.game.testSessions;

import static com.mygdx.game.utils.ApplicationSettings.PROOF_READING_COLUMNS;
import static com.mygdx.game.utils.ApplicationSettings.PROOF_READING_ROWS;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.testSessions.results.ResultsProofReadingTest;
import com.mygdx.game.testSessions.sessionsStates.StateProofTable;
import com.mygdx.game.utils.proofReadingHelper.TableProofReading;
import com.mygdx.game.utils.schulteHelper.SelectionResponse;
import com.mygdx.game.utils.time.Timer;


public class SessionProofTest {

    public StateProofTable testState;
    Timer testTimer;

    public TableProofReading table;
    int counter, occurrence, errors;
    public String targetLetter;

    public SessionProofTest() {
        testState = StateProofTable.GREETING;
        counter = 0;
        errors = 0;
        testTimer = new Timer();
    }

    public void startTest() {
        table = new TableProofReading(PROOF_READING_ROWS, PROOF_READING_COLUMNS);
        testState = StateProofTable.TABLE_SHOWING;
        table.generateTable();
        targetLetter = table.getTargetLetter();
        occurrence = table.getOccurrence();
        testTimer.startTimer();

    }

    public void endTest(ResultsProofReadingTest results) {
        testTimer.terminateTimer();
        int min = testTimer.getFinalTimeInSeconds() / 60;
        int sec = testTimer.getFinalTimeInSeconds() % 60;
        results.setMin(min);
        results.setSec(sec);
        results.setErrors(errors);
    }

    public void pauseTest() {
        testTimer.pauseTimer();
    }

    public void resumeTest() {
        testTimer.resumeTimer();
    }

    public void clearTest() {
        counter = 0;
        errors = 0;
        testState = StateProofTable.GREETING;
        testTimer.resetTimer();
    }

    public SelectionResponse checkSelection(String value) {
        if (value.equals(targetLetter)) {
            counter++;
            return SelectionResponse.SUCCESS;
        }
        errors++;
        return SelectionResponse.ERROR;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

