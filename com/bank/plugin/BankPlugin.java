package com.bank.plugin;

public interface BankPlugin {

	public PluginAttribute getAttributes();

	public boolean needToBank();

	public boolean atBank();

	public boolean atMobLocation();

	public boolean lost();

	public boolean goToBank();

	public boolean goToMobLocation();

}
