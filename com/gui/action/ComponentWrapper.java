package com.gui.action;

import java.awt.Component;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;

import com.gui.swing.JRangeSlider;

public class ComponentWrapper {

	private Component c;

	public ComponentWrapper(Component c) {
		this.c = c;
	}

	public boolean isSelected() {
		return ((JToggleButton) c).isSelected();
	}

	public void setSelected(boolean b) {
		((JToggleButton) c).setSelected(b);
	}

	public int getSelectedIndex() {
		return ((JComboBox<?>) c).getSelectedIndex();
	}

	public void setSelectedIndex(int i) {
		((JComboBox<?>) c).setSelectedIndex(i);
	}

	public int getSliderValue() {
		return ((JSlider) c).getValue();
	}

	public void setSliderValue(int i) {
		((JSlider) c).setValue(i);
	}

	public int getSpinnerValue() {
		return ((SpinnerNumberModel) ((JSpinner) c).getModel()).getNumber()
				.intValue();
	}

	public void setSpinnerValue(int i) {
		((JSpinner) c).setValue(i);
	}

	public int[] getRangeValues() {
		DefaultBoundedRangeModel m = (DefaultBoundedRangeModel) ((JRangeSlider) c)
				.getModel();
		return new int[] { m.getValue(), m.getValue() + m.getExtent() };
	}

	public void setRangeValues(int[] args) {
		DefaultBoundedRangeModel m = (DefaultBoundedRangeModel) ((JRangeSlider) c)
				.getModel();
		m.setValue(args[0]);
		m.setExtent(args[1] - args[0]);
	}

}
