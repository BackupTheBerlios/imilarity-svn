/*
 * Created on 25-sep-2005
 */
package models;


import java.util.Observable;

import de.berlios.imilarity.image.ImageData;




/**
 * @author Klaas Bosteels
 */
public class ImageModel extends Observable {
	private ImageData id;
	
	public void setImageData(ImageData id) {
		this.id = id;
		setChanged();
		notifyObservers();
	}
	
	public ImageData getImageData() {
		return id;
	}
}
