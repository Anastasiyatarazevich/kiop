package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.ApplicationSettings;

public class TextView extends View {

    public String text;
    public BitmapFont font;

    // send -1 as x to make text align center
    public TextView(BitmapFont font, String text, float x, float y) {
        super(x, y);
        this.font = font;
        this.text = text;

        GlyphLayout gl = new GlyphLayout(font, text);
        width = gl.width;
        height = gl.height;

        if ((int) x == -1) this.x = ApplicationSettings.SCR_WIDTH / 2 - width / 2;
    }

    public void setText(String text) {
        GlyphLayout gl = new GlyphLayout(font, text);
        width = gl.width;
        height = gl.height;
        this.text = text;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        font.draw(myGdxGame.batch, text, x, y);
    }

}
