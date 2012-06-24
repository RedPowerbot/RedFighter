package com.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import com.util.StringUtil;

public class PaintTool {

	private static final String DEFAULT_FONT_FAMILY = "Calibri";
	private static final int DEFAULT_ARC = 6;

	private long startTime = 0;

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getDefaultArc() {
		return DEFAULT_ARC;
	}

	public long getRunTime() {
		return System.currentTimeMillis() - startTime;
	}

	public Font getDefaultFont(int size, int style) {
		return new Font(DEFAULT_FONT_FAMILY, style, size);
	}

	public Font getDefaultFont(int size) {
		return getDefaultFont(size, 0);
	}

	public Point getTextLocation(Rectangle r) {
		if (r != null) {
			return new Point(r.x, r.y + r.height);
		}
		return new Point();
	}

	public void center(Rectangle frame, Rectangle r) {
		r.setLocation((int) (frame.getCenterX() - r.getWidth()),
				(int) (frame.getCenterY() - r.getHeight()));
	}

	public Color setAlpha(Color c, int alpha) {
		if (c != null && alpha > -1 && alpha < 256) {
			return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
		}
		return Color.black;
	}

	public int getTextWidth(Graphics2D render, String text) {
		if (render == null || text == null || text.equals("")) {
			return 0;
		}
		return render.getFontMetrics().stringWidth(text);
	}

	public Rectangle2D getTextDimensions(Graphics2D render, String text) {
		if (render == null || text == null || text.equals("")) {
			return new Rectangle2D.Double();
		}
		return render.getFontMetrics().getStringBounds(text, render);
	}

	public String format(long l) {
		return StringUtil.format(l);
	}

	public String format(double d) {
		return StringUtil.format(d);
	}

	public String format(int i) {
		return StringUtil.format(i);
	}

	public String formatTime(long milli) {
		long s = milli / 1000, m = s / 60, sec = s % 60, min = m % 60, hr = m / 60;
		return String.format("%d:%02d:%02d", hr, min, sec);
	}

	public String capatilize(String s) {
		char[] charArray = s.toCharArray();
		charArray[0] = Character.toUpperCase(charArray[0]);
		return new String(charArray);
	}

}
