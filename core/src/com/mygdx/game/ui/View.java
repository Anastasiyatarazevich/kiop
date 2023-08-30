package com.mygdx.game.ui;

import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.utils.ApplicationSettings.SCR_HEIGHT;
import static com.mygdx.game.utils.ApplicationSettings.SCR_WIDTH;

public class View implements Disposable {

    public float x;
    public float y;
    public float width;
    public float height;

    public OnClickListener onClickListener;

    public boolean isVisible;
    public boolean isClickable;

    private float velocityX;
    private float velocityY;
    private float accelerationX;
    private float accelerationY;

    View(float x, float y, float width, float height) {
        this(x, y);
        this.width = width;
        this.height = height;
        isVisible = true;
        isClickable = false;
    }

    public View(float x, float y) {
        this.x = x;
        this.y = y;
        this.onClickListener = null;
        isVisible = true;
        isClickable = false;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public float getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(float accelerationX) {
        this.accelerationX = accelerationX;
    }

    public float getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(float accelerationY) {
        this.accelerationY = accelerationY;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void draw(MyGdxGame myGdxGame) {
    }

    public boolean isHit(float tx, float ty) {
        if (!isClickable) return false;
        boolean isTouchHitComponent = x < tx && tx < x + width && y > ty && ty > y - height;
        if (isTouchHitComponent && onClickListener != null) onClickListener.onClicked();
        return isTouchHitComponent;
    }

    public void alignCenter() {
        x = SCR_WIDTH / 2f - width / 2f;
        y = SCR_HEIGHT / 2f - height / 2f;
    }

    public void alignCenterHorizontal() {
        x = SCR_WIDTH / 2f - width / 2f;
    }

    public void alignCenterVertical() {
        y = SCR_HEIGHT / 2f - height / 2f;
    }

    @Override
    public void dispose() {
    }

    public interface OnClickListener {
        void onClicked();
    }

    public void checkForAlignCenter() {
        if ((int) x == -1) {
            x = (float) SCR_WIDTH / 2 - width / 2;
        }
    }

}
