package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

import java.util.Objects;

public class ImageView extends View {

    String imgSource;
    Texture imgTexture;

    public void setImgTexture(Texture imgTexture) {
        this.imgTexture = imgTexture;
    }

    public void setImgSource(String imgSource) {
        if (imgSource.equals(this.imgSource)) return;
        this.imgSource = imgSource;
        imgTexture = new Texture(imgSource);
    }

    public ImageView(float x, float y, float width, float height, String imgSource) {
        super(x, y, width, height);
        this.imgSource = imgSource;
        imgTexture = new Texture(imgSource);
    }

    public ImageView(float x, float y, float width, float height, Texture imgTexture) {
        super(x, y, width, height);
        this.imgTexture = imgTexture;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        myGdxGame.batch.draw(imgTexture, x, y, width, height);
    }

    @Override
    public boolean isHit(float tx, float ty) {
        boolean isTouchHitComponent = x < tx && tx < x + width && y < ty && ty < y + height;
        if (isTouchHitComponent && onClickListener != null) onClickListener.onClicked();
        return isTouchHitComponent;
    }
}
