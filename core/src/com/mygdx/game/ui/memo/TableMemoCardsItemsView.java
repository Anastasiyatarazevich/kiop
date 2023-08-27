package com.mygdx.game.ui.memo;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.Movable;
import com.mygdx.game.ui.View;

import static com.mygdx.game.utils.ApplicationSettings.*;
import static com.mygdx.game.utils.ApplicationSettings.SCR_HEIGHT;

public class TableMemoCardsItemsView extends View implements Movable {

    boolean isSelected;

    ImageView imageViewBackground;
    ImageView imageViewCard;

    public TableMemoCardsItemsView(float x, float y, int cardSize, Texture backgroundTexture, String imageSrc) {
        super(x, y);
        this.width = cardSize;
        this.height = cardSize;
        imageViewBackground = new ImageView(x, y, cardSize, cardSize, backgroundTexture);
        setImage(imageSrc);
    }

    private void setImage(String imageSrc) {
        if (imageViewCard == null) {
            imageViewCard = new ImageView(x, y, imageSrc);
        } else {
            imageViewCard.setImgSource(imageSrc);
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

    public void setVector(float endX, float endY) {

        float movementVector = (float) Math.sqrt((endX - x) * (endX - x) + (endY - y) * (endY - y));
        float speedRation = CARD_MOVING_VELOCITY / movementVector;
        setVelocityX((endX - x) * speedRation);
        setVelocityY((endY - y) * speedRation);

        System.out.println(getVelocityX());
        System.out.println(getVelocityY());

    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        imageViewBackground.draw(myGdxGame);
        imageViewCard.draw(myGdxGame);
    }

    @Override
    public void move(int timeStep) {
        x += getVelocityX() * timeStep + getAccelerationX() * timeStep * timeStep / 2;
        imageViewCard.x += getVelocityX() * timeStep + getAccelerationX() * timeStep * timeStep / 2;
        imageViewBackground.x += getVelocityX() * timeStep + getAccelerationX() * timeStep * timeStep / 2;
        y += getVelocityY() * timeStep + getAccelerationY() * timeStep * timeStep / 2;
        imageViewCard.y += getVelocityY() * timeStep + getAccelerationY() * timeStep * timeStep / 2;
        imageViewBackground.y += getVelocityY() * timeStep + getAccelerationY() * timeStep * timeStep / 2;

        System.out.println(getVelocityX() * timeStep + getAccelerationX() * timeStep * timeStep / 2);

        if (x < SCR_WIDTH / 2f - width / 2f + 4 && x > SCR_WIDTH / 2f - width / 2f - 4
                && y < SCR_HEIGHT / 2f - height / 2f + 4 && y > SCR_HEIGHT / 2f - height / 2f - 4) {
            setVelocityX(0);
            setVelocityY(0);
        }
    }
}
