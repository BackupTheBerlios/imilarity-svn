/*
 * Created on 27-sep-2005
 */
package models;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;

import de.berlios.imilarity.image.ImageData;

/**
 * @author Klaas Bosteels
 */
public class ExamplesModel extends Observable {
	
	private Collection examples = new HashSet();
	
	
	public void addExample(ImageData id) {
		examples.add(id);
		setChanged();
		notifyObservers();
	}
	
	public void removeExample(ImageData id) {
		examples.remove(id);
		setChanged();
		notifyObservers();
	}
	
	public boolean containsExample(ImageData id) {
		return examples.contains(id);
	}
	
	public void clear() {
		examples.clear();
		setChanged();
		notifyObservers();
	}
	
	public boolean isEmpty() {
		return examples.isEmpty();
	}
	
	public ImageData[] getExamples() {
		ImageData[] result = new ImageData[examples.size()];
		Iterator it = examples.iterator();
		for (int i = 0; it.hasNext(); i++)
			result[i] = (ImageData) it.next();
		return result;
	}
}
