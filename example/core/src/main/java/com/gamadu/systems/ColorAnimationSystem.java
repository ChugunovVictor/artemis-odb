package com.gamadu.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.gamadu.components.ColorAnimation;
import com.gamadu.components.Sprite;

public class ColorAnimationSystem extends EntityProcessingSystem {
    @Wire
    ComponentMapper<ColorAnimation> cam;
    @Wire
    ComponentMapper<Sprite> sm;

    public ColorAnimationSystem() {
        super(Aspect.all(ColorAnimation.class, Sprite.class));
    }

    @Override
    protected void process(Entity e) {
        ColorAnimation c = cam.get(e);
        Sprite sprite = sm.get(e);

        if (c.alphaAnimate) {
            sprite.a += c.alphaSpeed * world.delta;

            if (sprite.a > c.alphaMax || sprite.a < c.alphaMin) {
                if (c.repeat) {
                    c.alphaSpeed = -c.alphaSpeed;
                } else {
                    c.alphaAnimate = false;
                }
            }
        }
    }

}
