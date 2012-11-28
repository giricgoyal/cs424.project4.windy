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
public class Help extends BasicControl{

	PApplet parent;
	boolean selected;
	
	public Help(PApplet parent, float x, float y, float width, float height) {
		super(parent, x, y, width, height);
		// TODO Auto-generated constructor stub
		this.parent = parent;
		selected = false;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		parent.fill(Colors.helpColor);
		parent.rectMode(PConstants.CORNER);
		parent.rect(myX + Utilities.Converter(2), myY + Utilities.Converter(2), myWidth - Utilities.Converter(4), myHeight - Utilities.Converter(4));
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
