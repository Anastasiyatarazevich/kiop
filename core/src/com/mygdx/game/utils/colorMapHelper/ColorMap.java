package com.mygdx.game.utils.colorMapHelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class ColorMap {

    String textureSrc;
    Texture texture;

    public ColorMap(String colorMapDir, int selectedSample) {
        this.textureSrc = colorMapDir + selectedSample + ".png";
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
