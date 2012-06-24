package com.gui.settings;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import com.io.Cache;
import com.io.FileSystem;
import com.util.ArrayEx;
import com.util.DialogUtil;

public class SettingsCache extends ArrayEx<Profile> implements Cache<Profile> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8720211791099074792L;
	private static final String[] COLUMN_INDEDNTIFIERS = { "Title",
			"Date Made", "Last Date Used" };
	private static final DateFormat FORMAT = new SimpleDateFormat(
			"h:mm a EEE, 'on' MMM d, ''yy");

	private JFrame gui;

	public SettingsCache() {
		this(null);
	}

	public SettingsCache(JFrame gui) {
		super();
		this.gui = gui;
		loadSettings();
	}

	@Override
	public boolean loadSettings() {
		clear();
		File[] fileArray = new File(FileSystem.getConfigurationDirectory())
				.listFiles();
		FileInputStream in;
		if (fileArray != null && fileArray.length > 0) {
			for (File f : fileArray) {
				String name = f.getName();
				if (name.contains(".")) {
					name = name.substring(0, name.indexOf("."));
				}
				Profile p = new Profile(name);
				try {
					in = new FileInputStream(f);
					p.load(in);
					in.close();
					p.setDateMade(Long.parseLong(p.getProperty("###DateMade")));
					p.setDateUsed(Long.parseLong(p.getProperty("###DateUsed")));
					p.remove("###DateMade");
					p.remove("###DateUsed");
					add(p);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Couldnt load profile:" + f.getPath());
				}
			}
		} else {
			System.out.println("No settings files were found.");
		}
		return true;
	}

	@Override
	public Component getGui() {
		return gui;
	}

	/**
	 * Returns "" if is acceptable, otherwise returns why its not acceptable.
	 */
	@Override
	public String acceptNewName(String name) {
		if (name == null) {
			return "Name Equals Null";
		}
		if (name.length() < 5) {
			return "Name must be 5 char's long";
		}
		for (Profile p : this) {
			if (p.getName().toLowerCase().equals(name.toLowerCase())) {
				return "Cache Already Contains This Name";
			}
		}
		return "";
	}

	@Override
	public boolean createNewElement(String name) {
		try {
			if (acceptNewName(name).equals("")) {
				Profile p = new Profile(name);
				ComponentReader.store(p, gui);
				add(p);
				saveSettings();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void sortByName() {
		boolean swapped = true;
		int j = 0;
		Profile tmp;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < size() - j; i++) {
				if (get(i).getTitle().compareTo(get(i + 1).getTitle()) > 0) {
					tmp = get(i);
					set(i, get(i + 1));
					set(i + 1, tmp);
					swapped = true;
				}
			}
		}
		fireEvents();
	}

	public void sortByDateMade() {
		boolean swapped = true;
		int j = 0;
		Profile tmp;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < size() - j; i++) {
				if (get(i).getDateMade() > get(i + 1).getDateMade()) {
					tmp = get(i);
					set(i, get(i + 1));
					set(i + 1, tmp);
					swapped = true;
				}
			}
		}
		fireEvents();
	}

	public void sortByDateUsed() {
		boolean swapped = true;
		int j = 0;
		Profile tmp;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < size() - j; i++) {
				if (get(i).getDateUsed() > get(i + 1).getDateUsed()) {
					tmp = get(i);
					set(i, get(i + 1));
					set(i + 1, tmp);
					swapped = true;
				}
			}
		}
		fireEvents();
	}

	@Override
	public void delete(int i) {
		if (i < 0 || i > size() - 1) {
			return;
		}
		File f = new File(FileSystem.getConfigurationDirectory()
				+ File.separator + get(i).getTitle() + ".ini");
		if (f.exists()) {
			f.delete();
		}
		remove(i);
		fireEvents();
		saveSettings();
	}

	@Override
	public boolean saveSettings() {
		String dir = FileSystem.getConfigurationDirectory();
		for (Profile p : this) {
			p.setProperty("###DateMade", Long.toString(p.getDateMade()));
			p.setProperty("###DateUsed", Long.toString(p.getDateUsed()));
			try {
				File f = new File(dir + File.separator + p.getTitle() + ".ini");
				if (!f.exists()) {
					f.createNewFile();
				}
				OutputStream bw;
				bw = new FileOutputStream(f);
				p.store(bw, null);
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
				DialogUtil.yell("Could not save this profile :" + p.getTitle(),
						gui);
				continue;
			}
		}
		return true;
	}

	public Profile[] loadMostRecent() {
		if (size() == 0) {
			return new Profile[0];
		}
		sortByDateUsed();
		Profile[] array = new Profile[size() <= 5 ? size() : 5]; // Max size of
																	// 5
		for (int i = 0; i < array.length; i++) {
			array[i] = get(i);
		}
		return array;
	}

	public void init(int index) {
		Profile p = get(index);
		if (p == null) {
			return;
		}
		System.out.println("Selected Profile Size = " + p.size());
		p.use();
		ComponentLoader.apply(p, gui);
	}

	@Override
	public Class<?> getColumnClass(int col) {
		return Object.class;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int col) {
		return COLUMN_INDEDNTIFIERS[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Profile p = get(row);
		switch (col) {
		case 0:
			return p.getTitle();
		case 1:
			return FORMAT.format(new Date(p.getDateMade()));
		case 2:
			return FORMAT.format(new Date(p.getDateUsed()));
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public void setValueAt(Object o, int row, int col) {
	}

}
