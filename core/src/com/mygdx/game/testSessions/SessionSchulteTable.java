package com.mygdx.game.testSessions;

import com.mygdx.game.testSessions.results.ResultsSchulteTable;
import com.mygdx.game.testSessions.sessionsStates.StateSchulteTable;
import com.mygdx.game.utils.schulteHelper.SelectionResponse;
import com.mygdx.game.utils.schulteHelper.Table;
import com.mygdx.game.utils.time.Timer;

import java.util.ArrayList;

import static com.mygdx.game.utils.ApplicationSettings.*;

public class SessionSchulteTable {

    public ResultsSchulteTable testResults;
    public StateSchulteTable testState;

    public int tableIdx;
    public ArrayList<Table> tables;
    Timer fullTime;

    public SessionSchulteTable() {
        fullTime = new Timer();
        tables = new ArrayList<>();
        testResults = new ResultsSchulteTable();
        testState = StateSchulteTable.GREETING;
    }

    public void startSession(int countOfTables) {
        testState = StateSchulteTable.GREETING;
        tableIdx = 0;
        tables.clear();
        for (int i = 0; i < countOfTables; i++) {
            tables.add(new Table(SCHULTE_TABLE_SIZE));
        }
        fullTime.startTimer();
    }

    public void startTest() {
        testState = StateSchulteTable.TABLE_SHOWING;
        tables.get(tableIdx).generateTable();
        tables.get(tableIdx).nextItem();
    }

    public void pauseTest() {
        fullTime.pauseTimer();
        tables.get(tableIdx).timer.pauseTimer();
    }

    public void resumeTest() {
        fullTime.resumeTimer();
        tables.get(tableIdx).timer.resumeTimer();
    }

    public void endTest() {
        fullTime.terminateTimer();
        int testOnlyTime = 0;
        for (Table table: tables) {
            testOnlyTime += table.timer.getFinalTimeInSeconds();
        }
        testState = StateSchulteTable.PASSED;
        testResults.setTime(testOnlyTime);
        // TODO: fetch user age from game session
        testResults.setWorkEffectiveMark(10, tables.size());
    }

    public void clearSession() {
        testState = StateSchulteTable.GREETING;
        fullTime.resetTimer();
        testResults.resetResults();
    }

    public void nextTable() {
        tableIdx += 1;
        tables.get(tableIdx).generateTable();
        tables.get(tableIdx).nextItem();
    }

    public SelectionResponse checkSelection(int value) {
        if (value == tables.get(tableIdx).countOfSelectedItems + 1) {
            return SelectionResponse.SUCCESS;
        }
        if (value <= tables.get(tableIdx).countOfSelectedItems) {
            return SelectionResponse.DUPLICATE;
        }
        return SelectionResponse.ERROR;
    }

}
