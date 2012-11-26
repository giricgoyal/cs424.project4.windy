/**
 * 
 */
package markers;

import Util.Colors;
import Util.Pos;
import Util.U;
import Util.Utilities;
import processing.core.PApplet;
import markers.AbstractMarker;

/**
 * @author joysword
 *
 */
public class DefaultMarker extends AbstractMarker {

	public DefaultMarker(PApplet p, float x, float y, int pid, int hour, int min, String text, String keywords, int loc) {
		super(p, x, y, pid, hour, min, text, keywords, loc);
	}
	
	@Override
	public void draw() {
		p.pushStyle();
		p.noStroke();
		//float scale = PApplet.map(super.halfhour,U.bHalf,U.eHalf,2,1);
		//p.fill(p.color(scale*p.red(Colors.LIGHT_BLUE),scale*p.green(Colors.LIGHT_BLUE),scale*p.blue(Colors.LIGHT_BLUE)));
		p.fill(Colors.LIGHT_BLUE);
		p.ellipse(x+Pos.mapX, y+Pos.mapY, Utilities.Converter(2), Utilities.Converter(2));
		p.popStyle();
	}
}