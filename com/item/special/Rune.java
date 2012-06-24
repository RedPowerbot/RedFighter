package com.item.special;


public enum Rune {

	AIR_RUNE(556, true), EARTH_RUNE(557, true), FIRE_RUNE(554, true), WATER_RUNE(
			555, true),

	DUST_RUNE(4696, new int[] { 4696, 556, 557 }), LAVA_RUNE(4699, new int[] {
			4699, 557, 554 }), MIST_RUNE(4695, new int[] { 4695, 556, 555 }), MUD_RUNE(
			4698, new int[] { 4698, 557, 555 }), SMOKE_RUNE(4697, new int[] {
			4697, 556, 554 }), STEAM_RUNE(4694, new int[] { 4694, 554, 555 }),

	DEATH_RUNE(560), SOUL_RUNE(566), NATURE_RUNE(561), LAW_RUNE(563), MIND_RUNE(
			558), BODY_RUNE(559), CHAOS_RUNE(562), COSMIC_RUNE(564);

	private int id;
	private boolean isElemental;
	private int[] subIds;

	private Rune(int id) {
		this.id = id;
	}

	private Rune(int id, boolean isElemental) {
		this.id = id;
		this.isElemental = isElemental;
	}

	private Rune(int id, int[] subIds) {
		this(id, true);
		this.subIds = subIds;
	}

	public int getID() {
		return id;
	}

	public int[] getAllIDs() {
		if (isCombination()) {
			return subIds;
		}
		return new int[] { getID() };
	}

	public boolean isElemental() {
		return isElemental;
	}

	public boolean isCombination() {
		return this == DUST_RUNE || this == LAVA_RUNE || this == MIST_RUNE
				|| this == MUD_RUNE || this == SMOKE_RUNE || this == STEAM_RUNE;
	}

}
