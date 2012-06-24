package com.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import org.powerbot.game.api.wrappers.Tile;

import com.data.Configuration;

public class ClientPainter {

	private Configuration con;
	
	public ClientPainter(Configuration con) {
		this.con = con;
	}
	
	/**
	 * Done independently to increase functionality of this method.
	 */
	private void paintTile(Graphics2D render, Tile tile, Color outline, Color fill) {
		if (render == null || tile == null || !tile.validate()) {
			return;
		}
		if (outline == null && fill == null) {
			return;
		}
		
	}

	
}
