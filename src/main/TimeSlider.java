/**
 * 
 */
package main;

import processing.core.PApplet;
import processing.core.PConstants;
import types.DataCountPair;

/**
 * @author joysword
 * 
 */

public class TimeSlider {

	private PApplet p;
	private float x, y;
	private float w, h;
	private DataCountPair[] data; // size = 21
	private int[] maxCnt; // size = 21
	
	Lock lLock, rLock;
	

	public TimeSlider(PApplet p, float _x, float _y, float _w, float _h, float lockW, float lockH, DataCountPair[] dataCountPair) {
		this.p = p;
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		
		lLock = new Lock(x, y+h/2, lockW, lockH);
		rLock = new Lock(x+w, y+h/2, lockW, lockH);
		
		data = dataCountPair;
		maxCnt = new int[21];
		
		for (int i=0;i<21;i++) {
			maxCnt[i] = 0;
			
			for (int j=0;j<48;j++) {
				if (maxCnt[i]<data[i].getCnt()[j]) {
					maxCnt[i]=data[i].getCnt()[j];
				}
			}
		}
	}

	public void draw() {
		p.pushStyle();

		
		p.strokeWeight(U.Converter(0.5));
		p.stroke(Colors.WHITE);
		p.fill(Colors.LIGHT_GRAY);

		p.rectMode(PConstants.CORNER);
		p.rect(x, y, w, h);

		p.fill(Colors.GRAPH_COLOR_1);
		p.noStroke();
		p.beginShape();
		for (int col = 0; col < 48; col++) {
			float plotX = PApplet.map((float)col, (float)0, (float)47, this.x, this.x+this.w);
			float plotY = PApplet.map((float)data[U.currentDay].getCnt()[col], (float)0, (float)(maxCnt[U.currentDay]*1.1), this.y+this.h, this.y);
			
			p.vertex(plotX, plotY);
		}
		p.vertex(this.x+this.w, this.y+this.h);
		p.vertex(this.x, this.y+this.h);
		p.endShape();
		
		p.popStyle();
		
		lLock.draw();
		rLock.draw();
	}

	public void resume() {
	}
}
