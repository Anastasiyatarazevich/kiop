package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;

public class ImageAlphaView extends View {

    boolean isFadingAway;
    boolean isFadingIn;

    long fadingStartTime;
    long fadingPeriod;

    float alpha;
    String imgSource;
    public Texture imgTexture;
    public Sprite sprite;

    public ImageAlphaView(float x, float y, String imgSource) {
        super(x, y);
        this.imgSource = imgSource;
        alpha = 1f;
        imgTexture = new Texture(imgSource);
        sprite = new Sprite(imgTexture);
    }

    public void setImgSource(String imgSource) {
        if (imgSource.equals(this.imgSource)) return;
        this.imgSource = imgSource;
        imgTexture = new Texture(imgSource);
        sprite = new Sprite(imgTexture);
    }

    public void loadSizeOfTexture() {
        width = imgTexture.getWidth();
        height = imgTexture.getHeight();
    }

    public String getImgSource() {
        return imgSource;
    }

    public void fadeAway(long fadingPeriod) {
        isFadingAway = true;
        isFadingIn = false;
        alpha = 1f;
        this.fadingPeriod = fadingPeriod;
        this.fadingStartTime = TimeUtils.millis();
    }

    public void fadeIn(long fadingPeriod) {
        isFadingAway = false;
        isFadingIn = true;
        alpha = 0f;
        this.fadingPeriod = fadingPeriod;
        this.fadingStartTime = TimeUtils.millis();
    }


    @Override
    public void draw(MyGdxGame myGdxGame) {

        long timeWent = TimeUtils.millis() - fadingStartTime;
        if (isFadingAway) {
            if (timeWent > fadingPeriod) {
                alpha = 0f;
                isFadingAway = false;
            } else {
                alpha = 1 - (float) timeWent / fadingPeriod;
            }
        } else if (isFadingIn) {
            if (timeWent > fadingPeriod) {
                alpha = 1f;
                isFadingIn = false;
            } else {
                alpha = (float) timeWent / fadingPeriod;
            }
        }

        sprite.setPosition(x, y);
        sprite.setSize(width, height);
        sprite.setAlpha(alpha);
        sprite.draw(myGdxGame.batch);
    }
}
