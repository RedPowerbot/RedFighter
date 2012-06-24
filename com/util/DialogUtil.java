package com.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class DialogUtil {
	
	public static boolean askPermission(String msg, Component c) {
		return JOptionPane.showConfirmDialog(c, msg) == JOptionPane.YES_OPTION;
	}

	public static void yell(String msg, Component c) {
		JOptionPane.showMessageDialog(c, msg);
	}
	
	public static String getInput(String msg, Component c) {
		return JOptionPane.showInputDialog(c, msg);
	}

}
