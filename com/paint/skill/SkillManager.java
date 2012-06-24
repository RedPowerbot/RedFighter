package com.paint.skill;

import java.awt.Graphics2D;
import java.awt.Point;

import com.data.Configuration;
import com.paint.button.ProgressBar;

public class SkillManager {

	public Configuration con;
	public final ProgressBar[] progressBars = createProgressBars();
	public final ProgressBar nextProgressBar = new ProgressBar(224, 423, 172,
			23);
	private SkillData data;
	private int enabledIndex = -1;
	private int[] skillIndexs = { -1, -1, -1, -1, -1, -1 };

	private ProgressBar[] createProgressBars() {
		return new ProgressBar[] { new ProgressBar(15, 350, 195, 20),
				new ProgressBar(15, 390, 195, 20),
				new ProgressBar(15, 430, 195, 20),
				new ProgressBar(240, 350, 165, 20),
				new ProgressBar(240, 390, 165, 20),
				new ProgressBar(240, 430, 165, 20), };
	}

	public SkillManager(Configuration con) {
		this.con = con;
	}

	public void onMouseClick(Point p) {
		if (con.skillDialog != null && con.skillDialog.isVisible()) {
			con.skillDialog.onClick(p);
			return;
		}
		for (int i = 0; i < 6 && i < enabledIndex; i++) {
			if (progressBars[i].contains(p)) {
				con.skillDialog = new SkillDialog(p, data, skillIndexs[i]);
				con.skillDialog.setVisible(true);
			}
		}
	}

	public void render(Graphics2D render) {
		int barIndex = -1;
		for (int i = 0; i < SkillData.SKILL_INDEXS.length && barIndex < 6; i++) {
			int index = SkillData.SKILL_INDEXS[i];
			if (data.getGainedExp(index) > 0) {
				barIndex++;
				skillIndexs[barIndex] = index;
				ProgressBar bar = progressBars[barIndex];
				bar.setText(data.getName(SkillData.SKILL_INDEXS[i]));
				bar.setProgressInt(data.getPercentageToLv(index));
				bar.render(render);
			}
		}
		enabledIndex = barIndex;
	}

}
