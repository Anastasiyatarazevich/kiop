package com.mygdx.game.testSessions.results;

public class ResultsMemo {

    private int count_of_correct_cards;
    private int count_of_errors;
    private int workEffectiveMark;
    private int spentTimeInSeconds;

    public int getCount_of_correct_cards() {
        return count_of_correct_cards;
    }

    public void setCount_of_correct_cards(int count_of_correct_cards) {
        this.count_of_correct_cards = count_of_correct_cards;
    }

    public int getCount_of_errors() {
        return count_of_errors;
    }

    public void setCount_of_errors(int count_of_errors) {
        this.count_of_errors = count_of_errors;
    }

    public int getWorkEffectiveMark() {
        return workEffectiveMark;
    }

    public void setWorkEffectiveMark(int workEffectiveMark) {
        this.workEffectiveMark = workEffectiveMark;
    }

    public int getSpentTimeInSeconds() {
        return spentTimeInSeconds;
    }

    public void setSpentTimeInSeconds(int spentTimeInSeconds) {
        this.spentTimeInSeconds = spentTimeInSeconds;
    }
}
