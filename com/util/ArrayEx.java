package com.util;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public abstract class ArrayEx<E> extends ArrayList<E> implements ListModel<E>,
		TableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205228365725165248L;
	private ArrayList<ListDataListener> listDataListeners = new ArrayList<>();
	private ArrayList<TableModelListener> tableModelListeners = new ArrayList<>();
	protected boolean allowDuplicates = true;

	public boolean fireEvents() {
		for (ListDataListener l : listDataListeners) {
			l.contentsChanged(new ListDataEvent(this,
					ListDataEvent.CONTENTS_CHANGED, 0, size()));
		}
		for (TableModelListener l : tableModelListeners) {
			l.tableChanged(new TableModelEvent(this));
		}
		return true;
	}

	@Override
	public boolean add(E e) {
		if (!allowDuplicates && contains(e)) {
			return false;
		}
		return super.add(e) && fireEvents();
	}

	public boolean addAll(E[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		boolean b = true;
		for (E e : array) {
			if (!allowDuplicates && contains(e)) {
				continue;
			}
			b = super.add(e) && b;
		}
		return fireEvents() && b;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean b = true;
		for (E e : c) {
			if (!allowDuplicates && contains(e)) {
				continue;
			}
			b = super.add(e) && b;
		}
		return fireEvents() && b;
	}

	@Override
	public boolean remove(Object e) {
		return super.remove(e) && fireEvents();
	}

	@Override
	public E remove(int index) {
		E e = super.remove(index);
		fireEvents();
		return e;
	}

	@Override
	public E getElementAt(int index) {
		return get(index);
	}

	@Override
	public int getSize() {
		return size();
	}

	@Override
	public int getRowCount() {
		return getSize();
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listDataListeners.add(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listDataListeners.remove(l);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		tableModelListeners.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		tableModelListeners.remove(l);
	}

}
