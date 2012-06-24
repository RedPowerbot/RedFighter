package com.data.enums;

import com.item.special.SlayerEquipment;

public enum SlayerMonster {

	GELATINOUS_ABOMINATION(1, "Gelatinous abomination", 2, 5, null),
	CRAWLING_HAND(5, "Crawling hand", new int[] {8, 12}, new int[] {16, 19}, null),
	CAVE_BUG(7, "Cave bug", new int[] {6, 96}, new int[] {5, 95}, null),
	CAVE_CRAWLER(10, "Cave crawler", new int[] {23, 138}, new int[] {22, 122}, null),
	BANSHEE(15, "BANSHEE", new int[] {23, 90}, new int[] {22, 85}, SlayerEquipment.EARMUFFS),
	CAVE_SLIME(17, "Cave slime", 23, 25, null),
	ROCKSLUG(20, "Rockslug", 29, 27, null),
	DESERT_LIZARD(22, "Desert lizard", new int[] {12, 24, 42}, new int[] {15, 25, 40}, SlayerEquipment.ICE_COOLER),
	COCKATRICE(25, "Cockatrice", 37, 37, SlayerEquipment.MIRROR_SHIELD),
	PYREFIEND(30, "Pyrefiend", new int[] {43, 48}, new int[] {45, 48}, null),
	MOGRE(32, "Mogre", 60, 48, SlayerEquipment.FISHING_EXPLOSIVE),
	HARPIE_BUG_SWARM(33, "Harpie Bug Swarm", 46, 25, SlayerEquipment.LIT_BUG_LANTERN),
	WALL_BEAST(35, "Wall beast", 49, 105, SlayerEquipment.SPINY_HELMET),
	KILLERWATT(37, "Killerwatt", 55, 51, SlayerEquipment.INSULATED_BOOTS),
	MOLANISK(39, "Molanisk", 51, 52, SlayerEquipment.SLAYER_BELL),
	BASILISK(40, "Basilisk", 61, 75, SlayerEquipment.MIRROR_SHIELD),
	TERROR_DOG(40, "Terror dog", new int[] {100, 110}, new int[] {85, 89}, null),
	NIGHT_SPIDER(41, "Night spider", 59, 0, null),
	FEVER_SPIDER(41, "Fever Spider", 49, 40, SlayerEquipment.SLAYER_GLOVES),
	INFERNAL_MAGE(45, "Infernal Mage", 68, 60, null),
	BRINE_RAT(47, "Brine rat", 70, 50, null),
	BLOODVELD(50, "Bloodveld", 76, 120, null),
	MUTATED_BLOODVELD(50, "Mutated bloodveld", new int[] {120, 146}, new int[] {178, 198}, null),
	PHOENIX(51, "Phoenix", 235, -1, null),
	JELLY(52, "Jelly", 78, 75, null),
	TUROTH(55, "Turoth", new int[] {83, 85, 87, 88, 89}, new int[] {76, 77, 79, 80, 81}, SlayerEquipment.LEAF_BLADED_SPEAR),
	WARPED_TERRORBIRD(56, "Warped terrorbird", new int[] {81, 143}, new int[] {150, 200}, SlayerEquipment.CRYSTAL_CHIME),
	WARPED_TORTOISE(56, "Warped tortoise", 96, 87, SlayerEquipment.CRYSTAL_CHIME),
	MUTATED_ZYGOMITE(57, "Mutated zygomite", new int[] {74, 86}, new int[] {65, 75}, SlayerEquipment.FUNGICIDE_SPRAY),
	CAVE_HORROR(58, "Cave horror", 80, 55, SlayerEquipment.WITCHWOOD_ICON),
	WILD_JADE_VINE(59, "Wild jade vine", 167, 2500, null),
	ABERRANT_SPECTRE(60, "Aberrant spectre", 96, 90, SlayerEquipment.NOSE_PEG),
	RUM_PUMPED_CRAB(61, "'Rum'-pumped crab", 91, 85, null),
	SPIRITUAL_RANGER(63, "Spiritual ranger", 112, -1, null),
	SPIRITUAL_GUARDIAN(63, "Spiritual guardian", 78, -1, null),
	DUST_DEVIL(65, "Dust devil", 93, 105, SlayerEquipment.FACE_MASK),
	SPIRITUAL_WARRIOR(68, "Spiritual warrior", 115, -1, null),
	KURASK(70, "Kurask", 106, 97, SlayerEquipment.LEAF_BLADED_SWORD),
	SEEKER(71, "Seeker", 70, -1, null),
	SKELETAL_WYVERN(72, "Skeletal wyvern", 140, 210, null),
	JUNGLE_STRYKEWYRM(73, "Jungle strykewyrm", 110, 110, null),
	GARGOYLE(75, "Gargoyle", 111, 105, SlayerEquipment.ROCK_HAMMER),
	DESERT_STRYKEWYRM(77, "Desert strykewyrm", 130, 120, null),
	AQUANITE(78, "Aquanite", 114, 125, null),
	NECHRYAEL(80, "Nechryael", 115, 105, null),
	MUTATED_JADINKO_BABY(80, "Mutated jadinko baby", 90, 127, null),
	GRIFOLAROO(82, "Grifolaroo", 180, 184, null),
	SPIRITUAL_MAGE(83, "Spiritual mage", 120, -1, null),
	ABYSSAL_DEMON(85, "Abyssal demon", 124, 150, null),
	MUTATED_JADINKO_GUARD(86, "Mutated jadinko guard", 145, 203, null),
	GRIFOLAPINE(88, "Grifolapine", 200, 205, null),
	DARK_BEAST(90, "Dark beast", 180, 220, null),
	EDIMMU(90, "Eddimmu", 121, 0, null),
	MUTATED_JADINKO_MALE(91, "Mutated jadinko male", 201, 280, null),
	ICE_STRYKEWYRM(93, "Ice strykewyrm", 220, 300, null),
	GANODERMIC_RUNT(95, "Ganodermic runt", 140, 125, null),
	GANODERMIC_BEAST(95, "Ganodermic beast", 280, 263, null),
	SOULGAZER(99, "Soulgazer", 105, 0, null);
	
	public static int getSlayerExp(String mobName, int combatLevel) {
		for (SlayerMonster m : values()) {
			if (m.name().equals(mobName)) {
				int index = 0;
				for (int cbLv : m.getCombatLevels()) 	{
					if (cbLv == combatLevel) {
						return m.getSlayerExp()[index];
					}
					index++;
				}
			}
		}
		return 0;
	}
	
	private int reqLevel;
	private String mobName;
	private int[] combatLevels;
	private int[] slayerExp;
	private SlayerEquipment reqEquipment;
	/**	
	 * @param reqLevel
	 * @param mobName
	 * @param combatLevels
	 * @param slayerExp
	 * @param reqEquipment
	 */
	private SlayerMonster(int reqLevel, String mobName, int[] combatLevels,
			int[] slayerExp, SlayerEquipment reqEquipment) {
		this.reqLevel = reqLevel;
		this.mobName = mobName;
		this.combatLevels = combatLevels;
		this.slayerExp = slayerExp;
		this.reqEquipment = reqEquipment;
	}
	
	private SlayerMonster(int reqLevel, String mobName, int combatLevel,
			int slayerExp, SlayerEquipment reqEquipment) {
		this.reqLevel = reqLevel;
		this.mobName = mobName;
		this.combatLevels = new int[] {combatLevel};
		this.slayerExp = new int[] {slayerExp};
		this.reqEquipment = reqEquipment;
	}

	public int getReqLevel() {
		return reqLevel;
	}

	public String getMobName() {
		return mobName;
	}

	public int[] getCombatLevels() {
		return combatLevels;
	}

	public int[] getSlayerExp() {
		return slayerExp;
	}

	public SlayerEquipment getReqEquipment() {
		return reqEquipment;
	}
	
	@Override
	public String toString() {
		return getMobName();
	}
	
}
