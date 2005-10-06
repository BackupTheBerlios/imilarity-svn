/*
 * Created on 27-sep-2005
 */
package gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import de.berlios.imilarity.image.ImageData;



/**
 * @author Klaas Bosteels
 */
public class BorderIcon implements Icon {

	private ImageData imageData;
	private Color color;
	
	public BorderIcon(ImageData id, Color color) {
		if (id == null)
			throw new NullPointerException("id == null");
		imageData = id;
		this.color = color;
	}
	
	public BorderIcon(ImageData id) {
		this(id, Color.BLACK);
	}
	
	
	public ImageData getImageData() {
		return imageData;
	}
	
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, imageData.getWidth() - 1, imageData.getHeight() - 1);
		g.drawImage(imageData.getImage(), x, y, null);
		g.setColor(color);
		g.drawRect(x, y, imageData.getWidth() - 1, imageData.getHeight() - 1);
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
		return imageData.getHeight();
	}
}
