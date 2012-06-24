package com.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;

public class Design {

	public static final RenderingHints antialiasing = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	public static final Color black = new Color(0, 0, 0);
	public static final Color red = new Color(204, 0, 0);
	public static final Color redA = new Color(204, 0, 0, 100);
	public static final Color white = new Color(255, 255, 255);

	public static final BasicStroke stroke1 = new BasicStroke(2);
	public static final BasicStroke stroke2 = new BasicStroke(1);

	private static final String FONT_FAMILY = "Calibri";
	
	public static final Font font1 = new Font(FONT_FAMILY, 0, 11);
	public static final Font font2 = new Font(FONT_FAMILY, 0, 16);
	public static final Font font11 = new Font(FONT_FAMILY, 0, 11);
	public static final Font font16 = new Font(FONT_FAMILY, 0, 16);
	public static final Font font20_3 = new Font(FONT_FAMILY, 3, 20);
	public static final Font font16_3 = new Font(FONT_FAMILY, 3, 16);
	public static final Font font18_3 = new Font(FONT_FAMILY, 3, 18);
	public static final Font font12 = new Font(FONT_FAMILY, 0, 12);
	public static final Font font15 = new Font("Calirbi", 0, 15);

}
