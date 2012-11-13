/**
 * 
 */
package main;

/**
 * @author giric
 *
 */
public class Positions {
	
	public static float sampleBoxWidth = Utilities.width - Utilities.Converter(50);
	public static float sampleBoxHeight = Utilities.height - Utilities.Converter(50);
	public static float sampleBoxX = Utilities.Converter(25);
	public static float sampleBoxY = Utilities.Converter(25);
	
	//public static float buttonSampleWidth = Utilities.Converter(50);
	//public static float buttonSampleHeight = Utilities.Converter(15);
	//public static float buttonSampleX = Utilities.Converter(2);
	//public static float buttonSampleY = Utilities.height - Utilities.Converter(2) - Positions.buttonSampleHeight;

	public static float sampleTextX = Utilities.width/2;
	public static float sampleTextY = Utilities.height/2;
	
	public static float dayButtonW = Utilities.Converter(15);
	public static float dayButtonH = Utilities.Converter(10);
	public static float dayButtonX = Utilities.Converter(40);
	public static float dayButtonY = Utilities.height - Utilities.Converter(2) - Positions.dayButtonH;
	
	public static float zoomInButtonX = Utilities.Converter(10);
	public static float zoomInButtonY = Utilities.height - Utilities.Converter(18);
	public static float zoomInButtonW = Utilities.Converter(20);
	public static float zoomInButtonH = Utilities.Converter(15);
	
	public static float zoomOutButtonX = zoomInButtonX;
	public static float zoomOutButtonY = Utilities.height + Utilities.Converter(3);
	public static float zoomOutButtonW = zoomInButtonW;
	public static float zoomOutButtonH = zoomInButtonH;
	
}
