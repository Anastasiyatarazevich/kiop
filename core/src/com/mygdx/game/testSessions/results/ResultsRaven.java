package com.mygdx.game.testSessions.results;

public class ResultsRaven {

    private int points, workEffectiveMark;
    private int spentTimeInSeconds;

    public int getSpentTimeInSeconds() {
        return spentTimeInSeconds;
    }

    public void setSpentTimeInSeconds(int spentTimeInSeconds) {
        this.spentTimeInSeconds = spentTimeInSeconds;
    }

    public ResultsRaven() {
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint() {
        points += 1;
    }

    public void calculateWorkEffectiveMark() {
        //todo: calculate mark
    }

    public void clearResults() {
        points = 0;
    }

    @Override
    public String toString() {
        return "ResultsRaven{" +
                "points=" + points +
                ",time=" + spentTimeInSeconds +
                '}';
    }
}
