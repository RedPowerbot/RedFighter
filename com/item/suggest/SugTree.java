package com.item.suggest;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.item.ItemList;

public class SugTree extends JTree {

	public static void main(String[] args) {
		new SugTree();
	}
	
	public SugTree() {
		super(new SuggestionEngine(new DefaultMutableTreeNode()));
		setCellRenderer(new SugCellRenderer());
		getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
		setRootVisible(false);
	}
	
	public ItemList getSelectedItems() {
		ItemList list = new ItemList();
		for (TreePath path : getSelectionPaths()) {
			Object o = path.getLastPathComponent();
			if (o instanceof ItemNode) {
				list.add(((ItemNode) o).getItem());
				continue;
			}
		}
		return list;
	}

}
