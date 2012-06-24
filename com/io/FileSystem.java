package com.io;

import java.io.File;

import org.powerbot.game.api.methods.Environment;

public class FileSystem {

	public static void main(String[] args) {
		System.out.println(DIR);
	}
	
	private static final String D = File.separator;
	
	private static String DIR;
	
	static {
		try {
			DIR = Environment.getStorageDirectory() + D + "Legendary Fighter Data";
		} catch (Exception e) {
			DIR = System.getProperty("user.home") + D + "Legendary Fighter Data";
		}
	}
	
	private static final String CONFIGIGURATION_FOLDER = "Configurations";
	private static final String DEFAULT_CONFIGURATION_FILE = "Default Configuration.ini";
	private static final String IMAGE_FOLDER = "Images";
	private static final String ITEM_FOLDER = "Items";
	private static final String ITEM_SAVES_FOLDER = "Saves";
	private static final String ITEM_DATABASE_FILE = "Item Cache.txt";
	private static final String BANK_FOLDER = "Banking Data";
	private static final String BANK_PLUGIN_FOLDER = "Plugins";
	private static final String BANK_TRIP_FOLDER = "Saved Trips";
	
	public static String getDirectory() {
		return DIR;
	}

	public static String getConfigurationDirectory() {
		return getDirectory() + D + CONFIGIGURATION_FOLDER;
	}

	/**
	 * This method will probably not be used anytime soon.
	 */
	@Deprecated
	public static String getDefaultConfigurationFile() {
		return getConfigurationDirectory() + D + DEFAULT_CONFIGURATION_FILE;
	}

	public static String getImageDirectory() {
		return getDirectory() + D + IMAGE_FOLDER;
	}

	public static String getItemDirectory() {
		return getDirectory() + D + ITEM_FOLDER;
	}

	public static String getItemSavesDirectory() {
		return getItemDirectory() + D + ITEM_SAVES_FOLDER;
	}

	public static String getItemDatabaseFile() {
		return getItemDirectory() + D + ITEM_DATABASE_FILE;
	}

	public static String getBankDirectory() {
		return getDirectory() + D + BANK_FOLDER;
	}

	public static String getBankPluginDirectory() {
		return getBankDirectory() + D + BANK_PLUGIN_FOLDER;
	}

	public static String getBankTripDirectory() {
		return getBankDirectory() + D + BANK_TRIP_FOLDER;
	}

}
