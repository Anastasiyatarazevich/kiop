package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.SessionTheExtraFourth;
import com.mygdx.game.ui.*;
import com.mygdx.game.ui.theExtraFourth.TableCardsView;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.theExtraFourth.CardsPresets;

import static com.mygdx.game.utils.ApplicationSettings.*;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;

public class ScreenTheExtraFourth implements Screen {

    MyGdxGame myGdxGame;
    SessionTheExtraFourth testSession;

    SceneHelper scenePassed;
    SceneHelper sceneGreeting;
    SceneHelper sceneCardShowing;

    TableCardsView tableCardsView;
    TextView tableCounterView;
    ImageView imageViewNext;

    public ScreenTheExtraFourth(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        testSession = new SessionTheExtraFourth();

        scenePassed = new SceneHelper();
        sceneGreeting = new SceneHelper();
        sceneCardShowing = new SceneHelper();

        tableCardsView = new TableCardsView(
                -1, 160,
                FOURTH_CARD_PADDING, FOURTH_CARD_SIZE,
                CardsPresets.presets[testSession.currentQuadIdx].getListOfCardsSrc()
        );

        tableCounterView = new TextView(
                myGdxGame.fontArialGray32,
                "1/" + CardsPresets.presets.length,
                -1, 62
        );

        imageViewNext = new ImageView(
                1550, 425,
                "icons/icon_next.png"
        );

        BackgroundPixmap background = new BackgroundPixmap(COLOR_BG_GRAY);

        TextButton buttonStart = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                775, 155
        );

        TextView textViewTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Выбери лишнюю карточку",
                -1, 925
        );

        TextView textViewPassedTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Молодец, ты справился!",
                -1, 816
        );

        TextButton buttonBack = new TextButton(
                myGdxGame.fontArialBlack64,
                "Вернуться в лес",
                "schulteTable/buttonBackground.png",
                -1, 228
        );

        imageViewNext.isVisible = false;
        imageViewNext.setOnClickListener(onImageViewNextClicked);
        tableCardsView.setOnTableCardsClickListener(onTableCardsClicked);
        buttonStart.setOnClickListener(onButtonStartClicked);
        buttonBack.setOnClickListener(onButtonBackClicked);

        sceneGreeting.addActor(background);
        sceneGreeting.addActor(buttonStart);

        sceneCardShowing.addActor(background);
        sceneCardShowing.addActor(tableCardsView);
        sceneCardShowing.addActor(textViewTitle);
        sceneCardShowing.addActor(tableCounterView);
        sceneCardShowing.addActor(imageViewNext);

        scenePassed.addActor(background);
        scenePassed.addActor(buttonBack);
        scenePassed.addActor(textViewPassedTitle);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        boolean justTouched = RenderHelper.checkTouch(myGdxGame);
        RenderHelper.draw(myGdxGame, drawScenes, justTouched);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    RenderHelper.DrawScenes drawScenes = new RenderHelper.DrawScenes() {
        @Override
        public void draw(boolean justTouched) {
            switch (testSession.testState) {
                case PASSED:
                    if (justTouched) scenePassed.checkHits(myGdxGame);
                    scenePassed.drawScene(myGdxGame);
                    break;
                case CARDS_SHOWING:
                    if (justTouched) sceneCardShowing.checkHits(myGdxGame);
                    sceneCardShowing.drawScene(myGdxGame);
                    break;
                case GREETING:
                    if (justTouched) sceneGreeting.checkHits(myGdxGame);
                    sceneGreeting.drawScene(myGdxGame);
                    break;
            }
        }
    };

    View.OnClickListener onButtonStartClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            testSession.startTest();
        }
    };

    TableCardsView.OnTableCardsClickListener onTableCardsClicked = new TableCardsView.OnTableCardsClickListener() {
        @Override
        public void onClick(int cardIdx) {
            imageViewNext.isVisible = true;
            testSession.cardWasSelected(cardIdx);
        }
    };

    View.OnClickListener onImageViewNextClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            imageViewNext.isVisible = false;
            if (!testSession.nextCards()) {
                return;
            }
            tableCardsView.setCards(CardsPresets.presets[testSession.currentQuadIdx].getListOfCardsSrc());
            tableCounterView.setText((testSession.currentQuadIdx + 1) + "/" + CardsPresets.presets.length);
        }
    };

    View.OnClickListener onButtonBackClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };
}
