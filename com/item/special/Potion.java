package com.item.special;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.node.Item;

public enum Potion {

	SUPER_PRAYER(new int[] { 15328, 15329, 15330, 15331 }, Skills.PRAYER), PRAYER_POTION(
			new int[] { 2434, 139, 141, 143 }, Skills.PRAYER),

	OVERLOAD(new int[] { 15332, 15333, 15334, 15335 }, new int[] {
			Skills.ATTACK, Skills.STRENGTH, Skills.DEFENSE, Skills.RANGE,
			Skills.MAGIC }), EXTREME_RANGING(new int[] { 15324, 15325, 15326,
			15327 }, Skills.RANGE), EXTREME_DEFENCE(new int[] { 15316, 15317,
			15318, 15319 }, Skills.DEFENSE), EXTREME_MAGIC(new int[] { 15320,
			15321, 15322, 15323 }, Skills.MAGIC), EXTREME_ATTACK(new int[] {
			15308, 15309, 15310, 15311 }, Skills.ATTACK), EXTREME_STRENGTGH(
			new int[] { 15312, 15313, 15314, 15315 }, Skills.STRENGTH),

	/**
	 * These three should only be used directly.
	 */
	ZAMORAK_BREW(new int[] { 2450, 189, 191, 193 }, getRestoreIndex()),
	SUPER_RESTORE(new int[] { 3024, 3026, 3028, 3030 }, getRestoreIndex()), 
	RESTORE_POTION(new int[] { 2430, 127, 129, 131 }, getRestoreIndex()),
	SUPER_STRENGTH(new int[] { 2440, 157, 159, 14245, 161 }, Skills.STRENGTH),
	SUPER_DEFENCE(new int[] { 2442, 163, 165, 167 }, Skills.DEFENSE), 
	SUPER_ATTACK(new int[] { 2436, 145, 147, 149 }, Skills.ATTACK),
	ATTACK_POTION(new int[] { 2428, 121, 123, 125 }, Skills.ATTACK),
	DEFENCE_POTION(new int[] { 2432, 133, 135, 137 }, Skills.DEFENSE),
	COMBAT_POTION(new int[] { 9739, 9741, 9743, 9745 }, new int[] { Skills.ATTACK,Skills.STRENGTH }),
	MAGIC_POTION(new int[] { 3040, 3042,3044, 3046 }, Skills.MAGIC), 
	RANGING_POTION(new int[] { 2444, 169,171, 173 }, Skills.RANGE), 
	STRENGTH_POTION(new int[] { 113, 115, 117, 119 }, Skills.STRENGTH),
	SUPER_ANTIFIRE(new int[] { 15304, 15305, 15306, 15307 }, getAntifireIndex()), 
	ANTIFIRE(new int[] { 2452, 2454, 2456, 2458 }, getAntifireIndex()),
	SUMMONING_POTION(new int[] { 12140, 12142, 12144, 12146 }, Skills.SUMMONING),
	SUPER_ENERGY(new int[] { 3016, 3018, 3020, 3022 }, getEnergyIndex()), 
	ENERGY_POTION(new int[] { 3008, 3010, 3012, 3014 }, getEnergyIndex()),
	SUPER_ANTIPOISON(new int[] { 2448, 2448, 181, 181, 183, 183, 185, 185 },getAntipoisonIndex()), 
	ANTIPOISON(new int[] { 2446, 175, 177, 179 }, getAntipoisonIndex());

	private final static int ANTIPOISON_INDEX = -1;
	private final static int ANTIFIRE_INDEX = -2;
	private final static int RESTORE_INDEX = -3;
	private final static int ENERGY_INDEX = -4;

	private int[] ids;
	private int[] SkillsIndexes;

	private Potion(int[] ids) {
		this.ids = ids;
	}

	private Potion(int[] ids, int... SkillsIndexes) {
		this.ids = ids;
		this.SkillsIndexes = SkillsIndexes;
	}

	public int[] getIds() {
		return ids;
	}

	public int[] getSkillsIndexes() {
		return SkillsIndexes;
	}

	public Potion getPotionByID(int id) {
		for (Potion p : values()) {
			for (int i : p.getIds()) {
				if (i == id) {
					return p;
				}
			}
		}
		return null;
	}

	public static Item getItemBySkillsIndex(int index, Item... inv) {
		for (Potion p : values()) {
			for (int SkillsIndex : p.getSkillsIndexes()) {
				if (SkillsIndex == index) {
					for (Item i : inv) {
						if (i == null) {
							continue;
						}
						for (int id : p.getIds()) {
							if (i.getId() == id) {
								return i;
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static int getAntipoisonIndex() {
		return ANTIPOISON_INDEX;
	}

	public static int getAntifireIndex() {
		return ANTIFIRE_INDEX;
	}

	public static int getRestoreIndex() {
		return RESTORE_INDEX;
	}

	public static int getEnergyIndex() {
		return ENERGY_INDEX;
	}

}
