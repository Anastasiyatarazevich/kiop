package com.mygdx.game.ui.shulteTable;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.View;
import org.w3c.dom.Text;

public class TimeCounterView extends View {

    BitmapFont fontText;
    BitmapFont fontTime;
    int fullTime;
    long startTime;
    TextView textViewTitle;
    TextView textViewTime;
    int padding = 20;

    public TimeCounterView(float x, float y, String text, BitmapFont fontText, BitmapFont fontTime, int fullTime) {
        super(x, y);
        this.fontTime = fontTime;
        this.fontText = fontText;
        this.fullTime = fullTime;
        this.textViewTitle = new TextView(fontText, text, x, y);
        this.textViewTime = new TextView(fontTime, String.valueOf(fullTime / 1000), x + textViewTitle.width + padding, y);
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        textViewTime.draw(myGdxGame);
        textViewTitle.draw(myGdxGame);
    }

    public void startTimer() {
        startTime = TimeUtils.millis();
    }

    public void updateTimer() {
        int time = (int) ((fullTime - (TimeUtils.millis() - startTime)) / 1000);
        textViewTime.setText(String.valueOf(time));
    }
}
