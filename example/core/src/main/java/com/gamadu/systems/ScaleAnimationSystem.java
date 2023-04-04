package com.gamadu.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.gamadu.components.ScaleAnimation;
import com.gamadu.components.Sprite;

public class ScaleAnimationSystem extends EntityProcessingSystem {
    @Wire
    ComponentMapper<ScaleAnimation> sa;
    @Wire
    ComponentMapper<Sprite> sm;

    public ScaleAnimationSystem() {
        super(Aspect.all(ScaleAnimation.class));
    }

    @Override
    protected void process(Entity e) {
        ScaleAnimation scaleAnimation = sa.get(e);
        if (scaleAnimation.active) {
            Sprite sprite = sm.get(e);

            sprite.scaleX += scaleAnimation.speed * world.delta;
            sprite.scaleY = sprite.scaleX;

            if (sprite.scaleX > scaleAnimation.max) {
                sprite.scaleX = scaleAnimation.max;
                scaleAnimation.active = false;
            } else if (sprite.scaleX < scaleAnimation.min) {
                sprite.scaleX = scaleAnimation.min;
                scaleAnimation.active = false;
            }
        }
    }

}
