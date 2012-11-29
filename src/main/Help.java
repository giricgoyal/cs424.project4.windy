/**
 * 
 */
package main;

import Util.Colors;
import Util.Positions;
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
	
	@SuppressWarnings("static-access")
	private void drawArrow(String arrow, float x1, float y1, float x2, float y2) {
		parent.stroke(Colors.LIGHT_BLUE);
		parent.strokeWeight(Utilities.Converter(1));
		parent.fill(Colors.LIGHT_BLUE);
		parent.line(x1, y1, x2, y2);
		if (arrow.compareToIgnoreCase("ul") == 0) {
			parent.line(x1, y1, x1 - Utilities.Converter(10) * parent.cos(45), y1 - Utilities.Converter(10) * parent.sin(45));
		}
		if (arrow.compareToIgnoreCase("ur") == 0) {
			parent.line(x2, y2, x2 + Utilities.Converter(10) * parent.cos(45), y2 - Utilities.Converter(10) * parent.sin(45));
		}
		if (arrow.compareToIgnoreCase("bl") == 0) {
			parent.line(x1, y1, x1 - Utilities.Converter(10) * parent.cos(45), y1 + Utilities.Converter(10) * parent.sin(45));
		}
		if (arrow.compareToIgnoreCase("br") == 0) {
			parent.line(x2, y2, x2 + Utilities.Converter(10) * parent.cos(45), y2 + Utilities.Converter(10) * parent.sin(45));
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		float x1, x2, y1, y2;
		if (selected) {
			parent.fill(Colors.helpColor);
			parent.rectMode(PConstants.CORNER);
			parent.noStroke();
			parent.rect(myX + Utilities.Converter(2), myY + Utilities.Converter(2), myWidth *3/6, myHeight - Utilities.Converter(4));
			parent.rect(myWidth * 5/6 - Utilities.Converter(2), 0, myWidth /6 + Utilities.Converter(2), myHeight / 3);
			parent.rect(myWidth * 3/6 + Utilities.Converter(2), myHeight * 1/3 - Utilities.Converter(2), myWidth * 3/6 - Utilities.Converter(2), myHeight * 2/3 + Utilities.Converter(2));
			
			// weather panel
			x1 = Positions.weatherPanelX + Positions.weatherPanelWidth - Utilities.Converter(3);
			y1 = Positions.weatherPanelY + Positions.weatherPanelHeight + Utilities.Converter(3);
			x2 = Positions.weatherPanelX + Positions.weatherPanelWidth + Utilities.Converter(35);
			y2 = Positions.weatherPanelY + Positions.weatherPanelHeight + Utilities.Converter(3);
			drawArrow("ul", x1, y1, x2, y2);
			parent.textSize(Utilities.Converter(5));
			parent.stroke(Colors.LIGHT_ORANGE);
			parent.strokeWeight(Utilities.Converter(1));
			parent.textAlign(PConstants.LEFT, PConstants.BOTTOM);
			parent.fill(Colors.LIGHT_ORANGE);
			parent.text("Weather Panel", x1, y1);
			
			// sliders
			x1 = Positions.timeSliderX + Utilities.Converter(10);
			y1 = Positions.weatherPanelY + Positions.weatherPanelHeight + Utilities.Converter(3);
			x2 = Positions.weatherPanelX + Positions.weatherPanelWidth + Utilities.Converter(35);
			y2 = Positions.weatherPanelY + Positions.weatherPanelHeight + Utilities.Converter(3);
			drawArrow("bl", x1, y1, x2, y2);
			parent.textSize(Utilities.Converter(5));
			parent.stroke(Colors.LIGHT_ORANGE);
			parent.strokeWeight(Utilities.Converter(1));
			parent.textAlign(PConstants.LEFT, PConstants.BOTTOM);
			parent.fill(Colors.LIGHT_ORANGE);
			parent.text("Weather Panel", x1, y1);
			
			
			
		
		
		}
	}
	
	
	
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
