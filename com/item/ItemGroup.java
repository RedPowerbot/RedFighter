package com.item;

import com.item.database.ItemDatabase;

/**
 * This class helps clean up the Item displays of large quanitites of the same
 * items.
 * 
 * @author Cody
 * 
 */
public class ItemGroup extends RItem {

	public static ItemGroup getGroup(String name, int... idArray) {
		ItemGroup p = new ItemGroup(name);
		for (int id : idArray) {
			p.list.add(ItemDatabase.get(id));
		}
		return p;
	}

	public ItemList list;

	public ItemGroup(ItemList list, String title) {
		super(-1, title);
		this.list = list;
	}
	
	public ItemGroup(String title) {
		this(new ItemList(), title);
	}
	
	@Override
	public void setPriority(int pri) {
		super.setPriority(pri);
		for (RItem i : list) {
			i.setPriority(pri);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof ItemGroup) {
			return getName().equals(((ItemGroup) obj).getName());
		}
		return false;
	}
	
	

}
