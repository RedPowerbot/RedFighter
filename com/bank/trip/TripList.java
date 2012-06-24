package com.bank.trip;

import com.util.ArrayEx;

public class TripList extends ArrayEx<TripStep> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1862627953708077941L;

	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public String getColumnName(int arg0) {
		return "Step Type";
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return get(arg0).getClass().getSimpleName();
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
	}

}
