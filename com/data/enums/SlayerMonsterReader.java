package com.data.enums;

import com.io.FileIO;
import com.io.FileSystem;
import com.io.WebIO;
import com.util.SimpleEnumeration;
import com.util.StringUtil;
import com.util.WebEnumeration;

public class SlayerMonsterReader {

	public static void main(String[] args) {
		System.out.println("Done.");
		SimpleEnumeration<String> e = new SimpleEnumeration<>(FileIO.readFile(FileSystem.getDirectory() + "/tempJars/slayerdata.txt"));
		boolean found = false;
		while (e.hasMoreElements()) {
			if (!found) {
				if (e.nextElement().equalsIgnoreCase("///Start////")) {
					System.out.println("Found");
					found = true;
				}
				continue;
			}

			String text = e.nextElement();
			if (text.equals("")) {
				continue;
			}
			StringBuilder b = new StringBuilder();
			String lv = text;
			e.nextElement();
			String name = e.nextElement();
			String cbl = e.nextElement();
			String lp = e.nextElement();
			String xp = e.nextElement();
			String eq = e.nextElement();
			String drops = e.nextElement();
			String loc = e.nextElement();
			b.append(name.toUpperCase().replace(" ", "_"));
			b.append("(");
			b.append(lv);
			b.append(", ");
			b.append("\"" + name + "\"");
			b.append(", ");
			if (cbl.contains(" ")) {
				b.append(toString(cbl));
			} else {
				b.append(cbl);
			}
			b.append(", ");
			if (xp.contains(" ")) {
				b.append(toString(xp));
			} else {
				b.append(xp);
			}
			if (eq.equalsIgnoreCase("none")) {
				b.append(", null");
			} else {
				b.append(", SlayerEquipment.");
				b.append(eq.toUpperCase().replace(" ", "_"));
			}
//			b.append(eq.toUpperCase().replace(" ", "_"));
			b.append("),");
			System.out.println(b.toString());
		}
	}

	private static String toString(String array) {
		return "new int[] {" + array.replace(" ", ", ") + "}";
	}
	
}
