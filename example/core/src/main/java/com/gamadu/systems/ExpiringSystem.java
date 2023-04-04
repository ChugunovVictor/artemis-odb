package com.gamadu.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.DelayedEntityProcessingSystem;
import com.gamadu.Constants;
import com.gamadu.components.Expires;

public class ExpiringSystem extends DelayedEntityProcessingSystem {
    @Wire
    ComponentMapper<Expires> em;

    public ExpiringSystem() {
        super(Aspect.all(Expires.class));
    }

    @Override
    protected void processDelta(Entity e, float accumulatedDelta) {
        Expires expires = em.get(e);
        expires.delay -= accumulatedDelta;
    }

    @Override
    protected void processExpired(Entity e) {
        e.deleteFromWorld();
        Constants.totalDeleted.incrementAndGet();
    }

    @Override
    protected float getRemainingDelay(Entity e) {
        Expires expires = em.get(e);
        return expires.delay;
    }
}
