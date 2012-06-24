package com.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class WebIO {

	public static String[] readWebLink(String path) {
		try {
			return FileIO.read(new BufferedReader(new InputStreamReader(
					new URL(path).openStream())));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String[0];
	}

}
