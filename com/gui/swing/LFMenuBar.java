package com.gui.swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.gui.settings.ComponentLoader;
import com.gui.settings.Profile;
import com.gui.settings.SettingsCache;

public class LFMenuBar extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8564123028821926421L;
	private SettingsCache cache;
	private JFrame gui;
	private JMenu recentMenu;

	public LFMenuBar(JFrame gui) {
		super();
		cache = new SettingsCache(gui);
		initiate();
		setMinimumSize(new Dimension(0, 15));
	}

	public LFMenuBar() {
		this(new JFrame());
	}

	public void rebuild() {
		removeAll();
		initiate();
		repaint();
	}

	private void initiate() {
		JMenu menu1 = new JMenu("Configuration");
		buildCongigMenu(menu1);
		JMenu menu2 = new JMenu("Shortcuts");
		JMenu menu3 = new JMenu("Sites");
		add(menu1);
		add(menu2);
		add(menu3);
	}

	private void buildCongigMenu(JMenu m) {
		JMenuItem i1 = new JMenuItem("Open Manager");
		i1.setActionCommand("Open Manager");
		i1.addActionListener(this);
		JMenuItem i2 = new JMenuItem("Save As");
		i2.setActionCommand("Save As");
		i2.addActionListener(this);
		m.add(i1);
		m.add(i2);
		m.add(createRecentMenu());
	}

	private void rebuildRecentMenu() {
		JMenu parentMenu = getMenu(0);
		parentMenu.remove(2);
		parentMenu.add(createRecentMenu());
	}

	private JMenu createRecentMenu() {
		recentMenu = new JMenu("Most Recent");
		if (cache.size() > 0) {
			for (Profile p : cache.loadMostRecent()) {
				JMenuItem item = new JMenuItem(p.getTitle());
				item.addActionListener(new ProfileAction(p));
				recentMenu.add(item);
			}
		}
		return recentMenu;
	}

	public class ProfileAction implements ActionListener {

		private Profile p;

		public ProfileAction(Profile p) {
			this.p = p;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			ComponentLoader.apply(p, gui);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Open Manager":
			new SettingManager(cache).setVisible(true);
			break;
		case "Save As":
			new CacheElementCreationDialog(cache).setVisible(true);
			break;
		}
		rebuildRecentMenu();
	}

}
