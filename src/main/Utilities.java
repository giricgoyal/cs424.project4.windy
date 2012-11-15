/**
 * 
 */
package main;

import markers.AbstractMarker;
import markers.DefaultMarker;
import markers.MarkerType;
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
	
	public static int mapMaxW = 5215;
	public static int mapMaxH = 2652;
	
	public static AbstractMarker getMarkerType(AbstractMarker marker, MarkerType markerType) {

		switch (markerType) {
		case DEFAULT_MARKER:
		    return new DefaultMarker(marker.getP(), marker.getX(), marker.getY());
		default:
		    return null;
	    }
	}
}
	
