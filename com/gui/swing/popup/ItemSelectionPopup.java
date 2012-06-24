package com.gui.swing.popup;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.item.ItemList;
import com.item.suggest.ItemSelectionDialog;

public class ItemSelectionPopup extends DefaultPopupMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1037203566699679766L;
	private String[] selectedNames;
	private ItemList targetItemList;
	private JFrame parentFrame;

	public ItemSelectionPopup(String[] selectedNames, ItemList targetItemList,
			JFrame parentFrame) {
		super();
		this.selectedNames = selectedNames;
		this.targetItemList = targetItemList;
		this.parentFrame = parentFrame;
		initialize();
	}

	@Override
	public void setLocation(Point p) {
		setLocation(p.x - 10, p.y - 10);
	}

	private void initialize() {
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				setVisible(false);
			}

		});

		JMenuItem addFirst = new JMenuItem("Add First Selected");
		JMenuItem addAll = new JMenuItem("Add All Selected");
		// JMenuItem deselectAll = new JMenuItem("Deselect All");
		JMenuItem cancel = new JMenuItem("Cancel");
		addFirst.addActionListener(this);
		addAll.addActionListener(this);
		// deselectAll.addActionListener(this);
		cancel.addActionListener(this);
		add(addFirst);
		add(addAll);
		// add(deselectAll);
		add(cancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem src = (JMenuItem) e.getSource();
		switch (src.getText()) {
		case "Add First Selected":
			if (selectedNames.length > 0) {
				targetItemList
						.addAll(ItemSelectionDialog.showItemSelectionDialog(
								parentFrame, selectedNames[0]));
			}
			setVisible(false);
			return;
		case "Add All Selected":
			if (selectedNames.length > 0) {
				for (String name : selectedNames) {
					targetItemList.addAll(ItemSelectionDialog
							.showItemSelectionDialog(parentFrame, name));
				}
			}
			setVisible(false);
			return;
		}
	}

}
