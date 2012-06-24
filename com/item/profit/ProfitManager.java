package com.item.profit;

import java.util.ArrayList;

import com.item.RItem;
import com.item.ItemList;
import com.util.SortUtil;

public class ProfitManager {

	public ProfitSort sortMethod = ProfitSort.COUNT;

	private ItemList itemList = new ItemList();
	private int profit = 0;
	private int charmsLooted = 0;
	private int timesLooted = 0;

	public ProfitManager(ItemList itemList) {
		this.itemList = itemList;
	}

	public int getProfit() {
		return profit;
	}

	public double getProfitHr() {
		return 0;
	}

	public int getCharmsLooted() {
		return charmsLooted;
	}

	public int getTimesLooted() {
		return timesLooted;
	}

	public ArrayList<RItem> getLog() {
		int[] indxs = getSortedIndxs();
		ArrayList<RItem> list = new ArrayList<>();
		for (int i = 0; i < indxs.length && i < 5; i++) {
			list.add(itemList.get(i));
		}
		return list;
	}

	private int[] getSortedIndxs() {
		int[] indxs = SortUtil.createOrderedArray(itemList.size());
		int i, j, tmp, n = itemList.size();
		switch (sortMethod) {
		case COUNT:
			for (i = 0; i < n; i++) {
				for (j = 1; j < (n - i); j++) {
					if (itemList.get(j).getCount() > itemList.get(j - 1)
							.getCount()) {
						tmp = indxs[j - 1];
						indxs[j - 1] = indxs[j];
						indxs[j] = tmp;
					}
				}
			}
			break;
		case PROFIT:
			RItem itemA,
			itemB;
			for (i = 0; i < n; i++) {
				for (j = 1; j < (n - i); j++) {
					itemA = itemList.get(j);
					itemB = itemList.get(j - 1);
					if (itemA.getCount() * itemA.getPrice() > itemB.getCount()
							* itemB.getPrice()) {
						tmp = indxs[j - 1];
						indxs[j - 1] = indxs[j];
						indxs[j] = tmp;
					}
				}
			}
			break;
		}
		return indxs;
	}

	public void loadPrices() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (RItem item : itemList) {
					// TODO Calc price.
				}
			}
		}).start();
	}

	public void addLoot(RItem item, int stackAmount) {
		if (item.getPrice() > 0) {
			profit += item.getPrice() * stackAmount;
		}
		timesLooted++;
		if (item.getName().endsWith("charm")) {
			charmsLooted += stackAmount;
		}
	}

	public void reloadProfit() {
		profit = 0;
		for (RItem item : itemList) {
			if (item.getPrice() > 0) {
				profit += item.getPrice() * item.getCount();
			}
		}
	}

}
