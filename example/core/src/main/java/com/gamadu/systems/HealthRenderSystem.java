package com.gamadu.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.gamadu.components.Health;
import com.gamadu.components.Position;

public class HealthRenderSystem extends EntityProcessingSystem {
    @Wire
    ComponentMapper<Position> pm;
    @Wire
    ComponentMapper<Health> hm;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont font;

    public HealthRenderSystem(OrthographicCamera camera) {
        super(Aspect.all(Position.class, Health.class));
        this.camera = camera;
    }

    @Override
    protected void initialize() {
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
    protected void process(Entity e) {
        Position position = pm.get(e);
        Health health = hm.get(e);

        int percentage = MathUtils.round(health.health / health.maximumHealth * 100f);

        font.draw(batch, percentage + "%", position.x, position.y);
    }

    @Override
    protected void end() {
        batch.end();
    }
}
