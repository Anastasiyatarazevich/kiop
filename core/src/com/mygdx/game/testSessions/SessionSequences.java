package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsSequences;
import com.mygdx.game.testSessions.sessionsStates.StateNonsense;
import com.mygdx.game.testSessions.sessionsStates.StateSequences;
import com.mygdx.game.utils.sequences.SequencesBuilder;
import com.mygdx.game.utils.time.Timer;
import com.sun.org.apache.bcel.internal.generic.PUSH;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mygdx.game.utils.ApplicationSettings.*;


public class SessionSequences {

    public StateSequences testState;
    public ResultsSequences testResults;

    int sequenceIdx;

    Timer timerFullSession;
    public ArrayList<Integer> listSequenceIdx;

    public SessionSequences() {
        testResults = new ResultsSequences();
        timerFullSession = new Timer();
        listSequenceIdx = new ArrayList<>();
        testState = StateSequences.GREETING;
    }

    public void startTest() {
        testState = StateSequences.FINDING_SEQUENCES;
        SequencesBuilder.shuffle();
        timerFullSession.startTimer();
        listSequenceIdx.clear();
        sequenceIdx = 0;
    }

    public String[] getCardSequence() {
        if (sequenceIdx >= SEQUENCES_COUNT) return null;
        return SequencesBuilder.arraySequences[sequenceIdx].getCardsPaths();
    }

    public boolean nextSequence() {
        if (!checkCorrectAnswer()) return false;
        sequenceIdx += 1;
        testResults.addPoint();
        if (sequenceIdx >= SEQUENCES_COUNT) {
            endTest();
        }
        return true;
    }

    private boolean checkCorrectAnswer() {
        for (int i = 0; i < listSequenceIdx.size(); i++) {
            String[] bufArray = SequencesBuilder.arraySequences[sequenceIdx].getCardsPaths()[listSequenceIdx.get(i)].split("/");
            if (Integer.parseInt(bufArray[bufArray.length - 1].split("\\.")[0]) != (i + 1)) {
                return false;
            }
        }
        return true;
    }

    public int addNewIdx(int idx) {
        if (listSequenceIdx.contains(idx)) return -1;
        if (listSequenceIdx.size() >= SEQUENCES_COUNT_CARDS) return -1;
        listSequenceIdx.add(idx);
        return listSequenceIdx.size();
    }

    public void dropIdxes() {
        listSequenceIdx.clear();
    }

    public void pauseTest() {
        timerFullSession.pauseTimer();
    }

    public void resumeTest() {
        timerFullSession.resumeTimer();
    }

    public void endTest() {
        timerFullSession.terminateTimer();
        testResults.setSpentTimeInSeconds(timerFullSession.getFinalTimeInSeconds());
        testState = StateSequences.PASSED;
        System.out.println(testResults);
    }

    public void clearSession() {
        sequenceIdx = 0;
        testResults.resetResults();
        listSequenceIdx.clear();
        timerFullSession.resetTimer();
        testState = StateSequences.GREETING;
    }

    public boolean checkTimer() {
        if (timerFullSession.getFinalTimeInSeconds() >= SEQUENCES_TIME_IN_SECONDS) {
            endTest();
            return false;
        }
        return true;
    }

}
