package com.data.enums;

public enum Prayer {

	THICK_SKIN("Thick Skin", 0, 1, 1.2),
	BURST_OF_STRENGTH("Burst of Strength", 0, 4, 1.2),
	CLARITY_OF_THOUGHT("Clarity of Thought", 0, 7, 1.2),
	SHARP_EYE("Sharp Eye", 0, 8, 1.2),
	MYSTIC_WILL("Mystic Will", 0, 9, 1.2),
	ROCK_SKIN("Rock Skin", 0, 10, .6),
	SUPERHUMAN_STRENGTH("Superhuman Strength", 0, 13, .6),
	IMPROVED_REFLEXES("Improved Reflexes", 0, 16, .6),
	RAPID_RESTORE("Rapid Restore", 0, 19, 3.6),
	RAPID_HEAL("Rapid Heal", 0, 22, 1.8),
	PROTECT_ITEM("Protect Item", 0, 25, 1.8),
	HAWK_EYE("Hawk Eye", 0, 26, .6),
	MYSTIC_LORE("Mystic Lore", 0, 27, .6),
	STEEL_SKIN("Steel Skin", 0, 28, .3),
	UTIMATE_STRENGTH("Ultimate Strength", 0, 31, .3),
	INCREDIBLE_REFLEXES("Incredible Reflexes", 0, 34, .3),
	PROTECT_FROM_SUMMONING("Protect from Summoning", 0, 35, .3),
	PROTECT_FROM_MAGIC("Protect from Magic", 0, 37, .3),
	PROTECT_FROM_MISSLES("Protect from Missles", 0, 40, .3),
	PROTECT_FROM_MELEE("Protect from Melee", 0, 43, .3),
	EAGLE_EYE("Eagle Eye", 0, 44, .3),
	MYSTIC_MIGHT("Mystic Eye", 0, 45, .3),
	RETRIBUTION("Retribution", 0, 46, 1.2),
	REDEMPTION("Redemption", 0, 49, .6),
	SMITE("Smite", 0, 52, .6),
	CHIVALRY("Chivalry", 0, 60, .18),
	RAPID_RENEWAL("Rapid Renewal", 0, 65, .24),
	PIETY("Piety", 0, 70, .15),
	RIGOUR("Rigour", 0, 74, .2),
	AUGURY("Augury", 0, 77, .18);
	
	private String name;
	private int iFaceID;
	private int reqLevel;
	private double secondsPerPoint;
	private double pointsPerSecond;
	
	private Prayer(String name, int iFaceID, int reqLevel, double secondsPerPoint) {
		this.name = name;
		this.iFaceID = iFaceID;
		this.reqLevel = reqLevel;
		this.secondsPerPoint = secondsPerPoint;
		this.pointsPerSecond = 1 / secondsPerPoint;
	}

	public String getName() {
		return name;
	}

	public int getiFaceID() {
		return iFaceID;
	}

	public int getRequiredLevel() {
		return reqLevel;
	}

	public double getSecondsPerPoint() {
		return secondsPerPoint;
	}

	public double getPointsPerSecond() {
		return pointsPerSecond;
	}
	
}
