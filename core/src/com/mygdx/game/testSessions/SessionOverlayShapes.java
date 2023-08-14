package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.sessionsStates.StateOverlayShapes;

public class SessionOverlayShapes {

    public StateOverlayShapes testState;

    public SessionOverlayShapes() {
        testState = StateOverlayShapes.GREETING;
    }

    public void startTest() {
        testState = StateOverlayShapes.SHAPES_SHOWING;
    }

}
