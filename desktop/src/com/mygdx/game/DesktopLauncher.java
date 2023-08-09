package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.utils.ApplicationSettings.SCR_HEIGHT;
import static com.mygdx.game.utils.ApplicationSettings.SCR_WIDTH;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(SCR_WIDTH, SCR_HEIGHT);
		config.setTitle("My GDX Game");
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
