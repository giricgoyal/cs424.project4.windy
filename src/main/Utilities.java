/**
 * 
 */
package main;

import processing.core.PFont;

/**
 * @author giric
 *
 */
public class Utilities {
	public static boolean isWall = false;
	
	public static float Converter(float pixel) {
		if (isWall)
			return 12*pixel;
		else
			return 2*pixel;
	}
	
	public static float Converter(double pixel) {
		if (isWall)
			return (float)(12*pixel);
		else
			return (float)(2*pixel);
	}
	
	public static float width = Converter(680);
	public static float height = Converter(192);
	
	public static CS424_Project4_Group4 CS424_Project4_Group4;
	
	public static PFont font;
	
	public static boolean sampleText = false;
	


}
	
