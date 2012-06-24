package com.antiban;

import com.util.PropertiesEx;

public interface Antiban {

	/**
	 * Returns a new PropertiesEx of the current settings.
	 */
	public PropertiesEx getProperties();

	/**
	 * Sets the current Antiban's setting from the PropertiesEx inputed.
	 */
	public void setProperties(PropertiesEx properties);

	/**
	 * Start the antiban. Helps with thread safety.
	 */
	public boolean start();

	/**
	 * Stops the antiban. Helps with thread safety.
	 */
	public boolean kill();

	public boolean isAlive();
	
}
