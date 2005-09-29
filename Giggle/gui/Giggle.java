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
		setPreferredSize(new Dimension(800, 600));
		
		Imilarity imilarity = new Imilarity();
		ExamplesModel examplesModel = new ExamplesModel(imilarity);
		SearchModel searchModel = new SearchModel(imilarity);
		
		Container content = getContentPane();
		content.add(new KeywordsPanel(searchModel), BorderLayout.NORTH);
		content.add(new ResultsPanel(searchModel, examplesModel));
		
		JPanel examplesPanel = new ExamplesPanel(examplesModel, searchModel);
		//examplesPanel.setPreferredSize(new Dimension(800, 100));
		content.add(examplesPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Giggle();
	}
}
