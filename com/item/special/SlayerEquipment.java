package com.item.special;

public enum SlayerEquipment {

	BAG_OF_SALT("Bag of salt",4161),
	BLACK_MASK("Black mask",8921),
	BROAD_ARROW("Broad arrow",4160),
	BROAD_TIPPED_BOLTS("Broad-tipped bolts",13280),
	CRYSTAL_CHIME("Crystal chime",11749),
	EARMUFFS("Earmuffs",4166),
	ENCHANTED_GEM("Enchanted gem",4155),
	FACE_MASK("Face mask",4164),
	FISHING_EXPLOSIVE("Fishing explosive",6660),
	FOCUS_SIGHT("Focus sight",15490),
	FUNGICIDE("Fungicide",7432),
	FUNGICIDE_SPRAY("Fungicide spray",
			new int[] {
			7431, 7430, 7429, 7428, 7427, 7426,
			7425, 7424, 7423, 7422, 7421
			}
		),
	HEXCREST("Hexcrest",15488),
	ICE_COOLER("Ice cooler",6696),
	INSULATED_BOOTS("Insulated boots",7159),
	LEAF_BLADED_SPEAR("Leaf-bladed spear",4158),
	LEAF_BLADED_SWORD("Leaf-bladed sword",13290),
	LIT_BUG_LANTERN("Lit bug lantern",7053),
	MIRROR_SHIELD("Mirror shield",4156),
	NOSE_PEG("Nose peg",4168),
	ROCK_HAMMER("Rock hammer",4162),
	SLAYER_BELL("Slayer bell",10952),
	SLAYER_GLOVES("Slayer gloves",6708),
	SLAYERS_STAFF("Slayer's staff",4170),
	SPINY_HELMET("Spiny helmet",4551),
	SUPER_FISHING_EXPLOSIVE("Super fishing explosive",12633),
	UNLIT_BUG_LANTERN("Unlit bug lantern",7051),
	WITCHWOOD_ICON("Witchwood icon",8923);

	
	private int[] itemIds;
	private String name;
	
	private SlayerEquipment(String name, int... itemIds) {
		this.name = name;
		this.itemIds = itemIds;
	}

	public int[] getItemIds() {
		return itemIds;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
