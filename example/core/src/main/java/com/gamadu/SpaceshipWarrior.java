package com.gamadu;

import com.badlogic.gdx.Game;

public class SpaceshipWarrior extends Game {
	public static final int FRAME_WIDTH = 1280;
	public static final int FRAME_HEIGHT = 900;
	
	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}
	


}
