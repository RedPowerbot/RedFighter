package com.bank.trip.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.bank.trip.TripList;
import com.bank.trip.TripNPC;
import com.bank.trip.TripObject;
import com.bank.trip.TripStep;
import com.bank.trip.TripTile;
import com.io.FileSystem;
import com.io.FileSystemInstaller;

public class TripIO {
	
	public static boolean writeTripList(String filePath, TripList list) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					filePath)));
			for (TripStep step : list) {
				step.write(bw);
			}
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean readTripList(String filePath, TripList list) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					filePath)));
			String tempStr;
			while ((tempStr = br.readLine()) != null) {
				if (tempStr.equals("<Step>")) {
					TripStep step = readStepType(br.readLine());
					if (step != null) {
						step.read(br);
						list.add(step);
					}
				}
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	private static TripStep readStepType(String value) {
		switch (value.split(":=")[1]) {
		case "TripTile":
			return new TripTile();
		case "TripObject":
			return new TripObject();
		case "TripNPC":
			return new TripNPC();
		default:
			return null;
		}
	}

}
