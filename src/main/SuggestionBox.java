/**
 * 
 */
/**
 * Class to create the suggestion and text box
 * @author giric
 */


package main;


import java.util.ArrayList;

import Util.Colors;
import Util.Positions;
import Util.Utilities;

//import com.modestmaps.InteractiveMap;
//import com.modestmaps.geo.Location;

import db.DatabaseManager;

import processing.core.PApplet;
import processing.core.PConstants;
import types.DataState;

public class SuggestionBox extends BasicControl {
	
	float maximumHeight;
	int textBoxBorderColor;
	int textBoxBackgroundColor;
	int suggestionBoxBorderColor;
	int suggestionBoxBackgroundColor;
	int textBoxTextColor;
	
	String textBoxText;
	DatabaseManager db;
	CS424_Project4_Group4 program;
	
	String[] dataWords;
	
	/**
	 * temp test string
	 */
	//String[] testString = {"hello","yoyo honey singh","holaaa","wadddhhuppp","blah blah","uncle","haloween","home"};
	ArrayList<WordCountPair> dataWordCountPair;
	
	public SuggestionBox(PApplet parent, float x, float y, float width,	float height, CS424_Project4_Group4 program) {
		super(parent, x, y, width, height);
		this.textBoxText = "";
		this.textBoxBorderColor = Colors.black;
		this.textBoxBackgroundColor = Colors.gray;
		this.suggestionBoxBackgroundColor = Colors.light;
		this.suggestionBoxBorderColor = Colors.dark;
		this.textBoxTextColor = Colors.black;
		
		this.program=program;
		
		Positions.suggestionBoxX = myX;
		Positions.suggestionBoxY = myY + Utilities.Converter(2) - myHeight*5;
		Positions.suggestionBoxWidth = myWidth;
		Positions.suggestionBoxHeight = myHeight*5 - Utilities.Converter(2);
		
		
		dataWordCountPair = new ArrayList<WordCountPair>();
		db = new DatabaseManager(parent);
	}

	//text box for searches
	
	
	public void draw() {
		
		
		int count = 0;
		int matchCount = 0;
		parent.strokeWeight(Utilities.Converter(1));
		parent.stroke(textBoxBorderColor);
		parent.fill(textBoxBackgroundColor);
		parent.rectMode(PConstants.CORNER);
		parent.rect(myX, myY, myWidth, myHeight);
		
		if (!textBoxText.isEmpty()) {
			
			parent.rect(Positions.suggestionBoxX, Positions.suggestionBoxY, Positions.suggestionBoxWidth, Positions.suggestionBoxHeight);
			parent.textAlign(PConstants.LEFT, PConstants.CENTER);
			parent.textSize((float) myHeight*0.5f);
			parent.fill(textBoxTextColor);
			
			String a = String.valueOf(textBoxText.charAt(0));
			String textBoxTextTemp = a.toUpperCase() + textBoxText.substring(1);
			
			parent.text(textBoxTextTemp, Positions.textBoxX + Utilities.Converter(2), Positions.textBoxY + Positions.textBoxHeight/2);
			
			parent.textAlign(PConstants.LEFT, PConstants.CENTER);
			parent.textSize(Positions.suggestionBoxHeight/5*0.5f);
			parent.fill(Colors.black);
			while (count < dataWordCountPair.size()) {
				if (dataWordCountPair.get(count).getWord().toLowerCase().contains(textBoxText)) {
					parent.text(dataWordCountPair.get(count).getWord(), Positions.suggestionBoxX + Utilities.Converter(2), Positions.suggestionBoxY + myHeight*(5-matchCount) - myHeight/2 - Utilities.Converter(1));
					matchCount++;
				}
				if (matchCount == 5) {
					break;
				}
				count++;
			}
			/*
			while(count<states.size()) {
				if(states.get(count).getName().toLowerCase().contains(textBoxText)){
					parent.text(states.get(count).getName(), Positions.suggestionBoxX + Utilities.Converter(2), Positions.suggestionBoxY + myHeight*(5-matchCount) - myHeight/2);
					matchCount++;
				}
				if (matchCount == 5) {
					break;
				}
			count++;
			}
			*/
			
		}
		
		
		/*
		else {
			while(count<5&&states.size()>0) {
				parent.text(states.get(count).getName(), Positions.suggestionBoxX, Positions.suggestionBoxY + myHeight*(5-matchCount) - myHeight/2);
				matchCount++;
			count++;
			}
		}
		*/
	}
	
	/**
	 * method to update the textbox (autoComplete0
	 * @param charNum
	 */
	
	public void updateTextBox(int charNum) {
		//this.dataWordCountPair = Utilities.dataWordCountPair;
		
		//states = db.getStates(textBoxText);
		System.out.println(charNum);
		if (charNum == -1) {
			if (textBoxText.isEmpty()) {
				System.out.println("empty");
				Utilities.suggestionBox = false;
			}
			else {
				textBoxText = textBoxText.substring(0, textBoxText.length()-1);
				Utilities.suggestionBox = true;
				if (textBoxText.isEmpty())
					Utilities.suggestionBox = false;
				
			}
		}
		else {
			textBoxText = textBoxText.concat(String.valueOf((char)charNum));
			Utilities.suggestionBox = true;
			if (textBoxText.isEmpty())
				Utilities.suggestionBox = false;
			//String a = String.valueOf(textBoxText.charAt(0));
			//textBoxText = a.toUpperCase() + textBoxText.substring(1);
		}
		this.dataWordCountPair = new ArrayList<WordCountPair>();
		for (WordCountPair wcp : Utilities.dataWordCountPair) {
			if (wcp.getWord().contains(textBoxText)) {
				this.dataWordCountPair.add(new WordCountPair(wcp.getWord()));
			}
		}
		System.out.println(textBoxText);
	}
	
	
	/**
	 * implementing the click function
	 * @param x = mouseX
	 * @param y = mouseY
	 */
	public void Click(float x, float y) {
		System.out.print("click");
		int count = 0;
		int matchCount = 0;
		String clickedString = "";
		if (!textBoxText.isEmpty()) {
			while(count < dataWordCountPair.size()) {
				if (dataWordCountPair.get(count).getWord().toLowerCase().contains(textBoxText)) {
					if(x > Positions.suggestionBoxX && x < Positions.suggestionBoxX + Positions.suggestionBoxWidth) {
						if(y > Positions.suggestionBoxY - myHeight*(4-matchCount) - Utilities.Converter(1) && y < Positions.suggestionBoxY + myHeight*(5-matchCount) - Utilities.Converter(1)) {
							clickedString = dataWordCountPair.get(count).getWord();
							textBoxText = "";
							Utilities.suggestionBox = false;
						}
					}
					matchCount++;
				}
				count++;
			}
			/*
			while(count<states.size()) {
				if(states.get(count).getName().toLowerCase().contains(textBoxText)){
					if(x > Positions.suggestionBoxX && x < Positions.suggestionBoxX + Positions.suggestionBoxWidth) {
						if(y > Positions.suggestionBoxY - myHeight*(4-matchCount) && y < Positions.suggestionBoxY + myHeight*(5-matchCount)) {
							clickedString = states.get(count).getName();
							textBoxText = "";
							Utilities.suggestionBox = false;
						}
					}
					matchCount++;
				}
			count++;
			}
			*/
		}
		else
			Utilities.suggestionBox = false;
		
		/**
		 * Whatever needs to be updated, call that here
		 * 
		 */
		for (count = 0; count < dataWordCountPair.size(); count++) {
			if (dataWordCountPair.get(count).getWord().equals(clickedString)) {
				System.out.println(dataWordCountPair.get(count).getWord());
				//program.map.setCenterZoom(new Location(states.get(i).getLatitude(), states.get(i).getLongitude()),Utilities.zoomState);
				//program.updateMarkerList();
				//program.gm.computeGridValues();
				break;
			}
		}
		
		/*
		else {
			while(count<5) {
				if(states.get(count).getName().contains(textBoxText)){
					if(x > Positions.suggestionBoxX && x < Positions.suggestionBoxX + Positions.suggestionBoxWidth) {
						if(y > Positions.suggestionBoxY - myHeight*(4-matchCount) && y < Positions.suggestionBoxY + myHeight*(5-matchCount)) {
							clickedString = states.get(count).getName();
							
						}
					}
				//parent.text(testString[count], Positions.suggestionBoxX, Positions.suggestionBoxY + myHeight*(5-matchCount) - myHeight/2);
				matchCount++;
				}
			count++;
			}
		}
		*/
		
		/**
		 * get the name of the string upon click
		 * currently just displaying on the console
		 * modify below to assign a variable or something
		 */
		
	}
}
