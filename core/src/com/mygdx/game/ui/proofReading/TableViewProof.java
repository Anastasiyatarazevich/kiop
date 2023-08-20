package com.mygdx.game.ui.proofReading;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.ui.View;
import com.mygdx.game.ui.shulteTable.TableItemView;
import com.mygdx.game.utils.proofReadingHelper.TableItemProofReading;


import java.util.ArrayList;

public class TableViewProof extends View {
    ArrayList<TableItemView> tableItems;
    TableItemView.OnTableItemClicked onTableItemClicked;


    public TableViewProof(float x, float y, int rows, int columns, BitmapFont font, float itemsSize, TableItemView.OnTableItemClicked onTableItemClicked) {
        super(x, y);
        this.onTableItemClicked = onTableItemClicked;

        tableItems = new ArrayList<>();
        for (int i = 0; i < rows * columns; i++) {
            float itemX = (i % columns) * itemsSize + (i % columns) * 10;
            float itemY = (i / columns) * itemsSize + (i / columns) * 10;
            TableItemView tableItemView = new TableItemView(font, "", x + itemX, y + itemY, itemsSize, itemsSize);
            tableItemView.setOnTableItemClicked(onTableItemClicked);
            tableItems.add(tableItemView);
            // System.out.println(tableItems.size());
        }
    }

    public void setTable(ArrayList<TableItemProofReading> values) {
        for (int i = 0; i < values.size(); i++) {
            tableItems.get(i).setText(values.get(i).getItemValueString());
            tableItems.get(i).setItemActive();
        }
    }


    public ArrayList<View> getAllViews() {
        ArrayList<View> tableItems1 = new ArrayList<>();
        tableItems1.addAll(tableItems);
        return tableItems1;
    }


    @Override
    public void dispose() {
//        tableItems.clear();
        for (TableItemView i: tableItems) {
            i.setClicked(false);
        }
    }
}
