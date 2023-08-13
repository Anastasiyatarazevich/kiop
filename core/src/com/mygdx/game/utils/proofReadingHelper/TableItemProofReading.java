package com.mygdx.game.utils.proofReadingHelper;

import com.badlogic.gdx.utils.TimeUtils;

public class TableItemProofReading {

    private char itemValue;
    private String value;
    boolean isActive, isClicked;
    long spentTime;

    TableItemProofReading(char itemValue) {
        isActive = true;
        this.itemValue = itemValue;

    }

    TableItemProofReading(String itemValue) {
        isActive = true;
        isClicked = false;
        this.value = itemValue;

    }

    public void startTimer() {
        spentTime = TimeUtils.millis();
    }

    public void endTimer() {
        spentTime = TimeUtils.millis() - spentTime;
    }

    public char getItemValue() {
        return itemValue;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public String getItemValueString() {
        return value;
    }

}
