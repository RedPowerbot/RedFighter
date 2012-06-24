package com.antiban;

import com.util.PropertiesEx;

public class TimedAntiban implements Antiban {

	public long minimumBreakPeriod = 0;
	public long maximumBreakPeriod = 0;
	public int chance = 100;

	@Override
	public PropertiesEx getProperties() {
		PropertiesEx p = new PropertiesEx();
		p.setProperty("MinimumBreakPeriod", Long.toString(minimumBreakPeriod));
		p.setProperty("MaximumBreakPeriod", Long.toString(maximumBreakPeriod));
		p.setProperty("Chance", Integer.toString(chance));
		return p;
	}

	@Override
	public void setProperties(PropertiesEx p) {
		minimumBreakPeriod = p.Long("MinimumBreakPeriod");
		maximumBreakPeriod = p.Long("MaximumBreakPeriod");
		chance = p.Int("Chance");
	}

	@Override
	public boolean start() {
		return true;
	}

	@Override
	public boolean kill() {
		return true;
	}

	public boolean chance() {
		return Math.random() * 100 <= chance;
	}

}
