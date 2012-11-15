package main;

import processing.core.PApplet;
import processing.core.PConstants;
import types.DataWeather;

/**
 * contains all information, 
 * doesn't need to update, 
 * will show different information according to different days, 
 * which is updated in main class
 * 
 * @author joysword
 * 
 * @since Nov 14
 */

public class WeatherPanel {
	
	PApplet p;
	float x,y,w,h;
	DataWeather[] info;
	
	public WeatherPanel(PApplet _p, float _x, float _y, float _w, float _h, DataWeather[] info) {
		p = _p;
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		this.info = info;
	}
	
	public void draw(int day) {
		
		p.pushStyle();
		
		p.rectMode(PConstants.CORNER);
		p.fill(Colors.WHITE);
		p.noStroke();
		p.rect(x,y,w,h);
		
		p.fill(Colors.DARK_GRAY);
		p.textAlign(PConstants.LEFT, PConstants.TOP);
		p.textSize(U.Converter(5));
		p.text("Weather: "+ info[day].getWeather()+"\nWind Speed: "+ info[day].getSpeed()+" mph\nWind Direction: ", x+w/20, y+h/15);
		
		if (info[day].getDirection().equals("E")) {
			
		}
		else if (info[day].getDirection().equals("N")) {
			
		}
		else if (info[day].getDirection().equals("W")) {
			
		}
		else if (info[day].getDirection().equals("NW")) {
			
		}
		else if (info[day].getDirection().equals("SE")) {
			
		}
		else if (info[day].getDirection().equals("NNW")) {
			
		}
		else { // WNW
			
		}
		
		p.popStyle();
	}
}
