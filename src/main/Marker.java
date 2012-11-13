package main;

import processing.core.PApplet;

public class Marker {
	float x;
	float y;
	PApplet p;
	
	public Marker(PApplet p, float x, float y) {
		this.p = p;
		this.x = x;
		this.y = y;
	}
	
	public void draw() {
		p.pushStyle();
		p.fill(Colors.LIGHT_BLUE);
		p.ellipse(x, y, Utilities.Converter(2), Utilities.Converter(2));
		p.popStyle();
	}
}
