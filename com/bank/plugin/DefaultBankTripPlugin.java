package com.bank.plugin;

import com.bank.banking.BankingInstructions;
import com.bank.banking.DefaultBankingInstructions;
import com.bank.trip.TripList;
import com.util.PropertiesEx;

public class DefaultBankTripPlugin implements BankPlugin {

	private PropertiesEx propeties = new PropertiesEx();
	private PluginAttribute attributes = new DefaultBankPluginAttributes();
	private BankingInstructions bankingInstructions = new DefaultBankingInstructions();

	private TripList toBankTripList;
	private TripList toMobTripList;

	@Override
	public PluginAttribute getAttributes() {
		return attributes;
	}

	@Override
	public boolean needToBank() {
		return bankingInstructions.needToBank();
	}

	@Override
	public boolean atBank() {
		return false;
	}

	@Override
	public boolean atMobLocation() {
		return false;
	}

	@Override
	public boolean lost() {
		return false;
	}

	@Override
	public boolean goToBank() {
		return false;
	}

	@Override
	public boolean goToMobLocation() {
		return false;
	}

}
