package com.item.TOOLS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.io.WebIO;
import com.util.WebEnumeration;

public class WebSaver {

	public static void main(String[] args) {
		WebEnumeration e = getE();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:/Users/Cody/Desktop/web save.txt")));
			while (e.hasMoreElements()) {
				bw.write(e.nextElementEx());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Done");
	}
	
	private static WebEnumeration getE() {
		System.out.println("Starting.");
		WebEnumeration e = new WebEnumeration(WebIO.readWebLink("http://runescape.wikia.com/wiki/Slayer_monsters")) {
			
//			@Override
//			public String nextElementEx() {
//				String t;
//				while ((t = super.nextElementEx()).equals("")) {
//					
//				}
//				return t;
//			}
			
		};
		System.out.println("Finished Reading.");
		return e;
	}
	
}
