package com.mygdx.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.google.gwt.user.client.Window;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.SessionHelper;

import static com.mygdx.game.utils.ApplicationSettings.SCR_HEIGHT;
import static com.mygdx.game.utils.ApplicationSettings.SCR_WIDTH;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
//                SessionHelper.params = Window.Location.getParameter("token");
                GwtApplicationConfiguration gwtApplicationConfiguration = new GwtApplicationConfiguration();
//                return new GwtApplicationConfiguration(1280, 800);
                return new GwtApplicationConfiguration(true);
                // Fixed size application:
//                return new GwtApplicationConfiguration(1024, 768);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new MyGdxGame();
        }
}