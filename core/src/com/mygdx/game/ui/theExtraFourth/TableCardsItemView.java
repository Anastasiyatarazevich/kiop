package com.mygdx.game.ui.theExtraFourth;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;

public class TableCardsItemView extends View {

    boolean isCardSelected;

    ImageView imageViewBackground;
    ImageView imageViewBorder;
    ImageView imageViewCard;

    Texture textureSelectedCardBackground;
    Texture textureActiveCardBackground;
    Texture textureCardItem;

    public TableCardsItemView(float x, float y, String cardSrc) {
        super(x, y);
        textureCardItem = new Texture(cardSrc);
        textureActiveCardBackground = new Texture("theExtraFourth/availableCardBackground.png");
        textureSelectedCardBackground = new Texture("theExtraFourth/selectedCardBackground.png");

        imageViewBackground = new ImageView(x, y, textureActiveCardBackground);
        imageViewBorder = new ImageView(x, y, "theExtraFourth/selectedCardBorder.png");
        imageViewCard = new ImageView(x, y, textureCardItem);

        imageViewBorder.x = imageViewBackground.x - (imageViewBorder.width - imageViewBackground.width) / 2;
        imageViewBorder.y = imageViewBackground.y - (imageViewBorder.height - imageViewBackground.height) / 2;

        imageViewCard.x = imageViewBackground.x + imageViewBackground.width / 2 - imageViewCard.width / 2;
        imageViewCard.y = imageViewBackground.y + imageViewBackground.height / 2 - imageViewCard.height / 2;

        imageViewBorder.isVisible = false;
        isCardSelected = false;

        width = imageViewBackground.width;
        height = imageViewBackground.height;

        checkForAlignCenter();
    }

    public void setCardSelected() {
        imageViewBackground.setImgTexture(textureSelectedCardBackground);
        imageViewBorder.isVisible = true;
    }

    public void setCardActive() {
        imageViewBackground.setImgTexture(textureActiveCardBackground);
        imageViewBorder.isVisible = false;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        if (imageViewBorder.isVisible) imageViewBorder.draw(myGdxGame);
        imageViewBackground.draw(myGdxGame);
        imageViewCard.draw(myGdxGame);
    }

    @Override
    public boolean isHit(float tx, float ty) {
        boolean hit = imageViewCard.isHit(tx, ty);
        if (hit) {
            isCardSelected = !isCardSelected;
        }

        return hit;
    }
}
