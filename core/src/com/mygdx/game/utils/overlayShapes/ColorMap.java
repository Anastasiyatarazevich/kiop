package com.mygdx.game.utils.overlayShapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import javax.swing.plaf.ColorUIResource;

import static com.mygdx.game.utils.ApplicationSettings.COLOR_MAP_DIR;

public class ColorMap {

    String textureSrc;
    Texture texture;

    public ColorMap(int selectedSample) {
        this.textureSrc = COLOR_MAP_DIR + selectedSample + ".png";
        texture = new Texture(textureSrc);
    }

    public int getColorCode(int x, int y) {

        if (x > texture.getWidth() || y > texture.getHeight() || x < 0 || y < 0) {
            return -1;
        }

        // just copy-past from stack overflow
        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }

        Pixmap pixmap = texture.getTextureData().consumePixmap();
        Color color = new Color(pixmap.getPixel(x, y));
        return ColorsCodes.getCode(color.r, color.g, color.b);

    }

}
