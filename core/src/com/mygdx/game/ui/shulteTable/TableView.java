package com.mygdx.game.ui.shulteTable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.ui.View;
import com.mygdx.game.utils.schulteHelper.TableItem;

import java.util.ArrayList;

import static com.mygdx.game.utils.ApplicationSettings.SCHULTE_TABLE_ITEMS_PADDING;

public class TableView extends View {

    ArrayList<TableItemView> tableItems;
    TableItemView.OnTableItemClicked onTableItemClicked;
    int tableSize;

    public TableView(float x, float y, int tableSize, BitmapFont font, float itemsSize, TableItemView.OnTableItemClicked onTableItemClicked) {
        super(x, y);
        this.tableSize = tableSize;
        this.onTableItemClicked = onTableItemClicked;

        tableItems = new ArrayList<>();
        for (int i = 0; i < tableSize * tableSize; i++) {
            float itemX = (i % tableSize) * itemsSize + (i % tableSize) * SCHULTE_TABLE_ITEMS_PADDING;
            float itemY = (i / tableSize) * itemsSize + (i / tableSize) * SCHULTE_TABLE_ITEMS_PADDING;
            TableItemView tableItemView = new TableItemView(font, "", x + itemX, y + itemY, itemsSize, itemsSize);
            tableItemView.setOnTableItemClicked(onTableItemClicked);
            tableItems.add(tableItemView);
        }
    }

    public void setTable(ArrayList<TableItem> values) {
        // tableItems.clear();
        for (int i = 0; i < values.size(); i++) {
            tableItems.get(i).setText(String.valueOf(values.get(i).getItemValue()));
            tableItems.get(i).setItemActive();
        }
    }

    public ArrayList<View> getAllViews() {
        ArrayList<View> tableItems1 = new ArrayList<>();
        tableItems1.addAll(tableItems);
        return tableItems1;
    }

}
