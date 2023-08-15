package com.mygdx.game.utils.overlayShapes;

public class Shape {

    private final int x;
    private final int y;
    private final int rotation;
    private final String name;

    public Shape(int x, int y, int rotation, String name) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRotation() {
        return rotation;
    }

    public String getName() {
        return name;
    }

}
