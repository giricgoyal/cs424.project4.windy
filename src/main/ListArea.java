/**
 * 
 */
package main;

import java.util.ArrayList;

import Util.Colors;
import Util.Positions;
import Util.Utilities;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import types.DataLocation;

/**
 * @author giric
 *
 */
public class ListArea extends BasicControl {
	
	PApplet parent;
	int cid;
	ArrayList<DataLocation> array;
	
	PShape backButton;
	float backButtonHeight;
	float backButtonWidth;
	
	float v1x, v2x, v3x, v4x, v5x, v6x;
	float v1y, v2y, v3y, v4y, v5y, v6y;

	public ListArea(PApplet parent, float x, float y, float width,
			float height) {
		super(parent, x, y, width, height);
		// TODO Auto-generated constructor stub
		
		this.parent = parent;
		
		v1x = myX;
		v1y = myY;
		
		v2x = v1x;
		v2y = v1y + myHeight;
		
		v3x = v1x + myWidth;
		v3y = v2y;
		
		v6x = v3x;
		v6y = v1y;
		
		backButton = parent.loadShape("backButton.svg");
		backButtonHeight = backButton.height / Utilities.Converter(3);
		backButtonWidth = backButton.width / Utilities.Converter(3);
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		parent.noStroke();
		parent.fill(Colors.buttonSelectedColor);
		parent.beginShape();
			parent.vertex(v1x, v1y);
			parent.vertex(v2x, v2y);
			parent.vertex(v3x, v3y);
			parent.vertex(v4x, v4y);
			parent.vertex(v5x, v5y);
			parent.vertex(v6x, v6y);
		parent.endShape(PConstants.CLOSE);
		
		parent.shape(backButton, Positions.listWindowX + Positions.listWindowWidth - backButtonWidth - Utilities.Converter(2), Positions.listWindowY + Positions.listWindowHeight - Utilities.Converter(2) - backButtonHeight, backButtonWidth, backButtonHeight);
		
	
		int count = 0;
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.textSize(Utilities.Converter(5));
		parent.fill(Colors.white);
		if (this.cid == 0){
			
			parent.text("All Locations", Positions.listWindowX + Utilities.Converter(5), Positions.listWindowY + Utilities.Converter(5));
			parent.text("Area", Positions.listWindowX + Utilities.Converter(5), Positions.listWindowY + Utilities.Converter(15));
			parent.text("Interstates", Positions.listWindowX + Utilities.Converter(5), Positions.listWindowY + Utilities.Converter(25));
			
		}
		else {
			int matchCount = 0;
			while(count< this.array.size()){
				if (this.array.get(count).getCid() == this.cid){
					parent.text(this.array.get(count).getName(), Positions.listWindowX + Utilities.Converter(5), Positions.listWindowY + Utilities.Converter(4 * (2*matchCount + 1)));
					matchCount++;
				}
				count++;
			}
			
		}
	
	}

	public void setButtonSelected(float buttonX, float buttonY, float buttonHeight) {

		v5x = buttonX;
		v5y = buttonY;
		
		v4x = buttonX;
		v4y = buttonY + buttonHeight;
		
	}
	
	
	public void setLocationData(ArrayList<DataLocation> array, int cid){
		this.array = array;
		this.cid = cid;
	}
}
