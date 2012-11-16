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

public class TimeSlider {

	private PApplet p;
	private float x, y;
	private float w, h;
	
	Lock lLock, rLock;
	

	public TimeSlider(PApplet p, float _x, float _y, float _w, float _h, float lockW, float lockH) {
		this.p = p;
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		
		lLock = new Lock(x, y+h/2, lockW, lockH);
		rLock = new Lock(x+w, y+h/2, lockW, lockH);
	}

	public void draw() {
		p.pushStyle();

		
		p.strokeWeight(U.Converter(1));
		p.stroke(Colors.WHITE);
		p.fill(Colors.LIGHT_GRAY);

		p.rectMode(PConstants.CORNER);
		p.rect(x, y, w, h);

		for (int col = 0; col < 21; col++) {
			if (col % 2 == 0) {
				//p.line(xx, y - h, xx, y + h);
				// text(years[column], x, (plotY2 + textAscent() + 7)*scale);
			}
		}

		p.popStyle();
		lLock.draw();
		rLock.draw();
	}

	public void resume() {
	}
}
