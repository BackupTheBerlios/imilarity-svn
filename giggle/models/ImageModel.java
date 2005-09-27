/*
 * Created on 25-sep-2005
 */
package models;

import image.ImageData;

import java.util.Observable;


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
