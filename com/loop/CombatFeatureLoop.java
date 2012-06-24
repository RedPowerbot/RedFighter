package com.loop;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.widget.WidgetComposite;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import com.data.Configuration;

public class CombatFeatureLoop extends ScriptLoop {

	public CombatFeatureLoop(ActiveScript parent, Configuration con) {
		super(parent, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		if (con.weaponSpecial_enable) {
			// Player should already have enough points.
			if (Tabs.EQUIPMENT.open()) {
				
			}
		}
	}

	@Override
	public boolean validate() {
		if (con.slayer_enable && needSlayerSupport()) {
			return true;
		}
		if (con.weaponSpecial_enable && getSpecialPoints() >= con.weaponSpecial_weapon.getMinimumPower()) {
			return true;
		}
		return false;
	}

	private WidgetChild getSpecialBar() {
		return null;
	}
	
	private boolean isSpecialBarEnabled() {
		return false;
	}
	
	private int getSpecialPoints() {
		return 0;
	}
	
	private boolean needSlayerSupport() {
		switch (con.slayer_task) {
		case DESERT_LIZARDS:
		case ROCK_SLUGS:
		case GARGOYLES:
		case MOGRES:
		case MOLANISKS:
		case MUTATED_ZYGOMITES:
		default:
			return false;
		}
	}
	
}
