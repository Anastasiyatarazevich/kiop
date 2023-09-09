package com.mygdx.game.testSessions.results;

public class ResultsNonsense {

    private int countOfFoundNonsenses;
    private int countOfErrorsClicks;
    private int spentTimeInSeconds;

    public ResultsNonsense() {
        countOfFoundNonsenses = 0;
        countOfErrorsClicks = 0;
    }

    public void addFoundNonsense() {
        countOfFoundNonsenses += 1;
    }

    public void addErrorClick() {
        countOfErrorsClicks += 1;
    }

    public void setSpentTimeInSeconds(int spentTimeInSeconds) {
        this.spentTimeInSeconds = spentTimeInSeconds;
    }

    public void resetResults() {
        countOfFoundNonsenses = 0;
        countOfErrorsClicks = 0;
    }

    @Override
    public String toString() {
        return "Result of nonsense {" +
                "spent time (sec) = " + spentTimeInSeconds +
                ", count of error clicks = " + countOfErrorsClicks +
                ", count of found nonsenses = " + countOfFoundNonsenses +
                '}';
    }
}
