/*
 * Created on 25-sep-2005
 */
package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.ProgressModel;
import models.SearchModel;

/**
 * @author Klaas Bosteels
 */
public class SearchStringPanel extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1753607446872090211L;

	private SearchModel searchModel;
	private ProgressModel progressModel;
	
	private JTextField textField;
	private JButton findButton, stopButton;
	
	public SearchStringPanel(SearchModel searchModel, ProgressModel progressModel) {
		super(new FlowLayout(FlowLayout.LEFT));
		
		if (searchModel == null)
			throw new NullPointerException("searchModel == null");
		this.searchModel = searchModel;
		
		if (progressModel == null)
			throw new NullPointerException("progressModel == null");
		this.progressModel = progressModel;
		progressModel.addObserver(this);
		
		add(new JLabel("Search for"));
		textField = new JTextField();
		textField.setColumns(20);
		textField.setActionCommand("find");
		textField.addActionListener(this);
		add(textField);
		findButton = new JButton("Find now");
		findButton.setActionCommand("find");
		findButton.addActionListener(this);
		add(findButton);
		stopButton = new JButton("Stop");
		stopButton.setActionCommand("stop");
		stopButton.addActionListener(this);
		stopButton.setEnabled(false);
		add(stopButton);
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("find")) {
			stopButton.setEnabled(true);
			searchModel.setSearchString(textField.getText());
			new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i <= searchModel.getPageCount(); i++) {
						try {
							searchModel.getPage(i);
						} catch (IOException e) {} // ignore
					}
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							stopButton.setEnabled(false);
						}
					});
				}
				
			}).start();
		} else {
			searchModel.stopLoading();
		}
	}

	public void update(Observable o, Object arg) {
		boolean value = progressModel.getValue() == progressModel.getMax();
		textField.setEnabled(value);
		findButton.setEnabled(value);
	}
}
