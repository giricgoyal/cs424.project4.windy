/**
 * 
 */
package main;

import processing.core.PVector;

/**
 * @author joysword
 *
 */
public class Lock {
	
	private PVector cen;
	private float lockW, lockH;

	public Lock(float _x, float _y, float lockW, float lockH) {
		cen = new PVector(_x, _y);
		this.lockW= lockW;
		this.lockH = lockH;
	}
	
	public void draw() {
		//TODO: draw Lock
	}
}
