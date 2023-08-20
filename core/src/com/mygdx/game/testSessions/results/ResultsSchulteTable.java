package com.mygdx.game.testSessions.results;

public class ResultsSchulteTable {

    private int spentTimeInSeconds;
    private int count_of_errors;
    private int count_of_duplicates;
    private int workEffectiveMark;

    public ResultsSchulteTable() {
        spentTimeInSeconds = 0;
        count_of_errors = 0;
        count_of_duplicates = 0;
        workEffectiveMark = -1;
    }

    public void setTime(long timeInMillis) {
        spentTimeInSeconds = Math.round((float) timeInMillis / 1000);
    }

    public void setTime(int timeInSeconds) {
        spentTimeInSeconds = timeInSeconds;
    }

    public void setCount_of_errors(int count_of_errors) {
        this.count_of_errors = count_of_errors;
    }

    public void addError() {
        count_of_errors += 1;
    }

    public void addDuplicate() {
        count_of_duplicates += 1;
    }

    public void setWorkEffectiveMark(int userAge, int countOfTables) {
        workEffectiveMark = calculateWorkEffectiveMark(userAge, countOfTables);
    }

    public int calculateWorkEffectiveMark(int userAge, int countOfTables) {

        int mark = spentTimeInSeconds / countOfTables;

        if (userAge == 6) {
            if (mark >= 56 && mark <= 60) return 5;
            if (mark >= 61 && mark <= 70) return 4;
            if (mark >= 71 && mark <= 80) return 3;
            if (mark >= 81 && mark <= 90) return 2;
            return 1;
        }
        if (userAge == 7) {
            if (mark >= 51 && mark <= 55) return 5;
            if (mark >= 56 && mark <= 65) return 4;
            if (mark >= 66 && mark <= 75) return 3;
            if (mark >= 76 && mark <= 85) return 2;
            return 1;
        }
        if (userAge == 8) {
            if (mark >= 46 && mark <= 50) return 5;
            if (mark >= 51 && mark <= 60) return 4;
            if (mark >= 61 && mark <= 70) return 3;
            if (mark >= 71 && mark <= 80) return 2;
            return 1;
        }
        if (userAge == 9) {
            if (mark >= 41 && mark <= 46) return 5;
            if (mark >= 46 && mark <= 55) return 4;
            if (mark >= 56 && mark <= 65) return 3;
            if (mark >= 66 && mark <= 75) return 2;
            return 1;
        }
        if (userAge == 10) {
            if (mark >= 36 && mark <= 40) return 5;
            if (mark >= 41 && mark <= 50) return 4;
            if (mark >= 51 && mark <= 60) return 3;
            if (mark >= 61 && mark <= 70) return 2;
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "ResultsSchulteTable{" +
                "spentTimeInSeconds=" + spentTimeInSeconds +
                ", count_of_errors=" + count_of_errors +
                ", count_of_duplicates=" + count_of_duplicates +
                ", workEffectiveMark=" + workEffectiveMark +
                '}';
    }
}
