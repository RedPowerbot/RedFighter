package com.monster;

import com.util.Documentable;

public class Monster implements Documentable {

	private int id = -1;
	private int level = -1;
	private String name = null;

	public Monster() {
	}

	public Monster(int id, int level, String name) {
		super();
		this.id = id;
		this.level = level;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toDataString() {
		return id + ";" + level + ";" + name;
	}

	@Override
	public void initFromDataString(String s) {
		String[] args = s.split(";");
		id = Integer.parseInt(args[0]);
		level = Integer.parseInt(args[1]);
		name = args[2];
	}

	@Override
	public String toString() {
		return name + " [ID:" + id + "]" + "[LV:" + level + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monster other = (Monster) obj;
		if (id != other.id)
			return false;
		if (level != other.level)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
