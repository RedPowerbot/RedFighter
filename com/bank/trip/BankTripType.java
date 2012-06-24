package com.bank.trip;

import java.util.HashMap;

public class BankTripType {
	
	private HashMap<String, Class<? extends TripStep>> map = new HashMap<String, Class<? extends TripStep>>();
	
	public BankTripType() {
		map.put("TripStep", TripStep.class);
		map.put("TripTile", TripTile.class);
		map.put("TripNPC", TripNPC.class);
		map.put("TripObject", TripObject.class);
	}
	
	public TripStep getStep(String type) throws InstantiationException, IllegalAccessException {
		Class<? extends TripStep> c = map.get(type);
		if (c != null) {
			return c.newInstance();
		}
		return null;
	}
	
}
