/*
 * Created on 25-sep-2005
 */
package gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.berlios.imilarity.image.ImageData;

import models.ExamplesModel;
import models.SearchModel;


/**
 * @author Klaas Bosteels
 */
public class ExamplesPanel extends JPanel implements Observer, ActionListener {
	
	private static final long serialVersionUID = 713755325610857205L;
	
	private ExamplesModel examplesModel;
	private SearchModel searchModel;
	
	private JPanel examplesPanel;
	private JButton firstButton, reorderButton, clearButton;
	
	public ExamplesPanel(ExamplesModel examplesModel, SearchModel searchModel) {
		super(new BorderLayout());
		
		if (examplesModel == null)
			throw new NullPointerException("examplesModel == null");
		this.examplesModel = examplesModel;
		examplesModel.addObserver(this);
		
		if (searchModel == null)
			throw new NullPointerException("searchModel == null");
		this.searchModel = searchModel;
		
		examplesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
		JScrollPane scrollPane = new JScrollPane(examplesPanel);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JPanel containerPanel = new JPanel(new GridLayout(1,1));
		containerPanel.setBorder(BorderFactory.createTitledBorder("Example images"));
		containerPanel.add(scrollPane);
		add(containerPanel);
		
		firstButton = new JButton("Use first as examples");
		firstButton.setActionCommand("useFirst");
		firstButton.addActionListener(this);
		firstButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		reorderButton = new JButton("Reorder results");
		reorderButton.setActionCommand("reorder");
		reorderButton.addActionListener(this);
		reorderButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		clearButton = new JButton("Clear examples");
		clearButton.setActionCommand("clear");
		clearButton.addActionListener(this);
		clearButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		clearButton.setEnabled(false);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,5,10));
		GridLayout gl = new GridLayout(3,1);
		gl.setHgap(5);
		gl.setVgap(5);
		buttonsPanel.setLayout(gl);
		buttonsPanel.add(firstButton);
		buttonsPanel.add(reorderButton);
		buttonsPanel.add(clearButton);
		add(buttonsPanel, BorderLayout.EAST);
	}

	/**
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		examplesPanel.removeAll();
		ImageData[] examples = examplesModel.getExamples();
		Insets insets = examplesPanel.getInsets();
		int height = examplesPanel.getHeight() - insets.bottom - insets.top - 5;
		for (int i = 0; i < examples.length; i++) {
			ImageData id = examples[i].getHScaledInstance(height);
			JLabel label = new JLabel(new BorderIcon(id));
			//label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			examplesPanel.add(label);
		}
		clearButton.setEnabled(!examplesModel.isEmpty());
		examplesPanel.revalidate();
		examplesPanel.repaint();
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("useFirst")) {
			new Thread(new Runnable() {
				public void run() {
					try {
						final ImageData[] examples = searchModel.getPage(1);
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								for (int i = 0; i < examples.length; i++)
									examplesModel.addExample(examples[i]);
							}
						});
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}).start();
		} else if (e.getActionCommand().equals("clear")) {
			examplesModel.clear();
		} // TODO: else...
	}
}
