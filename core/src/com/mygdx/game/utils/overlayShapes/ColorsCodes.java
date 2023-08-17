package com.mygdx.game.utils.overlayShapes;

public class ColorsCodes {

    public static int getCode(double red, double green, double blue) {

        // we get range (0-1) from libgdx Texture
        // but it better to check in real rgb format

        green = (int) (green * 255);
        blue = (int) (blue * 255);
        red = (int) (red * 255);

        // took this data from my description.txt
        // just because I can't read txt files I hardcoded it

        // purple (eggplant)
        if (red == 163 && green == 73 && blue == 164) return 1;
        // lime (broccoli)
        if (red == 181 && green == 230 && blue == 29) return 2;
        // blue (cauliflower)
        if (red == 0 && green == 162 && blue == 232) return 3;
        // green (peas)
        if (red == 34 && green == 177 && blue == 76) return 4;
        // carrot (cabbage)
        if (red == 255 && green == 127 && blue == 39) return 5;
        // orange (pumpkin)
        if (red == 255 && green == 201 && blue == 14) return 6;
        // yellow (corn)
        if (red == 255 && green == 242 && blue == 0) return 7;
        // brown (beet)
        if (red == 136 && green == 0 && blue == 21) return 8;
        // red (pepper)
        if (red == 255 && green == 174 && blue == 201) return 9;
        //  swamp (carrot)
        if (red == 200 && green == 191 && blue == 231) return 10;

        return 0;
    }

    public static String getImageNameByColorCode(int code) {
        if (code == 1) return "eggplant";
        if (code == 2) return "broccoli";
        if (code == 3) return "cauliflower";
        if (code == 4) return "peas";
        if (code == 5) return "cabbage";
        if (code == 6) return "pumpkin";
        if (code == 7) return "corn";
        if (code == 8) return "beet";
        if (code == 9) return "pepper";
        if (code == 10) return "carrot";
        return "";
    }

}
