package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.EvaluationsModel;
import models.TableSorter;

public class Imperforate extends JFrame {
	
	private static final long serialVersionUID = 4051132972866159438L;

	public Imperforate() {
		super("Imperforate");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final EvaluationsModel evalsModel = new EvaluationsModel();
		
		final TableSorter sorter = new TableSorter(evalsModel); 
		final JTable table = new JTable(sorter);    
		sorter.setTableHeader(table.getTableHeader());
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		
		getContentPane().add(new JScrollPane(table));
		
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		
		final JButton calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < sorter.getRowCount(); i++) {
					if (table.getSelectionModel().isSelectedIndex(i)) {
						evalsModel.calculate(sorter.modelIndex(i));
					}
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
		
		final JButton saveButton = new JButton("Save results");
		saveButton.setEnabled(false);
		buttonsPanel.add(saveButton);
		
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				boolean value = !table.getSelectionModel().isSelectionEmpty();
				calculateButton.setEnabled(value);
				resultsButton.setEnabled(value);
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
