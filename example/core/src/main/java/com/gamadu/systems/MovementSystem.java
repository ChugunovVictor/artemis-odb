package com.gamadu.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.gamadu.components.Position;
import com.gamadu.components.Velocity;

public class MovementSystem extends EntityProcessingSystem {
    @Wire
    ComponentMapper<Position> pm;
    @Wire
    ComponentMapper<Velocity> vm;

    public MovementSystem() {
        super(Aspect.all(Position.class, Velocity.class));
    }

    @Override
    protected void process(Entity e) {
        Position position = pm.get(e);
        Velocity velocity = vm.get(e);

        position.x += velocity.vectorX * world.delta;
        position.y += velocity.vectorY * world.delta;
    }

}
