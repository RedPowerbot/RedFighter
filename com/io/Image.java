package com.io;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Image {

	private static final HashMap<String, ImageSource> map = new HashMap<>();

	static {
		
	}

	public static BufferedImage getImage(String key) {
		ImageSource source = map.get(key);
		if (source == null) {
			return new BufferedImage(0, 0, 0);
		}
		return source.getImage();
	}

}
