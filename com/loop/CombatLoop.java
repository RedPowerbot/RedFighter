package com.loop;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.Character;
import org.powerbot.game.api.wrappers.interactive.NPC;

import com.data.Configuration;
import com.monster.Monster;

public class CombatLoop extends ScriptLoop {

	public CombatLoop(ActiveScript parent, Configuration con) {
		super(parent, con);
	}

	private NPC currentNPC;
	
	@Override
	public void run() {
		setStatus("Scanning for Npcs...");
		if (findNPC()) {
			if (!currentNPC.isOnScreen()) {
				setStatus("Walking to " + currentNPC.getName());
				Walking.walk(currentNPC.getLocation());
			}
			setStatus("Preparing for combat.");
			if (prepare()) {
				setStatus("Attacking " + currentNPC.getName());
				currentNPC.interact(getAttackAction());
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
		return getMyPlayer().getSpeed() == 0 && getMyPlayer().getInteracting() == null;
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
	
	public boolean findNPC() {
		if (!isNPCValid(currentNPC)) {
			currentNPC = getNPC();
			return isNPCValid(currentNPC);
		}
		return true;
	}
	
	public NPC getNPC() {
		if (getMyPlayer().isInCombat()) {
			Character inter = getMyPlayer().getInteracting();
			if (inter.validate() && inter.getHpPercent() > 0) {
				return (NPC) inter;
			}
		}
		return NPCs.getNearest(NPCFilter);
	}
	
	public NPC getNextNPC() {
		return NPCs.getNearest(nextNPCFilter);
	}
	
	public boolean isNPCValid(NPC npc) {
		return npc != null
			&& npc.validate()
			&& npc.getHpPercent() > 0
			&& (!npc.isInCombat()
				|| npc.getInteracting().equals(getMyPlayer()))
			;
	}
	
	//--------- Filters-----------//
	
	private final Filter<NPC> NPCFilter = new Filter<NPC>() {
		
		@Override
		public boolean accept(NPC n) {
			if (n == null
					|| !n.validate()
					|| n.getHpPercent() <= 0) {
				return false;
			}
			if (!con.cagedSupport_enable && !n.getLocation().canReach()) {
				return false;
			}
			if (n.isInCombat()) {
				if (con.attackBusyMobs_enable) {
					// TODO Count how many players are currently attacking this NPC.
				} else {
					return false;
				}
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
			return n != null 
				&& !n.equals(getMyPlayer().getInteracting()) 
				&& NPCFilter.accept(n);
		}
		
	};

}
