package com.gui.swing.popup;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import com.item.ItemList;

public class LootTablePopup extends DefaultPopupMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3893993046193785630L;
	private ItemList list;
	private JTable table;
	
	public LootTablePopup(ItemList list, JTable table) {
		super();
		this.list = list;
		this.table = table;
		init();
	}
	
	@Override
	public void setLocation(Point p) {
		setLocation(p.x - 10, p.y - 10);
	}
	
	private void init() {
		JMenuItem one = new JMenuItem("Remove Selected");
		JMenuItem all = new JMenuItem("Remove All Selected");
		one.addActionListener(this);
		all.addActionListener(this);
		one.setActionCommand("one");
		all.setActionCommand("all");
		add(one);
		add(all);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (table.getSelectedRow() == -1) {
			return;
		}
		switch (e.getActionCommand()) {
		case "one":
			list.remove(table.getSelectedRow());
			break;
		case "all":
			int shift = 0;
			for (int i : table.getSelectedRows()) {
				list.remove(i - shift);
				shift++;
			}
			break;
		}
		setVisible(false);
	}
	
}
