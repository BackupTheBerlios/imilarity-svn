package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
		JButton calculateButton = new JButton("Calculate selected");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < sorter.getRowCount(); i++) {
					if (table.getSelectionModel().isSelectedIndex(i)) {
						evalsModel.calculate(sorter.modelIndex(i));
					}
				}
			}
		});
		buttonsPanel.add(calculateButton);
		buttonsPanel.add(new JButton("Save results"));
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		
		pack();
		//setSize(new Dimension(800, 600));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Imperforate();
	}
}
