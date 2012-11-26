/**
 * class to implement histograph
 */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.text.Position;

import Util.Colors;
import Util.Positions;
import Util.Utilities;


import processing.core.PApplet;
import processing.core.PConstants;
import types.DataPair;
import types.DataYearPair;

/**
 * @author giric
 *
 */
public class Graph extends BasicControl {
	
	float lowerBound;
	float upperBound;
	String xLabel;
	String yLabel;
	String mainLabel;
	String focusAttribute;
	String defaultFocusAttribute;
	
	String[] dates = {"4/30" , "5/01", "5/02", "5/03" ,"5/04", "5/05", "5/06", "5/07", "5/08", 
						"5/09", "5/10", "5/11", "5/12", "5/13", "5/14", "5/15", "5/16", "5/17", "5/18", "5/19", "5/20"};
	/**
	 * sample string to plot an example. 
	 * add an argument in the constructor to draw histograms for different information as per the need
	 */
	//public String[][] sampleData = {{"2001","2002","2003","2004","2005","2006","2007","2008","2009","2010"},
	//		{"20","40","160","10","90","100","45","40","0","70"}};
	
	//private ArrayList<DataYearPair> sampleData;
	//private ArrayList<DataCrashInstance> sampleData;
	
	private int fieldCount;
	/*
	Hashtable allYearCrashes;
	Hashtable activeYearCrashes;
	Hashtable hashTemp;
	
	Enumeration enumerationAll;
	Enumeration enumerationActive;
	Enumeration enumTemp;
	
	*/
	
	CS424_Project4_Group4 program;
	
	
	public Graph(PApplet parent, float x, float y, float width,float height, CS424_Project4_Group4 program) {
		super(parent, x, y, width, height);
		//sampleData = new ArrayList<DataYearPair>();
		//sampleData = new ArrayList<DataCrashInstance>();
		this.program = program;
		// TODO Auto-generated constructor stub
	}
	
	public void setData() {
		int count = 0;
		while(count < Utilities.keywordGraph.size()) {
			program.dataKeywordCount[count] = program.qManager.getKeywordCount(Utilities.keywordGraph.get(count));
			count++;
		}
		setBounds();
	}
	
	/*
	public void setData(ArrayList<DataYearPair> sampleData){
		this.sampleData = sampleData;
	}
	*/
	/*
	public void setData(ArrayList<DataCrashInstance> sampleData) {
		this.sampleData = sampleData;
		this.focusAttribute = Utilities.focusAttribute;
		this.defaultFocusAttribute = Utilities.defaultFocusAttribute;
		allYearCrashes = new Hashtable();
		activeYearCrashes = new Hashtable();
		
		int index = FilterValues.attributesHasMap.get(this.defaultFocusAttribute);
		
		if (Utilities.histOptions != null){
			index = FilterValues.attributesHasMap.get(this.focusAttribute);
		}
		//System.out.println("Index : " + index);
		DataCrash[] temp = FilterValues.filtersValue[index];
		Utilities.histOptions = new String[temp.length];
		for (int i=0; i<Utilities.histOptions.length;i++) {
			Utilities.histOptions[i] = temp[i].getToShowVaue();
		}
		//System.out.println("Check");
		float[] arrayAllYears = new float[Utilities.histOptions.length];
		float[] arrayActiveYear = new float[Utilities.histOptions.length];
		Arrays.fill(arrayAllYears, 0);
		Arrays.fill(arrayActiveYear, 0);
		//System.out.println(Utilities.activeYear);
		for (int count = 0; count < sampleData.size(); count++) {
			int optionCount = 0;
			while(optionCount < Utilities.histOptions.length) {
				if (sampleData.get(count).getLatitude() > Utilities.minActiveLatitude && sampleData.get(count).getLatitude() < Utilities.maxActiveLatitude && sampleData.get(count).getLongitude() > Utilities.minActiveLongitude && sampleData.get(count).getLongitude() < Utilities.maxActiveLongitude){
					if (Utilities.histOptions[optionCount].compareToIgnoreCase(sampleData.get(count).getByIndex(index)) == 0){
						arrayAllYears[optionCount] += 1/Utilities.perStatePopulation[sampleData.get(count).getStateFIPS()-1];
					}
					if (Utilities.histOptions[optionCount].compareToIgnoreCase(sampleData.get(count).getByIndex(index)) == 0 && Utilities.activeYear == sampleData.get(count).getYear()){
						arrayActiveYear[optionCount] += 1/Utilities.perStatePopulation[sampleData.get(count).getStateFIPS()-1];
					}
				}	
				optionCount++;
			}
			
			
		}
		for (int count=0; count < Utilities.histOptions.length; count++) {
		
			allYearCrashes.put(Utilities.histOptions[count], new DataPair(Utilities.histOptions[count], arrayAllYears[count]));
			activeYearCrashes.put(Utilities.histOptions[count], new DataPair(Utilities.histOptions[count], arrayActiveYear[count]));
			
		}
		
		setBounds();
		
		Utilities.barWidth = (int)((int)Positions.histographWidth/Utilities.histOptions.length - Utilities.Converter(2));
		if (Utilities.histOptions.length == 2) {
			Utilities.barWidth = (int)((int)Positions.histographWidth/Utilities.histOptions.length - Utilities.Converter(8));
		}
	}
	*/
	
	private float getMax() {
		float max = PConstants.MIN_FLOAT;
		int count = 0;
		int[] tempArray;
		while (count < Utilities.keywordGraph.size()) {
			tempArray = program.dataKeywordCount[count].getCnt();
			for (int i = 0; i < tempArray.length; i++) {
				if (tempArray[i] > max) {
					max = tempArray[i];
				}
			}
			count++;
		}
		System.out.println("max : " + max);
		return max;
	}
	
	private float getMin() {
		float min = PConstants.MAX_FLOAT;
		int count = 0;
		int[] tempArray;
		while (count < Utilities.keywordGraph.size()) {
			tempArray = program.dataKeywordCount[count].getCnt();
			for (int i = 0; i < tempArray.length; i++) {
				if (tempArray[i] < min) {
					min = tempArray[i];
				}
			}
			count++;
		}
		System.out.println("min : " + min);
		return min;
	}
	/*
	private float getMin() {
		float min = PConstants.MAX_FLOAT;
		enumerationAll = allYearCrashes.keys();
		enumerationActive = activeYearCrashes.keys();
		if (mainLabel.compareToIgnoreCase(Utilities.hist1String) == 0) {
			enumTemp = enumerationAll;
			hashTemp = allYearCrashes;
		}
		else if (mainLabel.compareToIgnoreCase(Utilities.hist2String)==0) {
			enumTemp = enumerationActive;
			hashTemp = activeYearCrashes;
		}
		while(enumTemp.hasMoreElements()){
			DataPair dataPair = (DataPair)hashTemp.get(enumTemp.nextElement());
			if (min > dataPair.getValue()) {
				min = dataPair.getValue();
			}
		}
		//System.out.println("min : " + min);
		return min;
	}
	*/
	
	
	/*
	private float getMax() {
		float max = PConstants.MIN_FLOAT;
		enumerationAll = allYearCrashes.keys();
		enumerationActive = activeYearCrashes.keys();
		if (mainLabel.compareToIgnoreCase(Utilities.hist1String) == 0) {
			enumTemp = enumerationAll;
			hashTemp = allYearCrashes;
		}
		else if (mainLabel.compareToIgnoreCase(Utilities.hist2String)==0) {
			enumTemp = enumerationActive;
			hashTemp = activeYearCrashes;
		}
	
		while(enumTemp.hasMoreElements()){
			DataPair dataPair = (DataPair)hashTemp.get(enumTemp.nextElement());
			if (max <  dataPair.getValue()) {
				max =  dataPair.getValue();
			}
		}
		
		//System.out.println("max : " + max);
		return max;
		
	}
	*/
	
	/*
	@SuppressWarnings("static-access")
	private void drawDataBars() {
		String newMainLabel = "";
		
		// draw the rectangle outline window
		 
		
		parent.rectMode(PConstants.CENTER);
		parent.noFill();
		parent.stroke(Colors.light);
		parent.strokeWeight(Utilities.Converter(1));
		//parent.rect(Positions.histogramAreaX, Positions.histogramAreaY, Positions.histogramAreaWidth, Positions.histogramAreaHeight);
		parent.fill(Colors.dark);
		parent.stroke(Colors.light);
		parent.strokeWeight(Utilities.Converter(1));
		parent.rect(myX, myY, myWidth, myHeight);
		
		
		// Draw the data bars
		 
		
		enumerationAll = allYearCrashes.keys();
		enumerationActive = activeYearCrashes.keys();
		fieldCount = 0;
		if (mainLabel.compareToIgnoreCase(Utilities.hist1String) == 0) {
			enumTemp = enumerationAll;
			hashTemp = allYearCrashes;
			newMainLabel = mainLabel;
		}
		else if (mainLabel.compareToIgnoreCase(Utilities.hist2String)==0) {
			enumTemp = enumerationActive;
			hashTemp = activeYearCrashes;
			newMainLabel = mainLabel + " " + Utilities.activeYear;
		}
		
		int w=0;
		//while (enumTemp.hasMoreElements()){
		while(fieldCount<Utilities.histOptions.length){
			DataPair dataPair = (DataPair)hashTemp.get(Utilities.histOptions[fieldCount]);
			
			float value = dataPair.getValue();
			String field = dataPair.getField();
			
			float x = parent.map(fieldCount, 0, hashTemp.size(), myX - myWidth/2 + Utilities.Converter(15), myX + myWidth/2 - Utilities.Converter(15));
			float y = parent.map(value, 0, this.upperBound, myY + myHeight/2, myY - myHeight/2 + Utilities.Converter(10));
			//System.out.println(value);	
			
			// Bars
			 
			parent.noStroke();
			parent.fill(Utilities.colorCodes[fieldCount%Utilities.colorCodes.length]);
			parent.rectMode(PConstants.CORNERS);
			parent.rect(x, y, x + Utilities.barWidth - Utilities.Converter(10), myY + myHeight/2);
			
			
			//  X-axis units
			 
			parent.fill(Colors.white);
			parent.textAlign(PConstants.CENTER, PConstants.TOP);
			parent.textSize(Utilities.Converter(7));
			
			field = field.trim();
			if (this.focusAttribute.compareToIgnoreCase("Light Condition") == 0) {
				field = field.replaceAll("-", "");
			}
			String tempField = "";
			while(field.contains(" ")) {
				tempField = tempField.concat(field.substring(0,field.indexOf(" ")));
				tempField = tempField.concat("\n");
				System.out.println(field.substring(0,field.indexOf(" ")));
				field = field.substring(field.indexOf(" "));
				field = field.trim();
			}
			
			tempField = tempField.concat(field);
			
			//System.out.println("field: " + tempField);
			if (this.focusAttribute.compareToIgnoreCase("Month")==0 || this.focusAttribute.compareToIgnoreCase("day of week")==0) {
				tempField = tempField.substring(0,3);
			}
			parent.text(tempField, x + Utilities.barWidth/2, myY + myHeight/2 + Utilities.Converter(5));
			fieldCount++;
		}
		
		
		
		// Y-axis units: No. of Crashes or fatalities
		 
		
		for (int value = 0; value <= this.upperBound; value += (this.upperBound)/5) {
			float y = parent.map(value, 0, this.upperBound, myY + myHeight/2, myY - myHeight/2 + Utilities.Converter(10));
			parent.textAlign(PConstants.RIGHT, PConstants.CENTER);
			parent.text(value, myX - myWidth/2 - Utilities.Converter(10), y);
		
		}
		
		
		// X-axis label
		 
		parent.textAlign(PConstants.CENTER, PConstants.TOP);
		parent.textSize(Utilities.Converter(9));
		if (Utilities.histOptions != null)
			parent.text(this.focusAttribute, myX, myY + myHeight/2 + Utilities.Converter(25));
		else
			parent.text(this.defaultFocusAttribute, myX, myY + myHeight/2 + Utilities.Converter(25));
		
		
		 // Main label: "Crashes/Fatalities"
		 
		parent.textAlign(PConstants.CENTER, PConstants.TOP);
		parent.textSize(Utilities.Converter(10));
		parent.text(newMainLabel, myX, Positions.histogramAreaY - Positions.histogramAreaHeight/2 + Utilities.Converter(10));
	
	}
	*/
	
	@SuppressWarnings("static-access")
	private void drawDataLines() {
		parent.rectMode(PConstants.CORNER);
		parent.noFill();
		parent.stroke(Colors.light);
		parent.strokeWeight(Utilities.Converter(1));
		parent.rect(Positions.graphWindowX, Positions.graphWindowY, Positions.graphWindowWidth, Positions.graphWindowHeight);
		parent.fill(Colors.dark);
		parent.stroke(Colors.light);
		parent.strokeWeight(Utilities.Converter(1));
		parent.rect(myX, myY, myWidth, myHeight);
		
		// Draw Data lines
		
		int count = 0;
		String tempKeyword;
		int[] tempCountArray;
		while( count < Utilities.keywordGraph.size()) {
			tempCountArray = program.dataKeywordCount[count].getCnt();
			tempKeyword = program.dataKeywordCount[count].getKeyword();
			parent.beginShape();
			//parent.curveVertex(myX  + Utilities.Converter(2), myY + myHeight - Utilities.Converter(1));
			for (int i=0; i<tempCountArray.length; i++) {
				float value = tempCountArray[i];
				//System.out.println(i + " : " + value);
				float x = parent.map(i, 0, tempCountArray.length - 1, myX + Utilities.Converter(1), myX + myWidth - Utilities.Converter(1));
				float y = parent.map(value, this.lowerBound, this.upperBound, myY + myHeight - Utilities.Converter(1), myY + Utilities.Converter(5));
				
				// draw lines
				parent.stroke(Colors.GRAPH_COLORS[count]);
				parent.noFill();
				parent.strokeWeight(Utilities.Converter(1));
				parent.vertex(x, y);
				
			}
			//parent.curveVertex(myX + myWidth, myY + myHeight - Utilities.Converter(1));
			parent.endShape();
			count++;
		}
		
		// X-axis units
		for (int i=0; i<21; i++) {
			float x = parent.map(i, 0, 20, myX + Utilities.Converter(1), myX + myWidth - Utilities.Converter(1));
			parent.fill(Colors.WHITE);
			parent.textSize(Utilities.Converter(3.5));
			parent.textAlign(PConstants.CENTER, PConstants.TOP);
			parent.text(dates[i], x, myY + myHeight + Utilities.Converter(2));
		}
		
		// X-axis label
		parent.fill(Colors.WHITE);
		parent.textSize(Utilities.Converter(6));
		parent.textAlign(PConstants.CENTER, PConstants.TOP);
		parent.text("Day" , myX + myWidth/2, myY + myHeight + Utilities.Converter(6));
	}
	
	public void setBounds(){
		//float min = 0;
		float min = getMin();
		float max = getMax();
		
		this.lowerBound = min;
		this.upperBound = max;
			
	}
	
	public void setString(String mainLabel) {
		this.mainLabel = mainLabel;
		//this.xLabel = xLabel;
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		//drawDataBars();
		drawDataLines();
	}

}
