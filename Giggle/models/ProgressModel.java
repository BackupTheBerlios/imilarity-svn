/*
 * Created on 29-sep-2005
 */
package models;

import java.util.Observable;

public class ProgressModel extends Observable {

	private int value = 0;
	private boolean visible = false;
	private int max = 10, min = 0;
	

	public int getValue() {
		return value ;
	}
	
	public void setValue(int value) {
		this.value = value;
		setChanged();
		notifyObservers();
	}
	
	public void incrementValue() {
		setValue(value+1);
	}

	public boolean getVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
		setChanged();
		notifyObservers();
	}
	
	public int getMin() {
		return min;
	}
	
	public void setMin(int min) {
		this.min = min;
		setChanged();
		notifyObservers();
	}
	
	public int getMax() {
		return max ;
	}
	
	public void setMax(int max) {
		this.max = max;
		setChanged();
		notifyObservers();
	}
}
