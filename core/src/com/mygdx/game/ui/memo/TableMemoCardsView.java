package com.mygdx.game.ui.memo;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.Movable;
import com.mygdx.game.ui.View;
import com.mygdx.game.ui.theExtraFourth.TableCardsItemView;

import java.util.ArrayList;

import static com.mygdx.game.utils.ApplicationSettings.*;

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

    public void setCards(String[] arrayListImagesSrc) {

        width = cardSize * 5 + 4 * padding;
        x = SCR_WIDTH / 2f - width / 2f;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                arrayListCardsView.add(new TableMemoCardsItemsView(
                        x + j * cardSize + j * padding,
                        y + i * cardSize + i * padding,
                        cardSize,
                        textureActiveCardBackground,
                        CARDS_DIR + arrayListImagesSrc[j * 5 + i] + ".png"
                ));
            }
        }
    }

    public void hideCards() {

        float endX = SCR_WIDTH / 2f - cardSize / 2f;
        float endY = SCR_HEIGHT / 2f - cardSize / 2f;

        for (TableMemoCardsItemsView card : arrayListCardsView) {
            card.setVector(endX, endY);
        }
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

    @Override
    public void draw(MyGdxGame myGdxGame) {
        for (TableMemoCardsItemsView image : arrayListCardsView) {
            if (image.getVelocityX() != 0 || image.getVelocityY() != 0) image.move(1);
            if (image.isVisible) image.draw(myGdxGame);
        }
    }

}
