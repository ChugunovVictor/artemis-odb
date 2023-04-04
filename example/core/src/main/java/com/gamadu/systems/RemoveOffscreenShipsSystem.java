package com.gamadu.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.gamadu.Constants;
import com.gamadu.SpaceshipWarrior;
import com.gamadu.components.*;

public class RemoveOffscreenShipsSystem extends IntervalEntityProcessingSystem {
	@Wire ComponentMapper<Position> pm;
	@Wire ComponentMapper<Bounds> bm;

	public RemoveOffscreenShipsSystem() {
		super(Aspect.all(Velocity.class, Position.class, Health.class, Bounds.class).exclude(Player.class), 5);
	}

	@Override
	protected void process(Entity e) {
		Position position = pm.get(e);
		Bounds bounds = bm.get(e);
		
		if(position.y < -SpaceshipWarrior.FRAME_HEIGHT/2-bounds.radius) {
			e.deleteFromWorld();
			Constants.totalDeleted.incrementAndGet();
		}
	}

}
