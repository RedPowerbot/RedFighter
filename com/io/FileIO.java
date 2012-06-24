package com.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {

	public static String[] readFile(String path) {
		try {
			return read(new BufferedReader(new FileReader(new File(path))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String[0];
	}

	public static String[] read(BufferedReader br) {
		try {
			ArrayList<String> list = new ArrayList<>();
			String temp;
			while ((temp = br.readLine()) != null) {
				list.add(temp);
			}
			br.close();
			return list.toArray(new String[list.size()]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String[0];
	}

	public static boolean writeFile(String path, String... source) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					new File(path)));
			for (String temp : source) {
				bw.write(temp);
				bw.newLine();
			}
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
