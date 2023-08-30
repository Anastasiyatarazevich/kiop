package com.mygdx.game.ui.alerts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.*;

public class AlertConfirmBack extends View {

    BlackoutView blackoutView;
    TextButton textButtonContinue;
    ImageView imageViewBackground;
    TextView textViewReturn;
    TextView textViewTitle;

    OnButtonResumeClickListener onButtonResumeClickListener;
    OnButtonReturnHomeClickListener onButtonReturnHomeClickListener;

    public void setOnButtonResumeClickListener(OnButtonResumeClickListener onButtonResumeClickListener) {
        this.onButtonResumeClickListener = onButtonResumeClickListener;
    }

    public void setOnButtonReturnHomeClickListener(OnButtonReturnHomeClickListener onResumeButtonClicked) {
        this.onButtonReturnHomeClickListener = onResumeButtonClicked;
    }

    public AlertConfirmBack(BitmapFont titleFont, BitmapFont buttonsFont) {
        super(-1, -1);
        blackoutView = new BlackoutView();
        imageViewBackground = new ImageView(-1, -1, "alerts/alertConfirmBackground.png");
        textViewTitle = new TextView(titleFont, "Ваш прогресс будет потерян", -1, 685);
        textViewReturn = new TextView(buttonsFont, "В меню", 1080, 470);
        textButtonContinue = new TextButton(
                buttonsFont, "Отмена",
                "schulteTable/buttonBackground.png",
                1220, 450
        );

        // blackoutView.isVisible = false;
        imageViewBackground.alignCenter();

        textButtonContinue.setOnClickListener(onResumeButtonClicked);
        textViewReturn.setOnClickListener(onReturnHomeButtonClicked);

        isVisible = false;
    }

    View.OnClickListener onResumeButtonClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            isVisible = false;
            if (onButtonResumeClickListener != null) {
                onButtonResumeClickListener.onClicked();
            }
        }
    };

    View.OnClickListener onReturnHomeButtonClicked = new OnClickListener() {
        @Override
        public void onClicked() {
            isVisible = false;
            if (onButtonReturnHomeClickListener != null) {
                onButtonReturnHomeClickListener.onClicked();
            }
        }
    };

    @Override
    public boolean isHit(float tx, float ty) {
        textButtonContinue.isHit(tx, ty);
        textViewReturn.isHit(tx, ty);
        return false;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        blackoutView.draw(myGdxGame);
        imageViewBackground.draw(myGdxGame);
        textViewTitle.draw(myGdxGame);
        textViewReturn.draw(myGdxGame);
        textButtonContinue.draw(myGdxGame);
    }

    public interface OnButtonResumeClickListener {
        void onClicked();
    }

    public interface OnButtonReturnHomeClickListener {
        void onClicked();
    }
}
