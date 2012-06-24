package com.gui.swing.popup;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import com.data.Configuration;
import com.monster.Monster;
import com.monster.wiki.MonsterWikiPanel;
import com.monster.wiki.MonsterWikiParser;
import com.util.DialogUtil;

public class MonsterSelectionPopup extends DefaultPopupMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1152961357800233086L;
	private JTabbedPane targetGroup;
	private List<Monster> monsterList;
	private Configuration con;

	public MonsterSelectionPopup(JTabbedPane targetGroup,
			List<Monster> monsterList, Configuration con) {
		super();
		this.targetGroup = targetGroup;
		this.monsterList = monsterList;
		this.con = con;
		initialize();
	}

	@Override
	public void setLocation(Point p) {
		setLocation(p.x - 5, p.y - 5);
	}

	private void initialize() {
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				setVisible(false);
			}

		});
		JMenuItem lookup = new JMenuItem("Look up Selected Monster");
		lookup.setActionCommand("lookup");
		lookup.addActionListener(this);
		JMenuItem lookups = new JMenuItem("Look up Selected Monsters");
		lookups.setActionCommand("lookupall");
		lookups.addActionListener(this);
		JMenuItem cancel = new JMenuItem("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		add(lookup);
		add(lookups);
		add(cancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action Performmed:" + e.getActionCommand());
		switch (e.getActionCommand()) {
		case "lookup":
			add(monsterList.get(0));
			setVisible(false);
			break;
		case "lookupall":
			add(monsterList.toArray(new Monster[monsterList.size()]));
			setVisible(false);
			break;
		case "cancel":
			setVisible(false);
			break;
		}
	}

	private void add(final Monster... monsters) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (Monster m : monsters) {
					MonsterWikiParser source = new MonsterWikiParser(
							m.getName());
					MonsterWikiPanel panel = new MonsterWikiPanel(m.getName(),
							source, con);
					targetGroup.addTab(m.getName(), panel);
					DialogUtil.yell("Monster Infomation Has Been Loaded For :"
							+ m.getName(), targetGroup.getParent());
				}
			}

		}).start();
	}

}
