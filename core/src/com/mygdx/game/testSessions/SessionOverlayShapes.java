package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsOverlayShapes;
import com.mygdx.game.testSessions.sessionsStates.StateOverlayShapes;
import com.mygdx.game.utils.WebHelper;
import com.mygdx.game.utils.colorMapHelper.ColorMap;
import com.mygdx.game.utils.overlayShapes.Shape;
import com.mygdx.game.utils.overlayShapes.ShapesDescriptor;
import com.mygdx.game.utils.time.Timer;

import java.util.ArrayList;

import static com.mygdx.game.utils.ApplicationSettings.OVERLAY_COLOR_MAP_DIR;
import static com.mygdx.game.utils.ApplicationSettings.COUNT_OF_FILES;

public class SessionOverlayShapes {

    public StateOverlayShapes testState;
    public ResultsOverlayShapes testResults;

    public ColorMap colorMap;
    private int selectedSample;

    public ArrayList<Shape> listShapes;
    public int leftShapes;

    Timer fullTime;

    public int getSelectedSample() {
        return selectedSample;
    }

    public SessionOverlayShapes() {
        listShapes = new ArrayList<>();
        testState = StateOverlayShapes.GREETING;
        fullTime = new Timer();
        testResults = new ResultsOverlayShapes();
    }

    public void startSession() {

        // TODO: refactor this point
        // Doesn't work on linux
        // int countOfSamples = Objects.requireNonNull(new File(COLOR_MAP_DIR).list()).length;

        int countOfSamples = COUNT_OF_FILES;
        selectedSample = (int) (Math.random() * countOfSamples);

        colorMap = new ColorMap(OVERLAY_COLOR_MAP_DIR, selectedSample);
        listShapes = ShapesDescriptor.getSampleShapes(selectedSample);
        leftShapes = listShapes.size();

    }

    public void startTest() {
        testState = StateOverlayShapes.SHAPES_SHOWING;
        fullTime.startTimer();
    }

    public void pauseTest() {
        fullTime.pauseTimer();
    }

    public void resumeTest() {
        fullTime.resumeTimer();
    }

    public void clearSession() {
        testState = StateOverlayShapes.GREETING;
        fullTime.resetTimer();
    }

    public void shapeWasFound(int shapeIdx) {
        listShapes.get(shapeIdx).wasFound();
        leftShapes -= 1;

        if (leftShapes == 0) {
            testState = StateOverlayShapes.PASSED;
            fullTime.terminateTimer();
            testResults.setWorkEffectiveMark(fullTime.getFinalTimeInSeconds());
            System.out.println(testResults);

            WebHelper.postRequest(testResults.getWorkEffectiveMark(), testResults.getSpentTimeInSeconds());
        }
    }
}
