package com.mygdx.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.utils.ApplicationSettings.SCR_HEIGHT;
import static com.mygdx.game.utils.ApplicationSettings.SCR_WIDTH;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
                SessionHelper.params = Window.Location.getParameter("token");
                return new GwtApplicationConfiguration(SCR_WIDTH, SCR_HEIGHT);
//                return new GwtApplicationConfiguration(true);
                // Fixed size application:
                //return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new MyGdxGame();
        }
}