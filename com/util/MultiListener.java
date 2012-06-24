package com.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class MultiListener implements ActionListener, ChangeListener,
		ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		actionPerformed(new ActionEvent(e.getSource(), e.getID(),
				e.paramString()));
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		actionPerformed(new ActionEvent(e.getSource(), 0, null));
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);

}
