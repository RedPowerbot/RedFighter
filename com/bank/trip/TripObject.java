package com.bank.trip;

import java.io.BufferedWriter;
import java.io.IOException;

public class TripObject extends TripNPC implements TripStep {

	@Override
	public void write(BufferedWriter bw) throws IOException {
		bw.write("<STEP>");
		bw.write("TYPE:=TripObject");
		bw.write("ID:=" + id);
		bw.write("NAME:=" + name);
		bw.write("ACTION:=" + action);
		bw.write("X:=" + x);
		bw.write("Y:=" + y);
		bw.write("Z:=" + z);
		bw.write(STOP);
	}

}
