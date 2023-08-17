package com.mygdx.game.utils.overlayShapes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Shape {

    private int x;
    private  int y;
    private final int rotation;
    private final String name;
    private boolean isFound;

    private Texture texture;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private Image image;

    public Shape(int x, int y, int rotation, String name) {
        isFound = false;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public Texture getTexture() {
        return texture;
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

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void wasFound() {
        this.isFound = true;
    }

    public boolean getIsFound() {
        return isFound;
    }
}
