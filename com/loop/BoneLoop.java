package com.loop;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.wrappers.node.Item;

import com.data.Configuration;

public class BoneLoop extends ScriptLoop {

	private Item bone = null;
	
	public BoneLoop(ActiveScript parent, Configuration con) {
		super(parent, con);
	}

	@Override
	public boolean validate() {
		return false;
	}
	
	@Override
	public void run() {
	}

}
