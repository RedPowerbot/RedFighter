package com.paint.button;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.paint.PaintTool;

public class ProgressBar extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7212871544154157712L;

	private static final Color FLARE = new PaintTool().setAlpha(Color.black,
			125);

	private Color progressColor = Color.green;
	private double progress = 0D;
	private boolean addFlare = true; // Go watch "Office Space".

	public ProgressBar(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public void setProgressInt(int progress) {
		this.progress = progress / 100;
	}

	public void setProgressColor(Color progressColor) {
		this.progressColor = progressColor;
	}

	public void setAddFlare(boolean addFlare) {
		this.addFlare = addFlare;
	}

	@Override
	public void render(Graphics2D render) {
		if (enabled) {
			if (fill) {
				render.setColor(background);
				render.fillRoundRect(x, y, width, height, arc, arc);
			}
			if (progress > 0) {
				render.setColor(progressColor);
				render.fillRoundRect(x, y, (int) (width * progress), height,
						arc, arc);
			}
			if (addFlare) {
				render.setColor(FLARE);
				render.fillRect(x, (int) getCenterY(), width,
						(int) getHeight() / 2);
			}
			if (text != null && !text.equals("")) {
				render.setFont(font);
				render.setColor(foreground);
				Rectangle r = tool.getTextDimensions(render, text).getBounds();
				tool.center(this, r);
				tool.getTextLocation(r);
			}
			if (outline) {
				render.setColor(border);
				render.fillRoundRect(x, y, width, height, arc, arc);
			}
		}
	}

}
