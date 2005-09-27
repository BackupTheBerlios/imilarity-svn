/*
 * Created on 27-sep-2005
 */
package gui;

import image.ImageData;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;


/**
 * @author Klaas Bosteels
 */
public class ShadowIcon extends BorderIcon {

	private ImageData imageData;
	
	private static final int extra = 6;
	
	public ShadowIcon(ImageData id) {
		super(id);
		if (id == null)
			throw new NullPointerException("id == null");
		this.imageData = id;
	}
	
	/**
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		int width = imageData.getWidth();
		int height = imageData.getHeight();
		
		Graphics2D g2 = (Graphics2D) g;
		
		BufferedImage shadow = new BufferedImage(width + extra, height + extra, 
				BufferedImage.TYPE_INT_ARGB); 
		Graphics sg = shadow.getGraphics();
		sg.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		sg.fillRect(extra-1, extra-1, width-1, height-1);
	    sg.dispose();
		
	    float ninth = 1.0f / 9.0f;
	    float[] blurKernel = {
	    	ninth, ninth, ninth,
			ninth, ninth, ninth,
			ninth, ninth, ninth
	    };
	    ConvolveOp blurOp = new ConvolveOp(new Kernel(3, 3, blurKernel));
	    
		g2.drawImage(shadow, blurOp, x, y);
		super.paintIcon(c,g,x,y);
	}

	/**
	 * @see javax.swing.Icon#getIconWidth()
	 */
	public int getIconWidth() {
		return imageData.getWidth() + extra;
	}

	/**
	 * @see javax.swing.Icon#getIconHeight()
	 */
	public int getIconHeight() {
		return imageData.getHeight() + extra;
	}
}
