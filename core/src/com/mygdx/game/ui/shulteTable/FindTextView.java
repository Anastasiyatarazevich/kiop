package com.mygdx.game.ui.shulteTable;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.View;

public class FindTextView  extends View {

    public String text;
    public BitmapFont font;

    TextView textFind;
    TextView targetLetter;

    public FindTextView(float x, float y, String text, BitmapFont font, String targetLetter) {
        super(x, y);
        this.font = font;
        this.textFind = new TextView(font, text, x, y);
        this.targetLetter = new TextView(font, targetLetter, x + textFind.width + 64, y);
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        textFind.draw(myGdxGame);
        targetLetter.draw(myGdxGame);
    }

    public void setText(String text) {
        GlyphLayout gl = new GlyphLayout(font, text);
        width = gl.width;
        height = gl.height;
        this.text = text;
    }

    @Override
    public boolean isHit(float tx, float ty) {
        boolean isTouchHitComponent = x < tx && tx < x + width && y < ty && ty < y + height;
        if (isTouchHitComponent && onClickListener != null) onClickListener.onClicked();
        return isTouchHitComponent;
    }

    public void updateFindText(String text) {
        targetLetter.setText(text);
    }

}
