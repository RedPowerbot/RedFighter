package com.paint.button;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.paint.Paintable;

public class Button extends Paintable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2472566945581075640L;
	protected String text = "";

	public Button(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	private Button(Rectangle r) {
		super(r);
	}

	@Override
	public Button clone() {
		Button b = new Button(this);
		b.enabled = enabled;
		b.outline = outline;
		b.fill = fill;
		b.arc = arc;
		b.text = text;
		b.background = background;
		b.border = border;
		b.foreground = foreground;
		b.font = font;
		b.renderTab = renderTab;
		return b;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void render(Graphics2D render) {
		super.render(render);
		if (text != null && !text.equals("")) {
			render.setFont(font);
			render.setColor(foreground);
			Rectangle r = tool.getTextDimensions(render, text).getBounds();
			tool.center(this, r);
			Point p = tool.getTextLocation(r);
			render.drawString(text, p.x, p.y);
		}
	}

}
