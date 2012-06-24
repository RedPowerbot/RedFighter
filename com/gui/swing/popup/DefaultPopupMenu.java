package com.gui.swing.popup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

public class DefaultPopupMenu extends JPopupMenu {

	public DefaultPopupMenu() {
		super();
		addMouseListener(new PopupListener());
	}
	
	private class PopupListener extends MouseAdapter {

		@Override
		public void mouseExited(MouseEvent e) {
			setVisible(false);
		}
		
	}
	
}
