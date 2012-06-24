package com.bank.trip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public interface TripStep {

	public static final String STOP = "<STOP>";

	public void read(BufferedReader br) throws IOException;

	public void write(BufferedWriter bw) throws IOException;

}
