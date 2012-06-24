package com.item;

import com.util.StringUtil;

public enum ItemPriority {

	MINIMAL(0,
			"Only searches for loot when not in combat, and no monsters are available."), NORMAL(
			1, "Only searches for loot when not currently in combat."), HIGH(2,
			"Searches for the loot at any given time, no matter the situation.");

	private int index;
	private String description;

	private ItemPriority(int index, String description) {
		this.index = index;
		this.description = description;
	}

	public int getIndex() {
		return index;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return StringUtil.capatilize(name()) + " Priority - " + getIndex();
	}

}
