package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.time.Timer;

import static com.mygdx.game.utils.ApplicationSettings.SCR_WIDTH;

public class TimeCounterView extends View {

    BitmapFont fontText;
    BitmapFont fontTime;
    boolean wasTimerActivated;
    int fullTime;
    public Timer timer;
    TextView textViewTitle;
    TextView textViewTime;
    private int padding = 64;

    public TimeCounterView(float x, float y, String text, BitmapFont fontText, BitmapFont fontTime) {
        super(x, y);
        this.fontTime = fontTime;
        this.fontText = fontText;
        this.fullTime = -1;
        this.wasTimerActivated = false;
        this.textViewTitle = new TextView(fontText, text, x, y);
        this.textViewTime = new TextView(fontTime, String.valueOf(fullTime / 1000), x + textViewTitle.width + padding, y);

        timer = new Timer();

        if (x == -1) alignCenter();
    }

    public TimeCounterView(float x, float y, String text, BitmapFont fontText, BitmapFont fontTime, int fullTime) {
        super(x, y);
        this.fontTime = fontTime;
        this.fontText = fontText;
        this.fullTime = fullTime;
        this.wasTimerActivated = false;
        this.textViewTitle = new TextView(fontText, text, x, y);
        this.textViewTime = new TextView(fontTime, String.valueOf(fullTime / 1000), x + textViewTitle.width + padding, y);

        timer = new Timer();

        if (x == -1) alignCenter();
    }

    public TimeCounterView(float x, float y, String text, BitmapFont fontText, BitmapFont fontTime, int fullTime, int padding) {
        this(x, y, text, fontText, fontTime, fullTime);
        this.padding = padding;

        timer = new Timer();

        if (x == -1) alignCenter();
    }

    @Override
    public void alignCenter() {
        width = textViewTime.width + textViewTitle.width;
        x = SCR_WIDTH / 2f - width / 2f;
        textViewTitle.x = x;
        textViewTime.x = x + textViewTitle.width + padding;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        textViewTime.setText(getTime());
        textViewTime.draw(myGdxGame);
        textViewTitle.draw(myGdxGame);
    }

    private String getTime() {
        if (fullTime == -1)
            return String.valueOf(timer.getFinalTimeInSeconds());
        else
            return String.valueOf(fullTime - timer.getFinalTimeInSeconds());
    }

    public void startTimer() {
        timer.startTimer();
    }

    public boolean isTimerExpired() {
        return fullTime - timer.getFinalTimeInSeconds() <= 0;
    }

}
