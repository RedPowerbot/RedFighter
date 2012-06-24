package com.monster;

public enum SpecialType {

	NONE("No Special Features", ""),
	BANDITS("Bandit Features", "");
	
	private String name;
	private String description;
	
	private SpecialType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return getName();
	}
	
}
