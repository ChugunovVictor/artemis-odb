package com.gamadu;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gamadu.systems.*;

public class GameScreen implements Screen {
	private Game game;
	private World world;
	private OrthographicCamera camera;
	
	private SpriteRenderSystem spriteRenderSystem;
	private HealthRenderSystem healthRenderSystem;
	private HudRenderSystem hudRenderSystem;

	public GameScreen(Game game) {
		this.game = game;
		this.camera = new OrthographicCamera(SpaceshipWarrior.FRAME_WIDTH, SpaceshipWarrior.FRAME_HEIGHT);

		WorldConfiguration config = new WorldConfiguration();

		config.setSystem(new GroupManager());

		config.setSystem(new MovementSystem());
		config.setSystem(new PlayerInputSystem(camera));
		config.setSystem(new CollisionSystem());
		config.setSystem(new ExpiringSystem());
		config.setSystem(new EntitySpawningTimerSystem());
		config.setSystem(new ParallaxStarRepeatingSystem());
		config.setSystem(new ColorAnimationSystem());
		config.setSystem(new ScaleAnimationSystem());
		config.setSystem(new RemoveOffscreenShipsSystem());

		// were passive
		spriteRenderSystem = new SpriteRenderSystem(camera);
		config.setSystem(spriteRenderSystem);
		healthRenderSystem = new HealthRenderSystem(camera);
		config.setSystem(healthRenderSystem);
		hudRenderSystem = new HudRenderSystem(camera);
		config.setSystem(hudRenderSystem);

		world = new World(config);

		EntityFactory.createPlayer(world, 0, 0);

		for(int i = 0; 500 > i; i++) {
			EntityFactory.createStar(world);
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();

		world.setDelta(delta);
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			for(int i = 0; 10 > i; i++) {
				world.process();
			}
		}
		world.process();
		
		spriteRenderSystem.process();
		healthRenderSystem.process();
		hudRenderSystem.process();
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
