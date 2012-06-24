package com.gui.settings;

import com.util.PropertiesEx;

public class Profile extends PropertiesEx {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7117531281279664442L;
	private String title;
	private long dateMade = System.currentTimeMillis();
	private long dateUsed = System.currentTimeMillis();

	public Profile(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public long getDateMade() {
		return dateMade;
	}

	public long getDateUsed() {
		return dateUsed;
	}

	public void setDateMade(long dateMade) {
		this.dateMade = dateMade;
	}

	public void setDateUsed(long dateUsed) {
		this.dateUsed = dateUsed;
	}

	public void use() {
		dateUsed = System.currentTimeMillis();
	}

	public String getDataString() {
		return title + "," + dateMade + "," + dateUsed;
	}

	@Override
	public String toString() {
		return getTitle();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Profile other = (Profile) obj;
		if (dateMade != other.dateMade) {
			return false;
		}
		if (dateUsed != other.dateUsed) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

}
