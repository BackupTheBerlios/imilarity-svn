/*
 * Created on 25-sep-2005
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.jgoodies.looks.FontSizeHints;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;

import de.berlios.imilarity.Imilarity;

import models.ExamplesModel;
import models.ImageModel;
import models.ProgressModel;
import models.SearchModel;


/**
 * @author Klaas Bosteels
 */
public class Giggle extends JFrame {

	private static final long serialVersionUID = -4109693977961889830L;

	private void configureUI() {
        UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, Boolean.TRUE);
        Options.setGlobalFontSizeHints(FontSizeHints.MIXED);
        Options.setDefaultIconSize(new Dimension(18, 18));                                                       
        
        //String lafName =
        //	LookUtils.IS_OS_WINDOWS_XP
        //  ? Options.getCrossPlatformLookAndFeelClassName()
        //  : Options.getSystemLookAndFeelClassName();                                                                
                
        try {
            UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
        	//UIManager.setLookAndFeel(new GTKLookAndFeel());
        } catch (Exception e) {
            System.err.println("Can't set look & feel:" + e);
        }
    }
	
	public Giggle() {
		super("Giggle");
		
		configureUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Imilarity imilarity = new Imilarity();
		ImageModel selectedImgModel = new ImageModel();
		ExamplesModel examplesModel = new ExamplesModel(imilarity);
		SearchModel searchModel = new SearchModel(imilarity);
		ProgressModel progressModel = new ProgressModel();
		
		Container content = getContentPane();
		content.add(new SearchStringPanel(searchModel, progressModel), BorderLayout.NORTH);
		content.add
			(new ResultsPanel(searchModel, examplesModel, progressModel, selectedImgModel));
		
		JPanel examplesPanel = 
			new ExamplesPanel(examplesModel, searchModel, progressModel, selectedImgModel);
		content.add(examplesPanel, BorderLayout.SOUTH);
		
		pack();
		setSize(new Dimension(800, 600));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Giggle();
	}
}
