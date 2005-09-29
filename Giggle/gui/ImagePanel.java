/*
 * Created on 27-sep-2005
 */
package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.berlios.imilarity.image.ImageData;

import models.ImageModel;



/**
 * @author Klaas Bosteels
 */
public class ImagePanel extends JPanel implements Observer {

	private static final long serialVersionUID = 5152954516979902090L;

	public static final int FILL = 1;
	public static final int SCALE = 2;
	
	private ImageData imageData;
	private int padding = 10;
	private int mode = 0;

	private ImageModel model = null;
	
	
	public ImagePanel(int mode, int padding) {
		super();	
		this.mode = mode;
		this.padding = padding;
	}
	
	public ImagePanel() {
		this(ImagePanel.SCALE, 10);
	}
	
	public ImagePanel(ImageData id, int mode, int padding) {
		this(mode, padding);
		setPreferredSize(new Dimension(id.getWidth(), id.getHeight()));
		setImage(id);
	}
	
	public ImagePanel(ImageData id) {
		this(id, ImagePanel.SCALE, 10);
	}
	
	public ImagePanel(ImageModel model, int mode, int padding) {
		this(mode, padding);
		this.model = model;
		imageData = model.getImageData();
		model.addObserver(this);
	}
	
	public ImagePanel(ImageModel model) {
		this(model, ImagePanel.SCALE, 10);
	}
	
	
	public void setImage(ImageData id) {
		imageData = id;
		repaint();
	}
	
	public ImageData getImage() {
		return imageData;
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public void setPadding(int padding) {
		this.padding = padding;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imageData != null) {
			if (mode == ImagePanel.FILL) {
				g.drawImage(imageData.getImage(), padding, padding, 
						getWidth() - (padding * 2), 
						getHeight() - (padding * 2),
						null);
			} else {
				int w = imageData.getWidth(), h = imageData.getHeight(); 
				double factor = 1.0;
				if (w > getWidth() - 2 * padding) {
					factor = ((getWidth() - (padding * 2)) * 1.0) / w;
					w = getWidth() - (padding * 2);
					h *= factor;
				}
				if (h > getHeight() - 2 * padding) {
					factor = ((getHeight() - (padding * 2)) * 1.0) / h;
					h = getHeight() - (padding * 2);
					w *= factor;
				}
				g.drawImage(imageData.getImage(), (getWidth() - w) / 2, 
					(getHeight() - h) / 2, w, h, null);
			}
		}
	}

	public void setModel(ImageModel model) {
		this.model = model;
		model.addObserver(this);
	}
	
	public void update(Observable o, Object arg) {
		if (model != null) {
			setImage(model.getImageData());
		}
	}
}
