package com.gamadu.systems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamadu.Constants;
import com.gamadu.SpaceshipWarrior;
import com.gamadu.components.Position;
import com.gamadu.components.Sprite;

import java.util.HashMap;

public class HudRenderSystem extends BaseSystem {
	@Wire
	ComponentMapper<Position> pm;
	@Wire
	ComponentMapper<Sprite> sm;

	private HashMap<String, AtlasRegion> regions;
	private TextureAtlas textureAtlas;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont font;

	public HudRenderSystem(OrthographicCamera camera) {
		this.camera = camera;
	}

	@Override
	protected void initialize() {
		regions = new HashMap<String, AtlasRegion>();
		textureAtlas = new TextureAtlas(Gdx.files.internal("textures/pack.atlas"), Gdx.files.internal("textures"));
		for (AtlasRegion r : textureAtlas.getRegions()) {
			regions.put(r.name, r);
		}

		batch = new SpriteBatch();

		Texture fontTexture = new Texture(Gdx.files.internal("fonts/normal_0.png"));
		fontTexture.setFilter(TextureFilter.Linear, TextureFilter.MipMapLinearLinear);
		TextureRegion fontRegion = new TextureRegion(fontTexture);
		font = new BitmapFont(Gdx.files.internal("fonts/normal.fnt"), fontRegion, false);
		font.setUseIntegerPositions(false);
	}

	@Override
	protected void begin() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	@Override
	protected void processSystem() {
		batch.setColor(1, 1, 1, 1);
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), -(SpaceshipWarrior.FRAME_WIDTH / 2) + 20, SpaceshipWarrior.FRAME_HEIGHT / 2 - 20);
		//font.draw(batch, "Active entities: " + world.getEntityManager().getActiveEntityCount(), -(SpaceshipWarrior.FRAME_WIDTH / 2) + 20, SpaceshipWarrior.FRAME_HEIGHT / 2 - 40);
		font.draw(batch, "Active entities: " + (Constants.totalCreated.get() - Constants.totalDeleted.get()), -(SpaceshipWarrior.FRAME_WIDTH / 2) + 20, SpaceshipWarrior.FRAME_HEIGHT / 2 - 40);
		font.draw(batch, "Total created: " + Constants.totalCreated, -(SpaceshipWarrior.FRAME_WIDTH / 2) + 20, SpaceshipWarrior.FRAME_HEIGHT / 2 - 60);
		font.draw(batch, "Total deleted: " + Constants.totalDeleted, -(SpaceshipWarrior.FRAME_WIDTH / 2) + 20, SpaceshipWarrior.FRAME_HEIGHT / 2 - 80);
	}

	@Override
	protected void end() {
		batch.end();
	}

}
