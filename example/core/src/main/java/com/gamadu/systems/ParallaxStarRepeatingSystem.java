package com.gamadu.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.gamadu.SpaceshipWarrior;
import com.gamadu.components.ParallaxStar;
import com.gamadu.components.Position;

public class ParallaxStarRepeatingSystem extends IntervalEntityProcessingSystem {
    @Wire
    ComponentMapper<Position> pm;

    public ParallaxStarRepeatingSystem() {
        super(Aspect.all(ParallaxStar.class, Position.class), 1);
    }

    @Override
    protected void process(Entity e) {
        Position position = pm.get(e);

        if (position.y < -SpaceshipWarrior.FRAME_HEIGHT / 2) {
            position.y = SpaceshipWarrior.FRAME_HEIGHT / 2;
        }
    }

}
