/**
 * 
 */
package main;

import java.util.ArrayList;

import markers.AbstractMarker;
import markers.MarkerType;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import types.DataPos;
import db.QueryManager;

/**
 * @author joysword
 *
 */
public class Map {
	
	PApplet p;
	PImage pic;
	float x0, y0, w, h;
	float x1, x2, y1, y2;
	PVector cen;
	
	public Map(PApplet p, String file, float x, float y, float w, float h) {
			this.p = p;
			pic = p.loadImage(file);
			//mapWidth = Utilities.Converter(5216/16);
			//mapHeight = Utilities.Converter(2653/16);
			x0 = x;
			y0 = y;
			this.w = w;
			this.h = h;
			cen = new PVector((x1+x2)/2,(y1+y2)/2);
	}
	
	public void setup(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public void draw() {
		p.image(pic, x0, y0, x0+w, y0+h, PApplet.round(x1), PApplet.round(x2), PApplet.round(y1), PApplet.round(y2));
	}
}
