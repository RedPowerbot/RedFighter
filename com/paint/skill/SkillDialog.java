package com.paint.skill;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import com.paint.Design;
import com.paint.PaintTool;
import com.paint.Paintable;
import com.paint.button.Button;
import com.paint.button.ProgressBar;

public class SkillDialog extends Paintable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4867194769585377594L;
	private final Font font1 = new Font("Calibri", 0, 20);
	private final Font font2 = new Font("Calibri", 0, 16);

	private PaintTool tool = new PaintTool();
	private SkillData data;
	private int index;
	private ProgressBar bar = new ProgressBar(0, 0, 210, 30);
	private Button close = new Button(0, 0, 16, 14);

	/**
	 * Opens the Dialog from the click point. Adds a 15 pixil dev to each axis
	 */
	public SkillDialog(Point p, SkillData data, int index) {
		super(p.x - 15, p.y - 315, 240, 300);
		this.enabled = false;
		this.data = data;
		this.index = index;
		this.arc = 16;
		close.setForeground(Design.black);
		close.setOutline(false);
		close.setBackground(Design.red);
		close.setText("X");
	}

	public void onClick(Point p) {
		if (close.contains(p)) {
			setVisible(false);
		}
	}

	public void setVisible(boolean visible) {
		setEnabled(visible);
	}

	public boolean isVisible() {
		return enabled;
	}

	@Override
	public void render(Graphics2D g) {
		if (!enabled) {
			return;
		}
		super.render(g);
		g.setColor(Design.red);
		g.drawLine(x + 15, y + 30, x + 225, y + 30);
		g.setFont(font1);
		g.setColor(Design.white);
		g.drawString(data.getName(index), x + 45, y + 20);
		g.setFont(font2);
		g.setColor(Design.red);
		g.drawString("TTL", x + 15, y + 80);
		g.drawString("Gained Lv", x + 15, y + 100);
		g.drawString("Start Lv", x + 15, y + 60);
		g.drawString("Start XP", x + 15, y + 150);
		g.drawString("Cur XP", x + 15, y + 170);
		g.drawString("Gained XP", x + 15, y + 190);
		g.drawString("XP / Hr", x + 15, y + 210);
		g.drawString("EXTL", x + 15, y + 230);
		g.drawString(":", x + 90, y + 60);
		g.drawString(":", x + 90, y + 80);
		g.drawString(":", x + 90, y + 100);
		g.drawString(":", x + 90, y + 150);
		g.drawString(":", x + 90, y + 170);
		g.drawString(":", x + 90, y + 190);
		g.drawString(":", x + 90, y + 210);
		g.drawString(":", x + 90, y + 230);
		g.setColor(Design.white);
		g.drawLine(x + 15, y + 120, 225, 120);
		g.drawLine(x + 15, y + 240, 225, 240);
		;
		g.drawString(Integer.toString(data.getStartLv(index)), x + 105, y + 60);
		g.drawString(tool.formatTime(data.getTTL(index)), x + 105, y + 80);
		g.drawString(Integer.toString(data.getGainedExp(index)), x + 105,
				y + 100);
		g.drawString(tool.format(data.getStartExp(index)), x + 105, y + 150);
		g.drawString(tool.format(data.getExp(index)), x + 105, y + 170);
		g.drawString(tool.format(data.getGainedExp(index)), x + 105, y + 190);
		g.drawString(tool.format(data.getExpHr(index)), x + 105, y + 210);
		g.drawString(tool.format(data.getExpToLevel(index)), x + 105, y + 230);

		bar.setLocation(15 + x, 260 + y);
		bar.setProgressInt(data.getPercentageToLv(index));
		bar.render(g);

		close.setLocation(x + 196, y + 8);
		close.render(g);
	}

}
