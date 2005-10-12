/*
 * Created on 6-okt-2005
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import de.berlios.imilarity.aggregators.Aggregator;
import de.berlios.imilarity.measures.*;


public class SettingsDialog extends JDialog {

	private static final long serialVersionUID = 5534949425380832459L;

	private JRadioButton component, convert;
	private JComboBox measure, histMeasure, aggregator;
	private JCheckBox partitioned, homogenity, combined;
	
	public SettingsDialog(Frame owner) {
		super(owner, "Settings");
		
		JPanel content = new JPanel(new GridBagLayout());
		content.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		
		c.insets = new Insets(0,0,10,10);
		c.gridy = 0;
		content.add(new JLabel("Color measure:"), c);
		c.insets = new Insets(0,10,0,10);
		c.gridy++;
		component = new JRadioButton("component wise");
		content.add(component, c);
		c.gridy++;
		convert = new JRadioButton("convert to grayscale");
		convert.setSelected(true);
		content.add(convert, c);
		
		ButtonGroup group = new ButtonGroup();
		group.add(component);
		group.add(convert);
		
		c.insets = new Insets(15,0,10,10);
		c.gridy++;
		content.add(new JLabel("Grayscale measure:"), c);
		c.insets = new Insets(0,10,0,0);
		c.gridy++;
		measure = new JComboBox(new String[] { 
				"AD", "MD", 
				
				"Fuzzy using M1a", "Fuzzy using M2", "Fuzzy using M3", "Fuzzy using M4", 
				"Fuzzy using M20", "Fuzzy using M20c",
				
				"Fuzzy Histogram using M1a", "Fuzzy Histogram using M2", "Fuzzy Histogram using M3",
				"Fuzzy Histogram using M4", "Fuzzy Histogram using M20", "Fuzzy Histogram using M20c"
			});
		measure.setSelectedItem("Fuzzy using M20");
		content.add(measure, c);
		c.insets = new Insets(10,10,0,0);
		c.gridy++;
		partitioned = new JCheckBox("partitioned");
		partitioned.setSelected(true);
		content.add(partitioned, c);
		c.insets = new Insets(0,40,0,0);
		c.gridy++;
		homogenity = new JCheckBox("use homogenity");
		homogenity.setSelected(true);
		content.add(homogenity, c);
		partitioned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homogenity.setEnabled(partitioned.isSelected());
			}
		});
		c.insets = new Insets(0,10,0,0);
		c.gridy++;
		combined = new JCheckBox("combine with histogram measure:");
		combined.setSelected(true);
		content.add(combined, c);
		c.insets = new Insets(5,40,0,0);
		c.gridy++;
		histMeasure = new JComboBox(new String[] { 
				"Fuzzy Histogram using M1a", "Fuzzy Histogram using M2", "Fuzzy Histogram using M3",
				"Fuzzy Histogram using M4", "Fuzzy Histogram using M20", "Fuzzy Histogram using M20c"
			});
		content.add(histMeasure, c);
		measure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selection = (String) measure.getSelectedItem();
				combined.setEnabled(!selection.contains("Histogram"));	
				histMeasure.setEnabled(combined.isSelected());
			}
		});
		combined.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				histMeasure.setEnabled(combined.isSelected());
			}
		});
		
		c.insets = new Insets(15,0,10,10);
		c.gridy++;
		content.add(new JLabel("Aggregator:"), c);
		c.insets = new Insets(0,10,0,0);
		c.gridy++;
		aggregator = new JComboBox(new String[] { "ArithmeticMean" });
		content.add(aggregator, c);
		
		getContentPane().add(content);
		
		JPanel buttons = new JPanel(new FlowLayout());
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsDialog.this.setVisible(false);	
			}
		});
		buttons.add(close);
		getContentPane().add(buttons, BorderLayout.SOUTH);
		
		pack();
	}
	
	private static StagedGrayscaleMeasureFactory getFactoryFromString(String str) {
		StagedGrayscaleMeasureFactory factory = null;
		try {
			if (str.contains("Fuzzy")) {
				String name = str.substring(str.lastIndexOf(' ') + 1);
				final Class c1 = Class.forName("de.berlios.imilarity.measures." + name);
				if (str.contains("Histogram")) {
					factory = new StagedGrayscaleMeasureFactory() {
						public StagedGrayscaleMeasure createMeasure()  {
							try {
								return new FuzzyGrayscaleHistogramMeasure((StagedFuzzyMeasure) c1.newInstance());
							} catch (InstantiationException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
							return null;
						}
					};
				} else {
					factory = new StagedGrayscaleMeasureFactory() {
						public StagedGrayscaleMeasure createMeasure()  {
							try {
								return new FuzzyGrayscaleMeasure((StagedFuzzyMeasure) c1.newInstance());
							} catch (InstantiationException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
							return null;
						}
					};
				}
			} else {
				Class c1 = Class.forName("de.berlios.imilarity.measures." + str);
				factory = new ClassGrayscaleMeasureFactory(c1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return factory;
	}
	
	public ColorMeasure getMeasure() {
		final String selected = (String) measure.getSelectedItem();
		StagedGrayscaleMeasureFactory factory = getFactoryFromString(selected);
		if (homogenity.isEnabled() && homogenity.isSelected()) {
			final StagedGrayscaleMeasureFactory f = factory;
			factory = new StagedGrayscaleMeasureFactory() {
				public StagedGrayscaleMeasure createMeasure() {
					return new HomGrayscaleMeasure(f.createMeasure());
				}
			};
		}
		if (combined.isEnabled() && combined.isSelected()) {
			final StagedGrayscaleMeasureFactory f = factory;
			factory = new StagedGrayscaleMeasureFactory() {
				public StagedGrayscaleMeasure createMeasure() {
					return new ProductGrayscaleMeasure
					(getFactoryFromString(selected).createMeasure(), f.createMeasure());
				}
			};
		}
		StagedGrayscaleMeasure gm;
		if (partitioned.isSelected()) {
			gm = new PartGrayscaleMeasure(factory);
		} else
			gm = factory.createMeasure();
		if (component.isSelected())
			return new ComponentsColorMeasure(new ScalingGrayscaleMeasure(gm));
		else
			return new GrayscaledColorMeasure(new ScalingGrayscaleMeasure(gm));
	}
	
	public Aggregator getAggregator() {
		try {
			Class c = Class.forName("de.berlios.imilarity.aggregators." + aggregator.getSelectedItem());
			return (Aggregator) c.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// testprog
	public static void main(String[] args) {
		final JFrame frame = new JFrame("OptionsDialogTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("Open dialog ...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new SettingsDialog(frame);
				dialog.pack();
				dialog.setVisible(true);
			}
		});
		frame.getContentPane().add(button);
		frame.pack();
		frame.setVisible(true);
	}

}
