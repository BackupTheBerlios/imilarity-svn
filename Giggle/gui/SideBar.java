/*
 * Created on 25-sep-2005
 */
package gui;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import de.berlios.imilarity.image.ImageData;



import models.ExamplesModel;
import models.ImageModel;
import models.SearchModel;

/**
 * @author Klaas Bosteels
 */
public class SideBar extends GradientPanel implements Observer, HyperlinkListener {
	
	private static final long serialVersionUID = 1075644306515986492L;
	
	private ImageModel selectionModel, fullSizeModel;
	private ExamplesModel examplesModel;
	
	private static final GradientPaint gradient =
	    new GradientPaint(200, 0, Color.WHITE, 0, 0, new Color(226, 228, 255),
	                      false); // true means to repeat pattern
	
	private JLabel iconLabel, nameLabel;
	private JEditorPane editorPane;
	
	
	public SideBar(ImageModel selectionModel, ImageModel fullSizeModel,
			ExamplesModel examplesModel, SearchModel searchModel) {
		super(gradient);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		if (selectionModel == null)
			throw new NullPointerException("selectionModel == null");
		this.selectionModel = selectionModel;
		selectionModel.addObserver(this);
		
		if (fullSizeModel == null)
			throw new NullPointerException("fullSizeModel == null");
		this.fullSizeModel = fullSizeModel;
		fullSizeModel.addObserver(this);
		
		if (examplesModel == null)
			throw new NullPointerException("examplesModel == null");
		this.examplesModel = examplesModel;
		examplesModel.addObserver(this);
		
		if (searchModel == null)
			throw new NullPointerException("searchModel == null");
		searchModel.addObserver(this);
		
		setPreferredSize(new Dimension(200,600));
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		add(Box.createVerticalStrut(15));
		
		iconLabel = new JLabel();
		iconLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		iconLabel.setVisible(false);
		add(iconLabel);
		
		add(Box.createVerticalStrut(10));
		
		nameLabel = new JLabel();
		nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		add(nameLabel);
		
		add(Box.createVerticalStrut(20));
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		editorPane.setOpaque(false);
		editorPane.setVisible(false);
	    add(editorPane);
	    editorPane.addHyperlinkListener(this);
	}

	
	public void setCursor(Cursor cursor) {
		super.setCursor(cursor);
		editorPane.setCursor(cursor);
	}
	

	/**
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		update();
	}
		
	private void update() {	
		ImageData id = selectionModel.getImageData();
		if (id != null) {
			if (id.getHeight() > 150)
				id = id.getHScaledInstance(150);
			if (id.getWidth() > getWidth() - 20)
				id = id.getWScaledInstance(getWidth() - 20);
			iconLabel.setIcon(new GlintIcon(id));
			iconLabel.setVisible(true);
			
			nameLabel.setText(id.getName());
			nameLabel.setVisible(true);
			
			String similarityStr = ("" + (id.getSimilarity()*100));
			
			StringBuffer sb = new StringBuffer();
			sb.append("<h3>Similarity</h3>");
			sb.append("<center>");
			sb.append("" + similarityStr.substring(0,Math.min(10,similarityStr.length())));
			sb.append(" %</center>");
			sb.append("<h3>Actions</h3>");
			if (fullSizeModel.getImageData() == null)
				sb.append("<a href=\"fullSize\">Show full size image</a><br>");
			else
				sb.append("<a href=\"fullSize\">Hide full size image</a><br>");
		    if (!examplesModel.containsExample(id))
		    	sb.append("<a href=\"addToExamples\">Add to examples</a><br>");
		    else
		    	sb.append("<a href=\"delFromExamples\">Remove from examples</a><br>");
		    editorPane.setText(sb.toString());
		    editorPane.setVisible(true);
		    
			repaint();
		}
	}


	/**
	 * @see javax.swing.event.HyperlinkListener#hyperlinkUpdate(javax.swing.event.HyperlinkEvent)
	 */
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			if (e.getDescription().equals("fullSize")) {
				if (fullSizeModel.getImageData() == null)
					fullSizeModel.setImageData(selectionModel.getImageData());
				else
					fullSizeModel.setImageData(null);
			} else {
				if (e.getDescription().equals("addToExamples"))
					examplesModel.addExample(selectionModel.getImageData());
				else
					examplesModel.removeExample(selectionModel.getImageData());
				update();
			}
		}
	}
}
