package com.monster;

import com.util.ArrayEx;
import com.util.Documentable;
import com.util.StringUtil;

public class MonsterList extends ArrayEx<Monster> implements Documentable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6899185819124219303L;
	private static String[] COLUMN_INDENTIFIERS = { "Name", "ID", "Level" };

	@Override
	public Class<?> getColumnClass(int col) {
		switch (col) {
		case 0:
			return String.class;
		case 1:
		case 2:
			return Integer.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int col) {
		return COLUMN_INDENTIFIERS[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Monster m = get(row);
		switch (col) {
		case 0:
			return m.getName();
		case 1:
			return m.getId();
		case 2:
			return m.getLevel();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
	}

	@Override
	public String toDataString() {
		StringBuilder b = new StringBuilder();
		b.append("MonsterList[");
		b.append(size());
		b.append("]");
		b.append(StringUtil.PROPERTY_SEPERATOR);
		if (size() > 0) {
			for (Monster i : this) {
				b.append(i.toDataString());
				b.append(",");
			}
			b.deleteCharAt(b.length() - 1);
		}
		return b.toString();
	}

	@Override
	public void initFromDataString(String value) {
		if (value == null || value.equals("")
				|| !value.contains(StringUtil.PROPERTY_SEPERATOR)) {
			return;
		}
		int dataLength = value.indexOf(StringUtil.PROPERTY_SEPERATOR)
				+ StringUtil.PROPERTY_SEPERATOR.length() - value.length();
		if (dataLength <= 0) {
			return;
		}
		MonsterList list = new MonsterList();
		String data = value.split(StringUtil.PROPERTY_SEPERATOR)[1];
		if (value.contains(",")) {
			for (String s : data.split(",")) {
				if (!s.equals("")) {
					Monster m = new Monster();
					m.initFromDataString(s);
					list.add(m);
				}
			}
		} else {
			Monster m = new Monster();
			m.initFromDataString(data);
			add(m);
			return;
		}
		addAll(list);
	}

}
