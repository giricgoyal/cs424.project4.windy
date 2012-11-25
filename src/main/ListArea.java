/**
 * 
 */
package main;

import java.util.ArrayList;

import markers.MarkerType;

import Util.Colors;
import Util.Positions;
import Util.U;
import Util.Utilities;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import types.DataLocation;

/**
 * @author giric
 *
 */
public class ListArea extends BasicControl {
	
	PApplet parent;
	int cid;
	String parentId;
	ArrayList<DataLocation> array;
	String buttonName;
	
	PShape backButton;
	float backButtonHeight;
	float backButtonWidth;
	float backButtonX;
	float backButtonY;
	
	boolean selected;
	
	CS424_Project4_Group4 program;
	
	//int level;
	
	float v1x, v2x, v3x, v4x, v5x, v6x;
	float v1y, v2y, v3y, v4y, v5y, v6y;

	public ListArea(PApplet parent, float x, float y, float width,
			float height, CS424_Project4_Group4 program) {
		super(parent, x, y, width, height);
		// TODO Auto-generated constructor stub
		
		this.parent = parent;
		this.program = program;
		//this.level = 0;
		this.cid = 0;
		this.parentId = "null";
		
		v1x = myX;
		v1y = myY;
		
		v2x = v1x;
		v2y = v1y + myHeight;
		
		v3x = v1x + myWidth;
		v3y = v2y;
		
		v6x = v3x;
		v6y = v1y;
		
		backButton = parent.loadShape("backButton.svg");
		backButtonHeight = backButton.height / Utilities.Converter(3);
		backButtonWidth = backButton.width / Utilities.Converter(3);
		backButtonX = Positions.listWindowX + Positions.listWindowWidth - backButtonWidth - Utilities.Converter(2);
		backButtonY = Positions.listWindowY + Positions.listWindowHeight - Utilities.Converter(2) - backButtonHeight;
		
		selected = false;
		
	}
	
	void drawLocationItems() {
		if (this.selected) {
			parent.noStroke();
			parent.fill(Colors.buttonSelectedColor);
			parent.beginShape();
				parent.vertex(v1x, v1y);
				parent.vertex(v2x, v2y);
				parent.vertex(v3x, v3y);
				parent.vertex(v4x, v4y);
				parent.vertex(v5x, v5y);
				parent.vertex(v6x, v6y);
			parent.endShape(PConstants.CLOSE);
			
			parent.shape(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
			
		
			int count = 0;
			parent.textAlign(PConstants.LEFT, PConstants.CENTER);
			parent.textSize(Utilities.Converter(5));
			parent.fill(Colors.black);
			if (this.parentId.compareToIgnoreCase("null") == 0) {
				if (this.cid == 0){
					
					parent.text("All Locations", myX + Utilities.Converter(5), myY + Utilities.Converter(5));
					parent.text("Area", myX + Utilities.Converter(5), myY + Utilities.Converter(15));
					parent.text("Interstates", myX + Utilities.Converter(5), myY + Utilities.Converter(25));
					parent.text("Vast River", myX + Utilities.Converter(5), myY + Utilities.Converter(35));
				}
				else {
					int matchCount = 1;
					if (this.cid == 1)
						parent.text("All areas", myX + Utilities.Converter(5), myY + Utilities.Converter(4));
					else if (this.cid == 6)
						parent.text("All Interstates", myX + Utilities.Converter(5), myY + Utilities.Converter(4));
					while(count< this.array.size()){
						if (this.array.get(count).getCid() == this.cid){
							parent.text(this.array.get(count).getName(), myX + Utilities.Converter(5), myY + Utilities.Converter(4 * (2*matchCount + 1)));
							matchCount++;
						}
						count++;
					}
					
				}
			}
			else {
				int matchCount = 1;
				
				while(count< this.array.size()){
					if (this.array.get(count).getId() == Integer.valueOf(this.parentId)){
						parent.text(this.array.get(count).getName(), Positions.listWindowX + Utilities.Converter(5), Positions.listWindowY + Utilities.Converter(4));
					}
					if (this.array.get(count).getParentId().compareToIgnoreCase(this.parentId) == 0){
						parent.text(this.array.get(count).getName(), Positions.listWindowX + Utilities.Converter(5), Positions.listWindowY + Utilities.Converter(4 * (2*matchCount + 1)));
						matchCount++;
					}
					count++;
				}
			}
		}
	}
	
	void drawKeywordItems() {
		if (this.selected) {
			parent.noStroke();
			parent.fill(Colors.buttonSelectedColor);
			parent.beginShape();
				parent.vertex(v1x, v1y);
				parent.vertex(v2x, v2y);
				parent.vertex(v3x, v3y);
				parent.vertex(v4x, v4y);
				parent.vertex(v5x, v5y);
				parent.vertex(v6x, v6y);
			parent.endShape(PConstants.CLOSE);
			
			parent.shape(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
			
			parent.textAlign(PConstants.LEFT, PConstants.CENTER);
			parent.textSize(Utilities.Converter(5));
			parent.fill(Colors.black);
			
			int count = 0;
			while (count < Utilities.keywordList.size()) {
				parent.text(Utilities.keywordList.get(count), Positions.listWindowX + Utilities.Converter(5), Positions.listWindowY + Utilities.Converter(4 * (2*count + 1)));
				count++;
			}
			
		}
	}
	
	void drawEventItems() {
		if (this.selected) {
			parent.noStroke();
			parent.fill(Colors.buttonSelectedColor);
			parent.beginShape();
				parent.vertex(v1x, v1y);
				parent.vertex(v2x, v2y);
				parent.vertex(v3x, v3y);
				parent.vertex(v4x, v4y);
				parent.vertex(v5x, v5y);
				parent.vertex(v6x, v6y);
			parent.endShape(PConstants.CLOSE);
			
			parent.shape(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
			
			parent.textAlign(PConstants.LEFT, PConstants.CENTER);
			parent.textSize(Utilities.Converter(5));
			parent.fill(Colors.black);
			
			int count = 0;
			while (count < Utilities.eventList.size()) {
				parent.text(Utilities.eventList.get(count), Positions.listWindowX + Utilities.Converter(5), Positions.listWindowY + Utilities.Converter(4 * (2*count + 1)));
				count++;
			}
			
		}
	}
	
	void drawPersonItems() {
		if (this.selected) {
			parent.noStroke();
			parent.fill(Colors.buttonSelectedColor);
			parent.beginShape();
				parent.vertex(v1x, v1y);
				parent.vertex(v2x, v2y);
				parent.vertex(v3x, v3y);
				parent.vertex(v4x, v4y);
				parent.vertex(v5x, v5y);
				parent.vertex(v6x, v6y);
			parent.endShape(PConstants.CLOSE);
			
			parent.shape(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
			
			parent.textAlign(PConstants.LEFT, PConstants.CENTER);
			parent.textSize(Utilities.Converter(5));
			parent.fill(Colors.black);
			
			int count = 0;
			while (count < Utilities.personList.size()) {
				parent.text(Utilities.personList.get(count), Positions.listWindowX + Utilities.Converter(5), Positions.listWindowY + Utilities.Converter(4 * (2*count + 1)));
				count++;
			}
			
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		if (this.buttonName.compareTo("location") == 0)
			this.drawLocationItems();
		else if (this.buttonName.compareTo("keyword") == 0) 
			this.drawKeywordItems();
		else if (this.buttonName.compareTo("event") == 0)
			this.drawEventItems();
		else if (this.buttonName.compareTo("person") == 0)
			this.drawPersonItems();
	}
	
	void clickLocation(float mx, float my) {
		if (mx > backButtonX && mx < backButtonX + backButtonWidth && my > backButtonY && my < backButtonY + backButtonHeight){
			
			if (this.parentId.compareTo("null") == 0) {
				if (this.cid == 0) {
					this.selected = false;
				}
				else if (this.cid == 1 || this.cid == 6) {
					this.cid = 0;
				}
			}
			else {
				this.cid = 1;
				this.parentId = "null";
			}
		}
		else {
			if (this.parentId.compareToIgnoreCase("null") == 0){
				if (this.cid == 0) {
					if (mx > myX && my > myY && mx < myX + myWidth && my < myY + Utilities.Converter(10)){
						Utilities.selectedLocationId = 99;
						System.out.println("All locations Selected" + " : " + Utilities.selectedLocationId);
						this.selected = false;
					}
					if (mx > myX && my > myY + Utilities.Converter(10) && mx < myX + myWidth && my < myY + Utilities.Converter(20))
						this.cid = 1;
					if (mx > myX && my > myY + Utilities.Converter(20) && mx < myX + myWidth && my < myY + Utilities.Converter(30))
						this.cid = 6;
					if (mx > myX && my > myY + Utilities.Converter(30) && mx < myX + myWidth && my < myY + Utilities.Converter(40)) {
						Utilities.selectedLocationId = 36;
						System.out.println("VAst LAke selected" + " : " + Utilities.selectedLocationId);
						this.selected = false;
					}
				}
				else if (this.cid == 6) {
					int count = 0;
					int matchCount = 1;
					if (mx >  myX && mx < myX + myWidth) {
						if (my > myY && my < myY + Utilities.Converter(8)){
							Utilities.selectedLocationId = 97;
							System.out.println("All interstates selected" + " : " + Utilities.selectedLocationId);
							this.selected = false;
						}
					}
					while(count< this.array.size()){
						if (this.array.get(count).getCid() == this.cid){
							if (mx > myX && mx < myX + myWidth) {
								if (my > myY + Utilities.Converter(8 * matchCount) && my < myY + Utilities.Converter(8 * (matchCount+1))) {
									Utilities.selectedLocationId = this.array.get(count).getId();
									this.selected = false;
									System.out.println(this.array.get(count).getName() + " : " + Utilities.selectedLocationId);
								}
							}
							matchCount++;
						}
						count++;
					}
				}
				else if (this.cid == 1) {
					int count = 0;
					int matchCount = 1;
					if (mx >  myX && mx < myX + myWidth) {
						if (my > myY && my < myY + Utilities.Converter(8)){
							Utilities.selectedLocationId = 98;
							System.out.println("All areas except interstates and the vast river selected" + " : " + Utilities.selectedLocationId);
							this.selected = false;
						}
					}
					while(count< this.array.size()){
						if (this.array.get(count).getCid() == this.cid){
							if (mx > myX && mx < myX + myWidth) {
								if (my > myY + Utilities.Converter(8 * matchCount) && my < myY + Utilities.Converter(8 * (matchCount+1))) {
									this.parentId = String.valueOf(this.array.get(count).getId());
								}
							}
							matchCount++;
						}
						count++;
					}
				}
			}
			else if (this.parentId.compareToIgnoreCase("null")!=0) {
				int count = 0;
				int matchCount = 1;
				while(count < this.array.size()){
					if (this.array.get(count).getId() == Integer.valueOf(this.parentId)) {
						if (mx > myX && mx < myX + myWidth) {
							if (my > myY && my < myY + Utilities.Converter(8)){
								Utilities.selectedLocationId = this.array.get(count).getId();
								this.selected = false;
								System.out.println(this.array.get(count).getName() + " : " + Utilities.selectedLocationId);
							}
						}
					}
					if (this.array.get(count).getParentId().compareToIgnoreCase(this.parentId) == 0){
						if (mx > myX && mx < myX + myWidth) {
							if (my > myY + Utilities.Converter(8 * matchCount) && my < myY + Utilities.Converter(8 * (matchCount+1))) {
								Utilities.selectedLocationId = this.array.get(count).getId();
								this.selected = false;
								System.out.println(this.array.get(count).getName() + " : " + Utilities.selectedLocationId);
							}
						}
						matchCount++;
					}
					count++;
				}
			}
		}

	}
	
	void clickKeyword(float mx, float my) {
		int count = 0;
		if (mx > backButtonX && mx < backButtonX + backButtonWidth && my > backButtonY && my < backButtonY + backButtonHeight){
			this.selected = false;
		}
		else {
			while(count < Utilities.keywordList.size()){
				if (mx > myX && mx < myX + myWidth) {
					if (my > myY + Utilities.Converter(8 * count) && my < myY + Utilities.Converter(8 * (count+1))) {
						Utilities.currentWord = Utilities.keywordList.get(count);
						this.selected = false;
						
						program.setCurrentData(program.dataPos,program.dataDay,U.bHalf,U.eHalf,U.currentWord);
						program.setMarkerPos(program.dataPos,program.markers,MarkerType.DEFAULT_MARKER);
						
						System.out.println(Utilities.keywordList.get(count));
					}
				}
				
				count++;
			}
		}		
	}
	
	void clickEvent(float mx, float my) {
		int count = 0;
		if (mx > backButtonX && mx < backButtonX + backButtonWidth && my > backButtonY && my < backButtonY + backButtonHeight){
			this.selected = false;
		}
		else {
			while(count < Utilities.eventList.size()){
				if (mx > myX && mx < myX + myWidth) {
					if (my > myY + Utilities.Converter(8 * count) && my < myY + Utilities.Converter(8 * (count+1))) {
						Utilities.currentWord = Utilities.eventList.get(count);
						this.selected = false;
						
						program.setCurrentData(program.dataPos,program.dataDay,U.bHalf,U.eHalf,U.currentWord);
						program.setMarkerPos(program.dataPos,program.markers,MarkerType.DEFAULT_MARKER);
						
						System.out.println(Utilities.eventList.get(count));
					}
				}
				
				count++;
			}
		}		
	}
	
	void clickPerson(float mx, float my) {
		int count = 0;
		if (mx > backButtonX && mx < backButtonX + backButtonWidth && my > backButtonY && my < backButtonY + backButtonHeight){
			this.selected = false;
		}
		else {
			while(count < Utilities.personList.size()){
				if (mx > myX && mx < myX + myWidth) {
					if (my > myY + Utilities.Converter(8 * count) && my < myY + Utilities.Converter(8 * (count+1))) {
						Utilities.tweetPid = Utilities.personList.get(count);
						this.selected = false;
						
						//program.setCurrentData(program.dataPos,program.dataDay,U.bHalf,U.eHalf,U.currentWord);
						//program.setMarkerPos(program.dataPos,program.markers,MarkerType.DEFAULT_MARKER);
						
						System.out.println(Utilities.eventList.get(count));
					}
				}
				
				count++;
			}
		}		
	}
	
	public void click(float  mx, float my){
		if (this.buttonName.compareTo("location") == 0) 
			this.clickLocation(mx, my);
		else if (this.buttonName.compareTo("keyword") == 0)
			this.clickKeyword(mx, my);
		else if (this.buttonName.compareTo("event") == 0)
			this.clickEvent(mx, my);
		else if (this.buttonName.compareTo("person") == 0)
			this.clickPerson(mx, my);
		
		/*
		program.setCurrentData(program.dataPos,program.dataDay,U.bHalf,U.eHalf,U.currentWord);
		program.setMarkerPos(program.dataPos,program.markers,MarkerType.DEFAULT_MARKER);
		
		program.KeywordList.setSelected(false);
		program.locationButton.setSelected(false);
		program.EventList.setSelected(false);
		program.PersonList.setSelected(false);
		*/
	}

	public void setButtonSelected(String button, float buttonX, float buttonY, float buttonHeight) {

		this.buttonName = button;
		v5x = buttonX;
		v5y = buttonY;
		
		v4x = buttonX;
		v4y = buttonY + buttonHeight;
		
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	public void setLocationData(ArrayList<DataLocation> array, int cid, String parentId){
		this.array = array;
		this.cid = cid;
		this.parentId = parentId;
	}
}
