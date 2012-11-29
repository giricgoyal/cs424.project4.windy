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
	
	private void drawText(String text, float x1, float y1) {
		parent.textSize(Utilities.Converter(5));
		parent.stroke(Colors.LIGHT_ORANGE);
		parent.strokeWeight(Utilities.Converter(1));
		parent.textAlign(PConstants.LEFT, PConstants.BOTTOM);
		parent.fill(Colors.LIGHT_ORANGE);
		parent.text(text, x1, y1);
		
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
			x2 = x1 + Utilities.Converter(40);
			y2 = y1;
			drawArrow("ul", x1, y1, x2, y2);
			drawText("Weather Panel", x1, y1);

			// time slider
			x1 = Positions.timeSliderX + Positions.timeSliderWidth/2;
			y1 = Positions.timeSliderY - Utilities.Converter(2);
			x2 = x1 + Utilities.Converter(38);
			y2 = y1;
			drawArrow("bl", x1, y1, x2, y2);
			drawText("Time Slider", x1, y1);

			// day selector
			x1 = Positions.dayButtonX + Positions.dayButtonW * 10 - Utilities.Converter(10);
			y1 = Positions.dayButtonY - Utilities.Converter(2);
			x2 = x1 + Utilities.Converter(55);
			y2 = y1;
			drawArrow("bl", x1, y1, x2, y2);
			drawText("Day Selector Buttons", x1, y1);
			
			// play button
			x1 = Positions.playX + Positions.playW - Utilities.Converter(50);
			y1 = Positions.playY - Utilities.Converter(7);
			x2 = x1 + Utilities.Converter(35);
			y2 = y1;
			drawArrow("br", x1, y1, x2, y2);
			drawText("Play Button", x1, y1);
			
			// stop button
			x1 = Positions.stopX + Positions.stopW - Utilities.Converter(50);
			y1 = Positions.stopY - Utilities.Converter(7);
			x2 = x1 + Utilities.Converter(35);
			y2 = y1;
			drawArrow("br", x1, y1, x2, y2);
			drawText("Stop Button", x1, y1);
			
			// keep tweets ON/OFF
			x1 = Positions.trialX +  Positions.trialW - Utilities.Converter(110);
			y1 = Positions.trialY - Utilities.Converter(7);
			x2 = x1 + Utilities.Converter(95);
			y2 = y1;
			drawArrow("br", x1, y1, x2, y2);
			drawText("Keep Tweets On Map Toggle Button", x1, y1);
			
			// zoomin/zoomout
			x1 = Positions.zoomInButtonX +  Positions.zoomInButtonW - Utilities.Converter(85);
			y1 = Positions.zoomInButtonY - Utilities.Converter(7);
			x2 = x1 + Utilities.Converter(70);
			y2 = y1;
			drawArrow("br", x1, y1, x2, y2);
			drawText("ZoomIN/ZoomOUT Buttons", x1, y1);
			
			// map pan
			x1 = Positions.mapX + Positions.mapWidth/2 + Utilities.Converter(5);
			y1 = Positions.mapY + Positions.mapHeight/2 + Utilities.Converter(7);
			x2 = x1 + Utilities.Converter(70);
			y2 = y1;
			//drawArrow("br", x1, y1, x2, y2);
			drawText("Drag the Map to Pan Left/Right", x1, y1);
			
			// textBox
			x1 = Positions.textBoxX + Positions.textBoxWidth/2 - Utilities.Converter(10);
			y1 = Positions.textBoxY + Positions.textBoxHeight + Utilities.Converter(4.5);
			x2 = x1 + Utilities.Converter(30);
			y2 = y1;
			drawArrow("ul", x1, y1, x2, y2);
			drawText("Text Box", x1, y1);

		
		
		}
	}
	
	
	
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
