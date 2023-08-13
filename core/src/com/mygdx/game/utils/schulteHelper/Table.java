package com.mygdx.game.utils.schulteHelper;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.View;

import java.util.ArrayList;
import java.util.Collections;

import static com.mygdx.game.utils.ApplicationSettings.SCHULTE_TABLE_ITEMS_PADDING;

public class Table {

    public ArrayList<TableItem> tableMatrix;
    int tableSize;
    private long spendTime;
    public int countOfSelectedItems;

    public Table(int tableSize) {
        this.tableSize = tableSize;
        tableMatrix = new ArrayList<>();
    }

    public void generateTable() {
        tableMatrix.clear();
        for (int i = 0; i < tableSize * tableSize; i++) {
            tableMatrix.add(new TableItem(i + 1));
        }
        Collections.shuffle(tableMatrix);
        countOfSelectedItems = -1;
    }

    public boolean nextItem() {
        countOfSelectedItems += 1;
        if (countOfSelectedItems == 0) startTimer();
        if (countOfSelectedItems > 0) tableMatrix.get(countOfSelectedItems - 1).endTimer();
        if (countOfSelectedItems < tableSize * tableSize) tableMatrix.get(countOfSelectedItems).startTimer();
        else {
            endTimer();
            return false;
        }
        return true;
    }

    public void startTimer() {
        spendTime = TimeUtils.millis();
    }

    public void endTimer() {
        spendTime = TimeUtils.millis() - spendTime;
    }

    public int getSpendTime() {
        return (int) spendTime;
    }

}
