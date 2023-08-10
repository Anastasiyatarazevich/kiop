package com.mygdx.game.testSessions;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.testSessions.sessionsStates.StateSchulteTable;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.schulteHelper.Table;

import java.util.ArrayList;

import static com.mygdx.game.utils.ApplicationSettings.*;

public class SessionSchulteTable {

    public StateSchulteTable testState;
    long startTime;
    public int tableIdx;
    public ArrayList<Table> tables;

    public SessionSchulteTable() {
        tables = new ArrayList<>();
        testState = StateSchulteTable.GREETING;
    }

    public void startSession(int countOfTables) {
        testState = StateSchulteTable.GREETING;
        tableIdx = 0;
        tables.clear();
        for (int i = 0; i < countOfTables; i++) {
            tables.add(new Table(SCHULTE_TABLE_SIZE));
        }
        startTime = TimeUtils.millis();
    }

    public void startTest() {
        testState = StateSchulteTable.TABLE_SHOWING;
        newTable();
    }

    public void newTable() {
        tables.get(tableIdx).generateTable();
    }



}
