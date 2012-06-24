package com.loop;

import java.util.logging.Logger;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.node.Item;

import com.data.Configuration;

public abstract class ScriptLoop implements Task, Condition {

	protected Configuration con;
	private Logger log;
	
	private final Condition isMovingCon = new Condition() {
		
		@Override
		public boolean validate() {
			return getMyPlayer().isMoving();
		}
		
	};
	
	public void setStatus(String status) {
		con.status = status;
	}
	
	public ScriptLoop(ActiveScript parent, Configuration con) {
		this.con = con;
		if (parent != null) {
			this.log = parent.log;
		}
	}
	
	public int[] getInventoryIds() {
		Item[] itemArray = Inventory.getItems();
		int[] idArray = new int[itemArray.length];
		for (int i = 0; i < idArray.length; i++) {
			idArray[i] = itemArray[i].getId();
		}
		return idArray;
	}
	
	public Item getItem(int... id) {
		Item[] itemArray = Inventory.getItems();
		for (Item i : itemArray) {
			for (int tempID : id) {
				if (i.getId() == tempID) {
					return i;
				}
			}
		}
		return null;
	}
	
	public void log(String msg) {
		log.info(msg);
	}
	
	public void severe(String msg) {
		log.severe(msg);
	}

	public boolean wait(int time) {
		Time.sleep(time);
		return true;
	}
	
	public boolean wait(int min, int max) {
		return wait(Random.nextInt(min, max));
	}
	
	public Player getMyPlayer() {
		return Players.getLocal();
	}
	
	/**
	 * Waits on a Condition
	 * @param con
	 * @param isTrue
	 * @param timeout - if -1, waits till true. No exceptions.
	 * @return
	 */
	public int waitOnCondition(Condition con, boolean isTrue, int timeout) {
		long start = System.currentTimeMillis();
		while (con.validate() != isTrue) {
			if (timeout > 0 && System.currentTimeMillis() - start > timeout) {
				return -1;
			}
			wait(10);
		}
		return (int) (System.currentTimeMillis() - start);
	}
	
	public boolean waitOnConditionB(Condition con, boolean isTrue, int timeout) {
		return waitOnCondition(con, isTrue, timeout) != -1;
	}
	
	public boolean waitOnDestination(int timout) {
		return waitOnConditionB(isMovingCon, true, 3000)
			&& waitOnConditionB(isMovingCon, false, -1);
	}
	
	public boolean waitForAction(int timeout) {
		long start = System.currentTimeMillis();
		Player me = getMyPlayer();
		while (me.getAnimation() == -1) {
			if (System.currentTimeMillis() - start > timeout) {
				return false;
			}
			wait(10);
		}
		while (me.getAnimation() != -1) {
			if (System.currentTimeMillis() - start > timeout) {
				return false;
			}
			wait(10);
		}
		return true;
	}
	
	public boolean waitForAnimation(int animation, int timeout) {
		long start = System.currentTimeMillis();
		Player me = getMyPlayer();
		while (me.getAnimation() != animation) {
			if (System.currentTimeMillis() - start > timeout) {
				return false;
			}
			wait(10);
		}
		return true;
	}
	
	public boolean waitForMotion(boolean moving, int timeout) {
		return waitOnConditionB(isMovingCon, moving, timeout);
	}
	
	public Strategy toStragety() {
		return new Strategy(this, this);
	}
	
}