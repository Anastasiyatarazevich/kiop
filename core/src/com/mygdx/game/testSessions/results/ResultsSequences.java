package com.mygdx.game.testSessions.results;

public class ResultsSequences {

    int mark;
    int spentTimeInSeconds;

    public ResultsSequences() {
        mark = 0;
    }

    public void resetResults() {
        mark = 0;
        spentTimeInSeconds = 0;
    }

    public void setSpentTimeInSeconds(int timeInSeconds) {
        spentTimeInSeconds = timeInSeconds;
    }

    public void addPoint() {
        mark += 1;
    }

    @Override
    public String toString() {
        return "Results of Sequence test{" +
                "spent Time In Seconds=" + spentTimeInSeconds +
                ", mark = " + mark +
                '}';
    }
}
