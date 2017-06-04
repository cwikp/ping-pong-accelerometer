package com.gdx.pingpong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.pingpong.PingPongGame;

public class DesktopLauncher {
	private static final int SCREEN_WIDTH = 1024;
	private static final int SCREEN_HEIGHT = 576;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "PingPongAccelerometer";
		config.width = SCREEN_WIDTH;
		config.height = SCREEN_HEIGHT;
		new LwjglApplication(new PingPongGame(), config);
	}
}
