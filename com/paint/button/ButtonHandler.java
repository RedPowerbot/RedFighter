package com.paint.button;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.data.Configuration;
import com.gui.RFGui;
import com.item.profit.ProfitSort;
import com.paint.Design;
import com.paint.Paintable;
import com.paint.data.PaintTab;

public class ButtonHandler extends ArrayList<Paintable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3835853271601750385L;
	private Configuration con;

	public ButtonHandler(Configuration con) {
		this.con = con;
		buildButtons();
	}

	public void render(Graphics2D render) {
		for (Paintable p : this) {
			PaintTab t = p.getRenderTab();
			if (t.equals(PaintTab.ALL_TIME)) {
				p.render(render);
			} else {
				if (con.hidePaint || t.equals(PaintTab.HIDDEN)) {
					if (con.hidePaint && t.equals(PaintTab.HIDDEN)) {
						p.render(render);
					}
				} else {
					if (t.equals(PaintTab.ANY)) {
						p.render(render);
					} else {
						if (t.equals(con.paintTab)) {
							p.render(render);
						}
					}
				}
			}
		}
	}

	public void mouseMoved(Point p) {
		scan(p, "Move");
	}

	public void mouseClicked(Point p) {
		scan(p, "Click");
	}

	private void scan(Point p, String actionCommand) {
		for (Paintable b : this) {
			if (b.contains(p)) {
				b.getListener().actionPerformed(
						new ActionEvent(b, 0, actionCommand));
			}
		}
	}

	private void buildButtons() {
		add(getShowHideButton());
		addAll(getTabButtons());
		addAll(getProfitButtons());
		add(getOpenGUIButton());
	}

	private Button getShowHideButton() {
		Button b = new Button(414, 458, 103, 21);
		b.setRenderTab(PaintTab.ALL_TIME);
		b.setArc(0);
		b.setBackground(Design.black);
		b.setForeground(Design.white);
		b.setFont(Design.font11);
		b.setBorder(Design.red);
		b.setText("Click to Hide");
		b.setListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().startsWith("Click")) {
					con.hidePaint = !con.hidePaint;
					Button b = (Button) e.getSource();
					if (con.hidePaint) {
						b.setText("Click to Show");
					} else {
						b.setText("Click to Hide");
					}
				}
			}
		});
		return b;
	}

	private ArrayList<Button> getTabButtons() {
		ArrayList<Button> list = new ArrayList<>();
		Button b = new Button(415, 339, 101, 29);
		b.setArc(0);
		b.setFill(false);
		b.setFont(Design.font16_3);
		b.setBorder(Design.red);
		Button b1 = b.clone();
		b1.y = b1.y + 30;
		Button b2 = b.clone();
		b2.y = b1.y + 30;
		Button b3 = b.clone();
		b3.y = b2.y + 30;
		b3.height = 27;
		b.setText("Main");
		b1.setText("Skill");
		b2.setText("Profit");
		b3.setText("Controls");
		b.setListener(new TabListener(PaintTab.MAIN));
		b1.setListener(new TabListener(PaintTab.SKILL));
		b2.setListener(new TabListener(PaintTab.PROFIT));
		b3.setListener(new TabListener(PaintTab.CONTROL));
		list.add(b);
		list.add(b1);
		list.add(b2);
		list.add(b3);
		return list;
	}

	public ArrayList<Button> getProfitButtons() {
		ArrayList<Button> list = new ArrayList<>();
		Button b = new Button(225, 351, 56, 51);
		b.setOutline(false);
		b.setFill(false);
		b.setRenderTab(PaintTab.PROFIT);
		Button b1 = b.clone();
		b.setText("Count");
		b1.setText("Value");
		b.setListener(new ProfitButListener(ProfitSort.COUNT));
		b1.setListener(new ProfitButListener(ProfitSort.PROFIT));
		list.add(b);
		list.add(b1);
		return list;
	}

	private Button getOpenGUIButton() {
		Button b = new Button(15, 350, 180, 25);
		b.setArc(16);
		b.setRenderTab(PaintTab.CONTROL);
		b.setFill(false);
		b.setBorder(Design.white);
		b.setText("Reopen Gui");
		b.setListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						System.out
								.println("Opening Up Gui. Please dont reclick!!");
						RFGui gui = new RFGui(con);
						gui.setVisible(true);
					}

				});
			}

		});
		return b;
	}

	private class ProfitButListener implements ActionListener {

		private ProfitSort sortMethod;

		public ProfitButListener(ProfitSort sortMethod) {
			this.sortMethod = sortMethod;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().startsWith("Click")) {
				con.profitManager.sortMethod = sortMethod;
			}
		}

	}

	private class TabListener implements ActionListener {

		private PaintTab tab;

		public TabListener(PaintTab tab) {
			this.tab = tab;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().startsWith("Click")) {
				con.paintTab = tab;
			}
		}

	}

}
