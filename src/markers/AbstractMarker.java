package markers;

import processing.core.PApplet;

public class AbstractMarker {
	float x;
	float y;
	PApplet p;
	
	public AbstractMarker(PApplet p, float x, float y) {
		this.p = p;
		this.x = x;
		this.y = y;
	}
	
	public void draw() {
		p.pushStyle();
		//p.fill(Colors.LIGHT_BLUE);
		//p.ellipse(x, y, Utilities.Converter(2), Utilities.Converter(2));
		p.popStyle();
	}
	
	public void updatePos(float x1, float x2, float x3, float x4, float y1, float y2, float y3, float y4) {
		x = PApplet.map(x,x1,x2,x3,x4);
		y = PApplet.map(y,y1,y2,y3,y4);
	}

	public void movePos(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	public PApplet getP() {
		return p;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
