package com.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class PropertiesEx extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3847314115136249723L;
	private String name = "Properties";

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public String String(String key) {
		return getProperty(key);
	}

	public long Long(String key) {
		return Long.parseLong(getProperty(key));
	}

	public int Int(String key) {
		return Integer.parseInt(getProperty(key));
	}

	public boolean Boolean(String key) {
		return Boolean.parseBoolean(getProperty(key));
	}

	public double Double(String key) {
		return Double.parseDouble(getProperty(key));
	}

	public void list(DefaultTableModel model) {
		Enumeration<Object> keys = keys();
		Enumeration<Object> values = elements();
		while (keys.hasMoreElements() && values.hasMoreElements()) {
			model.addRow(new Object[] { keys.nextElement(),
					values.nextElement() });
		}
	}

	public void itemPropertiesList(DefaultTableModel model) {
		HashMap<String, Vector<Object>> map = new HashMap<>();
		Enumeration<Object> keys = keys();
		Enumeration<Object> values = elements();
		while (keys.hasMoreElements() && values.hasMoreElements()) {
			String key = keys.nextElement().toString();
			String elem = key.substring(0, key.indexOf("."));
			if (!map.containsKey(elem)) {
				System.out.println("Adding vector:" + elem);
				map.put(elem, new Vector<>());
			}
			map.get(elem).add(values.nextElement());
		}
		for (Vector<Object> list : map.values()) {
			model.addRow(list);
		}
	}

}
