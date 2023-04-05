package com.gamadu.systems;

import com.artemis.BaseSystem;
import com.artemis.utils.Timer;
import com.badlogic.gdx.math.MathUtils;
import com.gamadu.EntityFactory;
import com.gamadu.SpaceshipWarrior;
import com.gamadu.components.Sprite;

public class EntitySpawningTimerSystem extends BaseSystem {

    private Timer timer1;
    private Timer timer2;
    private Timer timer3;
    private Timer timer4;

    public EntitySpawningTimerSystem() {
        timer1 = new Timer(2, true) {
            @Override
            public void execute() {
                EntityFactory.createEnemyShip(world, "enemy1", Sprite.Layer.ACTORS_3, 10, MathUtils.random(-SpaceshipWarrior.FRAME_WIDTH / 2, SpaceshipWarrior.FRAME_WIDTH / 2), SpaceshipWarrior.FRAME_HEIGHT / 2 + 50, 0, -40, 20);
            }
        };

        timer2 = new Timer(6, true) {
            @Override
            public void execute() {
                EntityFactory.createEnemyShip(world, "enemy2", Sprite.Layer.ACTORS_2, 20, MathUtils.random(-SpaceshipWarrior.FRAME_WIDTH / 2, SpaceshipWarrior.FRAME_WIDTH / 2), SpaceshipWarrior.FRAME_HEIGHT / 2 + 100, 0, -30, 40);
            }
        };

        timer3 = new Timer(12, true) {
            @Override
            public void execute() {
                EntityFactory.createEnemyShip(world, "enemy3", Sprite.Layer.ACTORS_1, 60, MathUtils.random(-SpaceshipWarrior.FRAME_WIDTH / 2, SpaceshipWarrior.FRAME_WIDTH / 2), SpaceshipWarrior.FRAME_HEIGHT / 2 + 200, 0, -20, 70);
            }
        };

        timer4 = new Timer(12, true) {
            @Override
            public void execute() {
                int force = MathUtils.random(1,3);
                EntityFactory.createWeapon(world, "weapon" + force, force, Sprite.Layer.ACTORS_4, MathUtils.random(-SpaceshipWarrior.FRAME_WIDTH / 2, SpaceshipWarrior.FRAME_WIDTH / 2), SpaceshipWarrior.FRAME_HEIGHT / 2 + 50, 0, -40, 20);
            }
        };
    }

    @Override
    protected void processSystem() {
        timer1.update(world.delta);
        timer2.update(world.delta);
        timer3.update(world.delta);
        timer4.update(world.delta);
    }

}
