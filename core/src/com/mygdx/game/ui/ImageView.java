package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.ScreenRavenMatrices;

import java.util.Objects;

import static com.mygdx.game.utils.ApplicationSettings.SCR_WIDTH;

public class ImageView extends View {

    String imgSource;
    public Texture imgTexture;

    TextureRegion textureRegion;
    public Sprite sprite;

    public void setImgTexture(Texture imgTexture) {
        this.imgTexture = imgTexture;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public void setTextureRegion(String textureRegion) {
        this.textureRegion = ScreenRavenMatrices.textureAtlas.findRegion(textureRegion);
    }

    public ImageView(float x, float y, String imgSource) {
        super(x, y);
        this.imgSource = imgSource;
        imgTexture = new Texture(imgSource);

        sprite = new Sprite(imgTexture, (int) x, (int) y);

        width = imgTexture.getWidth();
        height = imgTexture.getHeight();
    }

    public ImageView(float x, float y, Texture imgTexture) {
        super(x, y);
        this.imgTexture = imgTexture;
        width = imgTexture.getWidth();
        height = imgTexture.getHeight();
    }

    public ImageView(float x, float y, float width, float height, String imgSource) {
        super(x, y, width, height);
        this.imgSource = imgSource;
        imgTexture = new Texture(imgSource);

        sprite = new Sprite(imgTexture, (int) x, (int) y, (int) width, (int) height);
        sprite.setRotation(0);
    }

    public ImageView(float x, float y, float width, float height, TextureRegion textureRegion) {
        super(x, y, width, height);
        this.textureRegion = textureRegion;

    }

    public ImageView(float x, float y, TextureRegion textureRegion) {
        super(x, y);
        this.textureRegion = textureRegion;

        width = textureRegion.getRegionWidth();
        height = textureRegion.getRegionHeight();
    }


    // generate image that align center
    public ImageView(float y, float width, float height, String imgSource) {
        super(0, y, width, height);
        this.imgSource = imgSource;
        imgTexture = new Texture(imgSource);

        sprite = new Sprite(imgTexture, 0, (int) y, (int) width, (int) height);
        sprite.setRotation(0);

        x = (float) (SCR_WIDTH - imgTexture.getWidth()) / 2;
    }

    public ImageView(float x, float y, float width, float height, Texture imgTexture) {
        super(x, y, width, height);
        this.imgTexture = imgTexture;
    }


    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        if (imgSource.equals(this.imgSource)) return;
        this.imgSource = imgSource;
        imgTexture = new Texture(imgSource);

        // was it necessary??????????????????
        // width = imgTexture.getWidth();
        // height = imgTexture.getHeight();

        sprite = new Sprite(imgTexture);
    }

    public void loadSizeOfTexture() {
        width = imgTexture.getWidth();
        height = imgTexture.getHeight();
    }

    public void setImgSource2(String imgSource) {
        if (imgSource.equals(this.imgSource)) return;
        this.imgSource = imgSource;
        imgTexture = new Texture(imgSource);

        sprite = new Sprite(imgTexture);
//        sprite.setRotation(0);
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        if (imgTexture != null) {
            myGdxGame.batch.draw(imgTexture, x, y, width, height);
        } else if (textureRegion != null) {
            myGdxGame.batch.draw(textureRegion, x, y, width, height);
        }
    }

    @Override
    public boolean isHit(float tx, float ty) {
        boolean isTouchHitComponent = x < tx && tx < x + width && y < ty && ty < y + height;
        if (isTouchHitComponent && onClickListener != null) onClickListener.onClicked();
        return isTouchHitComponent;
    }
}
