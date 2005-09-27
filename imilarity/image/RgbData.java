/*
 * Created on 25-sep-2005
 */
package image;

/**
 * @author Klaas Bosteels
 */
public class RgbData {
	private final int r, g, b;
	
	public RgbData(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public int getR() { return r; }
	public int getG() { return g; }
	public int getB() { return b; }
}
