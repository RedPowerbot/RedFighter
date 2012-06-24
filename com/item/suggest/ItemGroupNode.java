package com.item.suggest;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import com.item.RItem;
import com.item.ItemGroup;
import com.item.ItemList;

public class ItemGroupNode extends ItemNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2233884127964330784L;
	private ItemGroup group;
	
	public ItemGroupNode(String title) {
		super(null);
		setAllowsChildren(true);
		setUserObject(title);
		group = new ItemGroup(new ItemList(), title);
	}
	
	public ItemGroupNode(ItemGroup group) {
		super(null);
		setAllowsChildren(true);
		setUserObject(group.getName());
		this.group = group;
	}
	
	public void add(RItem item) {
		ItemNode node = new ItemNode(item);
		group.list.add(item);
		super.add(node);
	}
	
	@Override
	public void add(MutableTreeNode node) {
		if (node instanceof ItemNode) {
			group.list.add(((ItemNode) node).getItem());
		}
		super.add(node);
	}
	
	@SuppressWarnings("unchecked")
	public ItemList getItemList(ItemList list) {
		Enumeration<TreeNode> e = children();
		while (e.hasMoreElements()) {
			TreeNode n = e.nextElement();
			switch (n.getClass().getSimpleName()) {
			case "ItemNode":
				list.add(((ItemNode) n).getItem());
				break;
			case "ItemGroupNode":
				((ItemGroupNode) n).getItemList(list);
			}
		}
		return list;
	}
	
	@Override
	public RItem getItem() {
		return group;
	}
	
}
