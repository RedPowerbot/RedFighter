package com.gui.action;

import java.awt.Component;
import java.awt.Container;

import com.data.Configuration;

public class GuiIntiater {

	private Configuration con;

	public GuiIntiater(Configuration c) {
		this.con = c;
	}

	public void init(Component c) {
		scan(c);
	}

	private void scan(Component[] array) {
		for (Component c : array) {
			scan(c);
		}
	}

	private void scan(Component c) {
		switch (c.getClass().getSimpleName()) {
		case "LFGui": // Custom classes are here too.
		case "LFMenuBar":
		case "JFrame":
		case "JRootPane":
		case "JPanel":
		case "JConentPane":
		case "JLayeredPane":
		case "JTabbedPane":
		case "JMenuBar":
		case "JMenu":
		case "JToolBar":
		case "JSplitPane":
		case "JScrollPane":
		case "JViewport":
			scan(((Container) c).getComponents());
			return;
		}
		if (c.getName() != null && !c.getName().equals("")) {
			setValue(c);
		}
	}

	private void setValue(Component c) {
		if (c.getName() == null || c.getName().equals("")) {
			return;
		}
		ComponentWrapper w = new ComponentWrapper(c);
		switch (c.getName()) {
		case "b2p_enable":
			w.setSelected(con.b2p_enable);
			break;
		case "b2p_boneLimits":
			w.setRangeValues(new int[] { con.b2p_minBoneLimit,
					con.b2p_maxBoneLimit - con.b2p_minBoneLimit });
			break;
		case "specialFightMode":
			w.setSelectedIndex(con.specialFightMode.ordinal());
			break;
		case "fightMode":
			w.setSelectedIndex(con.fightMode.ordinal());
			break;
		case "multiCombat_enable":
			w.setSelected(con.multiCombat_enable);
			break;
		case "attackBusyMobs_enable":
			w.setSelected(con.attackBusyMobs_enable);
			break;
		case "attackBusyMobs_maxPlayerAmount":
			w.setSpinnerValue(con.attackBusyMobs_maxPlayerAmount);
			break;
		case "range_pickupAmmo":
			w.setSelected(con.range_pickupAmmo);
			break;
		case "range_equipAmmo":
			w.setSelected(con.range_equipAmmo);
			break;
		case "slayer_enable":
			w.setSelected(con.slayer_enable);
			break;
		case "slayer_task":
			w.setSelectedIndex(con.slayer_task.ordinal());
			break;
		case "weaponSpecial_enable":
			w.setSelected(con.weaponSpecial_enable);
			break;
		case "weaponSpecial_weapon":
			w.setSelectedIndex(con.weaponSpecial_weapon.ordinal());
			break;
		case "weaponSpecial_useSecondaryWeapon":
			w.setSelected(con.weaponSpecial_useSecondaryWeapon);
			break;
		case "eating_enable":
			w.setSelected(con.eating_enable);
			break;
		case "eating_ranges":
			w.setRangeValues(con.eating_ranges);
			break;
		case "eating_smartEating_enable":
			w.setSelected(con.eating_smartEating_enable);
			break;
		case "eating_smartEating_limit":
			w.setSliderValue(con.eating_smartEating_limit);
			break;
		case "enableAttackPotions":
			w.setSelected(con.enableAttackPotions);
			break;
		case "enableStrengthPotions":
			w.setSelected(con.enableStrengthPotions);
			break;
		case "enableDefensePotions":
			w.setSelected(con.enableDefensePotions);
			break;
		case "enableRangePotions":
			w.setSelected(con.enableRangePotions);
			break;
		case "enableMagicPotions":
			w.setSelected(con.enableMagicPotions);
			break;
		case "enableAntipoisons":
			w.setSelected(con.enableAntipoisons);
			break;
		case "enableAntifires":
			w.setSelected(con.enableAntifires);
			break;
		case "potions_minBoost":
			w.setSliderValue(con.potions_minBoost);
			break;
		case "prayer_enable":
			w.setSelected(con.prayer_enable);
			break;
		case "prayer_method":
			w.setSelectedIndex(con.prayer_method.ordinal());
			break;
		case "prayer_altarID":
			w.setSpinnerValue(con.prayer_altarID);
			break;
		case "cagedSupport_enable":
			w.setSelected(con.cagedSupport_enable);
			break;	
		}
	}

}
