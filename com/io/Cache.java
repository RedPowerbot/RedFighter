package com.io;

import java.awt.Component;

import javax.swing.ListModel;
import javax.swing.table.TableModel;

public interface Cache<E> extends TableModel, ListModel<E> {

	public String acceptNewName(String name);

	public boolean createNewElement(String name);

	public boolean loadSettings();

	public boolean saveSettings();

	public void delete(int index);

	public Component getGui();

}
