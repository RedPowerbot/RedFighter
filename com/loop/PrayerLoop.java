package com.loop;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

import com.data.Configuration;
import com.item.special.Potion;
import com.paint.skill.Skill;

public class PrayerLoop extends ScriptLoop {

	public PrayerLoop(ActiveScript parent, Configuration con) {
		super(parent, con);
	}

	@Override
	public void run() {
		if (needToRestorePoints()) {
			switch (con.prayer_method) {
			case POTION:
				Item potion = Potion.getItemBySkillsIndex(Skill.PRAYER, Inventory.getItems());
				if (potion != null) {
					potion.getWidgetChild().interact("Drink");
					waitForAction(3000);
				}
			case ALTAR:
				SceneObject altar = SceneEntities.getNearest(con.prayer_altarID);
				if (altar != null) {
					// TODO This probably isn't gonna cut it...
					if (altar.isOnScreen()
							|| (Walking.walk(altar.getLocation())
								&& altar.isOnScreen())) {
						// TODO Double check the action string
						altar.interact("Pray");
						waitOnDestination(3000);
						waitForAction(2000);
					}
				}
			}
		}
		if (!prayersAreOn()) {
			// TODO Figure threw interfaces
		}
	}

	@Override
	public boolean validate() {
		return con.prayer_enable && (needToRestorePoints() || !prayersAreOn());
	}

	public boolean prayersAreOn() {
		return true;
	}

	public boolean needToRestorePoints() {
		return false;
	}

	public int getPrayerPoints() {
		return 0;
	}

}
