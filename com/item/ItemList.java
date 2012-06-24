package com.item;

import com.util.ArrayEx;
import com.util.Documentable;
import com.util.StringUtil;

public class ItemList extends ArrayEx<RItem> implements Documentable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7956483391771720336L;
	private static String[] NORMAL_INDENTIFIERS = { "Name", "ID", "Stackable",
			"Noted" };
	private static String[] SELECTION_INDENTIFIERS = { "Name", "ID",
			"Stackable", "Noted", "Priority" };
	private static String[] PRICE_INDENTIFIERS = { "Name", "Price", "Amount",
			"Total Price" };

	private ItemListMode mode = ItemListMode.NORMAL;
	private boolean editable = true;

	public ItemList() {
		allowDuplicates = false;
	}
	
	public void expand() {
		for (RItem i : this) {
			if (i instanceof ItemGroup) {
				addAll(((ItemGroup) i).list);
				remove(i);
			}
		}
	}

	public void setMode(ItemListMode mode) {
		this.mode = mode;
		fireEvents();
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		fireEvents();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (mode) {
		case SELECTION:
			if (columnIndex == 4) {
				return Integer.class;
			}
		case NORMAL:
			switch (columnIndex) {
			case 0:
				return String.class;
			case 1:
				return Integer.class;
			case 2:
			case 3:
				return Boolean.class;
			}
			break;
		case PRICE:
			switch (columnIndex) {
			case 0:
				return String.class;
			case 1:
			case 2:
			case 3:
				return Integer.class;
			}
			break;
		}
		return Object.class;
	}

	@Override
	public int getColumnCount() {
		switch (mode) {
		case NORMAL:
		case PRICE:
			return 4;
		case SELECTION:
			return 5;
		}
		return 0;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (mode) {
		case NORMAL:
			return NORMAL_INDENTIFIERS[columnIndex];
		case SELECTION:
			return SELECTION_INDENTIFIERS[columnIndex];
		case PRICE:
			return PRICE_INDENTIFIERS[columnIndex];
		}
		return null;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RItem i = get(rowIndex);
		switch (mode) {
		case SELECTION:
			if (columnIndex == 4) {
				return i.getPriority();
			}
		case NORMAL:
			switch (columnIndex) {
			case 0:
				return i.getName();
			case 1:
				return i.getId();
			case 2:
				return i.isStackable();
			case 3:
				return i.isNoted();
			}
			break;
		case PRICE:
			switch (columnIndex) {
			case 0:
				return i.getName();
			case 1:
				if (i.getPrice() < 0) {
					return 0;
				}
				return i.getPrice();
			case 2:
				return i.getCount();
			case 3:
				if (i.getPrice() < 0) {
					return 0;
				}
				return i.getPrice() * i.getCount();
			}
			break;
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (mode) {
		case SELECTION:
		case NORMAL:
			return editable;
		case PRICE:
			;
			return false;
		}
		return false;
	}

	@Override
	public void setValueAt(Object o, int rowIndex, int columnIndex) {
		RItem i = get(rowIndex);
		switch (mode) {
		case SELECTION:
			if (columnIndex == 4) {
				i.setPriority((int) o);
				return;
			}
		case NORMAL:
			switch (columnIndex) {
			case 0:
				i.setName((String) o);
				break;
			case 1:
				i.setId((int) o);
				break;
			case 2:
				i.setStackable((boolean) o);
				break;
			case 3:
				i.setNoted((boolean) o);
				break;
			}
			break;
		case PRICE:
			return;
		}
	}

	@Override
	public String toDataString() {
		StringBuilder b = new StringBuilder();
		b.append("ItemList[");
		b.append(size());
		b.append("]:=");
		if (size() > 0) {
			for (RItem i : this) {
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
		ItemList list = new ItemList();
		String data = value.split(StringUtil.PROPERTY_SEPERATOR)[1];
		if (value.contains(",")) {
			for (String s : data.split(",")) {
				if (!s.equals("")) {
					RItem m = new RItem();
					m.initFromDataString(s);
					list.add(m);
				}
			}
		} else {
			RItem m = new RItem();
			m.initFromDataString(data);
			add(m);
			return;
		}
		addAll(list);
	}

}
