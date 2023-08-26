package com.mygdx.game.ui.memo;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;

import java.util.ArrayList;

public class TableMemoCardsView extends View {

    int cardSize;
    int padding;

    ArrayList<TableMemoCardsItemsView> arrayListCardsView;

    Texture textureSelectedCardBackground;
    Texture textureActiveCardBackground;

    public TableMemoCardsView(float x, float y, int cardSize, int padding) {
        super(x, y);

        this.cardSize = cardSize;
        this.padding = padding;

        arrayListCardsView = new ArrayList<>();
        textureActiveCardBackground = new Texture("ui/availableCardBackground.png");
        textureSelectedCardBackground = new Texture("ui/selectedCardBackground.png");
    }

    public void setCards() {
        // TODO: continue here
    }

    public void setCardActive(int cardIdx) {
        if (cardIdx > arrayListCardsView.size()) {
            return;
        }
        arrayListCardsView.get(cardIdx).imageViewBackground.setImgTexture(textureActiveCardBackground);
        arrayListCardsView.get(cardIdx).isSelected = false;
    }

    public void setCardSelected(int cardIdx) {
        if (cardIdx > arrayListCardsView.size()) {
            return;
        }
        arrayListCardsView.get(cardIdx).imageViewBackground.setImgTexture(textureSelectedCardBackground);
        arrayListCardsView.get(cardIdx).isSelected = true;
    }
}
