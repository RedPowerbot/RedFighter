package com.data.enums;

public enum FightMode {

	// TODO Write Descriptions
	MELEE("FightMode.Melee", "Melee", null), RANGE("FightMode.Range", "Range",
			null), MAGIC("FightMode.Magic", "Magic", null);

	private String imageName;
	private String title;
	private String description;

	private FightMode() {
	}

	private FightMode(String imageName, String title, String description) {
		this.imageName = imageName;
		this.title = title;
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return getTitle();
	}

}
