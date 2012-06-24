package com.item;

import com.io.FileIO;

public class ItemIO {

	public static RItem[] readItemFile(String filePath) {
		String[] src = FileIO.readFile(filePath);
		RItem[] itemArray = new RItem[src.length];
		if (src.length > 0) {
			for (int i = 0; i < src.length; i++) {
				itemArray[i] = new RItem();
				itemArray[i].initFromDataString(src[i]);
			}
		}
		return itemArray;
	}

	public static String[] toDataStringArray(RItem[] array) {
		String[] newArray = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].toDataString();
		}
		return newArray;
	}

	public static void writeItemFile(String filePath, RItem[] array) {
		FileIO.writeFile(filePath, toDataStringArray(array));
	}

}
