package com.data;

import java.util.ArrayList;
import java.util.List;

import org.powerbot.game.api.ActiveScript;

import com.breaking.BreakHandler;
import com.data.enums.FightMode;
import com.data.enums.Prayer;
import com.data.enums.PrayerMethod;
import com.data.enums.SlayerTask;
import com.item.RItem;
import com.item.ItemList;
import com.item.profit.ProfitManager;
import com.item.special.WeaponSpecial;
import com.loop.BoneLoop;
import com.loop.CombatLoop;
import com.loop.EatLoop;
import com.loop.ScriptLoop;
import com.monster.MonsterList;
import com.monster.SpecialType;
import com.paint.RFPainterV1;
import com.paint.data.PaintTab;
import com.paint.skill.SkillDialog;
import com.util.ArrayEx;
import com.util.BasicArrayEx;

public class Configuration {

	public Configuration() {
		
	}
	
	public Configuration(ActiveScript parent) {
		this.combatLoop = new CombatLoop(parent, this);
		this.eatLoop = new EatLoop(parent, this);
		this.boneLoop = new BoneLoop(parent, this);
		loopPool = new ScriptLoop[] {combatLoop, eatLoop, boneLoop};
	}
	
	/**
	 * Top Level Objects
	 */
	public ScriptLoop[] loopPool;
	public CombatLoop combatLoop;
	public EatLoop eatLoop;
	public BoneLoop boneLoop;
	public RFPainterV1 paintLoop;

	/**
	 * Painting
	 */
	public String status = "Starting up...";
	public long startTime = System.currentTimeMillis();
	public boolean hidePaint = false;
	public PaintTab paintTab = PaintTab.MAIN;
	public SkillDialog skillDialog;

	/**
	 * Fighting
	 */
	public final MonsterList monsterList = new MonsterList();
	public SpecialType specialFightMode = SpecialType.NONE;
	public FightMode fightMode = FightMode.MELEE;
	public boolean multiCombat_enable = false;
	public boolean attackBusyMobs_enable = false;
	public int attackBusyMobs_maxPlayerAmount = 3;
	public boolean range_pickupAmmo = false;
	public boolean range_equipAmmo = false;

	/**
	 * Slayer
	 */
	public boolean slayer_enable = false;
	public SlayerTask slayer_task = SlayerTask.DESERT_LIZARDS;
	
	/**
	 * Weapon Special
	 */
	public boolean weaponSpecial_enable = false;
	public WeaponSpecial weaponSpecial_weapon = WeaponSpecial.ABYSSAL_WHIP;
	public boolean weaponSpecial_useSecondaryWeapon = false;
	public RItem weaponSpecial_primaryWeapon;

	/**
	 * Loot
	 */
	public final ItemList lootList = new ItemList();
	public final ProfitManager profitManager = new ProfitManager(lootList);
	public int timesLooted = 0;

	/**
	 * Eating
	 */
	public boolean eating_enable = true;
	public boolean eating_smartEating_enable = false;
	public int[] eating_ranges = { 65, 80 };
	public int eating_smartEating_limit = 50;

	/**
	 * B2P
	 */
	public boolean b2p_enable = false;
	public boolean b2p_smartBoneCollecting_enable = false;
	public int b2p_minBoneLimit = 3;
	public int b2p_maxBoneLimit = 10;

	/**
	 * Potions
	 */
	public boolean enableAttackPotions = false;
	public boolean enableStrengthPotions = false;
	public boolean enableDefensePotions = false;
	public boolean enableRangePotions = false;
	public boolean enableMagicPotions = false;
	public boolean enableAntipoisons = false;
	public boolean enableAntifires = false;
	public int potions_minBoost = 3;

	/**
	 * Prayer
	 */
	public boolean prayer_enable = false;
	public PrayerMethod prayer_method = PrayerMethod.POTION;
	public int prayer_altarID = 0;
	public ArrayEx<Prayer> prayer_prayers = new BasicArrayEx<>();
	
	
	/**
	 * Break Handler
	 */
	public ArrayEx<BreakHandler> breakHandlers = new BasicArrayEx<>();
}
