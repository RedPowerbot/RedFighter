package com.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StringUtil {

	private static final DecimalFormat decimalFormat = new DecimalFormat(
			"###,###,###,###,###.##");
	public static final String BREAK_REPLACEMENT = "[br]";
	public static final String LINE_BREAK = "\n";
	public static final String PROPERTY_SEPERATOR = ">>>";

	public static String format(double d) {
		return decimalFormat.format(d);
	}
	
	public static String format(int i) {
		return decimalFormat.format(i);
	}
	
	public static String format(long i) {
		return decimalFormat.format(i);
	}
	
	public static String formatFileName(File f) {
		String name = f.getName();
		return name.substring(0, name.lastIndexOf("."));
	}

	public static String capatilize(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		StringBuilder b = new StringBuilder(s.toLowerCase());
		b.setCharAt(0, Character.toUpperCase(s.charAt(0)));
		return b.toString();
	}

	public static long getIntegerValue(String s) {
		StringBuilder b = new StringBuilder();
		boolean negative = false;
		for (char c : s.toCharArray()) {
			if (Character.toString(c).equals("-")) {
				negative = !negative;
			}
			if (isDigit(c) != -1) {
				b.append(c);
			}
		}
		return Long.parseLong(b.toString()) * (negative ? -1 : 1);
	}

	public static boolean isDigit(String s) {
		if (s != null && s.length() > 0) {
			for (char c : s.toCharArray()) {
				if (isDigit(c) == -1) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static int isDigit(char c) {
		try {
			return Integer.parseInt(Character.toString(c));
		} catch (Exception e) {
			return -1;
		}
	}

	public static SimpleEnumeration<String> toEnumeration(String... array) {
		return new SimpleEnumeration<String>(array);
	}

	public static String stripHTML(String s) {
		if (s == null) {
			return "";
		}
		return s.replaceAll("\\<.*?>", "").replaceAll(", ", ",")
				.replaceAll("&ndash;", ",");
	}
	
	public static String createStringFromArray(String... array) {
		StringBuilder b = new StringBuilder();
		for (int i=0; i < array.length; i++) {
			b.append(array[i]);
			b.append("\n");
		}
		return b.toString();
	}
	
	/**
	 * Same effect as String.indexOf(String, int), but accpets multiple keys, and can search
	 * forwards and backwards from begin.
	 * @param s - String to be found.
	 * @param begin - Beginning index.
	 * @param inc - True if forwards, false if backwards.
	 * @param keys - Key(s) to be located.
	 * @return - Index of first found key, or -1 if none are found.
	 */
	public static int multipleIndexOf(String s, boolean equals,  int begin, boolean inc, String... keys) {
		char[] charArray = s.toCharArray();
		int dev = inc ? 1 : -1;
		for (int i = begin; i > 0 && i < s.length(); i += dev) {
			NextKey :
				for (String key : keys) {
					char[] tempCharArray = key.toCharArray();
					for (int j = 0; j < tempCharArray.length; j++) {
						if (charArray[i + j] != tempCharArray[j]) {
							continue NextKey;
						}
					}
					return i;
				}
		}
		return -1;
	}

	/**
	 * Splits a string into a string away searching for keys.
	 * 
	 * @param source
	 *            - Source String
	 * @param includeKey
	 *            - Include keys in results
	 * @param ignoreKey
	 *            - If 'ignoreKey' will put the key in the next string, rather
	 *            then the one it is found on.
	 * @param keys
	 *            - Key(s) to be searched for.
	 * @return
	 */
	public static String[] multipleSplit(String source, boolean includeKey, boolean ignoreKey, String... keys) {
		ArrayList<String> list = new ArrayList<>();
		char[] tempArray, srcArray = source.toCharArray();
		int i, j, k, begin = 0;
		for (i = 0; i < srcArray.length; i++) {
			Key:
				for (j = 0; j < keys.length; j++) {
					tempArray = keys[j].toCharArray();
					for (k = 0; k < tempArray.length; k++) {
						if (srcArray[i + k] != tempArray[k]) {
							continue Key;
						}
					}
					list.add(source.substring(begin, i + (includeKey ? tempArray.length : 0)));
					i = begin = i + (ignoreKey && !includeKey ? 0 : tempArray.length);
				}
		};
		if (i - begin > 0) {
			list.add(source.substring(begin, source.length()));
		}
		return list.toArray(new String[list.size()]);
	}

}
