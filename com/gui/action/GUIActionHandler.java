package com.gui.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import com.data.Configuration;
import com.data.enums.FightMode;
import com.data.enums.PrayerMethod;
import com.data.enums.SlayerTask;
import com.item.special.WeaponSpecial;
import com.monster.SpecialType;
import com.util.MultiListener;

public class GUIActionHandler extends MultiListener {

	private Configuration con;

	public GUIActionHandler(Configuration con) {
		this.con = con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component c = (Component) e.getSource();
		if (c.getName() == null || c.getName().equals("")) {
			return;
		}
		ComponentWrapper w = new ComponentWrapper(c);
		switch (c.getName()) {
		case "specialFightMode":
			con.specialFightMode = SpecialType.values()[w.getSelectedIndex()];
			break;
		case "fightMode":
			con.fightMode = FightMode.values()[w.getSelectedIndex()];
			break;
		case "multiCombat_enable":
			con.multiCombat_enable = w.isSelected();
			break;
		case "attackBusyMobs_enable":
			con.attackBusyMobs_enable = w.isSelected();
			break;
		case "attackBusyMobs_maxPlayerAmount":
			con.attackBusyMobs_maxPlayerAmount = w.getSpinnerValue();
			break;
		case "range_pickupAmmo":
			con.range_pickupAmmo = w.isSelected();
			break;
		case "range_equipAmmo":
			con.range_equipAmmo = w.isSelected();
			break;
		case "slayer_enable":
			con.slayer_enable = w.isSelected();
			break;
		case "slayer_taskk":
			con.slayer_task = SlayerTask.values()[w.getSelectedIndex()];
		case "weaponSpecial_enable":
			con.weaponSpecial_enable = w.isSelected();
			break;
		case "weaponSpecial_weapon":
			con.weaponSpecial_weapon = WeaponSpecial.values()[w
					.getSelectedIndex()];
			break;
		case "weaponSpecial_useSecondaryWeapon":
			con.weaponSpecial_useSecondaryWeapon = w.isSelected();
			break;
		case "eating_enable":
			con.eating_enable = w.isSelected();
			break;
		case "eating_ranges":
			con.eating_ranges = w.getRangeValues();
			break;
		case "eating_smartEating_enable":
			con.eating_smartEating_enable = w.isSelected();
			break;
		case "eating_smartEating_limit":
			con.eating_smartEating_limit = w.getSliderValue();
			break;
		case "enableAttackPotions":
			con.enableAttackPotions = w.isSelected();
			break;
		case "enableStrengthPotions":
			con.enableStrengthPotions = w.isSelected();
			break;
		case "enableDefensePotions":
			con.enableDefensePotions = w.isSelected();
			break;
		case "enableRangePotions":
			con.enableRangePotions = w.isSelected();
			break;
		case "enableMagicPotions":
			con.enableMagicPotions = w.isSelected();
			break;
		case "enableAntipoisons":
			con.enableAntipoisons = w.isSelected();
			break;
		case "enableAntifires":
			con.enableAntifires = w.isSelected();
			break;
		case "potions_minBoost":
			con.potions_minBoost = w.getSliderValue();
			break;
		case "prayer_enable":
			con.prayer_enable = w.isSelected();
			break;
		case "prayer_method":
			con.prayer_method = PrayerMethod.values()[w.getSelectedIndex()];
			break;
		case "prayer_altarID":
			con.prayer_altarID = w.getSpinnerValue();
			break;
		case "cagedSupport_enable":
			con.cagedSupport_enable = w.isSelected();
			break;
		}
	}

}
