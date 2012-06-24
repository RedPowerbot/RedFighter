package com.item.suggest;

import javax.swing.tree.DefaultMutableTreeNode;

import com.item.RItem;

public class ItemNode extends DefaultMutableTreeNode {

	private RItem item;
	
	public ItemNode(RItem item) {
		super(null, false);
		if (item == null) {
			item = new RItem();
		}
		this.item = item;
		setUserObject(item.getName());
	}
	
	public RItem getItem() {
		return item;
	}
	
}
