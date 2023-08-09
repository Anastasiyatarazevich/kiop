package com.mygdx.game.testSessions;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.testSessions.sessionsStates.StateSchulteTable;
import com.mygdx.game.utils.SceneHelper;

import java.util.ArrayList;

public class SessionSchulteTable {

    public StateSchulteTable testState;
    long startTime;
    int tableIdx;

    public SessionSchulteTable() {
        testState = StateSchulteTable.GREETING;
    }

    public void startSession() {
        testState = StateSchulteTable.GREETING;
        tableIdx = 0;
        startTime = TimeUtils.millis();
    }



}
