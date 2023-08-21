package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsOverlayShapes;
import com.mygdx.game.testSessions.results.ResultsSchulteTable;
import com.mygdx.game.testSessions.sessionsStates.StateOverlayShapes;
import com.mygdx.game.utils.WebHelper;
import com.mygdx.game.utils.overlayShapes.ColorMap;
import com.mygdx.game.utils.overlayShapes.Shape;
import com.mygdx.game.utils.overlayShapes.ShapesDescriptor;
import com.mygdx.game.utils.time.Timer;

import java.util.ArrayList;

import static com.mygdx.game.utils.ApplicationSettings.COUNT_OF_FILES;

public class SessionOverlayShapes {

    public StateOverlayShapes testState;
    public ColorMap colorMap;
    private int selectedSample;

    public ArrayList<Shape> shapeList;
    public int leftShapes;

    Timer fullTime;

    public ResultsOverlayShapes testResults;


    public int getSelectedSample() {
        return selectedSample;
    }

    public SessionOverlayShapes() {
        shapeList = new ArrayList<>();
        testState = StateOverlayShapes.GREETING;
        fullTime = new Timer();
        testResults = new ResultsOverlayShapes();
        startSession();
    }

    public void startSession() {

        // TODO: refactor this point
        // Doesn't work on linux
        // int countOfSamples = Objects.requireNonNull(new File(COLOR_MAP_DIR).list()).length;

        int countOfSamples = COUNT_OF_FILES;
        selectedSample = (int) (Math.random() * countOfSamples);
        System.out.println("Selected sample: " + selectedSample);
        System.out.println("Count of samples: " + countOfSamples);

        colorMap = new ColorMap(selectedSample);
        shapeList = ShapesDescriptor.getSampleShapes(selectedSample);
        leftShapes = shapeList.size();


    }

    public void startTest() {
        testState = StateOverlayShapes.SHAPES_SHOWING;
        fullTime.startTimer();
    }

    public void shapeWasFound(int shapeIdx) {
        shapeList.get(shapeIdx).wasFound();
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
