package com.data.enums;

public enum PrayerMethod {

	POTION("Restore via Potions"),
	ALTAR("Restore via Prayer Alter");
	
	private String name;
	
	private PrayerMethod(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
