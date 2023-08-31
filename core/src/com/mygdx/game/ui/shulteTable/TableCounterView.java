package com.mygdx.game.ui.shulteTable;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.View;

public class TableCounterView extends View {

    BitmapFont fontText;
    BitmapFont fontTime;
    TextView textViewTitle;
    TextView textViewTitle2;
    TextView textViewTime;
    int padding = 32;
    int allCountOfTables;
    int leftCountOfTimes;

    public TableCounterView(float x, float y, String text, String text2, BitmapFont fontText, BitmapFont fontTime, int leftCountOfTimes) {
        super(x, y);
        this.fontTime = fontTime;
        this.fontText = fontText;
        this.leftCountOfTimes = leftCountOfTimes;
        this.allCountOfTables = leftCountOfTimes;
        this.textViewTitle = new TextView(fontText, text, x, y);
        this.textViewTime = new TextView(fontTime, String.valueOf(leftCountOfTimes), x + textViewTitle.width + padding, y);
        this.textViewTitle2 = new TextView(fontText, text2, x + textViewTitle.width + 2 * padding + textViewTime.width, y);
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        textViewTime.draw(myGdxGame);
        textViewTitle.draw(myGdxGame);
        textViewTitle2.draw(myGdxGame);
    }

    public void decreaseCountOfTimes() {
        leftCountOfTimes -= 1;
        textViewTime.setText(String.valueOf(leftCountOfTimes));
    }

    public void resetCounter() {
        System.out.println(leftCountOfTimes);
        leftCountOfTimes = allCountOfTables;
    }

}
