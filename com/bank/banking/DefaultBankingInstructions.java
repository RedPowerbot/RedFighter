package com.bank.banking;

import com.item.ItemList;

public class DefaultBankingInstructions implements BankingInstructions {

	private final ItemList preferedInventory = new ItemList();

	@Override
	public boolean needToBank() {
		return false;
	}

	@Override
	public ItemList getPreferedInventory() {
		return preferedInventory;
	}

}
