package com.table;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import com.table.data.PropertyModel;
import com.util.PropertiesEx;

public class JPropertyTree extends JTree {

	
	
	
	
	// --------- Model Creation -----------//
	
	private TreeModel createTreeModel(PropertiesEx[] properties) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		DefaultTreeModel model = new DefaultTreeModel(root);
		for (PropertiesEx prop : properties) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(prop.getName());
			for (Property p : PropertiesEx) {
				
			}
		}
	}
	
	private
	
}
