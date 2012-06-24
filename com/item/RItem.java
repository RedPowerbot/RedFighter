package com.item;

import com.util.Documentable;
import com.util.StringUtil;

public class RItem implements Comparable<RItem>, Documentable {

	private int id = 0;
	private String name = "null item";
	private boolean stackable;
	private boolean noted;

	/**
	 * -2 = Not loaded -1 = Could not load 0 = No cost
	 */
	private int price = -2;
	private int count;
	private int priority;

	public RItem() {
	}

	public RItem(int id, String name) {
		this.id = id;
		char[] array = name.toLowerCase().toCharArray();
		array[0] = Character.toUpperCase(array[0]);
		this.name = new String(array);
	}

	public RItem(int id, String name, boolean stackable, boolean noted) {
		this(id, name);
		this.stackable = stackable || noted;
		this.noted = noted;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void addToCount(int addition) {
		count += addition;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isStackable() {
		return stackable;
	}

	public boolean isNoted() {
		return noted;
	}

	public int getPrice() {
		return price;
	}

	public int getCount() {
		return count;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}

	public void setNoted(boolean noted) {
		this.noted = noted;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getLogString() {
		String amount = count > 0 ? " ("
				+ StringUtil.format(count) + ")" : "";
		int price = this.price * count;
		String profit = price > 0 ? " ("
				+ StringUtil.format(price) + ")" : "";
		return name + amount + profit;
	}

	@Override
	public String toString() {
		return name + " [" + id + "]" + (noted ? " (noted)" : "");
	}

	@Override
	public String toDataString() {
		return id + ";" + name + ";" + (stackable ? "t" : "f") + ";"
				+ (noted ? "t" : "f");
	}

	@Override
	public void initFromDataString(String s) {
		String[] array = s.split(";");
		id = Integer.parseInt(array[0]);
		name = array[1];
		stackable = array[2].equals("t");
		noted = array[3].equals("t");
	}

	@Override
	public int compareTo(RItem o) {
		if (o == null) {
			return 1;
		}
		return getName().compareTo(o.getName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RItem other = (RItem) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (noted != other.noted)
			return false;
		if (stackable != other.stackable)
			return false;
		return true;
	}

}
