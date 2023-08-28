package com.mygdx.game.ui.raven;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.ScreenRavenMatrices;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;

public class MatrixItemView extends View {

    boolean isCardSelected;

    ImageView imageViewBackground;
    ImageView imageViewCard;

    TextureRegion textureCardItem, textureActiveCardBackground, textureSelectedCardBackground;

    TextureAtlas textureAtlas;
    public MatrixItemView(float x, float y, String cardSrc) {
        super(x, y);
        textureAtlas = ScreenRavenMatrices.textureAtlas;

        textureCardItem = textureAtlas.findRegion(cardSrc);

        textureActiveCardBackground = textureAtlas.findRegion("white_back_raven");
        textureSelectedCardBackground =textureAtlas.findRegion("selected");

        imageViewBackground = new ImageView(x, y, textureActiveCardBackground);
        imageViewCard = new ImageView(x, y, textureCardItem);

        imageViewCard.x = imageViewBackground.x + imageViewBackground.width / 2 - imageViewCard.width / 2;
        imageViewCard.y = imageViewBackground.y + imageViewBackground.height / 2 - imageViewCard.height / 2;

        isCardSelected = false;

        width = imageViewBackground.width;
        height = imageViewBackground.height;

        checkForAlignCenter();
    }


    public void setCardSelected() {
        imageViewBackground.setTextureRegion(textureSelectedCardBackground);
    }

    public void setCardActive() {
        imageViewBackground.setTextureRegion(textureActiveCardBackground);

    }

    public void setCardImageSrc(String imageSrc) {
        imageViewCard.setTextureRegion(imageSrc);
        imageViewCard.x = imageViewBackground.x + imageViewBackground.width / 2 - imageViewCard.width / 2;
        imageViewCard.y = imageViewBackground.y + imageViewBackground.height / 2 - imageViewCard.height / 2;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        imageViewBackground.draw(myGdxGame);
        imageViewCard.draw(myGdxGame);
    }

    @Override
    public boolean isHit(float tx, float ty) {
        boolean hit = imageViewBackground.isHit(tx, ty);
        if (hit) {
            isCardSelected = !isCardSelected;
        }
        return hit;
    }
}

