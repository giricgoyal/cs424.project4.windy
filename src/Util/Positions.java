/**
 * 
 */
package Util;


import com.jogamp.opengl.util.texture.TextureData.Flusher;

/**
 * @author giric
 *
 */
public class Positions {
	
	/**
	 * positions coordinates for sample items
	 */
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
	
	/**
	 * positions coordinates for the items on the screen
	 * Positions are relative to each other.
	 * Do study their structure before changing any value
	 */
	
	
	// map
	public static float mapWidth = Utilities.width * 3 / 6 - Utilities.Converter(3);
	public static float mapHeight = Utilities.height * 2 / 3 - Utilities.Converter(3);
	public static float mapX = 0 + Utilities.Converter(2);
	public static float mapY = 0 + Utilities.Converter(2);
	
	// day buttons
	public static float dayButtonX = mapX;
	public static float dayButtonY = Utilities.height * 6 / 7;
	public static float dayButtonW = mapWidth/21;
	public static float dayButtonH = Utilities.height - dayButtonY - Utilities.Converter(3);
	
	// weatherPanel
	public static float weatherPanelWidth = Utilities.width / 9;
	public static float weatherPanelHeight = Utilities.height / 6;
	public static float weatherPanelX = mapX;
	public static float weatherPanelY = mapY;

	// daySlider
	public static float daySliderWidth = Positions.mapWidth;
	public static float daySliderHeight = Utilities.height / (3*2) - Utilities.Converter(10);
	public static float daySliderX = Positions.mapX;
	public static float daySliderY = Utilities.height - Positions.daySliderHeight + Utilities.Converter(2);

	// timeSlider
	public static float timeSliderWidth = Positions.mapWidth;
	public static float timeSliderHeight = Utilities.height / (3*2) - Utilities.Converter(3);
	public static float timeSliderX = Positions.mapX;
	public static float timeSliderY = Utilities.height * 2 / 3 + Utilities.Converter(3);
	public static float lockHeight = timeSliderHeight;
	public static float lockWidth = lockHeight*2/5;
	
	// tweetWindow
	public static float tweetWindowHeight = Utilities.height / 3 - Utilities.Converter(3);
	public static float tweetWindowWidth = Utilities.width / 6 - Utilities.Converter(2);
	public static float tweetWindowX = Positions.mapX + Positions.mapWidth + Utilities.Converter(2);
	public static float tweetWindowY = 0 + Utilities.Converter(2);
	public static float tweetWidth;
	public static float tweetHeight;
	
	// wordCloud
	public static float wordCloudBeforeWidth = Utilities.width / 6 - Utilities.Converter(2);
	public static float wordCloudBeforeHeight = Utilities.height / 3 - Utilities.Converter(3);
	public static float wordCloudBeforeX = Positions.tweetWindowX + Positions.tweetWindowWidth + Utilities.Converter(2);
	public static float wordCloudBeforeY = 0 + Utilities.Converter(2);
	
	// wordCloud
	public static float wordCloudAfterWidth = Utilities.width / 6 - Utilities.Converter(2);
	public static float wordCloudAfterHeight = Utilities.height / 3 - Utilities.Converter(3);
	public static float wordCloudAfterX = Positions.wordCloudBeforeX + Positions.wordCloudBeforeWidth + Utilities.Converter(2);
	public static float wordCloudAfterY = 0 + Utilities.Converter(2);
	
	// keyboard
	public static float keyboardWidth = Utilities.width / 6 - Utilities.Converter(3);
	public static float keyboardHeight = Utilities.height / 3 - Utilities.Converter(3);
	public static float keyboardX = Positions.wordCloudAfterX;
	public static float keyboardY = Utilities.height * 2 / 3 + Utilities.Converter(1);
	
	// suggestion box
	public static float suggestionBoxX;
	public static float suggestionBoxY;
	public static float suggestionBoxWidth;
	public static float suggestionBoxHeight;
	
	// textBox
	public static float textBoxWidth = Positions.keyboardWidth - Utilities.Converter(2);
	public static float textBoxHeight = Utilities.Converter(11);
	public static float textBoxX = Positions.keyboardX + Utilities.Converter(1);
	public static float textBoxY = Positions.keyboardY - Positions.textBoxHeight - Utilities.Converter(1);
	
	// zoom buttons
	public static float zoomInButtonW = Utilities.Converter(20);
	public static float zoomInButtonH = Utilities.Converter(15);
	public static float zoomInButtonX = Positions.mapX + Positions.mapWidth + Utilities.Converter(3);
	public static float zoomInButtonY = Utilities.height - Positions.zoomInButtonW;
	
	public static float zoomOutButtonW = zoomInButtonW;
	public static float zoomOutButtonH = zoomInButtonH;
	public static float zoomOutButtonX = zoomInButtonX;
	public static float zoomOutButtonY = Positions.zoomInButtonY - Positions.zoomInButtonW;
	
	//track buttons
	public static float trackButtonW = U.Converter(30);
	public static float trackButtonH = U.Converter(20);
	public static float trackButtonX = U.width/2 + U.Converter(5);
	public static float trackButtonY = U.width*9/10 + U.Converter(5);

	
	//location list button
	public static float locationButtonWidth = textBoxWidth;
	public static float locationButtonHeight = textBoxHeight;
	public static float locationButtonX = textBoxX;
	public static float locationButtonY = textBoxY - locationButtonHeight - Utilities.Converter(2);
	
	// list window
	public static float listWindowWidth = Utilities.width / 12 + Utilities.Converter(10);
	public static float listWindowHeight = Utilities.height * 2 /3 - Utilities.Converter(15);
	public static float listWindowX = Utilities.width * 4 / 6 + Positions.listWindowWidth - Utilities.Converter(30);
	public static float listWindowY = Utilities.height / 3 + Utilities.Converter(2);
	
	// keyboard toggle button
	public static float add2GraphWidth = Utilities.Converter(16);
	public static float add2GraphHeight = Utilities.Converter(9);
	public static float add2GraphX = Utilities.width / 6*5 - Positions.add2GraphWidth - Utilities.Converter(2);
	public static float add2GraphY = Utilities.height - Positions.add2GraphHeight - Utilities.Converter(3);
	
	// button addKeyword2list
	public static float addKeyword2ListWidth = Utilities.width / 6 / 2 - Utilities.Converter(2);
	public static float addKeyword2ListHeight = Utilities.height / 3 / 3 - Utilities.Converter(2);
	public static float addKeyword2ListX = Utilities.width * 4 / 6 + Utilities.Converter(1);
	public static float addKeyword2ListY = Utilities.height * 2 / 3 + Utilities.Converter(1);
	
	// button addPerson2List
	public static float addPerson2ListWidth = addKeyword2ListWidth;
	public static float addPerson2ListHeight = addKeyword2ListHeight;
	public static float addPerson2ListX = addKeyword2ListX + addKeyword2ListWidth + Utilities.Converter(2);
	public static float addPErson2LsitY = addKeyword2ListY;
	
	// button addEvent2List
	public static float addEvent2ListWidth = addKeyword2ListWidth;
	public static float addEvent2ListHeight = addKeyword2ListHeight;
	public static float addEvent2ListX = addKeyword2ListX;
	public static float addEvent2listY = addKeyword2ListY + addKeyword2ListHeight + Utilities.Converter(2);
	
	// button track Person
	public static float trackPersonWidth = addKeyword2ListWidth;
	public static float trackPersonHeight = addKeyword2ListHeight;
	public static float trackPersonX = addPerson2ListX;
	public static float trackPersonY = addEvent2listY;
}

