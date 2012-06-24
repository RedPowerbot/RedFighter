package com.loop;

import java.util.ArrayList;
import java.util.List;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;

import com.data.Configuration;
import com.item.RItem;

public class LootLoop extends ScriptLoop {

	/**
	 * There's no reason for the script to check for loot 30
	 * times or more every second. This could lead to bad
	 * looting after NPC death though.
	 */
	private static int CACHE_DELAY = 1000;
	
	private GroundItem[] itemArray = {};
	private RItem[] ritemArray = {};
	private long lastCacheTime = System.currentTimeMillis();
	
	public LootLoop(ActiveScript parent, Configuration con) {
		super(parent, con);
	}

	@Override
	public void run() {
		if (itemArray != null && itemArray.length > 0) {
			for (int i : getSortedIndexes()) {
				Tile loc = itemArray[i].getLocation();
				if (!loc.isOnScreen()) {
					Walking.walk(loc);
					waitOnDestination(3000);
					if (!loc.isOnScreen()) {
						log("Could not walk to:" + ritemArray[i].getName());
						continue;
					}
				}
				int stackSize = itemArray[i].getGroundItem().getStackSize();
				String name = itemArray[i].getGroundItem().getName();
				if (itemArray[i].interact("Pickup " + name)) {
					waitOnDestination(2000);
					waitForAction(2000);
					ritemArray[i].addToCount(stackSize);
				}
			}
		}
	}

	@Override
	public boolean validate() {
		if (System.currentTimeMillis() - lastCacheTime <= CACHE_DELAY) {
			return false;
		}
		lastCacheTime = System.currentTimeMillis();
		return updateItemArray();
	}
	
	/**
	 * Creates a pretty quick way of looting. Checking all possible paths would be better...
	 * @return
	 */
	public int[] getSortedIndexes() {
		if (itemArray != null && itemArray.length > 0) {
			List<Integer> sortedList = new ArrayList<>();
			Tile curTile = getMyPlayer().getLocation();
			int bestIdx = 0;
			double bestDist;
			double tempDist;
			while (sortedList.size() != itemArray.length) {
				bestDist = tempDist = -1;
				for (int i = 0; i < itemArray.length; i++) {
					if (sortedList.contains(i)) {
						continue;
					}
					tempDist = Calculations.distance(curTile, itemArray[i].getLocation());
					if (tempDist < bestDist) {
						bestDist = tempDist;
						bestIdx = i;
					}
				}
				sortedList.add(bestIdx);
				curTile = itemArray[bestIdx].getLocation();
			}
		}
		return new int[0];
	}
	
	public boolean updateItemArray() {
		int pri = getCurrentPriority();
		List<GroundItem> glist = new ArrayList<>();
		List<RItem> rlist = new ArrayList<>();
		Item tempItem;
		for (GroundItem gItem : GroundItems.getLoaded()) {
			for (RItem rItem : con.lootList) {
				if (rItem.getPriority() < pri) {
					continue;
				}
				tempItem = gItem.getGroundItem();
				if (rItem.getId() == tempItem.getId()) {
					glist.add(gItem);
					rlist.add(rItem);
				}
			}
		}
		itemArray = glist.toArray(new GroundItem[glist.size()]);
		ritemArray = rlist.toArray(new RItem[rlist.size()]);
		return itemArray.length > 0;
	}
	
	private int getCurrentPriority() {
		if (con.combatLoop.getNPC() == null) {
			return 0;
		}
		if (!getMyPlayer().isInCombat()) {
			return 1;
		}
		return 2;
	}

}
