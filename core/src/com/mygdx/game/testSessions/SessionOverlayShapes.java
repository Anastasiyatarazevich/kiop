package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.sessionsStates.StateOverlayShapes;
import com.mygdx.game.utils.overlayShapes.ColorMap;

import static com.mygdx.game.utils.ApplicationSettings.COUNT_OF_FILES;

public class SessionOverlayShapes {

    public StateOverlayShapes testState;
    public ColorMap colorMap;
    private int selectedSample;

    public int getSelectedSample() {
        return selectedSample;
    }

    public SessionOverlayShapes() {
        testState = StateOverlayShapes.GREETING;
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

        prepareColorMap(selectedSample);
    }

    public void startTest() {
        testState = StateOverlayShapes.SHAPES_SHOWING;
    }

    private void prepareColorMap(int selectedSample) {
        colorMap = new ColorMap(selectedSample);

        System.out.println("Color code: " + colorMap.getColorCode(535, 360));
    }

}
