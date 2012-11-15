package main;

public class Pos {
	
	/**
	 * positions coordinates for sample items
	 */
	public static float sampleBoxWidth = U.width - U.Converter(50);
	public static float sampleBoxHeight = U.height - U.Converter(50);
	public static float sampleBoxX = U.Converter(25);
	public static float sampleBoxY = U.Converter(25);
	
	//public static float buttonSampleWidth = U.Converter(50);
	//public static float buttonSampleHeight = U.Converter(15);
	//public static float buttonSampleX = U.Converter(2);
	//public static float buttonSampleY = U.height - U.Converter(2) - Pos.buttonSampleHeight;

	public static float sampleTextX = U.width/2;
	public static float sampleTextY = U.height/2;
	
	public static float dayButtonW = U.Converter(15);
	public static float dayButtonH = U.Converter(10);
	public static float dayButtonX = U.Converter(40);
	public static float dayButtonY = U.height - U.Converter(2) - Pos.dayButtonH;
	
	public static float zoomInButtonX = U.width / 2;
	public static float zoomInButtonY = U.height / 2 - U.Converter(18);
	public static float zoomInButtonW = U.Converter(20);
	public static float zoomInButtonH = U.Converter(15);
	
	public static float zoomOutButtonX = zoomInButtonX;
	public static float zoomOutButtonY = U.height / 2 + U.Converter(3);
	public static float zoomOutButtonW = zoomInButtonW;
	public static float zoomOutButtonH = zoomInButtonH;
	
	
	/**
	 * positions coordinates for the items on the screen
	 * Positions are relative to each other.
	 * Do study their structure before changing any value
	 */
	
	// map
	public static float mapWidth = U.width * 3 / 6 - U.Converter(3);
	public static float mapHeight = U.height * 2 / 3 - U.Converter(3);
	public static float mapX = 0 + U.Converter(2);
	public static float mapY = 0 + U.Converter(2);
	
	// weatherPanel
	public static float weatherPanelWidth = U.width / 9 - U.Converter(3);
	public static float weatherPanelHeight = U.height / 9 - U.Converter(3);
	public static float weatherPanelX = mapX;
	public static float weatherPanelY = mapY;

	// daySlider
	public static float daySliderWidth = Pos.mapWidth;
	public static float daySliderHeight = U.height / (3*2) - U.Converter(10);
	public static float daySliderX = Pos.mapX;
	public static float daySliderY = U.height - Pos.daySliderHeight + U.Converter(2);

	// timeSlider
	public static float timeSliderWidth = Pos.mapWidth;
	public static float timeSliderHeight = U.height / (3*2) - U.Converter(2);
	public static float timeSliderX = Pos.mapX;
	public static float timeSliderY = U.height * 2 / 3 - U.Converter(2);
	
	// tweetWindow
	public static float tweetWindowHeight = U.height / 3 - U.Converter(3);
	public static float tweetWindowWidth = U.width / 6 - U.Converter(3);
	public static float tweetWindowX = Pos.mapX + Pos.mapWidth + U.Converter(2);
	public static float tweetWindowY = 0 + U.Converter(2);
	
	// wordCloud
	public static float wordCloudBeforeWidth = U.width / 6 - U.Converter(3);
	public static float wordCloudBeforeHeight = U.height / 3 - U.Converter(3);
	public static float wordCloudBeforeX = Pos.tweetWindowX + Pos.tweetWindowWidth + U.Converter(2);
	public static float wordCloudBeforeY = 0 + U.Converter(2);
	
	// wordCloud
	public static float wordCloudAfterWidth = U.width / 6 - U.Converter(3);
	public static float wordCloudAfterHeight = U.height / 3 - U.Converter(3);
	public static float wordCloudAfterX = Pos.wordCloudBeforeX + Pos.wordCloudBeforeWidth + U.Converter(2);
	public static float wordCloudAfterY = 0 + U.Converter(2);
	
	// keyboard
	public static float keyboardWidth = U.width / 6 - U.Converter(3);
	public static float keyboardHeight = U.height / 3 - U.Converter(3);
	public static float keyboardX = Pos.wordCloudAfterX;
	public static float keyboardY = U.height * 2 / 3 + U.Converter(1);
	

}

