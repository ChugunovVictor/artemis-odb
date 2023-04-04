package com.gamadu.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class ImagePacker {

	public static void run() {
		Settings settings = new Settings();
		settings.paddingX = 2;
		settings.paddingY = 2;
		// settings.incremental = true;
		settings.stripWhitespaceX = false;
		settings.stripWhitespaceY = false;
		settings.minHeight = 1024;
		settings.minWidth = 1024;
		settings.filterMin = Texture.TextureFilter.Linear;
		settings.filterMag = Texture.TextureFilter.Linear;
        TexturePacker.process(settings, "textures-original","assets/textures", "pack");
	}

}
