/**
 * 
 */
package main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Comparator;
import java.util.List;

import Util.Colors;
import Util.Pos;
import Util.Positions;
import Util.StopWords;
import Util.U;
import Util.Utilities;

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
	boolean moved = false; 
	
	// weather panel
	WeatherPanel weatherPanel;
	
	// timeSlider
	TimeSlider timeSlider;
	
	float currentMX, currentMY; // current Mouse X & Y
	
	ArrayList<BasicControl> controls;
	ArrayList<DayButton> dayButtons;
	
	Button zoomInBtn;
	Button zoomOutBtn;
	
	Button addKeyword2List;
	Button addEvent2List;
	Button addPerson2List;
	Button trackPerson;
	Button trackButton;
	Button add2Graph;
	Button locationButton;
	Button KeywordList;
	Button PersonList;
	Button EventList;
	
	ListArea listArea;
	
	Keyboard keyboard;
	SuggestionBox sb;
	TweetWindow tw;
	
	Graph graph;
	WordCloud beforeWordCloud, afterWordCloud;
	
	// data
	ArrayList<DataPos> dataDay; //all data for current day
	ArrayList<DataPos> dataPos; //all data for current time and current keyword of current day
	ArrayList<DataLocation> dataLocation; // locations
	DataCountPair[] dataCount; //all counts for every halfhour (keyword not implemented)
	ArrayList<AbstractMarker> markers; // markers, contain all information
	String[] dataWords; // all keywords (multiple times) in dataPos (i.e. keywords for current time)
	ArrayList<WordCountPair> dataWordCountPair; // words count pair for current time of current day (not all words, just those appear in current time)
	DataCountPair[] dataKeywordCount;
	
	private boolean isTouchingMap = false; // mouse pressing
	
	private int whichLock = U.NEITHER;
	
	QueryManager qManager;
	
	public void initApp() {
		Utilities.CS424_Project4_Group4 = this;
		U.currentWord = "flu";
		
		dataPos = new ArrayList<DataPos>();
		dataDay = new ArrayList<DataPos>();
		dataWordCountPair = new ArrayList<WordCountPair>();
		dataLocation = new ArrayList<DataLocation>();
		markers = new ArrayList<AbstractMarker>();
		qManager = new QueryManager(this);
		dataDay = qManager.getDataPos_By_Date(U.currentDay);
		setCurrentData(dataPos, dataDay, U.bHalf, U.eHalf, U.currentWord);
		dataCount = qManager.getAllCount_By_Keyword("cs424");
		dataLocation = qManager.getDataLocationAll();
		dataKeywordCount = new DataCountPair[U.graphNumber];
		for (int i=0;i<U.graphNumber;i++) {
			dataKeywordCount[i] = new DataCountPair("",new int[21]);
		}
		
		//dataWords = setCurrentWords();
		
		//dataWordCountPair = getWordCountPair(dataWords);
		//Utilities.dataWordCountPair = dataWordCountPair;
			
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
				dataCount, U.bHalf, U.eHalf);
		
		// end of components initialization
		
		moveDis = new PVector(0,0);

		setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER); // this must be after map is initialized
		
		Utilities.font = this.loadFont("Helvetica-Bold-100.vlw");
		
		beforeWordCloud = new WordCloud(this, Positions.wordCloudBeforeX, Positions.wordCloudBeforeY, Positions.wordCloudBeforeWidth, Positions.wordCloudBeforeHeight, "KeywordsBefore.txt");
		//afterWordCloud = new WordCloud(this, Positions.wordCloudAfterX, Positions.wordCloudAfterY, Positions.wordCloudAfterWidth, Positions.wordCloudAfterHeight, "KeywordsAfter.txt");
	}

	public void initControls() {
		
		controls = new ArrayList<BasicControl>();
		
		dayButtons = new ArrayList<DayButton>();
		
		for (int i=0;i<=20;i++) {
			DayButton dayButton = new DayButton(this, Positions.dayButtonX+i*Pos.dayButtonW, Positions.dayButtonY, Positions.dayButtonW, Positions.dayButtonH, U.totalTweets[i], i);
			dayButton.setName("D"+i);
			if (i == U.currentDay) {
				dayButton.setSelected(true);
			}
			dayButtons.add(dayButton);
		}
		
		zoomInBtn = new Button(this, Positions.zoomInButtonX, Positions.zoomInButtonY, Positions.zoomInButtonW, Positions.zoomInButtonH);
		zoomInBtn.setName("+");
		controls.add(zoomInBtn);
		zoomOutBtn = new Button(this, Positions.zoomOutButtonX, Positions.zoomOutButtonY, Positions.zoomOutButtonW, Positions.zoomOutButtonH);
		zoomOutBtn.setName("-");
		controls.add(zoomOutBtn);
		
		tw = new TweetWindow(this, Positions.tweetWindowX, Positions.tweetWindowY, Positions.tweetWindowWidth, Positions.tweetWindowHeight);
		controls.add(tw);
		//tw.setText("1234 6789 234567891 3456 891234 678912345678912345678912 456 89");
		
		
		locationButton = new Button(this, Positions.locationButtonX, Positions.locationButtonY, Positions.locationButtonWidth, Positions.locationButtonHeight);
		locationButton.setName("Location");
		locationButton.setShowClick();
		controls.add(locationButton);
		
		
		add2Graph = new Button(this, Positions.add2GraphX, Positions.add2GraphY, Positions.add2GraphWidth, Positions.add2GraphHeight);
		add2Graph.setName("Add to Graph");
		controls.add(add2Graph);
		
		addKeyword2List = new Button(this, Positions.addKeyword2ListX, Positions.addKeyword2ListY, Positions.addKeyword2ListWidth, Positions.addKeyword2ListHeight);
		addKeyword2List.setName("Add to List");
		addKeyword2List.setShowClick();
		controls.add(addKeyword2List);
		
		trackPerson = new Button(this, Positions.trackPersonX, Positions.trackPersonY, Positions.trackPersonWidth, Positions.trackPersonHeight);
		trackPerson.setName("Track Person");
		trackPerson.setShowClick();
		controls.add(trackPerson);
		
		KeywordList = new Button(this, Pos.keywordListX, Pos.keywordListY, Pos.keywordListWidth, Pos.keywordListHeight);
		KeywordList.setName("Keyword List");
		KeywordList.setShowClick();
		controls.add(KeywordList);
		
		EventList = new Button(this, Positions.eventListX, Positions.eventListY, Positions.eventListWidth, Positions.eventListHeight);
		EventList.setName("Event List");
		EventList.setShowClick();
		controls.add(EventList);
		
		PersonList = new Button(this, Positions.personListX, Positions.personListY, Positions.personListWidth, Positions.personListHeight);
		PersonList.setName("Person List");
		PersonList.setShowClick();
		controls.add(PersonList);
		
		
		graph = new Graph(this, Positions.graphX, Positions.graphY, Positions.graphWidth, Positions.graphHeight, this);
		controls.add(graph);
		
		listArea = new ListArea(this, Positions.listWindowX, Positions.listWindowY, Positions.listWindowWidth, Positions.listWindowHeight, this);
		
		
		
		keyboard = new Keyboard(this, Positions.keyboardX, Positions.keyboardY,
				Positions.keyboardWidth, Positions.keyboardHeight);
		controls.add(keyboard);
		
		sb = new SuggestionBox(this, Positions.textBoxX, Positions.textBoxY,
				Positions.textBoxWidth, Positions.textBoxHeight,this);
		controls.add(sb);

		
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
		
		reDrawbackground();
		map.draw();
		
		// draw markers
		if (U.selectedLocationId == -1 || U.selectedLocationId == 99) {
			for (AbstractMarker m : markers) {
				if (map.checkIn(m.getX(),m.getY())) {
					m.draw();
				}
			}
		}
		// all locations except interstates and river
		else if (U.selectedLocationId == 98) {
			for (AbstractMarker m : markers) {
				if (m.loc < 36 && m.loc >0) {
					if (map.checkIn(m.getX(),m.getY())) {
						m.draw();
					}
				}
			}
		}
		// all interstates
		else if (U.selectedLocationId == 97) {
			for (AbstractMarker m : markers) {
				if (m.loc >= 37 && m.loc <=47) {
					if (map.checkIn(m.getX(),m.getY())) {
						m.draw();
					}
				}
			}
		}
		else {
			for (AbstractMarker m : markers) {
				if (m.loc == U.selectedLocationId) {
					if (map.checkIn(m.getX(),m.getY())) {
						m.draw();
					}
				}
			}
		}
		
		// TODO: draw button - will change
		for (DayButton d : dayButtons) {
			d.draw();
		}
		
		for (BasicControl bc : controls) {
			bc.draw();
		}
		

		if (locationButton.isSelected()){
			listArea.draw();
		}
		
		if (KeywordList.isSelected())
			listArea.draw();
		
		if (EventList.isSelected())
			listArea.draw();
		
		if (PersonList.isSelected())
			listArea.draw();

		// draw weather panel
		weatherPanel.draw(U.currentDay);
		
		// draw time slider
		timeSlider.draw();
		
		// draw word cloud
		beforeWordCloud.draw();
		//afterWordCloud.draw();

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
		popStyle();
		
		
		// PROCESS OMICRON
		if (Utilities.isWall) {
			omicronManager.process();
		}
	}
	
	void reDrawbackground() {
		rectMode(PConstants.CORNER);
		fill(Colors.BACKGROUND_COLOR);
		rect(0,0, Utilities.width * 4 / 6, Utilities.height);
		rect(Utilities.width * 4 / 6, Utilities.height / 3, Utilities.width, Utilities.height);
		
	}
	
	// second version - get all data from database when launching
	public void setCurrentData(ArrayList<DataPos> current, ArrayList<DataPos> day, int bHalf, int eHalf, String keyword) {
		current.clear();
		System.out.println("start updating current data");
		for (DataPos data : day) {
			int half = data.getHour()*2 + data.getMin()/30;
			if (bHalf <= half && half < eHalf) {
				/*
				String[] words = split(data.getKeywords(),(' '));
				for (int ii = 0; ii<words.length; ii++) {
					if (keyword.equals(words[ii])) {
						current.add(data);
						break;
					}
				}*/
				current.add(data);
			}
		}
		setTodayWordsToFile(current);
		current.clear();
		for (DataPos data : day) {
			int half = data.getHour()*2 + data.getMin()/30;
			if (bHalf <= half && half < eHalf) {
				String[] words = split(data.getKeywords(),(' '));
				for (int ii = 0; ii<words.length; ii++) {
					if (keyword.equals(words[ii])) {
						current.add(data);
						break;
					}
				}
			}
		}
		System.out.println("done!");
	}
	
	public void setCurrentData_forKeywords(ArrayList<DataPos> current, ArrayList<DataPos> day, int bHalf, int eHalf, String keyword) {
		current.clear();
		System.out.println("start updating current data");
		for (DataPos data : day) {
			int half = data.getHour()*2 + data.getMin()/30;
			if (bHalf <= half && half < eHalf) {
				String[] words = split(data.getKeywords(),(' '));
				for (int ii = 0; ii<words.length; ii++) {
					if (keyword.equals(words[ii])) {
						current.add(data);
						break;
					}
				}
			}
		}
		System.out.println("done!");
	}
	
	public void setMarkerPos(ArrayList<DataPos> dataPos, ArrayList<AbstractMarker> markers, MarkerType type) {
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
				markers.add(new DefaultMarker(this,x,y,pos.getPid(),pos.getHour(),pos.getMin(),pos.getTweet(),pos.getKeywords(),pos.getLocation()));
				break;
			default:
				markers.add(new DefaultMarker(this,x,y,pos.getPid(),pos.getHour(),pos.getMin(),pos.getTweet(),pos.getKeywords(),pos.getLocation()));
			}
		}
	}
	
	/*/ old version -- to get keywords directly from the Tweets
	private String[] setCurrentWords(int bHalf, int eHalf) {
		String str = "";
		System.out.println("setting current words");
		for (DataPos data : dataPos) {
			str = str + data.getTweet()+ " ";
		}
		String reg = "[,\\.\\s;!?]+";
		String[] temp = str.split(reg); // temp contains all words
		for (int i=0;i<temp.length;i++) {
				temp[i] = temp[i].toLowerCase();
		}
		System.out.println("done!");
		return temp;
	}/*/
	
	public void setTodayWordsToFile(ArrayList<DataPos> dataPos) {
		ArrayList<String> str = new ArrayList<String>();
		System.out.println("setting current words");
					// all location
					if (U.selectedLocationId == -1 || U.selectedLocationId == 99) {
						for (DataPos data : dataPos) {
							str.add(data.keywords);
						}
					}
					// all locations except interstates and river
					else if (U.selectedLocationId == 98) {
						for (DataPos data : dataPos) {
							if (data.lid < 36 && data.lid > 0) {
								str.add(data.keywords);
							}
						}
					}
					// all interstates
					else if (U.selectedLocationId == 97) {
						for (DataPos data : dataPos) {
							if (data.lid >= 37 && data.lid <=47) {
								str.add(data.keywords);
							}
						}
					}
					else {
						for (DataPos data : dataPos) {
							if (data.lid == U.selectedLocationId) {
								str.add(data.keywords);
							}
						}
					}
		String[] result = new String[str.size()];
		for (int ii=0;ii<result.length;ii++) {
			result[ii] = str.get(ii);
		}
		System.out.println("start to write!");	
		saveStrings(dataPath(sketchPath + "/data/KeywordsBefore.txt"), result);
		System.out.println("done!");
	}
	
	private ArrayList<WordCountPair> getWordCountPair(String[] words) {
		ArrayList<WordCountPair> entry = new ArrayList<WordCountPair>();
		for (int i=0;i<words.length;i++) {
			boolean exist = false;
			for (WordCountPair e : entry) {
				//boolean flag = false;
				//for (int j=0;j<StopWords.list.length;j++) {
				//	if (StopWords.list[j].equals(words[i])) {
				//		flag = true;
				//		break;
				//	}
				//}
				//if (flag) continue;
				//if (isStopWord(words[i])==true) continue;
				if (words[i].equals(e.getWord())) {
					e.countInc();
					exist = true;
					break;
				}
			}
			if (!exist) {
				entry.add(new WordCountPair(words[i]));
			}
			if (i%1000 == 0)
				System.out.println(i+" ("+(float)i*100/words.length+"%)");
		}
		/*
		Collections.sort(entry, new Comparator<WordCountPair>() {
		    public int compare(WordCountPair a, WordCountPair b) {
		    	if (a.getCount()>b.getCount()) return -1;
		    	else if (a.getCount()<b.getCount()) return 1;
		    	return 0;
		    }
		});*/
		ArrayList<WordCountPair> result = new ArrayList<WordCountPair>();
		for (WordCountPair e : entry) {
			if (!isStopWord(e.getWord()) && e.getCount()>entry.size()*0.01) {
				//System.out.println(e.getWord()+" "+e.getCount());
				result.add(new WordCountPair(e.getWord(),e.getCount()));
			}
		}
		return result;
	}
		
	private boolean isStopWord(String str) {
		return StopWords.check(str);
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
			moved = map.move(mx,my,currentMX,currentMY);
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
			timeSlider.updateLeft(map(half,0,48,Pos.timeSliderX,Pos.timeSliderX+Pos.timeSliderWidth), half);	
		}
		else if (whichLock == U.RIGHT) {
			int half = round(map(mx, timeSlider.getX(), timeSlider.getX()+timeSlider.getW(), 0, 48));
			if (half<=U.bHalf) half = U.bHalf + 1;
			if (half>48) half = 48;
			U.eHalf_temp = half;
			timeSlider.updateRight(map(half,0,48,Pos.timeSliderX,Pos.timeSliderX+Pos.timeSliderWidth), half);
		}
	}
	
	public void myReleased(int id, float mx, float my) {
		//touchList.remove(id);
		
		// first reset every variable that indicates 'pressing' to original value
		if (isTouchingMap) {
			isTouchingMap = false;
		}
		
		if (whichLock == U.LEFT) {
			whichLock = U.NEITHER;
			U.bHalf = U.bHalf_temp;
			setCurrentData(dataPos,dataDay,U.bHalf,U.eHalf,U.currentWord);
			setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
			//dataWords = setCurrentWords();
			//dataWordCountPair = getWordCountPair(dataWords);
			beforeWordCloud.clearArea();
			beforeWordCloud = new WordCloud(this, Positions.wordCloudBeforeX, Positions.wordCloudBeforeY, Positions.wordCloudBeforeWidth, Positions.wordCloudBeforeHeight, "KeywordsBefore.txt");
			return;
		}
		else if (whichLock == U.RIGHT) {
			whichLock = U.NEITHER;
			U.eHalf = U.eHalf_temp;
			setCurrentData(dataPos,dataDay,U.bHalf,U.eHalf,U.currentWord);
			setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
			//dataWords = setCurrentWords();
			//dataWordCountPair = getWordCountPair(dataWords);
			beforeWordCloud.clearArea();
			beforeWordCloud = new WordCloud(this, Positions.wordCloudBeforeX, Positions.wordCloudBeforeY, Positions.wordCloudBeforeWidth, Positions.wordCloudBeforeHeight, "KeywordsBefore.txt");
			return;
		}
		
		// then check interfaces
		for (int i=0;i<=20;i++) {
			if ((dayButtons.get(i)).checkIn(mx,my)) {
				System.out.println("Day "+i+" Clicked");
				dayButtons.get(U.currentDay).setSelected(false);
				U.currentDay = i;
				dayButtons.get(U.currentDay).setSelected(true);
				dataDay = qManager.getDataPos_By_Date(U.currentDay);
				setCurrentData(dataPos,dataDay,U.bHalf,U.eHalf,U.currentWord);
				setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
				//dataWords = setCurrentWords();
				//dataWordCountPair = getWordCountPair(dataWords);
				//Utilities.currentTweet = "";
				//tw.setTweet();
				beforeWordCloud.clearArea();
				beforeWordCloud = new WordCloud(this, Positions.wordCloudBeforeX, Positions.wordCloudBeforeY, Positions.wordCloudBeforeWidth, Positions.wordCloudBeforeHeight, "KeywordsBefore.txt");
				return;
			}
		}
		
		if (isIn(mx, my, Positions.keyboardX, Positions.keyboardY,
				Positions.keyboardWidth, Positions.keyboardHeight)) {
			sb.updateTextBox(keyboard.Click(mx, my));
			KeywordList.setSelected(false);
			EventList.setSelected(false);
			PersonList.setSelected(false);
			locationButton.setSelected(false);
			return;
		}

		if (Utilities.suggestionBox){
			if (isIn(mx, my, Positions.suggestionBoxX,
					Positions.suggestionBoxY, Positions.suggestionBoxWidth,
					Positions.suggestionBoxHeight)) {
				sb.Click(mx, my);
				return;
			}
		}
		if (!Utilities.suggestionBox){
			if (locationButton.isInRectangle(mx, my)){
				System.out.println("Location Clicked");
				locationButton.setSelected(!locationButton.isSelected());
				listArea.setSelected(locationButton.isSelected());
				if (locationButton.isSelected()){
					listArea.setButtonSelected("location", Positions.locationButtonX, Positions.locationButtonY, Positions.locationButtonHeight);
					listArea.setLocationData(dataLocation, 0, "null");
					KeywordList.setSelected(false);
					PersonList.setSelected(false);
					EventList.setSelected(false);
					trackPerson.setSelected(false);
					return;
				}
				
			}
			
			if (KeywordList.isInRectangle(mx, my)) {
				if (!addKeyword2List.isSelected()) {
					System.out.println("Keyword List Selected");
					KeywordList.setSelected(!KeywordList.isSelected());
					listArea.setSelected(KeywordList.isSelected());
					if (KeywordList.isSelected()) {
						listArea.setButtonSelected("keyword", Positions.keywordListX, Positions.keywordListY, Positions.keywordListHeight);
						locationButton.setSelected(false);
						PersonList.setSelected(false);
						EventList.setSelected(false);
						trackPerson.setSelected(false);
						return;
					}
				}
				else {
					for (int count = 0; count < Utilities.keywordList.size(); count++) {
						if (Utilities.keywordList.get(count).compareToIgnoreCase(Utilities.currentWord) == 0) {
							addKeyword2List.setSelected(!addKeyword2List.isSelected());
							return;
						}
					}
					Utilities.keywordList.add(Utilities.currentWord);
					System.out.println("Size : "  + Utilities.keywordList.size());
					addKeyword2List.setSelected(!addKeyword2List.isSelected());
				}
			}
			
			if (PersonList.isInRectangle(mx, my)) {
				if (!addKeyword2List.isSelected()) {
					System.out.println("Person List Selected");
					PersonList.setSelected(!PersonList.isSelected());
					listArea.setSelected(PersonList.isSelected());
					if (PersonList.isSelected()) {
						listArea.setButtonSelected("person", Positions.personListX, Positions.personListY, Positions.personListHeight);
						locationButton.setSelected(false);
						KeywordList.setSelected(false);
						EventList.setSelected(false);
						trackPerson.setSelected(false);
						return;
					}
				}
				else {
					if (Utilities.tweetPid != -1) {
						System.out.println("click on add Person 2 List");
						for (int count = 0; count < Utilities.personList.size(); count++) {
							if (Utilities.personList.get(count) == Utilities.tweetPid) {
								addKeyword2List.setSelected(!addKeyword2List.isSelected());
								return;
							}
						}
						Utilities.personList.add(Utilities.tweetPid);
						System.out.println("Size : "  + Utilities.personList.size());
						addKeyword2List.setSelected(!addKeyword2List.isSelected());
					}
				}
			}
			
			if (EventList.isInRectangle(mx, my)) {
				if (!addKeyword2List.isSelected()) {
					System.out.println("Event List Selected");
					EventList.setSelected(!EventList.isSelected());
					listArea.setSelected(EventList.isSelected());
					if (EventList.isSelected()) {
						listArea.setButtonSelected("event", Positions.eventListX, Positions.eventListY, Positions.eventListHeight);
						locationButton.setSelected(false);
						PersonList.setSelected(false);
						KeywordList.setSelected(false);
						trackPerson.setSelected(false);
						return;
					}
				}
				else {
					System.out.println("click on add Event 2 List");
					for (int count = 0; count < Utilities.eventList.size(); count++) {
						if (Utilities.eventList.get(count).compareToIgnoreCase(Utilities.currentWord) == 0) {
							addKeyword2List.setSelected(!addKeyword2List.isSelected());
							return;
						}
					}
					Utilities.eventList.add(Utilities.currentWord);
					System.out.println("Size : "  + Utilities.eventList.size());
					addKeyword2List.setSelected(!addKeyword2List.isSelected());
				}
			}
			
			if (listArea.isSelected()){
				if (isIn(mx, my, Positions.listWindowX, Positions.listWindowY, Positions.listWindowWidth, Positions.listWindowHeight)){
					listArea.click(mx, my);
					if (!listArea.isSelected()) {
						locationButton.setSelected(false);
						KeywordList.setSelected(false);
						EventList.setSelected(false);
						PersonList.setSelected(false);
					}
					return;
				}
			}
		}
		
		if (!locationButton.isSelected() && !KeywordList.isSelected() && !EventList.isSelected() && !PersonList.isSelected()) {
			if (add2Graph.isInRectangle(mx, my)){
				System.out.println("Click on add to graph");
				for (int count = 0; count < Utilities.keywordGraph.size(); count++) {
					if (Utilities.keywordGraph.get(count).compareToIgnoreCase(Utilities.currentWord) == 0) {
						return;
					}
				}
				if (Utilities.keywordGraph.size() <= Utilities.graphNumber) {
					Utilities.keywordGraph.add(Utilities.currentWord);
					graph.setData();
				}
				System.out.println("Size : "  + Utilities.keywordGraph.size());
			}
			
			if (trackPerson.isInRectangle(mx, my)){
				System.out.println("toggle Track Person");
				trackPerson.setSelected(!trackPerson.isSelected());
				
			}
						
			if (addKeyword2List.isInRectangle(mx, my)) {
				System.out.println("Click on add Keyword to list");
				addKeyword2List.setSelected(!addKeyword2List.isSelected());
			}
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
				
				// we don't want blank
				float offsetX = 0;
				float offsetY = 0;
				if (map.x1<0) {
					offsetX = 0-map.x1;
				}
				else if (map.x2 > U.mapMaxW) {
					offsetX = U.mapMaxW - map.x2;
				}
				if (map.y1<0) {
					offsetY = 0-map.y1;
				}
				else if (map.y2 > U.mapMaxH){
					offsetY = U.mapMaxH-map.y2;
				}
				map.x1 += offsetX;
				map.x2 += offsetX;
				map.y1 += offsetY;
				map.y2 += offsetY;
				// end
				
				mW = mW*4;
				mH = mH*4;
				setMarkerPos(dataPos,markers,MarkerType.DEFAULT_MARKER);
				//updateMarkerPos(markers);
			}
			return;
		}
		
		if (!moved) {
			for (AbstractMarker m : markers) {
				if (map.checkIn(m.getX(),m.getY())) {
					if (m.checkIn(mx, my)) {
						U.currentTweet = m.getTweet();
						U.tweetTime = (m.getHour() > 9)? 
								((m.getMin()>9)? (m.getHour()+":"+m.getMin()) : (m.getHour()+":0"+m.getMin()) )
								: 
								( (m.getMin()>9)? ("0"+m.getHour()+":"+m.getMin()) : ("0"+m.getHour()+":0"+m.getMin()) );
						U.tweetPid = m.getPid();
						System.out.println("pid: "+U.tweetPid+", Time: "+U.tweetTime+", Text: "+U.currentTweet);
						tw.setTweet();
						return;
					}
				}
			}
		}
		else {
			moved = false;
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
