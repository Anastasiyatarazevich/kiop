package com.mygdx.game.ui.memo;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.Movable;
import com.mygdx.game.ui.View;

import java.util.ArrayList;
import java.util.Collections;

import static com.mygdx.game.utils.ApplicationSettings.*;

public class TableMemoCardsView extends View {

    private int countOfAnimated;
    private int countOfSelected;

    private final int cardSize;
    private final int padding;

    private final Texture textureSelectedCardBackground;
    private final Texture textureActiveCardBackground;

    OnTableItemClickListener onTableItemClickListener;
    OnCardsReleasedListener onCardsReleasedListener;

    String[] arrayImagesSrc;
    ArrayList<TableMemoCardsItemsView> arrayListCardsView;
    OnCardsHidedListener onCardsHided;

    public TableMemoCardsView(float x, float y, int cardSize, int padding,
                              OnTableItemClickListener onTableItemClickListener,
                              OnCardsReleasedListener onCardsReleasedListener) {
        super(x, y);

        this.cardSize = cardSize;
        this.padding = padding;

        this.onTableItemClickListener = onTableItemClickListener;
        this.onCardsReleasedListener = onCardsReleasedListener;

        arrayListCardsView = new ArrayList<>();
        textureActiveCardBackground = new Texture("ui/availableCardBackground.png");
        textureSelectedCardBackground = new Texture("ui/selectedCardBackground.png");

        countOfSelected = 0;
    }

    public void setCards(String[] arrayImagesSrc, OnCardsHidedListener onCardsHided) {

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
                tableMemoCardsItemsView.isClickable = false;
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

        countOfAnimated = 0;

        float endX = SCR_WIDTH / 2f - cardSize / 2f;
        float endY = SCR_HEIGHT / 2f - cardSize / 2f;

        for (TableMemoCardsItemsView card : arrayListCardsView) {
            card.setVector(endX, endY);
            card.imageViewCard.fadeAway(MEMO_CARD_FADING_PERIOD);
        }
    }

    public void releaseCards() {

        countOfAnimated = 0;

        float beginX = SCR_WIDTH / 2f - cardSize / 2f;
        float beginY = SCR_HEIGHT / 2f - cardSize / 2f;

        int cardIdx = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {

                if (i == 0 && j == 0) continue;
                if (i == 0 && j == 7) continue;

                TableMemoCardsItemsView card = arrayListCardsView.get(cardIdx);

                card.setImage(CARDS_DIR + arrayImagesSrc[cardIdx] + ".png");
                card.setPosition(beginX, beginY);
                card.setVector(
                        x + j * cardSize + j * padding,
                        y + i * cardSize + i * padding
                );

                card.setOnEndAnimationListener(onEndAnimation);
                card.imageViewCard.fadeIn(MEMO_CARD_FADING_PERIOD);

                card.isSelected = false;
                card.isClickable = true;

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
            countOfAnimated += 1;
            if (arrayListCardsView.size() == COUNT_OF_CARDS_TO_REMEMBER) {
                if (countOfAnimated == COUNT_OF_CARDS_TO_REMEMBER) {
                    onCardsHided.onHided();
                }
            } else {
                if (countOfAnimated == COUNT_OF_CARDS_TO_REMEMBER + COUNT_OF_ADDITION_CARDS) {
                    onCardsReleasedListener.onReleased();
                }
            }
        }
    };

    @Override
    public boolean isHit(float tx, float ty) {
        for (int i = 0; i < arrayListCardsView.size(); i++) {
            TableMemoCardsItemsView image = arrayListCardsView.get(i);

            if (countOfSelected >= COUNT_OF_CARDS_TO_REMEMBER && !image.isSelected) {
                continue;
            }

            if (image.isHit(tx, ty)) {
                if (image.isSelected) {
                    setCardSelected(i);
                    countOfSelected += 1;
                } else {
                    setCardActive(i);
                    countOfSelected -= 1;
                }
                onTableItemClickListener.onClicked(image.imageViewCard.getImgSource());
                return true;
            }
        }
        return false;
    }

    public interface OnTableItemClickListener {
        public void onClicked(String cardSrc);
    }

    public interface OnCardsHidedListener {
        public void onHided();
    }

    public interface OnCardsReleasedListener {
        public void onReleased();
    }

}
