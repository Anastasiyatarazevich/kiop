package com.mygdx.game.testSessions;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.testSessions.sessionsStates.StateSchulteTable;
import com.mygdx.game.utils.schulteHelper.SelectionResponse;
import com.mygdx.game.utils.schulteHelper.Table;

import java.util.ArrayList;

import static com.mygdx.game.utils.ApplicationSettings.*;

public class SessionSchulteTable {

    public StateSchulteTable testState;
    long sessionTime;
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
        sessionTime = TimeUtils.millis();
    }

    public void startTest() {
        testState = StateSchulteTable.TABLE_SHOWING;
        tables.get(tableIdx).generateTable();
        tables.get(tableIdx).nextItem();
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

    public int getWorkEffectiveMark(int userAge) {

        int spentTime = 0;
        for (Table table : tables) {
            spentTime += table.getSpendTime();
        }
        int mark = spentTime / tables.size();

        if (userAge == 6) {
            if (mark >= 56 && mark <= 60) return 5;
            if (mark >= 61 && mark <= 70) return 4;
            if (mark >= 71 && mark <= 80) return 3;
            if (mark >= 81 && mark <= 90) return 2;
            return 1;
        }
        if (userAge == 7) {
            if (mark >= 51 && mark <= 55) return 5;
            if (mark >= 56 && mark <= 65) return 4;
            if (mark >= 66 && mark <= 75) return 3;
            if (mark >= 76 && mark <= 85) return 2;
            return 1;
        }
        if (userAge == 8) {
            if (mark >= 46 && mark <= 50) return 5;
            if (mark >= 51 && mark <= 60) return 4;
            if (mark >= 61 && mark <= 70) return 3;
            if (mark >= 71 && mark <= 80) return 2;
            return 1;
        }
        if (userAge == 9) {
            if (mark >= 41 && mark <= 46) return 5;
            if (mark >= 46 && mark <= 55) return 4;
            if (mark >= 56 && mark <= 65) return 3;
            if (mark >= 66 && mark <= 75) return 2;
            return 1;
        }
        if (userAge == 10) {
            if (mark >= 36 && mark <= 40) return 5;
            if (mark >= 41 && mark <= 50) return 4;
            if (mark >= 51 && mark <= 60) return 3;
            if (mark >= 61 && mark <= 70) return 2;
            return 1;
        }
        return 0;
    }


}
