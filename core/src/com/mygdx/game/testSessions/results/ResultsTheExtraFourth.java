package com.mygdx.game.testSessions.results;

public class ResultsTheExtraFourth {

    private int count_of_selections;
    private int spentTimeInSeconds;
    private int workEffectiveMark;
    private int count_of_errors;

    public ResultsTheExtraFourth() {
        count_of_selections = 0;
        count_of_errors = 0;
    }

    public int getCount_of_selections() {
        return count_of_selections;
    }

    public int getSpentTimeInSeconds() {
        return spentTimeInSeconds;
    }

    public int getWorkEffectiveMark() {
        return workEffectiveMark;
    }

    public int getCount_of_errors() {
        return count_of_errors;
    }

    public void setTime(int timeInSeconds) {
        spentTimeInSeconds = timeInSeconds;
    }

    public void add_error() {
        count_of_errors += 1;
    }

    public void add_selection() {
        count_of_selections += 1;
    }

}
