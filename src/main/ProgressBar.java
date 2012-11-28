/**
 * 
 */
package main;

import markers.MarkerType;
import Util.Colors;
import Util.Pos;
import Util.U;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @author joysword
 *
 */
public class ProgressBar extends Lock {
	
	CS424_Project4_Group4 program;

	public ProgressBar(PApplet p, float _x, float _y, float lockW, float lockH, CS424_Project4_Group4 program) {
		super(p, _x, _y, lockW, lockH);
		
		this.program=program;
	}
	
	@Override
	public void draw() {
		p.pushStyle();
	    p.fill(Colors.PEACH);
	    p.noStroke();
	    p.rectMode(PConstants.CENTER);
	    p.rect(cen.x, cen.y, lockW, lockH);
	    p.popStyle();
	}
	
	public void resume() {
		cen.x = PApplet.map(U.bHalf, 0,48, Pos.timeSliderX,Pos.timeSliderX+Pos.timeSliderWidth);
		U.playHalf = U.bHalf;
	}
	
	public void run() {
		cen.x += U.Converter(0.8);
		if (cen.x >= PApplet.map(U.eHalf, 0,48, Pos.timeSliderX,Pos.timeSliderX+Pos.timeSliderWidth)) {
			if (U.currentDay == 20) {
				U.Playing = U.STOP;
				program.setCurrentData(program.dataPos,program.dataDay,U.bHalf,U.eHalf,U.currentWord);
				program.setMarkerPos(program.dataPos,program.markers,MarkerType.DEFAULT_MARKER);
			}
			else {
				program.dayButtons.get(U.currentDay).setSelected(false);
				U.currentDay++;
				program.dayButtons.get(U.currentDay).setSelected(true);
				program.dataDay = program.qManager.getDataPos_By_Date(U.currentDay);
				program.setCurrentData(program.dataPos,program.dataDay,U.playHalf,U.playHalf+1,U.currentWord);
				program.setMarkerPos(program.dataPos,program.markers,MarkerType.DEFAULT_MARKER);
				resume();
			}
		}
		else {
			if (cen.x >= PApplet.map(U.playHalf+1, 0, 48, Pos.timeSliderX, Pos.timeSliderX+Pos.timeSliderWidth)) {
				U.playHalf++;
				program.setCurrentData(program.dataPos,program.dataDay,U.playHalf,U.playHalf+1,U.currentWord);
				program.setMarkerPos(program.dataPos,program.markers,MarkerType.DEFAULT_MARKER);
			}
		}
	}
}
