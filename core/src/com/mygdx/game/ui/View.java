package com.mygdx.game.ui;

import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.utils.ApplicationSettings.SCR_WIDTH;

public class View implements Disposable {

    public float x;
    public float y;
    public float width;
    public float height;
    public boolean isVisible;
    public OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    View(float x, float y, float width, float height) {
        this(x, y);
        this.width = width;
        this.height = height;
        isVisible = true;
    }

    public View(float x, float y) {
        this.x = x;
        this.y = y;
        this.onClickListener = null;
        isVisible = true;
    }

    public void draw(MyGdxGame myGdxGame) {
    }

    public boolean isHit(float tx, float ty) {
        boolean isTouchHitComponent = x < tx && tx < x + width && y > ty && ty > y - height;
        if (isTouchHitComponent && onClickListener != null) onClickListener.onClicked();
        return isTouchHitComponent;
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
