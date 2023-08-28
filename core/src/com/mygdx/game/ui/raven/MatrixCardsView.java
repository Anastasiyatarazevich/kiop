package com.mygdx.game.ui.raven;


import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.View;

import java.util.ArrayList;

public class MatrixCardsView extends View {
    MatrixItemView[] tableCardsItemView;
    MatrixCardsView.OnTableCardsClickListener onTableCardsClickListener;

    public int idxOfSelectedCard;
    public MatrixCardsView(float x, float y) {
        super(x, y);
    }

    public MatrixCardsView(float x, float y, int cardsPadding, int cardsSize, ArrayList<String> cardsSrc) {
        super(x, y);

        width = cardsPadding + cardsSize * 2;
        height = cardsPadding + cardsSize * 2;
        checkForAlignCenter();

        tableCardsItemView = new MatrixItemView[6];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                tableCardsItemView[i * 2 + j] = new MatrixItemView(
                        this.x + i * cardsPadding + i * cardsSize,
                        this.y + j * cardsPadding + j * cardsSize,
                         cardsSrc.get(i * 2 + j)
                );
                System.out.println(cardsSrc.get(i * 2 + j));
            }
        }
        idxOfSelectedCard = -1;
    }

    public void setOnTableCardsClickListener(MatrixCardsView.OnTableCardsClickListener onTableCardsClickListener) {
        this.onTableCardsClickListener = onTableCardsClickListener;
    }

    public void setCards(ArrayList<String> cardsSrc) {
        idxOfSelectedCard = -1;
        for (int i = 0; i < 6; i++) {
            tableCardsItemView[i].setCardActive();
//            tableCardsItemView[i].setCardImageSrc(RAVEN_CARD_IMAGES_DIR + cardsSrc.get(i) + ".png");
            tableCardsItemView[i].setCardImageSrc(cardsSrc.get(i));
        }
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        for (MatrixItemView cardView : tableCardsItemView) {
            cardView.draw(myGdxGame);
        }
    }

    @Override
    public boolean isHit(float tx, float ty) {
        for (int i = 0; i < tableCardsItemView.length; i++) {
            if (tableCardsItemView[i].isHit(tx, ty) && onTableCardsClickListener != null) {
                onTableCardsClickListener.onClick(i);
                if (idxOfSelectedCard != i) {
                    if (idxOfSelectedCard != -1) {
                        tableCardsItemView[idxOfSelectedCard].setCardActive();
                    }
                    idxOfSelectedCard = i;
                    tableCardsItemView[idxOfSelectedCard].setCardSelected();
                }
                return true;
            }
        }
        return false;
    }

    public interface OnTableCardsClickListener {
        public void onClick(int cardIdx);
    }
}
