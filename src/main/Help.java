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
		
		if (selected) {
			parent.fill(Colors.helpColor);
			parent.rectMode(PConstants.CORNER);
			parent.noStroke();
			parent.rect(myX + Utilities.Converter(2), myY + Utilities.Converter(2), myWidth *3/6, myHeight - Utilities.Converter(4));
			parent.rect(myWidth * 5/6 - Utilities.Converter(2), 0, myWidth /6 + Utilities.Converter(2), myHeight / 3);
			parent.rect(myWidth * 3/6 + Utilities.Converter(2), myHeight * 1/3 - Utilities.Converter(2), myWidth * 3/6 - Utilities.Converter(2), myHeight * 2/3 + Utilities.Converter(2));
			
			
		
		
		
		
		}
	}
	
	
	@SuppressWarnings("static-access")
	private void drawArraow(String arrow, float x1, float y1, float x2, float y2) {
		parent.line(x1, y1, x2, y2);
		if (arrow.compareToIgnoreCase("ul") == 0) {
			parent.line(x1, y1, x1 - Utilities.Converter(20) * parent.cos(45), y1 - Utilities.Converter(20) * parent.sin(45));
		}
		if (arrow.compareToIgnoreCase("ur") == 0) {
			
		}
		if (arrow.compareToIgnoreCase("bl") == 0) {
			
		}
		if (arrow.compareToIgnoreCase("br") == 0) {
			
		}
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
