package com.mygdx.game.ui.shulteTable;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;

public class TableItemView extends View {
    public String value;
    private ImageView backgroundImage;
    public BitmapFont font;

    float textX;
    float textY;

    public TableItemView(BitmapFont font, String value, float x, float y, float width, float height) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.value = value;
        this.font = font;
        GlyphLayout gl = new GlyphLayout(font, this.value);
        float textWidth = gl.width;
        float textHeight = gl.height;

        textX = 0;
        textY = 0;


        this.backgroundImage = new ImageView(x, y, width, height, "schulteTable/tileActive.png");
    }

    public void setText(String text) {
        this.value = text;
        GlyphLayout gl = new GlyphLayout(font, text);
        float textWidth = gl.width;
        float textHeight = gl.height;

        textX = x + (width - textWidth) / 2;
        textY = y + height - (height - textHeight) / 2;
        //textY = y;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        backgroundImage.draw(myGdxGame);
        font.draw(myGdxGame.batch, value, textX, textY);
    }
}
