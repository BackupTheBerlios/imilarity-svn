/*
 * Created on 25-sep-2005
 */
package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.SearchModel;

/**
 * @author Klaas Bosteels
 */
public class KeywordsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1753607446872090211L;

	private SearchModel searchModel;
	
	private JTextField textField;
	private JButton findButton;
	
	public KeywordsPanel(SearchModel searchModel) {
		super(new FlowLayout(FlowLayout.LEFT));
		
		if (searchModel == null)
			throw new NullPointerException("searchModel == null");
		this.searchModel = searchModel;
		
		add(new JLabel("Search for"));
		textField = new JTextField();
		textField.setColumns(20);
		textField.addActionListener(this);
		add(textField);
		findButton = new JButton("Find now");
		findButton.addActionListener(this);
		add(findButton);
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		searchModel.setKeywords(textField.getText());
		textField.setEnabled(false);
		findButton.setEnabled(false);
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < searchModel.getPageCount(); i++) {
					try {
						searchModel.getPage(i);
					} catch (IOException e) {} // ignore
				}
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						textField.setEnabled(true);
						findButton.setEnabled(true);
					}
				});
			}
			
		}).start();
	}
}
