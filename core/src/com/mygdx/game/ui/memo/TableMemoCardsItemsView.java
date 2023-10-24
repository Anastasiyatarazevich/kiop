package com.mygdx.game.ui.memo;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageAlphaView;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.Movable;
import com.mygdx.game.ui.View;

import static com.mygdx.game.utils.ApplicationSettings.*;

public class TableMemoCardsItemsView extends View implements Movable {

    boolean isSelected;

    ImageView imageViewBackground;
    ImageAlphaView imageViewCard;

    private float endX;
    private float endY;

    private OnEndAnimation onEndAnimationListener;

    public TableMemoCardsItemsView(float x, float y, int cardSize, Texture backgroundTexture, String imageSrc) {
        super(x, y);
        this.width = cardSize;
        this.height = cardSize;
        imageViewBackground = new ImageView(x, y, cardSize, cardSize, backgroundTexture);
        setImage(imageSrc);
    }

    public void setOnEndAnimationListener(OnEndAnimation onEndAnimationListener) {
        this.onEndAnimationListener = onEndAnimationListener;
    }

    public void setImage(String imageSrc) {
        if (imageViewCard == null) {
            imageViewCard = new ImageAlphaView(x, y, imageSrc);
        } else {
            imageViewCard.setImgSource(imageSrc);
        }
        imageViewCard.loadSizeOfTexture();

        alignCardImage();
    }

    private void alignCardImage() {
        if (imageViewCard.width > imageViewCard.height) {
            float size_ratio = (float) MEMO_CARD_IMAGE_SIZE / imageViewCard.width;
            imageViewCard.width = MEMO_CARD_IMAGE_SIZE;
            imageViewCard.height *= size_ratio;
        } else {
            float size_ratio = (float) MEMO_CARD_IMAGE_SIZE / imageViewCard.height;
            imageViewCard.height = MEMO_CARD_IMAGE_SIZE;
            imageViewCard.width *= size_ratio;
        }

        imageViewCard.x = x + imageViewBackground.width / 2 - imageViewCard.width / 2;
        imageViewCard.y = y + imageViewBackground.height / 2 - imageViewCard.height / 2;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;

        imageViewBackground.x = x;
        imageViewBackground.y = y;

        imageViewCard.x = x + imageViewBackground.width / 2 - imageViewCard.width / 2;
        imageViewCard.y = y + imageViewBackground.height / 2 - imageViewCard.height / 2;
    }

    public void setVector(float endX, float endY) {
        this.endX = endX;
        this.endY = endY;

        float movementVector = (float) Math.sqrt((endX - x) * (endX - x) + (endY - y) * (endY - y));
        if (movementVector == 0) {
            setVelocityX(0);
            setVelocityY(0);
            return;
        }
        float speedRation = MEMO_CARD_MOVING_VELOCITY / movementVector;
        setVelocityX((endX - x) * speedRation);
        setVelocityY((endY - y) * speedRation);
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        imageViewBackground.draw(myGdxGame);
        imageViewCard.draw(myGdxGame);
    }

    @Override
    public boolean isHit(float tx, float ty) {
        if (!isClickable) return false;
        boolean isHit = imageViewBackground.isHit(tx, ty);
        if (isHit) {
            isSelected = !isSelected;
        }
        return isHit;
    }

    @Override
    public void move(int timeStep) {

        boolean isFinalStep = false;

        float deltaX = getVelocityX() * timeStep + getAccelerationX() * timeStep * timeStep / 2;
        float deltaY = getVelocityY() * timeStep + getAccelerationY() * timeStep * timeStep / 2;

        if (x <= (endX + Math.abs(deltaX)) && x >= (endX - Math.abs(deltaX))
                && y <= (endY + Math.abs(deltaY)) && y >= (endY - Math.abs(deltaY))) {
            setVelocityX(0);
            setVelocityY(0);
            deltaX = endX - x;
            deltaY = endY - y;
            isFinalStep = true;
        }

        x += deltaX;
        y += deltaY;

        imageViewCard.x += deltaX;
        imageViewCard.y += deltaY;

        imageViewBackground.x += deltaX;
        imageViewBackground.y += deltaY;

        if (isFinalStep && onEndAnimationListener != null) {
            onEndAnimationListener.onEnd();
        }
    }
}
