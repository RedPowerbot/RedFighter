package com.item;

import java.awt.Component;
import java.io.File;

import com.io.Cache;
import com.io.FileSystem;
import com.util.ArrayEx;
import com.util.StringUtil;

public class ItemSaveCache extends ArrayEx<File> implements Cache<File> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1876316572300188881L;
	private Component component;
	private ItemList list;

	public ItemSaveCache() {
		this(null, new ItemList());
	}

	public ItemSaveCache(Component component, ItemList list) {
		super();
		this.component = component;
		this.list = list;
		loadSettings();
	}

	@Override
	public boolean loadSettings() {
		clear();
		addAll(new File(FileSystem.getItemSavesDirectory()).listFiles());
		return true;
	}

	@Override
	public String acceptNewName(String name) {
		if (name.length() < 5) {
			return "Name must contain at least 5 characters";
		}
		for (File f : this) {
			if (name.toLowerCase().equals(
					StringUtil.formatFileName(f).toLowerCase())) {
				return "This name is already being used.";
			}
		}
		return "";
	}

	@Override
	public boolean createNewElement(String newName) {
		if (acceptNewName(newName).equals("")) {
			File f = new File(FileSystem.getItemSavesDirectory()
					+ File.separator + newName + ".ini");
			ItemIO.writeItemFile(f.getPath(),
					list.toArray(new RItem[list.size()]));
			add(f);
			return true;
		}
		return false;
	}

	public void sort(String columnIdentifier) {

	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return "Item List File";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Object.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return get(rowIndex).getName();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	@Override
	public boolean saveSettings() {
		return true;
	}

	@Override
	public Component getGui() {
		return component;
	}

	@Override
	public void delete(int index) {
	}

}
