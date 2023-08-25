package com.mygdx.game.testSessions.results;

public class ResultsSchulteTable {

    private int spentTimeInSeconds;
    private int countOfErrors;
    private int countOfDuplicates;
    private int workEffectiveMark;

    public ResultsSchulteTable() {
        spentTimeInSeconds = 0;
        countOfErrors = 0;
        countOfDuplicates = 0;
        workEffectiveMark = -1;
    }

    public void setTime(long timeInMillis) {
        spentTimeInSeconds = Math.round((float) timeInMillis / 1000);
    }

    public void setTime(int timeInSeconds) {
        spentTimeInSeconds = timeInSeconds;
    }

    public void setCountOfErrors(int countOfErrors) {
        this.countOfErrors = countOfErrors;
    }

    public void addError() {
        countOfErrors += 1;
    }

    public void addDuplicate() {
        countOfDuplicates += 1;
    }

    public void setWorkEffectiveMark(int userAge, int countOfTables) {
        workEffectiveMark = calculateWorkEffectiveMark(userAge, countOfTables);
    }

    public int calculateWorkEffectiveMark(int userAge, int countOfTables) {

        int mark = spentTimeInSeconds / countOfTables;

        if (userAge == 6) {
            if (mark <= 60) return 5;
            if (mark >= 61 && mark <= 70) return 4;
            if (mark >= 71 && mark <= 80) return 3;
            if (mark >= 81 && mark <= 90) return 2;
            return 1;
        }
        if (userAge == 7) {
            if (mark <= 55) return 5;
            if (mark >= 56 && mark <= 65) return 4;
            if (mark >= 66 && mark <= 75) return 3;
            if (mark >= 76 && mark <= 85) return 2;
            return 1;
        }
        if (userAge == 8) {
            if (mark <= 50) return 5;
            if (mark >= 51 && mark <= 60) return 4;
            if (mark >= 61 && mark <= 70) return 3;
            if (mark >= 71 && mark <= 80) return 2;
            return 1;
        }
        if (userAge == 9) {
            if (mark <= 46) return 5;
            if (mark >= 46 && mark <= 55) return 4;
            if (mark >= 56 && mark <= 65) return 3;
            if (mark >= 66 && mark <= 75) return 2;
            return 1;
        }
        if (userAge == 10) {
            if (mark <= 40) return 5;
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
                "spent Time In Seconds=" + spentTimeInSeconds +
                ", count of errors=" + countOfErrors +
                ", count of duplicates=" + countOfDuplicates +
                ", work Effective Mark=" + workEffectiveMark +
                '}';
    }
}
