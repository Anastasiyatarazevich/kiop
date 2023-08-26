package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.ApplicationSettings;

public class BackgroundPixmapView extends View {

    Texture backgroundTexture;

    public BackgroundPixmapView(Color pixmapColor) {
        super(0, 0, ApplicationSettings.SCR_WIDTH, ApplicationSettings.SCR_HEIGHT);
        Pixmap pixmap = new Pixmap((int) width, (int) height, Pixmap.Format.RGBA8888);
        pixmap.setColor(pixmapColor);
        pixmap.fill();
        backgroundTexture = new Texture(pixmap);
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        myGdxGame.batch.draw(backgroundTexture, x, y, width, height);
    }

}
