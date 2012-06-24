package com.monster.wiki;

import java.io.PrintStream;

import javax.swing.table.DefaultTableModel;

import com.io.WebIO;
import com.util.PropertiesEx;
import com.util.StringUtil;
import com.util.WebEnumeration;

public class MonsterWikiParser extends PropertiesEx {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5163050018047082791L;
	private static final String ITEM_KEY = "<td align=\"center\"><span class=\"GEIcon\">";
	private static final String STOP_KEY = "Show/hide rare drop table";
	private static final String[] KEYS = { "Release date", "Combat level",
			"Life points", "Experience for killing", "Slayer level",
			"Slayer XP", "Assigned by", "Members only", "Agresive",
			"Poisonous", "Immune to poinson", "Attack style", "Max hit",
			"Weakness", "Always drops", "Examine text" };

	public static String createURL(String name) {
		return "http://runescape.wikia.com/wiki/" + name.replaceAll(" ", "_");
	}

	private PrintStream out;
	private String name;
	private WebEnumeration e;
	private int itemIndex = 0;
	private final PropertiesEx itemProperties = new PropertiesEx();

	public MonsterWikiParser(String name, PrintStream out) {
		super();
		this.name = name;
		this.out = out == null ? System.out : out;
		setProperty("Name", name);
		load();
		parse();
	}

	public MonsterWikiParser(String name) {
		this(name, null);
	}

	/**
	 * Format = <index>.<property>,<value>
	 */
	public PropertiesEx getItemProperties() {
		return itemProperties;
	}

	private void load() {
		out.println("Reading wiki page...");
		e = new WebEnumeration(WebIO.readWebLink(createURL(name)));
		out.println("Finished reading wiki page.");
	}

	private void parse() {
		out.println("Starting parsing...");
		long startTime = System.currentTimeMillis();
		String string;
		while (e.hasMoreElements() && (string = e.nextElement()) != null) {
			if (string.contains(ITEM_KEY)) {
				String value = e.nextElementEx() + ";" + e.nextElementEx()
						+ ";" + e.nextElementEx() + ";" + e.nextElementEx();
				itemProperties.setProperty(Integer.toString(itemIndex), value);
				itemIndex++;
				continue;
			}
			string = StringUtil.stripHTML(string);
			if (string.equals("")) {
				continue;
			}
			if (string.contains(STOP_KEY)) {
				break;
			}
			for (String key : KEYS) {
				if (string.contains(key)) {
					if (e.hasMoreElements()) {
						setProperty(key, e.nextElementEx());
						continue;
					}
				}
			}
		}
		out.print("Done parsing. Took "
				+ StringUtil.format(System.currentTimeMillis()
						- startTime) + " milliseconds.");
		e = null;
	}

	public void listItemProperties(DefaultTableModel model) {
		model.setColumnIdentifiers(new Object[] { "Item name", "Quanity",
				"Rarity", "Prices" });
		for (Object o : itemProperties.values()) {
			String[] strArray = o.toString().split(";");
			model.addRow(strArray);
		}

	}

}
