package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.utils.ApplicationSettings.SCR_WIDTH;

public class TextButton extends View {

    public String text;
    private ImageView backgroundImage;
    public BitmapFont font;

    public int imagePaddingVertical;
    public int imagePaddingHorizontal;

    public TextButton(BitmapFont font, String text, String backgroundSource, float x, float y) {
        this(font, text, backgroundSource, 20, 40, x, y);
        checkForAlignCenter();
    }

    public TextButton(BitmapFont font, String text, String backgroundSource, int paddingVertical, int paddingHorizontal,
                      float x, float y) {
        super(x, y);
        this.imagePaddingVertical = paddingVertical;
        this.imagePaddingHorizontal = paddingHorizontal;
        this.text = text;
        this.font = font;
        GlyphLayout gl = new GlyphLayout(font, text);
        float textWidth = gl.width;
        float textHeight = gl.height;

        width = textWidth + 2 * imagePaddingHorizontal;
        height = textHeight + 2 * imagePaddingVertical;

        checkForAlignCenter();

        this.backgroundImage = new ImageView(this.x, this.y, width, height, backgroundSource);
    }


    @Override
    public boolean isHit(float tx, float ty) {
        boolean isTouchHitComponent = x < tx && tx < x + width && y < ty && ty < y + height;
        if (isTouchHitComponent && onClickListener != null) onClickListener.onClicked();
        return isTouchHitComponent;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        backgroundImage.draw(myGdxGame);
        font.draw(myGdxGame.batch, text, x + imagePaddingHorizontal, height + y - imagePaddingVertical);
    }

}
