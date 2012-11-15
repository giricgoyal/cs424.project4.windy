package main;

import processing.core.PApplet;
import processing.core.PConstants;

public class WeatherPanel {
	
	PApplet p;
	float x,y,w,h;
	
	String weather;
	String speed;
	String dir;
	
	public WeatherPanel(PApplet _p, float _x, float _y, float _w, float _h) {
		p = _p;
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		this.dir = "";
	}
	
	public WeatherPanel(PApplet _p, float _x, float _y, float _w, float _h, String weather, String speed, String dir) {
		p = _p;
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		this.weather = weather;
		this.speed = speed;
		this.dir = dir;
	}
	
	public void update(String weather, String speed, String dir) {
		this.weather = weather;
		this.speed = speed;
		this.dir = dir;
	}
	public void draw() {
		
		p.pushStyle();
		
		p.rectMode(PConstants.CORNER);
		p.fill(Colors.WHITE);
		p.noStroke();
		p.rect(x,y,w,h);
		
		p.fill(Colors.DARK_GRAY);
		p.textAlign(PConstants.LEFT, PConstants.TOP);
		p.textSize(U.Converter(5));
		p.text("Weather: "+weather+"\nWind Speed: "+speed+" mph\nWind Direction: ", x+w/20, y+h/15);
		
		if (dir.equals("E")) {
			
		}
		else if (dir.equals("N")) {
			
		}
		else if (dir.equals("W")) {
			
		}
		else if (dir.equals("NW")) {
			
		}
		else if (dir.equals("SE")) {
			
		}
		else if (dir.equals("NNW")) {
			
		}
		else { // WNW
			
		}
		
		p.popStyle();
	}
}
