package com.item.TOOLS;

import java.util.Enumeration;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class UIPrinter {

	public static void main(String[] args) {
		// UIManager.getDefaults().put("Panel.background", Color.black);
		// System.out.println(UIManager.getDefaults().getColor("Panel.background"));
		Enumeration<Object> e = UIManager.getDefaults().keys();
		UIDefaults def = UIManager.getDefaults();
		while (e.hasMoreElements()) {
			Object item = e.nextElement();
			if (item.toString().contains("Dialog")
					&& item.toString().contains("foreground")) {
				System.out.println(item + " := " + def.get(item));
			}
		}
	}

}
