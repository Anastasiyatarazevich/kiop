package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsNonsense;
import com.mygdx.game.testSessions.sessionsStates.StateNonsense;
import com.mygdx.game.utils.colorMapHelper.ColorMap;
import com.mygdx.game.utils.nonsense.Nonsense;
import com.mygdx.game.utils.nonsense.NonsensePicturesDescriptions;
import com.mygdx.game.utils.time.Timer;

import static com.mygdx.game.utils.ApplicationSettings.NONSENSE_COLOR_MAP_DIR;
import static com.mygdx.game.utils.ApplicationSettings.NONSENSE_COUNT_OF_SAMPLES;

public class SessionNonsense {

    public StateNonsense testState;
    public ResultsNonsense testResults;

    public ColorMap colorMap;
    private int selectedSample;
    public int countOfFoundNonsenses;

    public Nonsense[] listNonsense;

    private OnNonsenseFoundListener onNonsenseFoundListener;

    Timer fullTime;

    public SessionNonsense() {
        testResults = new ResultsNonsense();
        fullTime = new Timer();

        testState = StateNonsense.GREETING;
    }

    public void setOnNonsenseFoundListener(OnNonsenseFoundListener onNonsenseFoundListener) {
        this.onNonsenseFoundListener = onNonsenseFoundListener;
    }

    public void startSession() {
        int countOfSamples = NONSENSE_COUNT_OF_SAMPLES;
        selectedSample = (int) (Math.random() * countOfSamples);

        colorMap = new ColorMap(NONSENSE_COLOR_MAP_DIR, selectedSample);
        listNonsense = (NonsensePicturesDescriptions.allDescriptions[selectedSample]);
    }

    public int getSelectedSample() {
        return selectedSample;
    }

    public void startTest() {
        testState = StateNonsense.FINDING_NONSENSES;
        fullTime.startTimer();
    }

    public void pauseTest() {
        fullTime.pauseTimer();
    }

    public void resumeTest() {
        fullTime.resumeTimer();
    }

    public void endTest() {
        fullTime.terminateTimer();
        testResults.setSpentTimeInSeconds(fullTime.getFinalTimeInSeconds());
        testState = StateNonsense.PASSED;
        System.out.println(testResults);
    }

    public void clearSession() {
        fullTime.resetTimer();
        countOfFoundNonsenses = 0;
        testState = StateNonsense.GREETING;
        testResults.resetResults();

        for (Nonsense nonsense : listNonsense) {
            nonsense.isFound = false;
        }
    }

    public void executeColorCode(int colorCode) {
        for (Nonsense nonsense : listNonsense) {
            if (nonsense.getColorCode() == colorCode && !nonsense.isFound) {
                countOfFoundNonsenses += 1;
                nonsense.isFound = true;
                testResults.addFoundNonsense();
                if (onNonsenseFoundListener != null) {
                    onNonsenseFoundListener.onFound(nonsense);
                }
                return;
            }
        }
        testResults.addErrorClick();
    }

    public interface OnNonsenseFoundListener {
        void onFound(Nonsense nonsense);
    }

}
