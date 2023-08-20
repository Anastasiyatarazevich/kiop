package com.mygdx.game.ui.shulteTable;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;
import com.mygdx.game.utils.schulteHelper.TableItem;

public class TableItemView extends View {
    public String value;
    public char symbol;
    public BitmapFont font;
    private ImageView backgroundImage;
    private OnTableItemClicked onTableItemClicked;

    public boolean isClicked;
    float textX;
    float textY;

    public void setOnTableItemClicked(OnTableItemClicked onTableItemClicked) {
        this.onTableItemClicked = onTableItemClicked;
        setOnClickListener(onClickListener);
    }

    public TableItemView(BitmapFont font, String value, float x, float y, float width, float height) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.value = value;
        this.font = font;

        textX = 0;
        textY = 0;

        this.backgroundImage = new ImageView(x, y, width, height, "schulteTable/tileActive.png");

        isClicked = false;
    }

    public void setText(String text) {
        this.value = text;
        GlyphLayout gl = new GlyphLayout(font, text);
        float textWidth = gl.width;
        float textHeight = gl.height;

        textX = x + (width - textWidth) / 2;
        textY = y + height - (height - textHeight) / 2;
        //textY = y;
    }


    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean getIsClicked() {
        return isClicked;
    }

    public void setItemSelected() {
        backgroundImage.setImgSource("schulteTable/tileSelected.png");
    }

    public void setItemActive() {
        backgroundImage.setImgSource("schulteTable/tileActive.png");
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        backgroundImage.draw(myGdxGame);
        font.draw(myGdxGame.batch, value, textX, textY);
    }

    @Override
    public boolean isHit(float tx, float ty) {
        boolean isTouchHitComponent = x < tx && tx < x + width && y < ty && ty < y + height;
        if (isTouchHitComponent && onClickListener != null) onClickListener.onClicked();
        return isTouchHitComponent;
    }

    TableItemView getContext() {
        return this;
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClicked() {
            onTableItemClicked.onClicked(getContext());
        }
    };

    public interface OnTableItemClicked {
        void onClicked(TableItemView tableItemView);
    }
}
