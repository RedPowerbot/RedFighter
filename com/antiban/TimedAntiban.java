package com.antiban;

import com.util.PropertiesEx;

public abstract class TimedAntiban implements Antiban, Runnable {

	public long minimumBreakPeriod = 0;
	public long maximumBreakPeriod = 0;
	public int chance = 100;
	private Thread thread;
	private boolean stop = false;
	private long nextOccurance = -1;
	
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

	public void reset() {
		
	}
	
	public boolean chance() {
		return Math.random() * 100 <= chance;
	}

	@Override
	public boolean start() {
		thread = new Thread(this);
		thread.start();
		return true;
	}

	@Override
	public boolean kill() {
		stop = true;
		return true;
	}
	
	@Override
	public boolean isAlive() {
		return !stop;
	}
	
	

}
