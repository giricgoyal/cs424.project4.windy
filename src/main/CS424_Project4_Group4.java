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
import types.DataPos;

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
	
	PImage map;
	
	ArrayList<Button> controls;
	Button dayButton;
	
	ArrayList<DataPos> dataPos;
	ArrayList<Marker> markers;
	String[] dataWords;
	
	private int currentDay;
	private String currentWord;
	private int bHour; //begin hour
	private int eHour; //end hour
	
	private float mapWidth;
	private float mapHeight;
	
	
	QueryManager qManager;
	
	public void initApp() {
		Utilities.CS424_Project4_Group4 = this;
		currentDay = 17;
		currentWord = "accident";
		bHour = 10;
		eHour = 14;
		map = loadImage("map.png");
		mapWidth = Utilities.Converter(5216/16);
		mapHeight = Utilities.Converter(2653/16);
		dataPos = new ArrayList<DataPos>();
		markers = new ArrayList<Marker>();
		qManager = new QueryManager(this);
		dataPos = qManager.getDataPos_By_Date_TimeRange_Word(currentDay, bHour, eHour, currentWord);
		dataWords = qManager.getAllText_By_Date_TimeRange(currentDay, bHour, eHour);
		getWordCountPair(dataWords);
		setMarkerPos(dataPos,markers);
		
		//Utilities.font = this.loadFont("Helvetica-Bold-100.vlw");
	}

	public void initControls() {
		
		controls = new ArrayList<Button>();
		
		for (int i=0;i<=20;i++) {
			dayButton = new Button(this, Positions.dayButtonX+Utilities.Converter(20*i), Positions.dayButtonY, Positions.dayButtonW, Positions.dayButtonH);
			dayButton.setName("D"+i);
			controls.add(dayButton);
		}
		
		dayButton = new Button(this, Utilities.width*9/10, Utilities.height*9/10, Positions.dayButtonW, Positions.dayButtonH);
		dayButton.setName("h-");
		controls.add(dayButton);
		dayButton = new Button(this, Utilities.width*9/10+Utilities.Converter(20), Utilities.height*9/10, Positions.dayButtonW, Positions.dayButtonH);
		dayButton.setName("h+");
		controls.add(dayButton);
	}
	
	public void setup() {
		size((int) Utilities.width, (int) Utilities.height, JAVA2D);
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
		background(Colors.DARK_GRAY);
		noStroke();
		
		pushStyle();
		textAlign(PConstants.LEFT,PConstants.CENTER);
		textSize(Utilities.Converter(10));
		fill(Colors.WHITE);
		text("current keyword: "+currentWord,Utilities.width*4/5,Utilities.height/2);
		text("current Day: "+currentDay,Utilities.width*4/5,Utilities.height/2+Utilities.Converter(10));
		text("current Time: "+bHour+" - "+eHour,Utilities.width*4/5,Utilities.height/2+Utilities.Converter(20));
		popStyle();
		
		image(map,0,0,mapWidth,mapHeight);
		
		for (Button bc : controls) {
			bc.draw();
		}
		
		for (Marker m : markers) {
			m.draw();
		}
		
		// PROCESS OMICRON
		if (Utilities.isWall) {
			omicronManager.process();
		}
	}
	
	private void setMarkerPos(ArrayList<DataPos> dataPos,
			ArrayList<Marker> markers) {
		markers.clear();
		for (DataPos pos : dataPos) {
			float _x = pos.getLongitude();
			float _y = pos.getLatitude();
			float x = map(_x, (float)93.5673, (float)93.1923, (float)0, (float)mapWidth);
			float y = map(_y, (float)42.3017, (float)42.1609, (float)0, (float)mapHeight);	
			markers.add(new Marker(this,x,y));
		}
	}
	
	private List<WordCountPair> getWordCountPair(String[] words) {
		List<WordCountPair> entry = new ArrayList<WordCountPair>();
		for (int i=0;i<words.length;i++) {
			boolean exist = false;
			for (WordCountPair e : entry) {
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
		
	}
	
	public void myDragged(int id, float mx, float my) {
		
	}
	
	public void myReleased(int id, float mx, float my) {
		//touchList.remove(id);
		
		if (isIn(mx, my, Positions.sampleBoxX, Positions.sampleBoxY, Positions.sampleBoxWidth, Positions.sampleBoxHeight)){
			System.out.println("Click Check");
		}
		
		for (int i=0;i<=20;i++) {
			if (controls.get(i).checkIn(mx,my)) {
				System.out.println("Day "+i+" Clicked");
				currentDay = i;
				//dataPos = qManager.getDataPosByDateAndWord(currentDay, currentWord);
				dataPos = qManager.getDataPos_By_Date_TimeRange_Word(currentDay, bHour, eHour, currentWord);
				dataWords = qManager.getAllText_By_Date_TimeRange(currentDay, bHour, eHour);
				setMarkerPos(dataPos,markers);
				break;
			}
		}
		if (controls.get(21).checkIn(mx,my)) {
			System.out.println("hour- Clicked");
			if (bHour>0) {
				bHour --;
				eHour --;
				dataPos = qManager.getDataPos_By_Date_TimeRange_Word(currentDay, bHour, eHour, currentWord);
				dataWords = qManager.getAllText_By_Date_TimeRange(currentDay, bHour, eHour);
				setMarkerPos(dataPos,markers);
			}
		}
		if (controls.get(22).checkIn(mx,my)) {
			System.out.println("hour+ Clicked");
			if (eHour <24) {
				bHour ++;
				eHour ++;
				dataPos = qManager.getDataPos_By_Date_TimeRange_Word(currentDay, bHour, eHour, currentWord);
				dataWords = qManager.getAllText_By_Date_TimeRange(currentDay, bHour, eHour);
				setMarkerPos(dataPos,markers);
			}
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
