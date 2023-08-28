package com.mygdx.game.utils.time;

import com.badlogic.gdx.utils.TimeUtils;

public class Timer {

    private long pauseTime;
    private long startTime;
    private long endTime;
    private TimeState timerState;

    public Timer() {
        timerState = TimeState.READY_TO_START;
    }

    public int startTimer() {
        if (timerState != TimeState.READY_TO_START)
            return 1;

        startTime = TimeUtils.millis();
        timerState = TimeState.RUNNING;

        return 0;
    }

    public int terminateTimer() {
        if (timerState != TimeState.RUNNING)
            return 1;

        endTime = TimeUtils.millis();
        timerState = TimeState.TERMINATED;

        return 0;
    }

    public int pauseTimer() {
        if (timerState != TimeState.RUNNING)
            return 1;

        pauseTime = TimeUtils.millis();
        timerState = TimeState.STOPPED;

        return 0;
    }

    public int resumeTimer() {
        if (timerState != TimeState.STOPPED)
            return 1;

        pauseTime = TimeUtils.millis() - pauseTime;
        timerState = TimeState.RUNNING;
        startTime += pauseTime;
        return 0;
    }

    public long getFinalTimeInMillis() {
        return endTime - startTime;
    }

    public int getFinalTimeInSeconds() {
        return Math.round((endTime - startTime) / 1000f);
    }


    public int updateTime() {
        return (int) Math.round((TimeUtils.millis() - startTime) / 1000f);
    }
}
