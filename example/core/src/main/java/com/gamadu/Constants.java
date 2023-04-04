package com.gamadu;

import java.util.concurrent.atomic.AtomicLong;

public class Constants {
	
	public class Groups {
		public static final String PLAYER_BULLETS = "player bullets";
		public static final String PLAYER_SHIP = "player ship";
		public static final String ENEMY_SHIPS = "enemy ships";
		public static final String ENEMY_BULLETS = "enemy bullets";
	}

	public static AtomicLong totalCreated = new AtomicLong(0L);
	public static AtomicLong totalDeleted = new AtomicLong(0L);

}
