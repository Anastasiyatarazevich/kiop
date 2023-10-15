package com.mygdx.game.screens;

import static com.mygdx.game.utils.UsingColors.COLOR_BG_GRAY;
import static com.mygdx.game.utils.UsingColors.COLOR_BG_WHITE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.BackgroundPixmapView;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.ui.View;
import com.mygdx.game.utils.RenderHelper;
import com.mygdx.game.utils.SceneHelper;

public class ScreenEnd implements Screen {
    MyGdxGame myGdxGame;
    SceneHelper sceneRacoon;

    public ScreenEnd(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        sceneRacoon = new SceneHelper();

        BackgroundPixmapView backgroundView = new BackgroundPixmapView(COLOR_BG_WHITE);
        TextButton buttonExit = new TextButton(
                myGdxGame.fontArialBlack64,
                "Пока!",
                "schulteTable/buttonBackground.png",
                1320, 256
        );

        ImageView imageViewRacoon = new ImageView(
                70, 600,
                "ending/byebye_racoon.png"
        );

        ImageView imageViewMessageToExit = new ImageView(
                1200, 150,
                "beginning/start_btn.png"
        );

        buttonExit.setOnClickListener(onButtonExitClick);

        sceneRacoon.addActor(backgroundView);
        sceneRacoon.addActor(imageViewRacoon);
        sceneRacoon.addActor(imageViewMessageToExit);
        sceneRacoon.addActor(buttonExit);

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
            if (justTouched) sceneRacoon.checkHits(myGdxGame);
            sceneRacoon.drawScene(myGdxGame);
        }
    };

    View.OnClickListener onButtonExitClick = new View.OnClickListener() {
        @Override
        public void onClicked() {
            Gdx.app.exit();
        }
    };
}
