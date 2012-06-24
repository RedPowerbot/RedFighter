package com.item.special;

public enum WeaponSpecial {

	// ----Melee Weapons---
	ABYSSAL_WHIP(4151, "Abyssal Whip", 50), ANCIENT_MACE(11061, "Ancient mace",
			100), ARMADYL_GODSWORD(11694, "Armadyl godsword", 50), BANDOS_GODSWORD(
			11696, "Bandos godsword", 100), BARRELCHEST_ANCHOR(10887,
			"Barrelchest anchor", 50), BONE_DAGGER(8872, "Bone dagger", 75), BONE_DAGGER_P(
			8874, "Bone Dagger (P)", 75), BONE_DAGGER_PP(8876,
			"Bone Dagger (P+)", 75), BONE_DAGGER_PPP(8878, "Bone Dagger (P++)",
			75), BRINE_SABRE(11037, "Brine Sabre", 75), DARKLIGHT(6746,
			"Darklight", 50), DRAGON_2H_SWORD(7158, "Dragon 2h sword", 60), DRAGON_BATTLEAXE(
			1377, "Dragon battleaxe", 100), DRAGON_CLAWS(14484, "Dragon claws",
			50), DRAGON_DAGGER(1215, "Dragon dagger", 25), DRAGON_DAGGER_P(
			1231, "Dragon dagger (P)", 25), DRAGON_DAGGER_PP(5680,
			"Dragon dagger (P+)", 25), DRAGON_DAGGER_PPP(5698,
			"Dragon dagger (P++)", 25), DRAGON_HALBERD(3204, "Dragon halberd",
			50), DRAGON_HATCHET(6739, "Dragon hatchet", 100), DRAGON_LONGSWORD(
			1305, "Dragon longsword", 25), DRAGON_MACE(1434, "Dragon mace", 25), DRAGON_SCIMITAR(
			4587, "Dragon scimitar", 55), DRAGON_SPEAR(1249, "Dragon spear", 25), DRAGON_SPEAR_P(
			1263, "Dragon spear (P)", 25), DRAGON_SPEAR_PP(5716,
			"Dragon spear (P+)", 25), DRAGON_SPEAR_PPP(5730,
			"Dragon spear (P++)", 25), ENHANCED_EXCALIBUR(14632,
			"Enhanced excalibur", 100), EXCALIBUR(35, "Excalibur", 100), GRANITE_MAUL(
			4153, "Granite maul", 50), KORASIS_SWORD(18786, "Korasis sword", 60), RUNE_CLAWS(
			3101, "Rune claws", 25), SARADOMIN_GODSWORD(11698,
			"Saradomin godsword", 50), SARADOMIN_SWORD(11730,
			"Saradomin sword", 100), STATIUSS_WARHAMMER(13902,
			"Status's warhammer", 35), VESTAS_LONGSWORD(13899,
			"Vesta's longsword", 25), VESTAS_SPEAR(13905, "Vesta's spear", 50), ZAMORAK_GODSWORD(
			11700, "Zamorak godsword", 60), ZAMORAKIAN_SPEAR(11716,
			"Zamorakian spear", 25),

	// ---Range Weapons---
	DARK_BOW(11235, "Dark bow", 65), DORGESHUUN_CBOW(8880, "Dorgeshuun c'bow",
			75), GUTHIX_BOW(19146, "Guthix bow", 55), HAND_CANNON(15241,
			"Hand cannon", 50), MAGIC_COMPOSITE_BOW(10284,
			"Magic composite bow", 35), MAGIC_LONGBOW(859, "Magic longbow", 35), MAGIC_LONGBOW_SIGHTED(
			18332, "Magic longbow (sighted)", 35), MAGIC_SHORTBOW(861,
			"Magic shortbow", 55), MORRIGANS_THROWING_AXE(13883,
			"Morrigan's throwing axe", 50), RUNE_THROWNAXE(805,
			"Rune thrownaxe", 10), SARADOMIN_BOW(19143, "Saradmin bow", 55), SEERCULL(
			6724, "Seercull", 100), ZAMORAK_BOW(19149, "Zamorak bow", 55), ZANIKS_CROSSBOW(
			14684, "Zanik's crossbow", 50),

	// ---Magic weapons---
	STAFF_OF_LIGHT(15486, "Staff of light", 100);

	public static WeaponSpecial get(int weaponID) {
		for (WeaponSpecial ws : values()) {
			if (ws.getWeaponId() == weaponID) {
				return ws;
			}
		}
		return null;
	}

	private String name;
	private int weaponId;
	private int minimumPower;

	private WeaponSpecial(int weaponId, String name) {
		this.name = name;
		this.weaponId = weaponId;
	}

	private WeaponSpecial(int weaponId, String name, int minimumPower) {
		this.weaponId = weaponId;
		this.name = name;
		this.minimumPower = minimumPower;
	}

	public String getName() {
		return name;
	}

	public int getWeaponId() {
		return weaponId;
	}

	public int getMinimumPower() {
		return minimumPower;
	}

	@Override
	public String toString() {
		return getName() + " (" + getMinimumPower() + ")";
	}

}
