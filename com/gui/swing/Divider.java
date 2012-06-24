package com.gui.swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Divider extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2905475762559425692L;
	private boolean horizontal = true;
	private Color c = Color.red;

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(c);
		if (horizontal) {
			int y = getHeight() / 2;
			g.drawLine(0, y, getWidth(), y);
		} else {
			int x = getWidth() / 2;
			g.drawLine(x, 0, x, getHeight());
		}
	}

}
