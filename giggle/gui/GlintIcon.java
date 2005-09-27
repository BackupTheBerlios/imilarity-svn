/*
 * Created on 27-sep-2005
 */
package gui;

import image.ImageData;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


/**
 * @author Klaas Bosteels
 */
public class GlintIcon extends BorderIcon {
	
	private ImageData imageData;
	
	public GlintIcon(ImageData image) {
		super(image);
		if (image == null)
			throw new NullPointerException("imageData == null");
		this.imageData = image;
	}
	
	
	/**
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {		
		int width = imageData.getWidth();
		int height = imageData.getHeight();
		
		Graphics2D g2d = (Graphics2D) g; 
		
		BufferedImage reflection = 
			new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D reflectionGraphics = reflection.createGraphics();

		AffineTransform tranform = AffineTransform.getScaleInstance(1.0, -0.5);
		tranform.translate(0, -height);
		reflectionGraphics.drawImage(getImageData().getImage(), tranform, null);

		GradientPaint painter = new GradientPaint(0.0f, 0.0f, 
				new Color(0.0f, 0.0f, 0.0f, 0.5f),
				0.0f, height / 3.0f,
				new Color(0.0f, 0.0f, 0.0f, 1.0f));

		reflectionGraphics.setComposite(AlphaComposite.DstOut);
		reflectionGraphics.setPaint(painter);
		reflectionGraphics.fill(new Rectangle2D.Double(0, 0, width, height));

		reflectionGraphics.dispose();
		
		g2d.drawImage(reflection, x, y+height, null);
		super.paintIcon(c,g,x,y);
	}

	/**
	 * @see javax.swing.Icon#getIconWidth()
	 */
	public int getIconWidth() {
		return imageData.getWidth();
	}

	/**
	 * @see javax.swing.Icon#getIconHeight()
	 */
	public int getIconHeight() {
		return imageData.getHeight() + (imageData.getHeight() / 3);
	} 
}
