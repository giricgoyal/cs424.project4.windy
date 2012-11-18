package main;

import Util.Colors;
import Util.Positions;
import Util.Utilities;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

public class TweetWindow extends BasicControl{

	PApplet parent;
	
	PShape tweet;
	String tweetText = "";
	
	float v1x, v1y, v2x, v2y, v3x, v3y, v4x, v4y, v5x, v5y, v6x, v6y, v7x, v7y, v8x, v8y;
	

	public TweetWindow(PApplet parent, float x, float y, float width,
			float height) {
		super(parent, x, y, width, height);
		// TODO Auto-generated constructor stub
		
		this.parent = parent;
		
		tweet = parent.loadShape("tweet.svg");
		Positions.tweetWidth = tweet.width/Utilities.Converter(8);
		Positions.tweetHeight = tweet.height/Utilities.Converter(8);
		
		v2x = myX + Utilities.Converter(20);
		v2y = myY + Positions.tweetHeight + Utilities.Converter(3);
		
		v3x = v2x - Utilities.Converter(18);
		v3y = v2y;
		
		v4x = v3x;
		v4y = myY + myHeight - Utilities.Converter(2);
		
		v5x = myX + myWidth - Utilities.Converter(2);
		v5y = v4y;
		
		v6x = v5x;
		v6y = v2y;
		
		v7x = v2x + Utilities.Converter(5);
		v7y = v2y;
		
		v1x = myX + Positions.tweetWidth + Utilities.Converter(2);
		v1y = v2y - Utilities.Converter(8);
	
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		parent.shapeMode(PConstants.CORNER);
		parent.shape(tweet, myX, myY, Positions.tweetWidth, Positions.tweetHeight);
		parent.fill(Colors.tweetColor);
		parent.strokeWeight(Utilities.Converter(0.5));
		parent.stroke(Colors.DARK_GRAY);
		parent.beginShape();
			parent.vertex(v1x, v1y);
			parent.vertex(v2x, v2y);
			parent.vertex(v3x, v3y);
			parent.vertex(v4x, v4y);
			parent.vertex(v5x, v5y);
			parent.vertex(v6x, v6y);
			parent.vertex(v7x, v7y);
		parent.endShape(PConstants.CLOSE);
		drawText();
	}
	
	public void setText(String tweetText) {
		/*this.tweetText = tweetText;
		float length = this.tweetText.length();
		float maxLenghtPerLine = (v5x - v4x - 10)/Utilities.Converter(5);
		int spaceIndex = (int)length;
		int index = 0;
		System.out.println(length + "  " + maxLenghtPerLine);
		while(length > maxLenghtPerLine){
			while(spaceIndex > index + maxLenghtPerLine ) {
				spaceIndex = (int)(this.tweetText.lastIndexOf(" ", spaceIndex-1));
				System.out.println(spaceIndex);
			}
			index = spaceIndex;
			this.tweetText = this.tweetText.replace(this.tweetText.charAt(spaceIndex), '\n');
			System.out.println(this.tweetText);
			length = length - spaceIndex + 1;
		}
		*/
	}
	
	
	public void drawText() {
		parent.noStroke();
		parent.fill(Colors.BLACK);
		parent.textSize(Utilities.Converter(5));
		parent.textAlign(PConstants.LEFT, PConstants.CENTER);
		parent.text(this.tweetText, v3x + Utilities.Converter(3), v3y + Utilities.Converter(4));
	}

}
