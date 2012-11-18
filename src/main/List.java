/**
 * 
 */
package main;

import Util.Colors;
import Util.Utilities;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @author giric
 *
 */
public class List extends BasicControl {
	
	PApplet parent;
	float v1x, v2x, v3x, v4x, v5x, v6x;
	float v1y, v2y, v3y, v4y, v5y, v6y;

	public List(PApplet parent, float x, float y, float width,
			float height, float buttonX, float buttonY, float buttonHeight) {
		super(parent, x, y, width, height);
		// TODO Auto-generated constructor stub
		
		this.parent = parent;
		
		v1x = myX;
		v1y = myY;
		
		v2x = v1x;
		v2y = v1y + myHeight;
		
		v3x = v1x + myWidth;
		v3y = v2y;
		
		v4x = v3x;
		v4y = v1y;
		
		v5x = buttonX;
		v5y = buttonY;
		
		v6x = buttonX;
		v6y = buttonY + buttonHeight;
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
		parent.fill(Colors.buttonSelectedColor);
		parent.beginShape();
			parent.vertex(v1x, v1y);
			parent.vertex(v2x, v2y);
			parent.vertex(v3x, v3y);
			parent.vertex(v4x, v4y);
			parent.vertex(v5x, v5y);
			parent.vertex(v6x, v6y);
		parent.endShape(PConstants.CLOSE);
	}

}
