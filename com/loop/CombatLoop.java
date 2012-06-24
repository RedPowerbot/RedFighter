package com.loop;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.interactive.NPC;

import com.data.Configuration;
import com.monster.Monster;

public class CombatLoop extends ScriptLoop {

	private NPC currentNPC;
	private NPC nextNPC;
	
	private final Filter<NPC> NPCFilter = new Filter<NPC>() {
		
		@Override
		public boolean accept(NPC n) {
			if (n == null
					|| n.getHpPercent() <= 0) {
				return false;
			}
			if (!con.attackBusyMobs_enable && n.isInCombat() && n.getInteracting() != getMyPlayer()) {
				return false;
			}
			for (Monster m : con.monsterList) {
				if (m.getId() == n.getId()
						&& m.getLevel() == n.getLevel()) {
					return true;
				}
			}
			return false;
		}
		
	};
	
	/**
	 * Need to find a better alternative.
	 */
	private final Filter<NPC> nextNPCFilter = new Filter<NPC>() {
		
		@Override
		public boolean accept(NPC n) {
			if (n == currentNPC) {
				return false;
			}
			if (n == null
					|| n.getHpPercent() <= 0) {
				return false;
			}
			if (!con.attackBusyMobs_enable && n.isInCombat() && n.getInteracting() != getMyPlayer()) {
				return false;
			}
			for (Monster m : con.monsterList) {
				if (m.getId() == n.getId()
						&& m.getLevel() == n.getLevel()) {
					return true;
				}
			}
			return false;
		}
		
	};
	
	public CombatLoop(ActiveScript parent, Configuration con) {
		super(parent, con);
	}

	// TODO More dynamic waiting
	@Override
	public void run() {
		if (!getMyPlayer().isInCombat()) {
			setStatus("Scanning for Npcs...");
			NPC tempNPC = getNPC(); // Avoids unnecessary checks
			if (tempNPC == null) {
				setStatus("Couldn't find Npc.");
				return;
			}
			if (!tempNPC.isOnScreen()) {
				setStatus("Walking to " + tempNPC.getName());
				Walking.walk(tempNPC.getLocation());
			}
			setStatus("Preparing for combat.");
			if (prepare()) {
				setStatus("Attacking " + tempNPC.getName());
				tempNPC.interact(getAttackAction());
				wait(300, 700);
				waitForMotion(false, 3000);
			} else {
				severe("Could not prepare for battle. Please check equipment!!!");
				wait(5000);
				return;
			}
		}
	}

	@Override
	public boolean validate() {
		return !getMyPlayer().isInCombat();
	}
	
	public NPC getNPC() {
		if (currentNPC == null || currentNPC.getHpPercent() <= 0) {
			currentNPC = NPCs.getNearest(NPCFilter);
		}
		return currentNPC;
	}
	
	public NPC getNextNPC() {
		if (nextNPC == null || nextNPC.getHpPercent() <= 0) {
			nextNPC = NPCs.getNearest(nextNPCFilter);
		}
		return nextNPC;
	}
	
	private boolean prepare() {
		return true;
	}
	
	private String getAttackAction() {
		switch  (con.fightMode) {
		case MELEE:
		case RANGE:
			return "Attack";
		case MAGIC: return "Cast";
		}
		return "";
	}

}
