/*
 * Created on 25-sep-2005
 */
package gui;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;


/**
 * @author Klaas Bosteels
 */
public class GradientPanel extends JPanel {
	
	private static final long serialVersionUID = 6164789397410241428L;
	
	private GradientPaint gradient;
	
	public GradientPanel(GradientPaint gradient) {
		if (gradient == null)
			throw new NullPointerException("gradient == null");
		this.gradient = gradient;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D)g;
	    g2d.setPaint(gradient);
	    g2d.fill(new Rectangle(getWidth(), getHeight()));
	}
}
