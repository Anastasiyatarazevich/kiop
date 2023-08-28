package com.mygdx.game.screens;

import static com.mygdx.game.utils.ApplicationSettings.RAVEN_CARD_IMAGES_DIR;
import static com.mygdx.game.utils.ApplicationSettings.RAVEN_CARD_PADDING;
import static com.mygdx.game.utils.ApplicationSettings.RAVEN_CARD_SIZE;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;
import static com.mygdx.game.utils.raven.Cards.targetTextures;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.testSessions.RavenSession;
import com.mygdx.game.ui.BackgroundPixmapView;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.View;
import com.mygdx.game.ui.raven.MatrixCardsView;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;
import com.mygdx.game.utils.raven.Cards;

public class ScreenRavenMatrices implements Screen {

    MyGdxGame myGdxGame;
    SceneHelper sceneGreeting, sceneCardShowing, scenePassed;
    RavenSession testSession;
    ImageView imageViewNext, imageViewTargetPicture;
    MatrixCardsView tableCardsView;

    TextView tableCounterView;
    public static TextureAtlas textureAtlas;
    public ScreenRavenMatrices(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        textureAtlas = new TextureAtlas(RAVEN_CARD_IMAGES_DIR);

        sceneGreeting = new SceneHelper();
        sceneCardShowing = new SceneHelper();
        scenePassed = new SceneHelper();

        testSession = new RavenSession();

        tableCardsView = new MatrixCardsView(
                900, 270,
                RAVEN_CARD_PADDING, RAVEN_CARD_SIZE,
                Cards.presets[0].getListOfCardsSrc()
        );
        tableCardsView.setOnTableCardsClickListener(onTableCardsClicked);

        BackgroundPixmapView background = new BackgroundPixmapView(COLOR_BG_GRAY);
        TextButton buttonStart = new TextButton(
                myGdxGame.fontArialBlack64,
                "Начать",
                "schulteTable/buttonBackground.png",
                775, 155
        );

        imageViewTargetPicture = new ImageView(100, 182, 712, 600, textureAtlas.findRegion(targetTextures[0]));
        imageViewNext = new ImageView(
                1750, 425,
                "icons/icon_next.png"
        );
        TextView textViewTitle = new TextView(
                myGdxGame.fontArialBlack64,
                "Найди недостающий фрагмент",
                -1, 950
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

        tableCounterView = new TextView(
                myGdxGame.fontArialGray32,
                "1/" + Cards.presets.length,
                -1, 62
        );

        buttonBack.setOnClickListener(onButtonBackClicked);

        imageViewNext.isVisible = false;
        imageViewNext.setOnClickListener(onImageViewNextClicked);


        buttonStart.setOnClickListener(onButtonStartClicked);

        sceneGreeting.addActor(background);
        sceneGreeting.addActor(buttonStart);

        sceneCardShowing.addActor(background);
        sceneCardShowing.addActor(tableCardsView);
        sceneCardShowing.addActor(imageViewNext);
        sceneCardShowing.addActor(imageViewTargetPicture);
        sceneCardShowing.addActor(textViewTitle);
        sceneCardShowing.addActor(tableCounterView);


        scenePassed.addActor(background);
        scenePassed.addActor(buttonBack);
        scenePassed.addActor(textViewPassedTitle);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        testSession.checkingTime();
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

    View.OnClickListener onImageViewNextClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            imageViewNext.isVisible = false;
            if (!testSession.nextCards()) {
                return;
            }
            tableCardsView.setCards(Cards.presets[testSession.currentMatrixIndex].getListOfCardsSrc());
            imageViewTargetPicture.setTextureRegion(textureAtlas.findRegion(targetTextures[testSession.currentMatrixIndex]));
            tableCounterView.setText((testSession.currentMatrixIndex + 1) + "/" + Cards.presets.length);

        }
    };

    MatrixCardsView.OnTableCardsClickListener onTableCardsClicked = new MatrixCardsView.OnTableCardsClickListener() {
        @Override
        public void onClick(int cardIdx) {
            imageViewNext.isVisible = true;
            testSession.cardWasSelected(cardIdx);
        }
    };


    View.OnClickListener onButtonBackClicked = new View.OnClickListener() {
        @Override
        public void onClicked() {
            myGdxGame.setScreen(myGdxGame.screenMenu);
        }
    };
}
