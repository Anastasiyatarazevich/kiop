package com.mygdx.game.utils.overlayShapes;

public class ColorsCodes {

    public static int getCode(double red, double green, double blue) {

        // we get range (0-1) from libjdx Texture
        // but it better to check in real rgb format

        green = (int) (green * 255);
        blue = (int) (blue * 255);
        red = (int) (red * 255);

        // took this data from my description.txt
        // just because I can't read txt files I hardcoded it

        // purple
        if (red == 163 && green == 73 && blue == 164) return 1;
        // lime
        if (red == 181 && green == 230 && blue == 29) return 2;
        // blue
        if (red == 0 && green == 162 && blue == 232) return 3;
        // green
        if (red == 34 && green == 177 && blue == 76) return 4;
        // carrot
        if (red == 255 && green == 127 && blue == 39) return 5;
        // orange
        if (red == 255 && green == 201 && blue == 14) return 6;
        // yellow
        if (red == 255 && green == 242 && blue == 0) return 7;
        // brown
        if (red == 136 && green == 0 && blue == 21) return 8;

        return 0;
    }

}
