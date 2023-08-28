package com.mygdx.game.utils.raven;

import java.util.ArrayList;
import java.util.Collections;

public class Matrix {

    private final ArrayList<String> listOfCardsSrc;
    private int correct;

    public Matrix(String card1Src, String card2Src, String card3Src, String card4Src, String card5Src, String card6Src, int correct) {
        listOfCardsSrc = new ArrayList<>();
        listOfCardsSrc.add(card1Src);
        listOfCardsSrc.add(card2Src);
        listOfCardsSrc.add(card3Src);
        listOfCardsSrc.add(card4Src);
        listOfCardsSrc.add(card5Src);
        listOfCardsSrc.add(card6Src);
        this.correct = correct;
    }

    public void shuffleCards() {
        String extraCardSrc = listOfCardsSrc.get(correct);
        Collections.shuffle(listOfCardsSrc);

        for (int i = 0; i < listOfCardsSrc.size(); i++) {
            if (listOfCardsSrc.get(i).equals(extraCardSrc)) {
                correct = i;
                break;
            }
        }
    }

    public ArrayList<String> getListOfCardsSrc() {
        return listOfCardsSrc;
    }

    public int getIdxOfExtra() {
        return correct;
    }
}
