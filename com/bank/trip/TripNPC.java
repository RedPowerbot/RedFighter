package com.bank.trip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TripNPC implements TripStep {

	public int id = 0;
	public String name = "null";
	public String action = "null";
	public int x = 0;
	public int y = 0;
	public int z = 0;

	@Override
	public void read(BufferedReader br) throws IOException {
		String s;
		while (!(s = br.readLine()).equals(STOP)) {
			String[] array = s.split(":=");
			switch (array[0]) {
			case "ID":
				Integer.parseInt(array[1]);
				break;
			case "X":
				Integer.parseInt(array[1]);
				break;
			case "Y":
				Integer.parseInt(array[1]);
				break;
			case "Z":
				Integer.parseInt(array[1]);
				break;
			case "NAME":
				name = array[1];
			case "Action":
				action = array[1];
			}
		}
	}

	@Override
	public void write(BufferedWriter bw) throws IOException {
		bw.write("<STEP>");
		bw.write("TYPE:=TripNPC");
		bw.write("ID:=" + id);
		bw.write("NAME:=" + name);
		bw.write("ACTION:=" + action);
		bw.write("X:=" + x);
		bw.write("Y:=" + y);
		bw.write("Z:=" + z);
		bw.write(STOP);
	}

}
