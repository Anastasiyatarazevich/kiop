package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MyGdxGame;

public class SpriteShapes {
    public Sprite sprite;
    public String texture;
    public int x, y;


    public SpriteShapes(int x, int y, String imgSource, int rotate){
        this.x = x;
        this.y = y;
        this.texture = imgSource;
        sprite = new Sprite(new Texture(texture));
        sprite.setRotation(rotate);
    }

    public void setPos(int x, int y){
        sprite.setPosition(x, y);
    }

    public void setRotate(int degrees){
        sprite.setRotation(degrees);
    }

    public void draw(MyGdxGame myGdxGame){
        sprite.draw(myGdxGame.batch);
    }

    public float getWidth(){
        return sprite.getWidth();
    }

    public float getHeight(){
        return sprite.getHeight();
    }

    public String getImgSource(){
        return texture;
    }
}
