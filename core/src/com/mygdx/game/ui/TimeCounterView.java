package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.View;
import org.w3c.dom.Text;

import static com.mygdx.game.utils.ApplicationSettings.SCR_WIDTH;

public class TimeCounterView extends View {

    BitmapFont fontText;
    BitmapFont fontTime;
    boolean wasTimerActivated;
    int fullTime;
    long startTime;
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
        if (x == -1) alignCenter();
    }

    public TimeCounterView(float x, float y, String text, BitmapFont fontText, BitmapFont fontTime, int fullTime, int padding) {
        this(x, y, text, fontText, fontTime, fullTime);
        this.padding = padding;
        if (x == -1) alignCenter();
    }

    private void alignCenter() {
        width = textViewTime.width + textViewTitle.width;
        x = SCR_WIDTH / 2f - width / 2f;
        textViewTitle.x = x;
        textViewTime.x = x + textViewTitle.width + padding;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        textViewTime.draw(myGdxGame);
        textViewTitle.draw(myGdxGame);
    }

    public void startTimer() {
        startTime = TimeUtils.millis();
    }

    public int updateTimer() {
        int time;
        if (fullTime == -1) {
            time = (int) ((TimeUtils.millis() - startTime) / 1000);
        } else {
            time = (int) (fullTime - (TimeUtils.millis() - startTime) / 1000);
            if (time < 0) return 1;
        }
        textViewTime.setText(String.valueOf(time));
        return 0;
    }
}