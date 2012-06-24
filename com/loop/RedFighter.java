package com.loop;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;

import com.data.Configuration;
import com.gui.RFGui;
import com.io.FileSystemInstaller;
import com.item.database.ItemDatabase;
import com.monster.Monster;
import com.monster.MonsterList;

@Manifest(authors = { "RedDevil" }, name = "Red Fighter")
public class RedFighter extends ActiveScript {

	public static final boolean DEBUG = true;
	public static boolean INITIALIZED = false;

	public static synchronized void initialize(boolean inBackground) {
		if (!FileSystemInstaller.installed()) {
			System.out.println("COULD NOT INSTALL FILE SYSTEM!!!");
			return;
		}
		if (!INITIALIZED) {
			if (inBackground) {
				ItemDatabase.loadInBackground();
			} else {
				ItemDatabase.loadData();
			}
			INITIALIZED = true;
		}
	}

	public Configuration con;

	@Override
	protected void setup() {
		initialize(false);
		if (!initSystem()) {
			log.severe("Could not initialize script system.");
			stop();
			return;
		}
		con = new Configuration(this);
		if (!initGui()) {
			log.severe("Could not initialize gui.");
			stop();
			return;
		}
		for (ScriptLoop sl : con.loopPool) {
			provide(sl.toStragety());
		}
	}

	private boolean initSystem() {
		return FileSystemInstaller.installed();
	}
	
	private boolean initGui() {
		RFGui gui = new RFGui(con);
		MonsterList list = gui.getAvailableMonsterModel();
		gui.setVisible(true);
		while (gui != null && gui.isVisible()) {
			Time.sleep(100);
			NPC[] npcArray = NPCs.getLoaded();
			for (NPC npc : npcArray) {
				Monster m = new Monster(npc.getId(), npc.getLevel(), npc.getName());
				if (!con.monsterList.contains(m) && !list.contains(m)) {
					list.add(m);
				}
			}
		}
		return gui.isCompleted();
	}
	
}
