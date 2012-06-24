package com.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageSource {

	private BufferedImage image = null;
	private String webSource;
	private String localSource;

	public ImageSource(BufferedImage image, String webSource, String localSource) {
		super();
		this.image = image;
		this.webSource = webSource;
		this.localSource = localSource;
	}

	public BufferedImage getImage() {
		try {
			if (image == null && (image = findImage()) == null) {
				image = new BufferedImage(0, 0, 0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	private BufferedImage findImage() throws IOException {
		BufferedImage image = null;
		File f = new File(localSource);
		if (!f.exists() || (image = ImageIO.read(f)) == null) {
			return ImageIO.read(new URL(webSource));
		}
		return image;
	}

}
