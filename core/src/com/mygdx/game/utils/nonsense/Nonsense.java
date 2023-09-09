package com.mygdx.game.utils.nonsense;

public class Nonsense {

    public boolean isFound;
    final private int colorCode;
    final private int pointerRadius;
    final private int x, y;

    public Nonsense(int colorCode, int pointerRadius, int x, int y) {
        this.colorCode = colorCode;
        this.pointerRadius = pointerRadius;
        this.x = x;
        this.y = y;
        isFound = false;
    }

    public int getColorCode() {
        return colorCode;
    }

    public int getPointerRadius() {
        return pointerRadius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
