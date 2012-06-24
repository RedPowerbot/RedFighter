package com.util;

public class BasicArrayEx<E> extends ArrayEx<E> {

	@Override
	public Class<?> getColumnClass(int arg0) {
		return Object.class;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public String getColumnName(int arg0) {
		return "Object";
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return get(arg0).toString();
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {	}

	
	
}
