/**
 * 
 */
package main;

import Util.Colors;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * @author joysword
 *
 */
public class Lock {
	
	private PApplet p;
	private PVector cen;
	private float lockW, lockH;

	public Lock(PApplet p, float _x, float _y, float lockW, float lockH) {
		this.p = p;
		cen = new PVector(_x, _y);
		this.lockW= lockW;
		this.lockH = lockH;
	}
	
	public void draw() {
		p.pushStyle();
	    p.fill(Colors.GRAPH_COLOR_2);
	    p.noStroke();
	    p.ellipse(cen.x, cen.y, lockW, lockH);
	    //rect(x-w*scale,y-h*scale,x+w*scale,y+h*scale);
	    p.popStyle();
	}
	
	protected float getX() {
		return cen.x;
	}
	
	protected float getY() {
		return cen.y;
	}
	
	protected void update(float _x) {
		cen.x = _x;
	}
}
