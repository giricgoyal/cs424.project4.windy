/**
 * 
 */
package main;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @author joysword
 *
 */
public class DayButton extends Button {
	
	PApplet p;
	int count;
	
	public DayButton(PApplet p, float x, float y, float width, float height, int cnt) {
		super(p, x, y, width, height);
		this.p = p;
		count = cnt;
	}
	
	@Override
	public void draw() {
		p.pushStyle();
		p.noStroke();
		p.fill(Colors.GRAPH_COLOR_3);
		p.rectMode(PConstants.CORNERS);
		float Y = PApplet.map(count, U.dayButtonLowerBound, U.maxTweets * (float)1.05, myY+myHeight, myY);
		p.rect(myX, Y, myX+myWidth, myY+myHeight);
		p.strokeWeight(U.Converter(0.5));
		p.stroke(Colors.WHITE);
		p.noFill();
		p.rectMode(PConstants.CORNER);
		p.rect(myX,myY,myWidth,myHeight);
		p.popStyle();
	}
}
