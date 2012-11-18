/**
 * 
 */
package main;

import Util.Colors;
import Util.Utilities;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @author giric
 *
 */
public class LocationList extends BasicControl {
	
	PApplet parent;
	boolean isSelected;

	public LocationList(PApplet parent, float x, float y, float width,
			float height) {
		super(parent, x, y, width, height);
		// TODO Auto-generated constructor stub
		
		this.parent = parent;
		isSelected = false;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		if (isSelected){
			parent.fill(Colors.buttonSelectedColor);
			parent.stroke(Colors.buttonSelectedBorder);
		}
		else {
			parent.stroke(Colors.buttonBorder);
			parent.fill(Colors.buttonColor);
		}
		parent.strokeWeight(Utilities.Converter(0.5));
		parent.rectMode(PConstants.CORNER);
		parent.rect(myX, myY, myWidth, myHeight);
	}

}
