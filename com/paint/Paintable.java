package com.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import com.paint.data.PaintTab;

public class Paintable extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8535837036303914927L;

	protected static final PaintTool tool = new PaintTool();

	protected ActionListener listener;

	protected boolean enabled = true;
	protected boolean outline = true;
	protected boolean fill = true;
	protected int arc = tool.getDefaultArc();

	protected Color background = Color.black;
	protected Color border = Color.white;
	protected Color foreground = Color.white;
	protected Font font = tool.getDefaultFont(12);
	protected PaintTab renderTab = PaintTab.ANY;

	public Paintable() {
		super();
	}

	public void render(Graphics2D render) {
		if (enabled) {
			if (fill) {
				render.setColor(background);
				render.fillRoundRect(x, y, width, height, arc, arc);
			}
			if (outline) {
				render.setColor(border);
				render.fillRoundRect(x, y, width, height, arc, arc);
			}
		}
	}

	public Paintable(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public Paintable(int width, int height) {
		super(width, height);
	}

	public Paintable(Rectangle r) {
		super(r);
	}

	public PaintTab getRenderTab() {
		return renderTab;
	}

	public void setRenderTab(PaintTab renderTab) {
		this.renderTab = renderTab;
	}

	public ActionListener getListener() {
		return listener;
	}

	public void setListener(ActionListener listener) {
		this.listener = listener;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isOutline() {
		return outline;
	}

	public void setOutline(boolean outline) {
		this.outline = outline;
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public int getArc() {
		return arc;
	}

	public void setArc(int arc) {
		this.arc = arc;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public Color getBorder() {
		return border;
	}

	public void setBorder(Color border) {
		this.border = border;
	}

	public Color getForeground() {
		return foreground;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public static PaintTool getTool() {
		return tool;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Paintable other = (Paintable) obj;
		if (arc != other.arc) {
			return false;
		}
		if (background == null) {
			if (other.background != null) {
				return false;
			}
		} else if (!background.equals(other.background)) {
			return false;
		}
		if (border == null) {
			if (other.border != null) {
				return false;
			}
		} else if (!border.equals(other.border)) {
			return false;
		}
		if (enabled != other.enabled) {
			return false;
		}
		if (fill != other.fill) {
			return false;
		}
		if (font == null) {
			if (other.font != null) {
				return false;
			}
		} else if (!font.equals(other.font)) {
			return false;
		}
		if (foreground == null) {
			if (other.foreground != null) {
				return false;
			}
		} else if (!foreground.equals(other.foreground)) {
			return false;
		}
		if (outline != other.outline) {
			return false;
		}
		if (renderTab != other.renderTab) {
			return false;
		}
		return true;
	}

}
