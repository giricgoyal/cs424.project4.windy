/**
 * 
 */
package main;

import Util.Colors;
import Util.U;
import Util.Utilities;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @author joysword
 *
 */
public class ModeButton extends Button {

	/**
	 * @param parent
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public ModeButton(PApplet parent, float x, float y, float width,
			float height) {
		super(parent, x, y, width, height);
	}
	
	@Override
	public void draw() {
		parent.pushStyle();
		if (U.playMode == U.TRIAL) {
			parent.fill(Colors.DARK_BLUE);
		}
		else if (U.playMode == U.REALTIME){
			parent.fill(Colors.WHITE);
		}
		parent.noStroke();
		parent.rectMode(PConstants.CORNER);
		parent.rect(myX, myY, myWidth, myHeight);
		parent.textAlign(PConstants.CENTER,PConstants.CENTER);
		if (U.playMode == U.TRIAL) {
			parent.fill(Colors.WHITE);
		}
		else if (U.playMode == U.REALTIME){
			parent.fill(Colors.DARK_GRAY);
		}
		parent.textSize(Utilities.Converter(5));
		if (U.playMode == U.TRIAL) {
			parent.text("APP", (myWidth)/2+myX, (myHeight)/2+myY);
		}
		else if (U.playMode == U.REALTIME){
			parent.text("REAL", (myWidth)/2+myX, (myHeight)/2+myY);
		}
		parent.popStyle();
	}

}
