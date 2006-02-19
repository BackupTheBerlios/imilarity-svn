package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.EvaluationsModel;
import models.TableSorter;

public class Imperforate extends JFrame {
	
	private static final long serialVersionUID = 4051132972866159438L;

	private boolean calculateRemaining = false, calculating = false;

	private JTextArea console = new JTextArea(20,50);
	
	
	public Imperforate() {
		super("Imperforate");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final EvaluationsModel evalsModel = new EvaluationsModel();
		
		final TableSorter sorter = new TableSorter(evalsModel); 
		final JTable table = new JTable(sorter);    
		sorter.setTableHeader(table.getTableHeader());
		table.getColumnModel().getColumn(0).setPreferredWidth(400);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		getContentPane().add(new JScrollPane(table));
		
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		
		final JButton calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (calculating) {
					calculateButton.setText("Calculate");
					calculateRemaining = false;
					calculateButton.setEnabled(false);
				} else {
					calculateButton.setText("Do not calculate remaining");
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
							while (it.hasNext() && calculateRemaining) {
								int rowIndex = ((Integer)it.next()).intValue();
								console.append("Calculating '" + evalsModel.getValueAt(rowIndex,0) + "'"
										+ "  ["+(rowIndex+1)+"/"+indices.size()+"] ...");
								evalsModel.calculate(rowIndex);
								console.append(" done.\n");
							}
							calculating = false;
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
								final HtmlDialog dialog = 
									new HtmlDialog(Imperforate.this, "Results",
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
		buttonsPanel.add(resultsButton);
		
		
		
		//////////////////////
		//// CUSTOM HACKS ////
		//////////////////////
		
		
		final JButton printGnuplotButton = new JButton("Print gnuplot data");
		printGnuplotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.append("\nGNUPLOT DATA:\n\n");
				int counter = 0;
				for (int i = 0; i < sorter.getRowCount(); i++) {
					if (table.getSelectionModel().isSelectedIndex(i)) {
						console.append("" + counter++ + "\t" 
								+ evalsModel.getValueAt(i,1) + "\t"
								+ evalsModel.getValueAt(i,2) + "\n");
					}
				}
				console.append("e\n");
			}
		});
		printGnuplotButton.setEnabled(false);
		buttonsPanel.add(printGnuplotButton);
		
		
		final Map imgMapper = new HashMap();
		imgMapper.put("obj12", new Integer(0));
		imgMapper.put("obj3", new Integer(12));
		imgMapper.put("obj38", new Integer(18));
		imgMapper.put("obj42", new Integer(24));
		imgMapper.put("obj78", new Integer(54));
		imgMapper.put("obj43", new Integer(30));
		imgMapper.put("obj45", new Integer(36));
		imgMapper.put("obj16", new Integer(6));
		imgMapper.put("obj59", new Integer(48));
		imgMapper.put("obj81", new Integer(60));
		imgMapper.put("obj51", new Integer(42));
		
		final Map degreesMapper = new HashMap();
		degreesMapper.put("0", new Integer(0));
		degreesMapper.put("180", new Integer(1));
		degreesMapper.put("270", new Integer(2));
		degreesMapper.put("315", new Integer(3));
		degreesMapper.put("45", new Integer(4));
		degreesMapper.put("90", new Integer(5));
		
		final JButton printLatexButton = new JButton("Print latex tabular");
		printLatexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < sorter.getRowCount(); i++) {
					if (table.getSelectionModel().isSelectedIndex(i)) {
						console.append("\nLATEX TABULAR:\n\n");
						console.append("\\begin{tabular}{m{11cm} | m{3cm} |}\n");
						console.append("\\textbf{Eerste tien resultaten:} & \\textbf{GGR:} \\\\\n");
						console.append("\\vspace{4pt}\n");
						String[][] allUrls = evalsModel.getSortedFirstUrls(i);
						double[] allNars = evalsModel.getSortedNars(i);
						if (allUrls != null) {
							for (int k = 0; k < allUrls.length; k++) {
								for (int l = 0; l < allUrls[k].length; l++) {
									String key1 = allUrls[k][l].substring
										(allUrls[k][l].lastIndexOf('/')+1,allUrls[k][l].indexOf('_'));
									int nr = ((Integer) imgMapper.get(key1)).intValue();
									String key2 = allUrls[k][l].substring
										(allUrls[k][l].lastIndexOf('_')+1,allUrls[k][l].indexOf('.'));
									int offset = ((Integer) degreesMapper.get(key2)).intValue();
									console.append("\\includegraphics[width=1cm]{coil/beeld-" 
											+ (nr+offset) + ".eps}\n");
								}
								console.append("& {\\scriptsize " + allNars[k] + "}\n");
								console.append("\\\\\n");
							}
						}
						console.append("\\end{tabular}\n");
					}
				}
			}
		});
		printLatexButton.setEnabled(false);
		buttonsPanel.add(printLatexButton);
		
		
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
					calculateButton.setEnabled(value);
				resultsButton.setEnabled(value);
				printGnuplotButton.setEnabled(value);
				printLatexButton.setEnabled(value);
			}
		});
		
		pack();
		setSize(new Dimension(800, 600));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Imperforate();
	}
}
