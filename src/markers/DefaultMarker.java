/**
 * 
 */
package markers;

import processing.core.PApplet;
import main.Colors;
import main.Pos;
import main.Utilities;
import markers.AbstractMarker;

/**
 * @author joysword
 *
 */
public class DefaultMarker extends AbstractMarker {

	public DefaultMarker(PApplet p, float x, float y) {
		super(p, x, y);
	}
	
	@Override
	public void draw() {
		p.pushStyle();
		p.fill(Colors.LIGHT_BLUE);
		p.ellipse(x+Pos.mapX, y+Pos.mapY, Utilities.Converter(2), Utilities.Converter(2));
		p.popStyle();
	}
}