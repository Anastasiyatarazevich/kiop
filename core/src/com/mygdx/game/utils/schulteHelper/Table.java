package com.mygdx.game.utils.schulteHelper;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.View;

import java.util.ArrayList;
import java.util.Collections;

import static com.mygdx.game.utils.ApplicationSettings.SCHULTE_TABLE_ITEMS_PADDING;

public class Table {

    public ArrayList<TableItem> tableMatrix;
    int tableSize;

    public Table(int tableSize) {
        this.tableSize = tableSize;
        tableMatrix = new ArrayList<>();
    }

    public void generateTable() {
        tableMatrix.clear();
        for (int i = 0; i < tableSize * tableSize; i++) {
            tableMatrix.add(new TableItem(i));
        }
        Collections.shuffle(tableMatrix);
    }

}
