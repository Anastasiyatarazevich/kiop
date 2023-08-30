package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.ApplicationSettings;

public class BlackoutView extends View {

    Texture blackoutTexture;

    public BlackoutView() {
        super(0, 0, ApplicationSettings.SCR_WIDTH, ApplicationSettings.SCR_HEIGHT);
        Pixmap pixmap = new Pixmap((int) width, (int) height, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0.5f);
        pixmap.fill();
        blackoutTexture = new Texture(pixmap);
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        myGdxGame.batch.draw(blackoutTexture, x, y, width, height);
    }

}
