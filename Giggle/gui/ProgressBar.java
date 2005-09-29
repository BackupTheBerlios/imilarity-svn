/*
 * Created on 29-sep-2005
 */
package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JProgressBar;

import models.ProgressModel;

public class ProgressBar extends JProgressBar implements Observer {

	private static final long serialVersionUID = -2549420313381115621L;

	private ProgressModel progressModel;
	
	public ProgressBar(ProgressModel progressModel) {
		if (progressModel == null)
			throw new NullPointerException("progressModel == null");
		this.progressModel = progressModel;
		progressModel.addObserver(this);
	}
	
	public void update(Observable o, Object arg) {
		setMinimum(progressModel.getMin());
		setMaximum(progressModel.getMax());
		setValue(progressModel.getValue());
		setVisible(progressModel.getVisible());
	}

}
