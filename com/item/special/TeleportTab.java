package com.item.special;

public enum TeleportTab {

	AIR_ALTAR_TELEPORT(13599, "Air altar teleport"), ARDOUGNE_TELEPORT(8011,
			"Ardougne teleport", true), ASTRAL_ALTAR_TELEPORT(13611,
			"Astral altar teleport"), BANDIT_CAMP_TELEPORT(19476,
			"Bandit camp teleport"), BLOOD_ALTAR_TELEPORT(13610,
			"Blood altar teleport"), BODY_ALTAR_TELEPORT(13604,
			"Body altar teleport"), CAMELOT_TELEPORT(8010, "Camelot teleport",
			true), CHAOS_ALTAR_TELEPORT(13606, "Chaos altar teleport"), COSMIC_ALTAR_TELEPORT(
			13605, "Cosmic altar teleport"), DEATH_ALTAR_TELEPORT(13609,
			"Death altar teleport"), EARTH_ALTAR_TELEPORT(13602,
			"Earth altar teleport"), FALADOR_TELEPORT(8009, "Falador teleport",
			true), FIRE_ALTAR_TELEPORT(13603, "Fire altar teleport"), JUJU_TELEPORT_SPIRITBAG(
			19967, "Juju teleport spiritbag"), LAW_ALTAR_TELEPORT(13608,
			"Law altar teleport"), LUMBER_YARD_TELEPORT(19480,
			"Lumber yard teleport"), LUMBRIDGE_TELEPORT(8008,
			"Lumbridge teleport", true), MIND_ALTAR_TELEPORT(13600,
			"Mind altar teleport"), MISCELLANIA_TELEPORT(19477,
			"Miscellania teleport"), NARDAH_TELEPORT(19475, "Nardah teleport"), NATURE_ALTAR_TELEPORT(
			13607, "Nature altar teleport"), TAI_BWO_WANNAI_TELEPORT(19479,
			"Tai bwo wannai teleport"), VARROCK_TELEPORT(8007,
			"Varrock teleport", true), WATER_ALTAR_TELEPORT(13601,
			"Water altar teleport");

	private int id;
	private String name;
	private boolean normalTeleport = false;

	private TeleportTab(int id, String name) {
		this.id = id;
		this.name = name;
	}

	private TeleportTab(int id, String name, boolean normalTeleport) {
		this(id, name);
		this.normalTeleport = normalTeleport;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isNormalTeleport() {
		return normalTeleport;
	}

	@Override
	public String toString() {
		return getName();
	}

}