package com.mygdx.game.utils.schulteHelper;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.shulteTable.TableItemView;

import static com.mygdx.game.utils.ApplicationSettings.SCHULTE_TABLE_SIZE;

public class TableItem {

    private int itemValue;
    boolean isActive;
    long spentTime;

    TableItem(int itemValue) {
        isActive = true;
        if (itemValue < 0 || itemValue > SCHULTE_TABLE_SIZE * SCHULTE_TABLE_SIZE) {
            this.itemValue = 0;
        }
        else {
            this.itemValue = itemValue;
        }

    }

    public void startTimer() {
        spentTime = TimeUtils.millis();
    }

    public void endTimer() {
        spentTime = TimeUtils.millis() - spentTime;
    }

    public int getItemValue() {
        return itemValue;
    }
}
