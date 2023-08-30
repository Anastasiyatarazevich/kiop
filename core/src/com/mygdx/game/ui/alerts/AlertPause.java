package com.mygdx.game.ui.alerts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.*;
import org.w3c.dom.Text;

public class AlertPause extends View {

    BlackoutView blackoutView;
    TextButton textButtonContinue;
    ImageView imageViewBackground;
    ImageView imageViewRaccoon;
    TextView textViewReturn;
    TextView textViewTitle;

    AlertConfirmBack alertConfirmBack;

    int stage = 0;

    OnButtonResumeClickListener onButtonResumeClickListener;
    OnButtonReturnHomeClickListener onButtonReturnHomeClickListener;

    public void setOnButtonResumeClickListener(OnButtonResumeClickListener onButtonResumeClickListener) {
        this.onButtonResumeClickListener = onButtonResumeClickListener;
    }

    public void setOnButtonReturnHomeClickListener(OnButtonReturnHomeClickListener onButtonReturnHomeClickListener) {
        this.onButtonReturnHomeClickListener = onButtonReturnHomeClickListener;
    }

    public AlertPause(BitmapFont titleFont, BitmapFont buttonsFont) {
        super(-1, -1);

        alertConfirmBack = new AlertConfirmBack(titleFont, buttonsFont);

        blackoutView = new BlackoutView();
        imageViewBackground = new ImageView(-1, -1, "alerts/alertPauseBackground.png");
        imageViewRaccoon = new ImageView(-1, 460, "images/slippingRaccoon.png");
        textViewTitle = new TextView(titleFont, "Игра приостановлена", -1, 790);
        textViewReturn = new TextView(buttonsFont, "В меню", 980, 360);
        textButtonContinue = new TextButton(
                buttonsFont, "Продолжить",
                "schulteTable/buttonBackground.png",
                1158, 340
        );

        // blackoutView.isVisible = false;
        imageViewBackground.alignCenter();
        imageViewRaccoon.alignCenterHorizontal();

        textButtonContinue.setOnClickListener(onResumeButtonClicked);
        textViewReturn.setOnClickListener(onReturnBackButtonClicked);
        alertConfirmBack.setOnButtonResumeClickListener(onDoubleResumeClickListener);
        alertConfirmBack.setOnButtonReturnHomeClickListener(onDoubleReturnClickListener);

        isVisible = false;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        if (stage == 0) {
            blackoutView.draw(myGdxGame);
            imageViewBackground.draw(myGdxGame);
            textViewTitle.draw(myGdxGame);
            imageViewRaccoon.draw(myGdxGame);
            textViewReturn.draw(myGdxGame);
            textButtonContinue.draw(myGdxGame);
        }
        if (stage == 1) {
            alertConfirmBack.draw(myGdxGame);
        }
    }

    View.OnClickListener onResumeButtonClicked = new OnClickListener() {
        @Override
        public void onClicked() {
            stage = 0;
            isVisible = false;
            if (onButtonResumeClickListener != null) {
                onButtonResumeClickListener.onClicked();
            }
        }
    };

    View.OnClickListener onReturnBackButtonClicked = new OnClickListener() {
        @Override
        public void onClicked() {
            stage = 1;
        }
    };

    @Override
    public boolean isHit(float tx, float ty) {
        if (stage == 0) {
            textButtonContinue.isHit(tx, ty);
            textViewReturn.isHit(tx, ty);
        }
        if (stage == 1) {
            alertConfirmBack.isHit(tx, ty);
        }
        return false;
    }

    AlertConfirmBack.OnButtonResumeClickListener onDoubleResumeClickListener = new AlertConfirmBack.OnButtonResumeClickListener() {
        @Override
        public void onClicked() {
            onResumeButtonClicked.onClicked();
        }
    };

    AlertConfirmBack.OnButtonReturnHomeClickListener onDoubleReturnClickListener = new AlertConfirmBack.OnButtonReturnHomeClickListener() {
        @Override
        public void onClicked() {
            stage = 0;
            isVisible = false;
            if (onButtonResumeClickListener != null) {
                onButtonReturnHomeClickListener.onClicked();
            }
        }
    };

    public interface OnButtonResumeClickListener {
        void onClicked();
    }

    public interface OnButtonReturnHomeClickListener {
        void onClicked();
    }
}
