package com.io;

import java.io.File;
import java.io.IOException;

import com.util.SimpleEnumeration;

public class FileSystemInstaller {

	public static void main(String[] args) {
		System.out.println(installed());
	}
	
	public static boolean installed() {
		try {
			install();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void install() throws IOException {
		SimpleEnumeration<String> filePaths = getEnumeration();
		File f = null;
		while (filePaths.hasMoreElements()) {
			f = new File(filePaths.nextElement());
			System.out.println();
			System.out.print(f.getPath());
			if (!f.exists()) {
				System.out.print(" - Did not exist.");
				if (f.getName().contains(".")) {
					f.createNewFile();
				} else {
					f.mkdir();
				}
			}
		}
	}

	private static SimpleEnumeration<String> getEnumeration() {
		return new SimpleEnumeration<String>(new String[] {
				FileSystem.getDirectory(),
				FileSystem.getConfigurationDirectory(),
				FileSystem.getItemDirectory(),
				FileSystem.getItemSavesDirectory(),
				FileSystem.getItemDatabaseFile(),
				FileSystem.getImageDirectory(),
				FileSystem.getBankDirectory(),
				FileSystem.getBankPluginDirectory(),
				FileSystem.getBankTripDirectory()});
	}

}
