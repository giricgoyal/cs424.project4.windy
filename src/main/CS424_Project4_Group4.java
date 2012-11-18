/**
 * 
 */
package main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Comparator;
import java.util.List;

import omicronAPI.OmicronAPI;
import processing.core.*;
import db.*;
import types.*;
import markers.AbstractMarker;
import markers.DefaultMarker;
import markers.MarkerType;

/**
 * @author giric
 *
 */

@SuppressWarnings("serial")
public class CS424_Project4_Group4 extends PApplet{
	static public void main(String args[]) {
		System.out.println("premain");
		PApplet.main(new String[] { "main.CS424_Project4_Group4" });
		System.out.println("postmain");
	}
	
	/**
	 * Variable and object declarations
	 */	
	OmicronAPI omicronManager;
	TouchListener touchListener;
	
	// map
	Map map;
	PVector moveDis;
	
	// weather panel
	WeatherPanel weatherPanel;
	
	// timeSlider
	TimeSlider timeSlider;
	
	float currentMX, currentMY; // current Mouse X & Y
	
	ArrayList<BasicControl> controls;
	ArrayList<DayButton> dayButtons;
	
	Button zoomInBtn;
	Button zoomOutBtn;
	
	Button trackButton;
	
	Button locationButton;
	
	Keyboard keyboard;
	SuggestionBox sb;
	TweetWindow tw;
	
	WordCloud beforeWordCloud, afterWordCloud;
	
	// data
	ArrayList<DataPos> dataPos;
	DataCountPair[] dataCount;
	ArrayList<AbstractMarker> markers;
	String[] dataWords;
	
	private boolean isTouchingMap = false; // mouse pressing
	
	private int whichLock = U.NEITHER;
	
	QueryManager qManager;
	
	public void initApp() {
		Utilities.CS424_Project4_Group4 = this;
		U.currentWord = "accident";
		
		dataPos = new ArrayList<DataPos>();
		markers = new ArrayList<AbstractMarker>();
		qManager = new QueryManager(this);
		dataPos = qManager.getDataPos_By_Date_TimeRange_Word(U.currentDay, U.bHalf, U.eHalf, U.currentWord);
		dataCount = qManager.getAllCount_By_Keyword("cs424");
		//dataWords = qManager.getAllText_By_Date_TimeRange(U.currentDay, bHalf, eHalf);
		//getWordCountPair(dataWords);
		
		// begin of components initialization
		map = new Map(this, "map.png", Pos.mapX, Pos.mapY, Pos.mapWidth, Pos.mapHeight);
		map.setup(0, 0, U.mapMaxW, U.mapMaxH); // initial range of the map by pixel in original image
		
		weatherPanel = new WeatherPanel(this,
				Pos.weatherPanelX, Pos.weatherPanelY,
				Pos.weatherPanelWidth, Pos.weatherPanelHeight, 
				qManager.getAllWeather());
		
		timeSlider = new TimeSlider(this,
				Pos.timeSliderX, Pos.timeSliderY, Pos.timeSliderWidth, Pos.timeSliderHeight,
				map(U.bHalf, 0, 48, Pos.timeSliderX, Pos.timeSliderX+Pos.timeSliderWidth),
				map(U.eHalf, 0, 48, Pos.timeSliderX, Pos.timeSliderX+Pos.timeSliderWidth),
				Pos.lockWidth, Pos.lockHeight,
				dataCount);
		
		// end of components initialization
		
		moveDis = new PVector(0,0);

		setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER); // this must be after map is initialized
		
		Utilities.font = this.loadFont("Helvetica-Bold-100.vlw");
		
		beforeWordCloud = new WordCloud(this, Positions.wordCloudBeforeX, Positions.wordCloudBeforeY, Positions.wordCloudBeforeWidth, Positions.wordCloudBeforeHeight, "KeywordsBefore.txt");
		afterWordCloud = new WordCloud(this, Positions.wordCloudAfterX, Positions.wordCloudAfterY, Positions.wordCloudAfterWidth, Positions.wordCloudAfterHeight, "KeywordsAfter.txt");
	}

	public void initControls() {
		
		controls = new ArrayList<BasicControl>();
		
		dayButtons = new ArrayList<DayButton>();
		
		for (int i=0;i<=20;i++) {
			DayButton dayButton = new DayButton(this, Positions.dayButtonX+i*Pos.dayButtonW, Positions.dayButtonY, Positions.dayButtonW, Positions.dayButtonH, U.totalTweets[i]);
			dayButton.setName("D"+i);
			dayButtons.add(dayButton);
		}
		
		zoomInBtn = new Button(this, Positions.zoomInButtonX, Positions.zoomInButtonY, Positions.zoomInButtonW, Positions.zoomInButtonH);
		zoomInBtn.setName("+");
		controls.add(zoomInBtn);
		zoomOutBtn = new Button(this, Positions.zoomOutButtonX, Positions.zoomOutButtonY, Positions.zoomOutButtonW, Positions.zoomOutButtonH);
		zoomOutBtn.setName("-");
		controls.add(zoomOutBtn);
		
		keyboard = new Keyboard(this, Positions.keyboardX, Positions.keyboardY,
				Positions.keyboardWidth, Positions.keyboardHeight);
		controls.add(keyboard);
		
		sb = new SuggestionBox(this, Positions.textBoxX, Positions.textBoxY,
				Positions.textBoxWidth, Positions.textBoxHeight,this);
		controls.add(sb);

		tw = new TweetWindow(this, Positions.tweetWindowX, Positions.tweetWindowY, Positions.tweetWindowWidth, Positions.tweetWindowHeight);
		controls.add(tw);
		
		
		locationButton = new Button(this, Positions.locationButtonX, Positions.locationButtonY, Positions.locationButtonWidth, Positions.locationButtonHeight);
		locationButton.setName("Location");
		locationButton.setShowClick();
		controls.add(locationButton);
		
	}
	
	public void setup() {
		size((int) Utilities.width, (int) Utilities.height, JAVA2D);
		background(Colors.DARK_GRAY);
		if (Utilities.isWall) {
			initOmicron();
		}
		
		// CREATE SUPPORT VARS
		initApp();
		System.out.println("App setup DONE");
		initControls();
		System.out.println("Control setup DONE");
				
	}
	
	public void draw() {
		noStroke();
		
		map.draw();
		
		// TODO: draw button - will change
		for (DayButton d : dayButtons) {
			d.draw();
		}
		
		for (BasicControl bc : controls) {
			bc.draw();
		}
		
		// draw markers
		for (AbstractMarker m : markers) {
			if (map.checkIn(m.getX(),m.getY())) {
				m.draw();
			}
		}
		
		// draw weather panel
		weatherPanel.draw(U.currentDay);
		
		// draw time slider
		timeSlider.draw();
		
		// draw word cloud
		beforeWordCloud.draw();
		afterWordCloud.draw();
		
		if (U.drawGridLine) {
			pushStyle();
			strokeWeight(U.Converter(0.5));
			stroke(Colors.RED);
			line(0, U.height/3*1, U.width,  U.height/3*1);
			line(0, U.height/3*2, U.width,  U.height/3*2);
			line(U.width/6*1,0,U.width/6*1,U.height);
			line(U.width/6*2,0,U.width/6*2,U.height);
			line(U.width/6*3,0,U.width/6*3,U.height);
			line(U.width/6*4,0,U.width/6*4,U.height);
			line(U.width/6*5,0,U.width/6*5,U.height);
		}
		
		pushStyle();
		textAlign(PConstants.LEFT,PConstants.TOP);
		textSize(Utilities.Converter(7));
		fill(Colors.TEXT_GRAY);
		text("current keyword: "+U.currentWord,Utilities.width*4/6,Utilities.height/3);
		text("current Day: "+U.currentDay,Utilities.width*4/6,Utilities.height/3+Utilities.Converter(10));
		text("current Time: "+U.bHalf+" - "+U.eHalf,Utilities.width*4/6,Utilities.height/3+Utilities.Converter(20));
		popStyle();
		
		
		// PROCESS OMICRON
		if (Utilities.isWall) {
			omicronManager.process();
		}
	}
	
	private void setMarkerPos(ArrayList<DataPos> dataPos, ArrayList<AbstractMarker> markers, MarkerType type) {
		markers.clear();
		for (DataPos pos : dataPos) {
			float _x = pos.getLongitude();
			float _y = pos.getLatitude();
			float x1Lon = map(map.x1, 0, Utilities.mapMaxW, (float)93.5673, (float)93.1923);
			float x2Lon = map(map.x2, 0, Utilities.mapMaxW, (float)93.5673, (float)93.1923);
			float y1Lat = map(map.y1, 0, Utilities.mapMaxH, (float)42.3017, (float)42.1609);
			float y2Lat = map(map.y2, 0, Utilities.mapMaxH, (float)42.3017, (float)42.1609);
			float x = map(_x, x1Lon, x2Lon, map.x0, map.w);
			float y = map(_y, y1Lat, y2Lat, map.y0, map.h);	
			switch (type) {
			case DEFAULT_MARKER:
				markers.add(new DefaultMarker(this,x,y));
				break;
			default:
				markers.add(new DefaultMarker(this,x,y));
			}
		}
	}
	
	private List<WordCountPair> getWordCountPair(String[] words) {
		List<WordCountPair> entry = new ArrayList<WordCountPair>();
		for (int i=0;i<words.length;i++) {
			boolean exist = false;
			for (WordCountPair e : entry) {
				if (isNormalWord(words[i])==true) continue;
				if (words[i].equals(e.getWord())) {
					e.countInc();
					exist = true;
					break;
				}
			}
			if (!exist) {
				entry.add(new WordCountPair(words[i]));
			}
		}
		Collections.sort(entry, new Comparator<WordCountPair>() {
		    public int compare(WordCountPair a, WordCountPair b) {
		    	if (a.getCount()>b.getCount()) return -1;
		    	else if (a.getCount()<b.getCount()) return 1;
		    	return 0;
		    }
		});
	for (WordCountPair e : entry) {
		if (e.getCount()>100) {
			System.out.println(e.getWord()+" "+e.getCount());
		}
	}
		return entry;
	}
	
	private boolean isNormalWord(String str) {
		if (str.equals("I")) return true;
		if (str.equals("i")) return true;
		if (str.equals("is")) return true;
		if (str.equals("Is")) return true;
		if (str.equals("the")) return true;
		if (str.equals("to")) return true;
		if (str.equals("a")) return true;
		if (str.equals("my")) return true;
		if (str.equals("of")) return true;
		if (str.equals("and")) return true;
		if (str.equals("&")) return true;
		if (str.equals("in")) return true;
		if (str.equals("on")) return true;
		if (str.equals("that")) return true;
		if (str.equals("for")) return true;
		//if (str.equals("this")) return true;
		if (str.equals("be")) return true;
		if (str.equals("not")) return true;
		if (str.equals("at")) return true;
		if (str.equals("with")) return true;
		if (str.equals("The")) return true;
		if (str.equals("but")) return true;
		if (str.equals("just")) return true;
		if (str.equals("are")) return true;
		if (str.equals("an")) return true;
		if (str.equals("get")) return true;
		
		if (str.equals("-")) return true;
		if (str.equals("A")) return true;
		if (str.equals("or")) return true;
		//if (str.equals("if")) return true;
		if (str.equals("&")) return true;
		if (str.equals("by")) return true;
		return false;
	}
	
	
	//FIXME: not using updateMarkerPos
	private void updateMarkerPos(ArrayList<AbstractMarker> markers, float x1, float x2, float x3, float x4, float y1, float y2, float y3, float y4) {
		for(AbstractMarker m : markers) {
			m.updatePos(x1, x2, x3, x4, y1, y2, y3, y4);
		}
	}
	
	private void moveMarkers(ArrayList<AbstractMarker> markers, float x, float y) {
		for (AbstractMarker m : markers) {
			m.movePos(x,y);
		}
	}
	
	
	// ***********************************
		// --> HERE BEGINS THE MOUSE STUFF <--
		// ***********************************

		// See TouchListener on how to use this function call
		// In this example TouchListener draws a solid ellipse
		// Ths functions here draws a ring around the solid ellipse

		// NOTE: Mouse pressed, dragged, and released events will also trigger these
		// using an ID of -1 and an xWidth and yWidth value of 10.

		// Touch position at last frame

	public void initOmicron() {
		// Creates the OmicronAPI object. This is placed in init() since we want
		// to use fullscreen
		omicronManager = new OmicronAPI(this);

		// Removes the title bar for full screen mode (present mode will not
		// work on Cyber-commons wall)
		omicronManager.setFullscreen(true);

		// Make the connection to the tracker machine
		omicronManager.ConnectToTracker(7001, 7340, "131.193.77.159");
		// Create a listener to get events
		touchListener = new TouchListener();
		touchListener.setThings(this);
		// Register listener with OmicronAPI
		omicronManager.setTouchListener(touchListener);
		System.out.println("Omicron Initiated");
	}

	public boolean isIn(float mx, float my, float bx, float by, float bw,
			float bh) {
		return (bx <= mx && mx <= bx + bw && by <= my && my <= by + bh);
	}
	
	public boolean isInCenter(float _x, float _y, float x, float y, float w, float h) {
		return (_x > x-w/2 && _x < x+w/2 && _y > y-h/2 && _y < y+h/2);
	}
	
	public boolean isIn(float mx, float my, float bx, float by, float bw,
			float bh, float tolerance) {
		return (bx * (1 - tolerance) <= mx && mx <= bx + bw * (1 + tolerance)
				&& by * (1 - tolerance) <= my && my <= by + bh
				* (1 + tolerance));
	}
	
	
	
	int touchID1;
	int touchID2;
	PVector initTouchPos = new PVector();
	PVector initTouchPos2 = new PVector();
	PVector lastTouchPos = new PVector();
	PVector lastTouchPos2 = new PVector();
	int mapDragHack=1;
	
	@SuppressWarnings("rawtypes")
	Hashtable touchList;

	public void myPressed(int id, float mx, float my) {
		if (isTouchingMap == false && whichLock == U.NEITHER) {
			if (isIn(mx,my,Pos.mapX,Pos.mapY,map.w,map.h)) {
				isTouchingMap = true;
				currentMX = mx;
				currentMY = my;
			}
			else if (isInCenter(mx,my,timeSlider.getLeftLockX(),timeSlider.getLockY(),Pos.lockWidth,Pos.lockHeight)) {
				whichLock = U.LEFT;
			}
			else if (isInCenter(mx,my,timeSlider.getRightLockX(),timeSlider.getLockY(),Pos.lockWidth,Pos.lockHeight)) {
				whichLock = U.RIGHT;
			}
		}
	}
	
	public void myDragged(int id, float mx, float my) {
		if (isTouchingMap) {
			boolean moved = map.move(mx,my,currentMX,currentMY);
			if (moved) {
				moveMarkers(markers, mx-currentMX, my-currentMY);
			}
			currentMX = mx;
			currentMY = my;
		}
		else if (whichLock == U.LEFT) {
			int half = round(map(mx, Pos.timeSliderX, Pos.timeSliderX+Pos.timeSliderWidth, 0, 48));
			if (half<0) half = 0;
			if (half>=U.eHalf) half = U.eHalf - 1;
			U.bHalf_temp = half;
			timeSlider.updateLeft(map(half,0,48,Pos.timeSliderX,Pos.timeSliderX+Pos.timeSliderWidth));	
		}
		else if (whichLock == U.RIGHT) {
			int half = round(map(mx, timeSlider.getX(), timeSlider.getX()+timeSlider.getW(), 0, 48));
			if (half<=U.bHalf) half = U.bHalf + 1;
			if (half>48) half = 48;
			U.eHalf_temp = half;
			timeSlider.updateRight(map(half,0,48,Pos.timeSliderX,Pos.timeSliderX+Pos.timeSliderWidth));
		}
	}
	
	public void myReleased(int id, float mx, float my) {
		//touchList.remove(id);
		
		// first reset every variable that indicates 'pressing' to original value
		if (isTouchingMap) {
			isTouchingMap = false;
			return;
		}
		
		if (whichLock == U.LEFT) {
			whichLock = U.NEITHER;
			U.bHalf = U.bHalf_temp;
			dataPos = qManager.getDataPos_By_Date_TimeRange_Word(U.currentDay, U.bHalf, U.eHalf, U.currentWord);
			//dataWords = qManager.getAllText_By_Date_TimeRange(U.currentDay, U.bHalf, U.eHalf);
			setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
			return;
		}
		else if (whichLock == U.RIGHT) {
			whichLock = U.NEITHER;
			U.eHalf = U.eHalf_temp;
			dataPos = qManager.getDataPos_By_Date_TimeRange_Word(U.currentDay, U.bHalf, U.eHalf, U.currentWord);
			//dataWords = qManager.getAllText_By_Date_TimeRange(U.currentDay, U.bHalf, U.eHalf);
			setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
			return;
		}
		
		// then check interfaces
		for (int i=0;i<=20;i++) {
			if ((dayButtons.get(i)).checkIn(mx,my)) {
				System.out.println("Day "+i+" Clicked");
				U.currentDay = i;
				//dataPos = qManager.getDataPosByDateAndWord(U.currentDay, U.currentWord);
				dataPos = qManager.getDataPos_By_Date_TimeRange_Word(U.currentDay, U.bHalf, U.eHalf, U.currentWord);
				//dataWords = qManager.getAllText_By_Date_TimeRange(U.currentDay, bHalf, eHalf);
				setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
				return;
			}
		}
		
		if (isIn(mx, my, Positions.keyboardX, Positions.keyboardY,
				Positions.keyboardWidth, Positions.keyboardHeight)) {
			sb.updateTextBox(keyboard.Click(mx, my));
			return;
		}
<<<<<<< HEAD
		if (Utilities.suggestionBox){
			if (isIn(mx, my, Positions.suggestionBoxX,
					Positions.suggestionBoxY, Positions.suggestionBoxWidth,
					Positions.suggestionBoxHeight)) {
				sb.Click(mx, my);
			}
		}
		if (!Utilities.suggestionBox){
			if (locationButton.isInRectangle(mx, my)){
				System.out.println("Location Clicked");
				locationButton.setSelected(!locationButton.isSelected());
			}
		}

		if (hourMinus.checkIn(mx,my)) {
			System.out.println("hour- Clicked");
			if (bHour>0) {
				bHour --;
				eHour --;
				dataPos = qManager.getDataPos_By_Date_TimeRange_Word(U.currentDay, bHour, eHour, U.currentWord);
				dataWords = qManager.getAllText_By_Date_TimeRange(U.currentDay, bHour, eHour);
				setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
			}
			return;
		}
		if (hourPlus.checkIn(mx,my)) {
			System.out.println("hour+ Clicked");
			if (eHour <24) {
				bHour ++;
				eHour ++;
				dataPos = qManager.getDataPos_By_Date_TimeRange_Word(U.currentDay, bHour, eHour, U.currentWord);
				//dataWords = qManager.getAllText_By_Date_TimeRange(U.currentDay, bHour, eHour);
				setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
			}
=======

		if (isIn(mx, my, Positions.suggestionBoxX,
				Positions.suggestionBoxY, Positions.suggestionBoxWidth,
				Positions.suggestionBoxHeight)) {
			sb.Click(mx, my);
>>>>>>> db6ec05a8e6782c83c34bbbecfd506019be57a1a
			return;
		}

		if (zoomInBtn.checkIn(mx,my)) {
			System.out.println("Zoom in Clicked");
			float mW =  map.x2 - map.x1 + 1;
			float mH = map.y2 - map.y1 + 1;
			if (mW > Pos.mapWidth && mW > Pos.mapHeight) {
				mW = mW/4;
				mH = mH/4;
				map.x1 += mW;
				map.x2 -= mW;
				map.y1 += mH;
				map.y2 -= mH;
				mW = mW*2;
				mH = mH*2;
				setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
				//updateMarkerPos(markers);
			}
			return;
		}
		if (zoomOutBtn.checkIn(mx,my)) {
			System.out.println("Zoom out Clicked");
			float mW = map.x2 - map.x1 + 1;
			float mH = map.y2 - map.y1 + 1;
			if (mW < U.mapMaxW && mW < U.mapMaxH) { 
				mW = mW/2;
				mH = mH/2;
				map.x1 -= mW;
				map.x2 += mW;
				map.y1 -= mH;
				map.y2 += mH;
				mW = mW*4;
				mH = mH*4;
				setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
				//updateMarkerPos(markers);
			}
			return;
		}
	}
	
	
	public void myClicked(int id, float mx, float my) {
	}

	public void mouseDragged() {
		myDragged(-1, mouseX, mouseY);
	}

	public void mousePressed() {
		//System.out.println("MOUSE PRESSED");
		myPressed(-1, mouseX, mouseY);
	}

	public void mouseClicked() {
		myClicked(-1, mouseX, mouseY);
	}

	public void mouseReleased() {
		// MAP CLICK:
		myReleased(-1, mouseX, mouseY);
	}

	@SuppressWarnings("unchecked")
	public void touchDown(int ID, float xPos, float yPos, float xWidth,
			float yWidth) {
		
		Touch t = new Touch(ID, xPos, yPos, xWidth, yWidth);
		touchList.put(ID,t);
		System.out.println("Added Touch "+ID);
		
		if (touchList.size() == 1) { // If one touch record initial position
			// (for dragging). Saving ID 1 for later
			touchID1 = ID;
			initTouchPos.x = mouseX;
			initTouchPos.y = mouseY;
		} else if (touchList.size() == 2) { 
			// If second touch record initial
			// position (for zooming). Saving ID
			// 2 for later
			touchID2 = ID;
			initTouchPos2.x = mouseX;
			initTouchPos2.y = mouseY;
		}
		
		if (touchList.size() >= 5) {
		
		}
		
		if (ID == touchID1) {
			lastTouchPos.x = mouseX;
			lastTouchPos.y = mouseY;
		} else if (ID == touchID2) {
			lastTouchPos2.x = mouseX;
			lastTouchPos2.y = mouseY;
		}
		
		myPressed(ID, xPos, yPos);
	}

	public void touchMove(int ID, float xPos, float yPos, float xWidth,
			float yWidth) {
		myDragged(ID, xPos, yPos);
	}

	public void touchUp(int ID, float xPos, float yPos, float xWidth,
			float yWidth) {
		touchList.remove(ID);
		System.out.println("Released Touch "+ID);
		myReleased(ID, xPos, yPos);
	}

}
