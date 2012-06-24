package com.bank.trip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TripTile implements TripStep {

	public int x = 0;
	public int y = 0;
	public int z = 0;

	@Override
	public void read(BufferedReader br) throws IOException {
		String s;
		while (!(s = br.readLine()).equals(STOP)) {
			String[] array = s.split(":=");
			int i = Integer.parseInt(array[1]);
			switch (array[0]) {
			case "X":
				x = i;
				break;
			case "Y":
				y = i;
				break;
			case "Z":
				z = i;
				break;
			}
		}
	}

	@Override
	public void write(BufferedWriter bw) throws IOException {
		bw.write("<STEP>");
		bw.write("TYPE:=TripTile");
		bw.write("X:=" + x);
		bw.write("Y:=" + y);
		bw.write("Z:=" + z);
		bw.write(STOP);
	}

}
