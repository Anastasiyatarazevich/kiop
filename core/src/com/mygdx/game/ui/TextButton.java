package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.MyGdxGame;

public class TextButton extends View {

    public String text;
    private ImageView backgroundImage;
    public BitmapFont font;

    // adjusting vars

    public int imagePaddingVertical;
    public int imagePaddingHorizontal;

    public TextButton(BitmapFont font, String text, String backgroundSource, float x, float y) {
        this(font, text, backgroundSource, 20, 40, x, y);
    }

    public TextButton(BitmapFont font, String text, String backgroundSource, int paddingVertical, int paddingHorizontal,
                      float x, float y) {
        super(x + paddingHorizontal, y + paddingVertical);
        this.imagePaddingVertical = paddingVertical;
        this.imagePaddingHorizontal = paddingHorizontal;
        this.text = text;
        this.font = font;
        GlyphLayout gl = new GlyphLayout(font, text);
        width = gl.width;
        height = gl.height;

        this.backgroundImage = new ImageView(
                x, y,
                width + 2 * imagePaddingHorizontal,
                height + 2 * imagePaddingVertical,
                backgroundSource);
    }

    public void setText(String text) {
        this.text = text;
        GlyphLayout gl = new GlyphLayout(font, text);
        width = gl.width;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        backgroundImage.draw(myGdxGame);
        font.draw(myGdxGame.batch, text, x, height + y);
    }

}
