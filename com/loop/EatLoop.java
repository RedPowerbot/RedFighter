package com.loop;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.Item;

import com.data.Configuration;
import com.data.FConstants;

public class EatLoop extends ScriptLoop {

	private int nextLP = -1;
	private Item foodItem = null;
	
	public EatLoop(ActiveScript parent, Configuration con) {
		super(parent, con);
	}

	@Override
	public boolean validate() {
		return (con.eating_enable || con.b2p_enable)
			&& needToEat();
	}
	
	@Override
	public void run() {
		foodItem = getFoodItem();
		if (foodItem == null && con.b2p_enable) {
			Item b2pTab = getItem(FConstants.B2P_TAB_ID);
			Item bone = getItem(FConstants.B2P_BONES_IDS);
			if (b2pTab != null && bone != null) {
				if (!(b2pTab.getWidgetChild().interact("Break")
					&& waitForAction(3000))) {
					log("Could not break B2P tab.");
				}
				return;
			}
		}
		if (foodItem != null) {
			foodItem.getWidgetChild().interact("Eat");
			waitForAction(3000);
			randomizeNextLP();
		}
	}
	
	private Item getFoodItem() {
		Item[] invItems = Inventory.getItems();
		String[] tempActs;
		for (Item item : invItems) {
			tempActs = item.getWidgetChild().getActions();
			for (String tempAct : tempActs) {
				if (tempAct.contains("Eat")) {
					return item;
				}
			}
		}
		return null;
	}
	
	private boolean needToEat() {
		return getNextLP() <= getMyPlayer().getHpPercent();
	}
	
	private int getLifePoints() {
		return 0;
	}

	private int getNextLP() {
		if (nextLP == -1) {
			randomizeNextLP();
		}
		return nextLP;
	}
	
	private void randomizeNextLP() {
		nextLP = Random.nextInt(con.eating_ranges[0], con.eating_ranges[1]);
	}
}
