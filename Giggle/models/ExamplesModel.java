/*
 * Created on 27-sep-2005
 */
package models;


import java.util.Observable;

import be.ugent.imilarity.Imilarity;
import be.ugent.imilarity.image.ImageData;


/**
 * @author Klaas Bosteels
 */
public class ExamplesModel extends Observable {
	
	private Imilarity imilarity; 
	
	public ExamplesModel(Imilarity imilarity) {
		if (imilarity == null)
			throw new NullPointerException("imilarity == null");
		this.imilarity = imilarity;
	}
	
	public void addExample(ImageData id) {
		imilarity.addExample(id);
		setChanged();
		notifyObservers();
	}
	
	public void removeExample(ImageData id) {
		imilarity.removeExample(id);
		setChanged();
		notifyObservers();
	}
	
	public boolean containsExample(ImageData id) {
		return imilarity.containsExample(id);
	}
	
	public void clear() {
		imilarity.clearExamples();
		setChanged();
		notifyObservers();
	}
	
	public boolean isEmpty() {
		return imilarity.containsExamples();
	}
	
	public ImageData[] getExamples() {
		return imilarity.getExamples();
	}
}
