package com.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.data.Configuration;
import com.item.RItem;
import com.paint.button.ButtonHandler;
import com.paint.skill.SkillData;
import com.paint.skill.SkillManager;

/**
 * 
 * Mostly generated using Enfilade's Easal Program
 * 
 */
public class RFPainterV1 {

	private final PaintTool tool = new PaintTool();
	private Configuration con;
	private ButtonHandler paintHandler;
	private SkillManager skillManager;
	private SkillData data;

	public RFPainterV1(Configuration con) {
		this.con = con;
		skillManager = new SkillManager(con);
		tool.setStartTime(con.startTime);
		paintHandler = new ButtonHandler(con);
	}

	public void render(Graphics render) {
		Graphics2D g = (Graphics2D) render;
		g.setRenderingHints(Design.antialiasing);
		if (!con.hidePaint) {
			paintFramework(g);
			paintScriptInfo(g);
			switch (con.paintTab) {
			case MAIN:
				paintMainTab(g);
				break;
			case SKILL:
				skillManager.render(g);
				break;
			case PROFIT:
				paintProfitTab(g);
				break;
			case CONTROL:
				break; // Controls are handled in PaintableManager
			}
		}
		paintHandler.render(g);
	}

	/**
	 * Needs Cleaning up
	 */
	private void paintFramework(Graphics2D g) {
		g.setColor(Design.black);
		g.fillRect(0, 339, 517, 118);
		g.fillRect(414, 339, 103, 118);
		g.fillRect(0, 322, 517, 16);

		g.setColor(Design.red);
		g.setStroke(Design.stroke2);
		g.drawRect(414, 339, 103, 118);
		g.drawRect(0, 339, 517, 118);
		g.drawRect(0, 322, 517, 166);
		g.drawRect(415, 339, 101, 29);
		g.drawRect(415, 369, 101, 29);
		g.drawRect(415, 399, 101, 29);
		g.drawRect(415, 429, 102, 27);
	}

	private void paintScriptInfo(Graphics2D g) {
		g.setFont(Design.font11);
		g.setColor(Design.red);
		g.drawString("Time :", 8, 335);
		g.drawString("Status :", 251, 334);
		g.setColor(Design.white);
		g.drawString(tool.formatTime(tool.getRunTime()), 42, 335);
		g.drawString(con.status, 293, 335);
	}

	private void paintMainTab(Graphics2D g) {
		g.setStroke(Design.stroke2);
		g.setColor(Design.white);
		g.drawRoundRect(15, 345, 180, 105, 16, 16);
		g.drawRoundRect(210, 345, 195, 105, 16, 16);
		g.setFont(Design.font20_3);
		g.setColor(Design.white);
		g.drawString("Legendary Fighter", 23, 381);
		g.setFont(Design.font18_3);
		g.drawString("By", 40, 410);
		g.setFont(Design.font20_3);
		g.setColor(Design.red);
		g.drawString("RedDevil~~", 79, 413);
		g.setColor(Design.redA);
		g.drawString("RedDevil~~", 72, 413);
		g.setFont(Design.font16);
		g.setColor(Design.red);
		g.drawString("Total Exp :", 225, 360);
		g.drawString("Total Profit :", 225, 390);
		g.drawString("Profit Hr :", 225, 405);
		g.drawString("Exp hr :", 225, 375);
		g.drawString("Next Skill to Level", 225, 420);
		g.setColor(Design.white);
		g.drawString(tool.format(data.getTotalExp()), 300, 360);
		g.drawString(tool.format(data.getTotalExpHr()), 270, 375);
		g.drawString(tool.format(con.profitManager.getProfit()), 302, 390);
		g.drawString(tool.format(con.profitManager.getProfitHr()), 308, 405);
	}

	private void paintProfitTab(Graphics2D g) {
		g.setStroke(Design.stroke2);
		g.setColor(Design.white);
		g.drawRoundRect(15, 350, 195, 100, 16, 16);
		g.drawRoundRect(225, 350, 180, 100, 16, 16);

		g.setFont(Design.font15);
		g.setColor(Design.red);
		g.drawString("Total Profit", 30, 372);
		g.drawString("Profit Hr", 31, 393);
		g.drawString("Times Looted", 30, 414);
		g.drawString("Charms Looted", 30, 438);

		g.setColor(Design.white);
		g.drawString(tool.format(con.profitManager.getProfit()), 120, 372);
		g.drawString(tool.format(con.profitManager.getProfitHr()), 120, 396);
		g.drawString(tool.format(con.profitManager.getTimesLooted()), 120, 414);
		g.drawString(tool.format(con.profitManager.getCharmsLooted()), 135, 438);

		g.drawLine(281, 351, 281, 450);
		g.drawLine(281, 400, 226, 401);

		g.setFont(Design.font12);
		ArrayList<RItem> list = con.profitManager.getLog();
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			RItem item = list.get(i);
			String title = item.getLogString();
			if (!title.equals(item.getName())) {
				g.drawString(title, 285, 365 + (20 * index));
				index++;
			}
		}
	}

}
