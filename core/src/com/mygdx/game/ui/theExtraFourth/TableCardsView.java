package com.mygdx.game.ui.theExtraFourth;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.View;

import java.util.ArrayList;

import static com.mygdx.game.utils.ApplicationSettings.FOURTH_CARD_DIR;

public class TableCardsView extends View {

    TableCardsItemView[] tableCardsItemView;
    OnTableCardsClickListener onTableCardsClickListener;

    public int idxOfSelectedCard;

    public TableCardsView(float x, float y, int cardsPadding, int cardsSize, ArrayList<String> cardsSrc) {
        super(x, y);

        width = cardsPadding + cardsSize * 2;
        height = cardsPadding + cardsSize * 2;
        checkForAlignCenter();

        tableCardsItemView = new TableCardsItemView[4];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                tableCardsItemView[i * 2 + j] = new TableCardsItemView(
                        this.x + i * cardsPadding + i * cardsSize,
                        this.y + j * cardsPadding + j * cardsSize,
                        FOURTH_CARD_DIR + cardsSrc.get(i * 2 + j) + ".png"
                );
            }
        }

        idxOfSelectedCard = -1;
    }

    public void setOnTableCardsClickListener(OnTableCardsClickListener onTableCardsClickListener) {
        this.onTableCardsClickListener = onTableCardsClickListener;
    }

    public void setCards(ArrayList<String> cardsSrc) {
        idxOfSelectedCard = -1;
        for (int i = 0; i < 4; i++) {
            tableCardsItemView[i].setCardActive();
            tableCardsItemView[i].setCardImageSrc(FOURTH_CARD_DIR + cardsSrc.get(i) + ".png");
        }
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        for (TableCardsItemView cardView : tableCardsItemView) {
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
