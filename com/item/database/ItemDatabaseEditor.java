package com.item.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.io.FileSystem;

public class ItemDatabaseEditor {

	public static void main(String[] args) {
		try {
			recreate();
			write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean setNoted(int id, boolean noted) {
		for (int i = 0; i < ItemDatabase.length; i++) {
			if (ItemDatabase.ids[i] == id) {
				ItemDatabase.noted[i] = noted;
				if (noted) {
					ItemDatabase.stackable[i] = true;
				}
				return true;
			}
		}
		return false;
	}

	public static boolean setStackable(int id, boolean stackable) {
		for (int i = 0; i < ItemDatabase.length; i++) {
			if (ItemDatabase.ids[i] == id) {
				ItemDatabase.stackable[i] = stackable;
				if (!stackable) {
					ItemDatabase.noted[i] = false;
				}
				return true;
			}
		}
		return false;
	}

	public static boolean remove(int id) {
		for (int i = 0; i < ItemDatabase.length; i++) {
			if (ItemDatabase.ids[i] == id) {
				ItemDatabase.names[i] = "Remove:=" + ItemDatabase.names[i];
				return true;
			}
		}
		return false;
	}

	public static void write() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				FileSystem.getItemDatabaseFile())));
		bw.write(Integer.toString(ItemDatabase.length));
		bw.newLine();
		for (int i = 0; i < ItemDatabase.length; i++) {
			if (ItemDatabase.names[i] == null) {
				System.out.println("Name == null:" + i);
				continue;
			}
			if (ItemDatabase.names[i].startsWith("Remove:=")) {
				try {
					System.out.println("Removed Item ID:"
							+ ItemDatabase.names[i].split(":=")[1]);
				} catch (Exception e) {
				}
				continue;
			}
			bw.write(ItemDatabase.ids[i] + ";" + ItemDatabase.names[i] + ";"
					+ (ItemDatabase.stackable[i] ? "t" : "f") + ";"
					+ (ItemDatabase.noted[i] ? "t" : "f"));
			bw.newLine();
		}
		bw.close();
	}

	private static ArrayList<Integer> loadStackableList() {
		ArrayList<Integer> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("")));
			String s;
			while ((s = br.readLine()) != null) {
				list.add(Integer.parseInt(s));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void recreate() throws IOException {
		if (true) {
			ItemDatabase.loadData();
			ArrayList<Integer> stackables = loadStackableList();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					FileSystem.getItemDatabaseFile())));
			bw.write(ItemDatabase.ids[0] + ";" + ItemDatabase.names[0] + ";f;f");
			bw.newLine();
			int i;
			for (i = 1; i < ItemDatabase.length; i++) {
				if (ItemDatabase.names[i] == null) {
					continue;
				}
				if (!ItemDatabase.stackable[i]
						&& stackables.contains(ItemDatabase.ids[i])) {
					ItemDatabase.stackable[i] = true;
					System.out
							.println("Now Stackable:" + ItemDatabase.names[i]);
				}
				if (ItemDatabase.names[i - 1] != null) {
					if (ItemDatabase.names[i - 1].equals(ItemDatabase.names[i])) {
						if (ItemDatabase.ids[i] - ItemDatabase.ids[i - 1] == 1) {
							if (!ItemDatabase.stackable[i - 1]) {
								ItemDatabase.noted[i] = true;
								ItemDatabase.stackable[i] = true;
							}
						}
					}
				}
				if (ItemDatabase.names[i].contains("Clue Scroll")
						|| ItemDatabase.names[i].contains("null")) {
					ItemDatabase.stackable[i] = false;
					ItemDatabase.noted[i] = false;
				}
				bw.write(ItemDatabase.ids[i] + ";" + ItemDatabase.names[i]
						+ ";" + (ItemDatabase.stackable[i] ? "t" : "f") + ";"
						+ (ItemDatabase.noted[i] ? "t" : "f"));
				bw.newLine();
			}
			bw.close();
			System.out.println("Writing index:=" + i);
			return;
		}
	}

}
