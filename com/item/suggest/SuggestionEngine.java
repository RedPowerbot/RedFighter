package com.item.suggest;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.data.FConstants;
import com.item.ItemGroup;
import com.item.database.ItemDatabase;

public class SuggestionEngine extends DefaultTreeModel {
	
	private DefaultMutableTreeNode root;
	
	public SuggestionEngine(DefaultMutableTreeNode root) {
		super(root);
		this.root = root;
		create();
	}
	
	private void create() {
		new Thread(new Runnable() {
			public void run() {
				ItemDatabase.loadData();
				addCharms();
				addClues();
				System.out.println("Finished Creating SuggestionEngine Tree.");
			}
		}).start();
	}

	private void addCharms() {
		ItemGroupNode groupNode = new ItemGroupNode("Charms");
		for (int charmID : FConstants.CHARM_IDS) {
			groupNode.add(ItemDatabase.get(charmID));
		}
		root.add(groupNode);
	}
	
	private void addClues() {
		ItemGroupNode topNode = new ItemGroupNode("Clues");
		topNode.add(new ItemGroupNode(ItemGroup.getGroup("Easy Cues", FConstants.CLUE_SCROLL_EASY_IDS)));
		topNode.add(new ItemGroupNode(ItemGroup.getGroup("Medium Clues", FConstants.CLUE_SCROLL_MEDIUM_IDS)));
		topNode.add(new ItemGroupNode(ItemGroup.getGroup("Hard Clues", FConstants.CLUE_SCROLL_HARD_IDS)));
		topNode.add(new ItemGroupNode(ItemGroup.getGroup("Elite Clues", FConstants.CLUE_SCROLL_ELITE_IDS)));
		root.add(topNode);
	}
	
}
