package com.table;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;

public class EditingTreeCellRenderer extends DefaultTreeCellEditor {

	public EditingTreeCellRenderer(JTree tree, DefaultTreeCellRenderer renderer) {
		super(tree, renderer);
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row) {
		DefaultTreeCellEditor editor= super.getTreeCellEditorComponent(tree, value, isSelected, expanded,
				leaf, row);
	}
	
}
