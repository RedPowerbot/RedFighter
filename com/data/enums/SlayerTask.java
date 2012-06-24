package com.data.enums;

import com.item.special.SlayerEquipment;

public enum SlayerTask {

	ROCK_SLUGS(SlayerMonster.ROCKSLUG),
	DESERT_LIZARDS(SlayerMonster.DESERT_LIZARD),
	MOGRES(SlayerMonster.MOGRE),
	MOLANISKS(SlayerMonster.MOLANISK),
	MUTATED_ZYGOMITES(SlayerMonster.MUTATED_ZYGOMITE),
	GARGOYLES(SlayerMonster.GARGOYLE);
	
	private SlayerMonster monster;

	private SlayerTask(SlayerMonster monster) {
		this.monster = monster;
	}
	
	public SlayerMonster getMonster() {
		return monster;
	}
	
	public SlayerEquipment getEquipment() {
		return monster.getReqEquipment();
	}
	
	@Override
	public String toString() {
		return monster.getMobName();
	}
	
}
