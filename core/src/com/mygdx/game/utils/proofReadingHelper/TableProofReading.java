package com.mygdx.game.utils.proofReadingHelper;


import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Collections;

public class TableProofReading {

    public ArrayList<TableItemProofReading> tableMatrix;

    public int rows, columns;
    public int countOfSelectedItems;

    public String targetLetter;

    public int occurrence;

    ArrayList<String> values;
    public TableProofReading(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        tableMatrix = new ArrayList<>();
    }

    public void generateTable() {
        tableMatrix.clear();
        char randomLetter;
        for (int i = 0; i < rows * columns; i++) {
            randomLetter = (char) (MathUtils.random('A', 'Z'));
            tableMatrix.add(new TableItemProofReading(String.valueOf(randomLetter)));
        }
        Collections.shuffle(tableMatrix);
        targetLetter = tableMatrix.get(MathUtils.random(0, tableMatrix.size()-1)).getItemValueString();
        occurrence = Collections.frequency(getValues(), targetLetter);
        countOfSelectedItems = -1;
    }

    public String getTargetLetter() {
        return targetLetter;
    }

    public int getOccurrence() {
        return occurrence;
    }
    public ArrayList<String> getValues() {
        values = new ArrayList<>();
        for (TableItemProofReading i : tableMatrix) {
            values.add(i.getItemValueString());
        }
        return values;
    }
}
