package com.mygdx.game.ui.memo;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;

import static com.mygdx.game.utils.ApplicationSettings.CARD_IMAGE_SIZE;

public class TableMemoCardsItemsView extends View {

    boolean isSelected;

    ImageView imageViewBackground;
    ImageView imageViewCard;

    public TableMemoCardsItemsView(float x, float y, int cardSize, Texture backgroundTexture, String imageSrc) {
        super(x, y);
        this.width = cardSize;
        this.height = cardSize;
        imageViewBackground = new ImageView(x, y, backgroundTexture);
        setImage(imageSrc);
    }

    private void setImage(String imageSrc) {
        if (imageViewCard == null) {
            imageViewCard = new ImageView(x, y, imageSrc);
        }
        if (imageViewCard.width > imageViewCard.height) {
            float size_ratio = (float) CARD_IMAGE_SIZE / imageViewCard.width;
            imageViewCard.width = CARD_IMAGE_SIZE;
            imageViewCard.height *= size_ratio;
        } else {
            float size_ratio = (float) CARD_IMAGE_SIZE / imageViewCard.height;
            imageViewCard.height = CARD_IMAGE_SIZE;
            imageViewCard.width *= size_ratio;
        }

        imageViewCard.x = x + imageViewBackground.width / 2 - imageViewCard.width / 2;
        imageViewCard.y = y + imageViewBackground.height / 2 - imageViewCard.height / 2;
    }
}
