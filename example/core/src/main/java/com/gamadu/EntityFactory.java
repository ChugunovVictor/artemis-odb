package com.gamadu;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.math.MathUtils;
import com.gamadu.components.*;

public class EntityFactory {
	
	public static Entity createPlayer(World world, float x, float y) {
		Entity e = world.createEntity();
		Constants.totalCreated.incrementAndGet();

		Position position = new Position();
		position.x = x;
		position.y = y;
		e.edit().add(position);

		/*
		Sprite sprite = new Sprite();
		sprite.name = "fighter";
		sprite.r = 93/255f;
		sprite.g = 255/255f;
		sprite.b = 129/255f;
		sprite.layer = Sprite.Layer.ACTORS_3;
		e.edit().add(sprite);
		*/

		Velocity velocity = new Velocity();
		velocity.vectorX = 0;
		velocity.vectorY = 0;
		e.edit().add(velocity);


		Bounds bounds = new Bounds();
		bounds.radius = 43;
		e.edit().add(bounds);

		Weapon weapon = new Weapon();
		weapon.force = 1;
		e.edit().add(weapon);

		e.edit().add(new Player());

		world.getSystem(GroupManager.class).add(e, Constants.Groups.PLAYER_SHIP);
		
		return e;
	}
	
	public static Entity createPlayerBullet(World world, float x, float y) {
		Entity e = world.createEntity();
		Constants.totalCreated.incrementAndGet();

		Position position = new Position();
		position.x = x;
		position.y = y;
		e.edit().add(position);

		Sprite sprite = new Sprite();
		sprite.name = "bullet";
		sprite.layer = Sprite.Layer.PARTICLES;
		e.edit().add(sprite);

		Velocity velocity = new Velocity();
		velocity.vectorY = 800;
		e.edit().add(velocity);


		Bounds bounds = new Bounds();
		bounds.radius = 5;
		e.edit().add(bounds);

		Expires expires = new Expires();
		expires.delay = 5;
		e.edit().add(expires);

		world.getSystem(GroupManager.class).add(e, Constants.Groups.PLAYER_BULLETS);
		
		return e;
	}

	public static Entity createWeapon(World world, String name, int force, Sprite.Layer layer, float x, float y, float velocityX, float velocityY, float boundsRadius) {
		Entity e = world.createEntity();
		Constants.totalCreated.incrementAndGet();

		Position position = new Position();
		position.x = x;
		position.y = y;
		e.edit().add(position);

		Sprite sprite = new Sprite();
		sprite.name = name;
		sprite.r = 0/255f;
		sprite.g = 293/255f;
		sprite.b = 255/255f;
		sprite.layer = layer;
		e.edit().add(sprite);

		Velocity velocity = new Velocity();
		velocity.vectorX = velocityX;
		velocity.vectorY = velocityY;
		e.edit().add(velocity);

		Bounds bounds = new Bounds();
		bounds.radius = boundsRadius;
		e.edit().add(bounds);

		Weapon weapon = new Weapon();
		weapon.force = force;
		e.edit().add(weapon);

		world.getSystem(GroupManager.class).add(e, Constants.Groups.WEAPONS);


		return e;
	}
	
	public static Entity createEnemyShip(World world, String name, Sprite.Layer layer, float health, float x, float y, float velocityX, float velocityY, float boundsRadius) {
		Entity e = world.createEntity();
		Constants.totalCreated.incrementAndGet();

		Position position = new Position();
		position.x = x;
		position.y = y;
		e.edit().add(position);

		Sprite sprite = new Sprite();
		sprite.name = name;
		sprite.r = 255/255f;
		sprite.g = 0/255f;
		sprite.b = 142/255f;
		sprite.layer = layer;
		e.edit().add(sprite);

		Velocity velocity = new Velocity();
		velocity.vectorX = velocityX;
		velocity.vectorY = velocityY;
		e.edit().add(velocity);

		Bounds bounds = new Bounds();
		bounds.radius = boundsRadius;
		e.edit().add(bounds);

		Health h = new Health();
		h.health = h.maximumHealth = health;
		e.edit().add(h);

		world.getSystem(GroupManager.class).add(e, Constants.Groups.ENEMY_SHIPS);
		
		return e;
	}
	
	public static Entity createExplosion(World world, float x, float y, float scale) {
		Entity e = world.createEntity();
		Constants.totalCreated.incrementAndGet();

		Position position = new Position();
		position.x = x;
		position.y = y;
		e.edit().add(position);

		Sprite sprite = new Sprite();
		sprite.name = "explosion";
		sprite.scaleX = sprite.scaleY = scale;
		sprite.r = 255/255f;
		sprite.g = 250/255f;
		sprite.b = 0/255f;
		sprite.a = 0.75f;
		sprite.layer = Sprite.Layer.PARTICLES;
		e.edit().add(sprite);

		Expires expires = new Expires();
		expires.delay = 0.5f;
		e.edit().add(expires);

		ScaleAnimation scaleAnimation = new ScaleAnimation();
		scaleAnimation.active = true;
		scaleAnimation.max = scale;
		scaleAnimation.min = scale/100f;
		scaleAnimation.speed = -3.0f;
		scaleAnimation.repeat = false;
		e.edit().add(scaleAnimation);

		return e;
	}	
	
	public static Entity createStar(World world) {
		Entity e = world.createEntity();
		Constants.totalCreated.incrementAndGet();

		Position position = new Position();
		position.x = MathUtils.random(-SpaceshipWarrior.FRAME_WIDTH/2, SpaceshipWarrior.FRAME_WIDTH/2);
		position.y = MathUtils.random(-SpaceshipWarrior.FRAME_HEIGHT/2, SpaceshipWarrior.FRAME_HEIGHT/2);
		e.edit().add(position);

		Sprite sprite = new Sprite();
		sprite.name = "particle";
		sprite.scaleX = sprite.scaleY = MathUtils.random(0.5f, 1f);
		sprite.a = MathUtils.random(0.1f, 0.5f);
		sprite.layer = Sprite.Layer.BACKGROUND;
		e.edit().add(sprite);

		Velocity velocity = new Velocity();
		velocity.vectorY = MathUtils.random(-10f, -60f);
		e.edit().add(velocity);

		e.edit().add(new ParallaxStar());

		ColorAnimation colorAnimation = new ColorAnimation();
		colorAnimation.alphaAnimate = true;
		colorAnimation.repeat = true;
		colorAnimation.alphaSpeed = MathUtils.random(0.2f, 0.7f);
		colorAnimation.alphaMin = 0.1f;
		colorAnimation.alphaMax = 0.5f;
		e.edit().add(colorAnimation);

		return e;
	}
	
	public static Entity createParticle(World world, float x, float y) {
		Entity e = world.createEntity();
		Constants.totalCreated.incrementAndGet();

		Position position = new Position();
		position.x = x;
		position.y = y;
		e.edit().add(position);

		Sprite sprite = new Sprite();
		sprite.name = "particle";
		sprite.scaleX = sprite.scaleY = MathUtils.random(0.3f, 0.6f);
		sprite.r = 255/255f;
		sprite.g = 250/255f;
		sprite.b = 0/255f;
		sprite.a = 0.75f;
		sprite.layer = Sprite.Layer.PARTICLES;
		e.edit().add(sprite);

		Velocity velocity = new Velocity();
		velocity.vectorX = MathUtils.random(-400, 400);
		velocity.vectorY = MathUtils.random(-400, 400);
		e.edit().add(velocity);

		Expires expires = new Expires();
		expires.delay = 1;
		e.edit().add(expires);

		ColorAnimation colorAnimation = new ColorAnimation();
		colorAnimation.alphaAnimate = true;
		colorAnimation.alphaSpeed = -1f;
		colorAnimation.alphaMin = 0f;
		colorAnimation.alphaMax = 1f;
		colorAnimation.repeat = false;
		e.edit().add(colorAnimation);

		return e;
	}

}
