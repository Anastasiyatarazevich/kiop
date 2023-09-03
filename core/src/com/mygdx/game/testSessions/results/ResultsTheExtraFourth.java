package com.mygdx.game.testSessions.results;

import com.mygdx.game.utils.theExtraFourth.CardsPresetsTheExtraFourth;

public class ResultsTheExtraFourth {

    private int count_of_selections;
    private int spentTimeInSeconds;
    private int workEffectiveMark;
    private int count_of_errors;

    public ResultsTheExtraFourth() {
        count_of_selections = 0;
        count_of_errors = 0;
    }

    public int getCountOfSelections() {
        return count_of_selections;
    }

    public int getSpentTimeInSeconds() {
        return spentTimeInSeconds;
    }

    public int getWorkEffectiveMark() {
        return workEffectiveMark;
    }

    public int getCountOfErrors() {
        return count_of_errors;
    }

    public void setTime(int timeInSeconds) {
        spentTimeInSeconds = timeInSeconds;
    }

    public void addError() {
        count_of_errors += 1;
    }

    public void addSelection() {
        count_of_selections += 1;
    }

    public void computeWorkEffectiveMark() {
        workEffectiveMark = CardsPresetsTheExtraFourth.presets.length - count_of_errors;
    }

    public void resetResults() {
        count_of_selections = 0;
        count_of_errors = 0;
    }

    @Override
    public String toString() {
        return "ResultsSchulteTable{" +
                "spentTimeInSeconds=" + spentTimeInSeconds +
                ", count of errors=" + count_of_errors +
                ", count of selections=" + count_of_selections +
                ", workEffectiveMark=" + workEffectiveMark +
                '}';
    }
}
