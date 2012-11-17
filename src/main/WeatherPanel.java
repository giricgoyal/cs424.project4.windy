package main;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;
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
	
	PApplet parent;
	float x,y,w,h;
	DataWeather[] info;
	
	PShape weather;
	
	public WeatherPanel(PApplet _p, float _x, float _y, float _w, float _h, DataWeather[] info) {
		parent = _p;
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		this.info = info;
		//Utilities.rain = parent.loadShape("rain.svg");
		//Utilities.cloudy = parent.loadShape("cloudy.svg");
		//Utilities.showers = parent.loadShape("rain.svg");
		//Utilities.clear = parent.loadShape("clear.svg");
		
	}
	
	public void draw(int day) {
		
		parent.pushStyle();
		
		parent.rectMode(PConstants.CORNER);
		parent.fill(Colors.WHITE);
		parent.noStroke();
		parent.rect(x,y,w,h);
		
		parent.fill(Colors.DARK_GRAY);
		parent.textAlign(PConstants.LEFT, PConstants.TOP);
		parent.textSize(U.Converter(5));
		
		
		if (info[day].getWeather().compareToIgnoreCase("clear") == 0){
			this.weather = Utilities.clear;	
		}
		else if (info[day].getWeather().compareToIgnoreCase("rain")==0){
			this.weather = Utilities.rain;
		}
		else if (info[day].getWeather().compareToIgnoreCase("showers")==0){
			this.weather = Utilities.showers;
		}
		else if (info[day].getWeather().compareToIgnoreCase("cloudy") == 0) {
			this.weather = Utilities.cloudy;
		}
		
		if (weather!=null){
			parent.shapeMode(PConstants.CORNER);
			parent.shape(weather, x, y, w/2, h);
			
		}
		parent.text("Weather: "+ info[day].getWeather()+"\nWind Speed: "+ info[day].getSpeed()+" mph\nWind Direction: ", x+w/20, y+h/15);
		
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
		
		parent.popStyle();
	}
}
