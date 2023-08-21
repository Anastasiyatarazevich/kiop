package com.mygdx.game.testSessions.results;


public class ResultsOverlayShapes {

    private int spentTimeInSeconds;
    private int workEffectiveMark;

    public ResultsOverlayShapes() {
        spentTimeInSeconds = 0;
        workEffectiveMark = -1;
    }

    public void setWorkEffectiveMark(int timeInSeconds) {
        workEffectiveMark = calculateWorkEffectiveMark(timeInSeconds);
    }

    public int getSpentTimeInSeconds() {
        return spentTimeInSeconds;
    }

    public int getWorkEffectiveMark() {
        return workEffectiveMark;
    }

    public int calculateWorkEffectiveMark(int timeInSeconds) {
        spentTimeInSeconds = timeInSeconds;

        if (spentTimeInSeconds <= 20) {
            return 10;
        }
        if (spentTimeInSeconds >= 21 && spentTimeInSeconds <= 25) {
            return 9;
        }
        if (spentTimeInSeconds >= 26 && spentTimeInSeconds <= 30) {
            return 8;
        }
        if (spentTimeInSeconds >= 31 && spentTimeInSeconds <= 35) {
            return 7;
        }
        if (spentTimeInSeconds >= 36 && spentTimeInSeconds <= 40) {
            return 6;
        }
        if (spentTimeInSeconds >= 41 && spentTimeInSeconds <= 45) {
            return 5;
        }
        if (spentTimeInSeconds >= 46 && spentTimeInSeconds <= 50) {
            return 4;
        }
        if (spentTimeInSeconds >= 51 && spentTimeInSeconds <= 55) {
            return 3;
        }
        if (spentTimeInSeconds >= 56 && spentTimeInSeconds <= 60) {
            return 2;
        }
        //todo: consider the case with 1 and 0 mark
        return 0;
    }

    @Override
    public String toString() {
        return "Result of overlay shapes{" +
                "spent time (sec) =" + spentTimeInSeconds +
                ", mark =" + workEffectiveMark +
                '}';
    }
}
