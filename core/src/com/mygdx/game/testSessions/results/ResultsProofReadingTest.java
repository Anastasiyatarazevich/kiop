package com.mygdx.game.testSessions.results;

public class ResultsProofReadingTest {
    private long  min, sec;
    private int errors;

    public ResultsProofReadingTest() {
        this.errors = 0;
        this.min = 0;
        this.sec = 0;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getSec() {
        return sec;
    }

    public void setSec(long sec) {
        this.sec = sec;
    }

    @Override
    public String toString() {
        return "ProofReadingTestResults{" +
                "errors=" + errors +
                ", min=" + min +
                ", sec=" + sec +
                '}';
    }
}
