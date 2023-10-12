package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.utils.ApplicationSettings.SCR_HEIGHT;

public class TaskView extends View {

    TextView textViewTaskTitle;
    TextView textViewTaskDescription;

    public TaskView(BitmapFont fontTitle, BitmapFont fontDescription, String description) {
        super(0, 0);
        System.out.println(SCR_HEIGHT - 240);
        textViewTaskTitle = new TextView(fontTitle, "Задание", 100, SCR_HEIGHT - 160);
        textViewTaskDescription = new TextView(fontDescription, description, 100, SCR_HEIGHT - 300, true);
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        textViewTaskTitle.draw(myGdxGame);
        textViewTaskDescription.draw(myGdxGame);
    }
}
