package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import com.jgoodies.looks.FontSizeHints;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;


import models.EvaluationsModel;
import models.ImageCollection;
import models.TableSorter;

public class Imperforate extends JFrame {
	
	private static final long serialVersionUID = 4051132972866159438L;

	private boolean calculateRemaining = false, calculating = false;

	private JTextArea console = new JTextArea(15,50);
	private JButton calculateButton;
	
	private EvaluationsModel evalsModel;
	private List imageCollections;

	
	private void configureUI() {
        UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, Boolean.TRUE);
        Options.setGlobalFontSizeHints(FontSizeHints.MIXED);
        Options.setDefaultIconSize(new Dimension(18, 18));                                                                                                                     
                
        try {
            UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
        	//UIManager.setLookAndFeel(new GTKLookAndFeel());
        } catch (Exception e) {
            System.err.println("Can't set look & feel:" + e);
        }
    }
	
	
	public Imperforate() {
		super("Imperforate");
		
		configureUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		evalsModel = new EvaluationsModel();
		imageCollections = new ArrayList();
		//imageCollections.add(new CoilImageCollection());
		//evalsModel.setImageCollection((ImageCollection)imageCollections.get(0)); 
		
		final TableSorter sorter = new TableSorter(evalsModel); 
		final JTable table = new JTable(sorter);    
		sorter.setTableHeader(table.getTableHeader());
		table.getColumnModel().getColumn(0).setPreferredWidth(450);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		getContentPane().add(new JScrollPane(table));
		
		JPanel buttonsPanel = new JPanel();
		//buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		buttonsPanel.add(new JLabel("Images:"));
		buttonsPanel.add(Box.createHorizontalStrut(5));
		final JComboBox collectionCombo = new JComboBox(); 
			//new JComboBox(new String[] { 
			//	((ImageCollection)imageCollections.get(0)).getDescription() 
			//});
		collectionCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evalsModel.setImageCollection
					((ImageCollection)imageCollections.get(collectionCombo.getSelectedIndex()));
			}
		});
		buttonsPanel.add(collectionCombo);
		buttonsPanel.add(Box.createHorizontalStrut(5)); 
		JButton addCollectionButton = new JButton("Add ...");
		addCollectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new FileFilter() {
					public boolean accept(File f) {
						if (f.isDirectory())
				            return true;
						else {
							String name = f.getName();
							String extension = 
								name.substring(name.lastIndexOf('.'), name.length());
							return extension.equals(".class");
						}
					}
					public String getDescription() {
						return "*.class";
					}
				});
				int retVal = fc.showOpenDialog(Imperforate.this);
				if (retVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
				    String name = file.getName();
				    Class cls = loadClass(file.getParentFile(), 
				    		name.substring(0, name.indexOf('.')));
				    if (cls != null) {
						try {
							ImageCollection collection = 
								(ImageCollection) cls.newInstance();
							imageCollections.add(collection);
					    	collectionCombo.addItem(collection.getDescription());
					    	calculateButton.setEnabled(
									!table.getSelectionModel().isSelectionEmpty() &&
									collectionCombo.getSelectedIndex() >= 0);
						} catch (InstantiationException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						}
				    } else {
				    	JOptionPane.showMessageDialog(Imperforate.this,
								"Could not load image collection plugin!", 
								"Error", JOptionPane.ERROR_MESSAGE);
				    }
				}
			}
		});
		buttonsPanel.add(addCollectionButton);
		
		buttonsPanel.add(Box.createHorizontalGlue());
		
		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (calculating) {
					calculateButton.setText("Calculate");
					calculateRemaining = false;
					calculateButton.setEnabled(false);
				} else {
					calculateButton.setText("Do not calculate remaining");
					collectionCombo.setEnabled(false);
					calculateRemaining = true;
					final List indices = new LinkedList(); 
					for (int i = 0;	i < sorter.getRowCount(); i++) {
						if (table.getSelectionModel().isSelectedIndex(i)) {
							//evalsModel.calculate(sorter.modelIndex(i));
							indices.add(new Integer(sorter.modelIndex(i)));
						}
					}
					new Thread(new Runnable() {
						public void run() {
							calculating = true;
							console.append("\nCALCULATION:\n\n");
							Iterator it = indices.iterator();
							int counter = 1;
							while (it.hasNext() && calculateRemaining) {
								int rowIndex = ((Integer)it.next()).intValue();
								console.append("Calculating '" + evalsModel.getValueAt(rowIndex,0) + "'"
										+ "  ["+(counter++)+"/"+indices.size()+"] ...");
								evalsModel.calculate(rowIndex);
								console.append(" done.\n");
							}
							calculating = false;
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									calculateButton.setText("Calculate");
									if (!calculating)
										calculateButton.setEnabled(
												!table.getSelectionModel().isSelectionEmpty() &&
												collectionCombo.getSelectedIndex() >= 0);
									collectionCombo.setEnabled(true);
								}
							});
						}
					}).start();
				}
			}
		});
		calculateButton.setEnabled(false);
		buttonsPanel.add(calculateButton);
		
		final JButton resultsButton = new JButton("Show results");
		resultsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < sorter.getRowCount(); i++) {
					if (table.getSelectionModel().isSelectedIndex(i)) {
						final int index = sorter.modelIndex(i);
						new Thread(new Runnable() {
							public void run() {
								final HtmlFrame dialog = 
									new HtmlFrame("Results",
											"<h3>Please wait...</h3>");
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										dialog.setVisible(true);
									}
								});
								final String html = evalsModel.getHtml(index);
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										dialog.setHtml(html);
									}
								});
							}
						}).start();
					}
				}
			}
		});
		resultsButton.setEnabled(false);
		buttonsPanel.add(Box.createHorizontalStrut(5));
		buttonsPanel.add(resultsButton);
		
		
		final JButton printGnuplotButton = new JButton("Print gnuplot data");
		printGnuplotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.append("\nGNUPLOT DATA:\n\n");
				int counter = 0;
				for (int i = 0; i < sorter.getRowCount(); i++) {
					if (table.getSelectionModel().isSelectedIndex(i)) {
						int index = sorter.modelIndex(i);
						console.append("" + counter++ + "\t" 
								+ evalsModel.getValueAt(index,1) + "\t"
								+ evalsModel.getValueAt(index,2) + "\n");
					}
				}
				console.append("e\n");
			}
		});
		printGnuplotButton.setEnabled(false);
		buttonsPanel.add(Box.createHorizontalStrut(5));
		buttonsPanel.add(printGnuplotButton);
		
		
		
		
		//////////////////////
		//// CUSTOM HACKS ////
		//////////////////////
		
		
//		final Map imgMapper = new HashMap();
//		imgMapper.put("obj12", new Integer(0));
//		imgMapper.put("obj3", new Integer(12));
//		imgMapper.put("obj38", new Integer(18));
//		imgMapper.put("obj42", new Integer(24));
//		imgMapper.put("obj78", new Integer(54));
//		imgMapper.put("obj43", new Integer(30));
//		imgMapper.put("obj45", new Integer(36));
//		imgMapper.put("obj16", new Integer(6));
//		imgMapper.put("obj59", new Integer(48));
//		imgMapper.put("obj81", new Integer(60));
//		imgMapper.put("obj51", new Integer(42));
//		
//		final Map degreesMapper = new HashMap();
//		degreesMapper.put("0", new Integer(0));
//		degreesMapper.put("180", new Integer(1));
//		degreesMapper.put("270", new Integer(2));
//		degreesMapper.put("315", new Integer(3));
//		degreesMapper.put("45", new Integer(4));
//		degreesMapper.put("90", new Integer(5));
//		
//		final JButton printLatexButton = new JButton("Print latex tabular");
//		printLatexButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				for (int i = 0; i < sorter.getRowCount(); i++) {
//					if (table.getSelectionModel().isSelectedIndex(i)) {
//		                int index = sorter.modelIndex(i);
//						console.append("\nLATEX TABULAR:\n\n");
//						console.append("\\begin{tabular}{m{11cm} | m{3cm} |}\n");
//						console.append("\\textbf{Eerste tien resultaten:} & \\textbf{GGR:} \\\\\n");
//						console.append("\\vspace{4pt}\n");
//						String[][] allUrls = evalsModel.getSortedFirstUrls(index);
//						double[] allNars = evalsModel.getSortedNars(index);
//						if (allUrls != null) {
//							for (int k = 0; k < allUrls.length; k++) {
//								for (int l = 0; l < allUrls[k].length; l++) {
//									String key1 = allUrls[k][l].substring
//										(allUrls[k][l].lastIndexOf('/')+1,allUrls[k][l].indexOf('_'));
//									int nr = ((Integer) imgMapper.get(key1)).intValue();
//									String key2 = allUrls[k][l].substring
//										(allUrls[k][l].lastIndexOf('_')+1,allUrls[k][l].indexOf('.'));
//									int offset = ((Integer) degreesMapper.get(key2)).intValue();
//									console.append("\\includegraphics[width=1cm]{coil/beeld-" 
//											+ (nr+offset) + ".eps}\n");
//								}
//								console.append("& {\\scriptsize " + allNars[k] + "}\n");
//								console.append("\\\\\n");
//							}
//						}
//						console.append("\\end{tabular}\n");
//					}
//				}
//			}
//		});
//		printLatexButton.setEnabled(false);
//		buttonsPanel.add(Box.createHorizontalStrut(5));
//		buttonsPanel.add(printLatexButton);
		
		
		//////////////////////
		//// END OF HACKS ////
		//////////////////////
		
		
		console.setEditable(false);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(buttonsPanel, BorderLayout.NORTH);
		bottomPanel.add(new JScrollPane(console), BorderLayout.CENTER);

		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				boolean value = !table.getSelectionModel().isSelectionEmpty();
				if (!calculating)
					calculateButton.setEnabled(value &&
							collectionCombo.getSelectedIndex() >= 0);
				resultsButton.setEnabled(value);
				printGnuplotButton.setEnabled(value);
//				printLatexButton.setEnabled(value);
			}
		});
		
		pack();
		setSize(new Dimension(800, 600));
		setVisible(true);
	}
	
	private static Class loadClass(File dir, String name) {
		try {
			URL url = dir.toURI().toURL();
			URL[] urls = new URL[]{url};
			ClassLoader loader = new URLClassLoader(urls);
			return loader.loadClass(name);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			File parent = dir.getParentFile();
			if (parent != null)
				return loadClass(parent, dir.getName()+"."+name);
		} catch (NoClassDefFoundError e) {
			File parent = dir.getParentFile();
			if (parent != null)
				return loadClass(parent, dir.getName()+"."+name);
		}
		return null;
	}
	
	public static void main(String[] args) {
		new Imperforate();
	}
}
