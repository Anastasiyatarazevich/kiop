package com.mygdx.game.ui.memo;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.Movable;
import com.mygdx.game.ui.View;

import java.util.ArrayList;
import java.util.Collections;

import static com.mygdx.game.utils.ApplicationSettings.*;

public class TableMemoCardsView extends View {

    private int count_of_animated;

    private int cardSize;
    private int padding;

    private Texture textureSelectedCardBackground;
    private Texture textureActiveCardBackground;

    OnTableItemClickListener onTableItemClickListener;

    String[] arrayImagesSrc;
    ArrayList<TableMemoCardsItemsView> arrayListCardsView;
    OnCardsHided onCardsHided;

    public TableMemoCardsView(float x, float y, int cardSize, int padding,
                              OnTableItemClickListener onTableItemClickListener) {
        super(x, y);

        this.cardSize = cardSize;
        this.padding = padding;

        this.onTableItemClickListener = onTableItemClickListener;

        arrayListCardsView = new ArrayList<>();
        textureActiveCardBackground = new Texture("ui/availableCardBackground.png");
        textureSelectedCardBackground = new Texture("ui/selectedCardBackground.png");

    }

    public void setCards(String[] arrayImagesSrc, OnCardsHided onCardsHided) {

        this.onCardsHided = onCardsHided;
        this.arrayImagesSrc = arrayImagesSrc;

        width = cardSize * 5 + 4 * padding;
        x = SCR_WIDTH / 2f - width / 2f;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                TableMemoCardsItemsView tableMemoCardsItemsView = new TableMemoCardsItemsView(
                        x + j * cardSize + j * padding,
                        y + i * cardSize + i * padding,
                        cardSize,
                        textureActiveCardBackground,
                        CARDS_DIR + arrayImagesSrc[j * 5 + i] + ".png"
                );
                tableMemoCardsItemsView.setOnEndAnimationListener(onEndAnimation);
                arrayListCardsView.add(tableMemoCardsItemsView);
            }
        }
    }

    public void addUnknownCards() {
        for (int i = COUNT_OF_CARDS_TO_REMEMBER; i < COUNT_OF_CARDS_TO_REMEMBER + COUNT_OF_ADDITION_CARDS; i++) {
            TableMemoCardsItemsView tableMemoCardsItemsView = new TableMemoCardsItemsView(
                    0, 0,
                    cardSize,
                    textureActiveCardBackground,
                    CARDS_DIR + arrayImagesSrc[i] + ".png"
            );
            arrayListCardsView.add(tableMemoCardsItemsView);
        }
        Collections.shuffle(arrayListCardsView);

        width = 8 * cardSize + padding * 7;
        height = 4 * cardSize + padding * 3;

        x = SCR_WIDTH / 2f - width / 2f;
        y = SCR_HEIGHT / 2f - height / 2f;

        releaseCards();
    }

    public void hideCards() {

        count_of_animated = 0;

        float endX = SCR_WIDTH / 2f - cardSize / 2f;
        float endY = SCR_HEIGHT / 2f - cardSize / 2f;

        for (TableMemoCardsItemsView card : arrayListCardsView) {
            card.setVector(endX, endY);
        }
    }

    public void releaseCards() {

        float beginX = SCR_WIDTH / 2f - cardSize / 2f;
        float beginY = SCR_HEIGHT / 2f - cardSize / 2f;

        int cardIdx = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {

                if (i == 0 && j == 0) continue;
                if (i == 0 && j == 7) continue;

                arrayListCardsView.get(cardIdx).setImage(
                        CARDS_DIR + arrayImagesSrc[cardIdx] + ".png"
                );

                arrayListCardsView.get(cardIdx).setPosition(
                        beginX,
                        beginY
                );

                arrayListCardsView.get(cardIdx).setVector(
                        x + j * cardSize + j * padding,
                        y + i * cardSize + i * padding
                );

                arrayListCardsView.get(cardIdx).isSelected = false;

                cardIdx += 1;
            }
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
        for (int i = 0; i < arrayListCardsView.size(); i++) {
            TableMemoCardsItemsView image = arrayListCardsView.get(i);
            if (image.getVelocityX() != 0 || image.getVelocityY() != 0) {
                image.move(1);
            }
            if (image.isVisible) {
                image.draw(myGdxGame);
            }
        }
    }

    Movable.OnEndAnimation onEndAnimation = new Movable.OnEndAnimation() {
        @Override
        public void onEnd() {
            count_of_animated += 1;
            if (count_of_animated == COUNT_OF_CARDS_TO_REMEMBER) {
                onCardsHided.onHided();
            }
        }
    };

    @Override
    public boolean isHit(float tx, float ty) {
        for (int i = 0; i < arrayListCardsView.size(); i++) {
            TableMemoCardsItemsView image = arrayListCardsView.get(i);
            if (image.isHit(tx, ty)) {
                if (image.isSelected) setCardSelected(i);
                else setCardActive(i);
                onTableItemClickListener.onClicked(image.imageViewCard.getImgSource());
                return true;
            }
        }
        return false;
    }

    public interface OnTableItemClickListener {
        public void onClicked(String cardSrc);
    }

    public interface OnCardsHided {
        public void onHided();
    }

}
