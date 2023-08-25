package com.mygdx.game.utils.theExtraFourth;

import java.util.ArrayList;
import java.util.Collections;

public class Quad {

    private final ArrayList<String> listOfCardsSrc;
    private int idxOfExtra;

    public Quad(String card1Src, String card2Src, String card3Src, String card4Src, int idxOfExtra) {
        listOfCardsSrc = new ArrayList<>();
        listOfCardsSrc.add(card1Src);
        listOfCardsSrc.add(card2Src);
        listOfCardsSrc.add(card3Src);
        listOfCardsSrc.add(card4Src);
        this.idxOfExtra = idxOfExtra;
    }

    public void shuffleCards() {
        String extraCardSrc = listOfCardsSrc.get(idxOfExtra);
        Collections.shuffle(listOfCardsSrc);

        for (int i = 0; i < listOfCardsSrc.size(); i++) {
            if (listOfCardsSrc.get(i).equals(extraCardSrc)) {
                idxOfExtra = i;
                break;
            }
        }
    }

    public ArrayList<String> getListOfCardsSrc() {
        return listOfCardsSrc;
    }

    public int getIdxOfExtra() {
        return idxOfExtra;
    }
}
