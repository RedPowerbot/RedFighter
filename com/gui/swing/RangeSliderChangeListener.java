package com.gui.swing;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RangeSliderChangeListener implements ChangeListener {
	
	private JRangeSlider slider;
	
	public RangeSliderChangeListener(JRangeSlider slider) {
		this.slider = slider;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		slider.fireChangeEvent();
	}
}
