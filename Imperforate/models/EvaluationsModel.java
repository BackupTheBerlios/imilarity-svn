package models;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.table.AbstractTableModel;

import de.berlios.imilarity.aggregators.*;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.quantizers.*;
import de.berlios.imilarity.measures.*;

public class EvaluationsModel extends AbstractTableModel {

	private static final long serialVersionUID = 2285465758067991988L;

	private static final ImageMeasure[] MEASURES = new ImageMeasure[] {
			new FuzzyImageMeasure(new M1a()),
			new FuzzyImageMeasure(new M2()),
			new FuzzyImageMeasure(new M3()),
			new FuzzyImageMeasure(new M4()),
			new FuzzyImageMeasure(new M20()),
			new FuzzyImageMeasure(new M20c()),
			new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a())),
			new FuzzyQuantizedImageMeasure(new M1a(), new NeuQuant(30,4)),
			new FuzzyQuantizedImageMeasure(new M1a(), new Wu(8))
	};
	
	private static final Aggregator[] AGGREGATORS = new Aggregator[] {
			new ArithmeticMean()
	};
	
	private static ImageData[] EXAMPLES;
	static {
		try {
			EXAMPLES = new ImageData[] { 
					ImageData.loadFile("/home/klbostee/Images/coil3/obj59__0.png")
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
	
	private ImageCollection collection = new CoilImageCollection();
	private Evaluation[] evaluations; 
	
	public EvaluationsModel() {
		super();
		evaluations = new Evaluation[MEASURES.length*AGGREGATORS.length];
		for (int i = 0; i < MEASURES.length; i++) {
			for (int j = 0; j < AGGREGATORS.length; j++) {
				int index = j*AGGREGATORS.length+i;
				evaluations[index] = new Evaluation();
				evaluations[index].imilarities = new EvalImilarity[EXAMPLES.length]; 
				for (int k = 0; k < EXAMPLES.length; k++) {
					evaluations[index].imilarities[k] = new EvalImilarity(collection);
					evaluations[index].imilarities[k].setMeasure(MEASURES[i]);
					evaluations[index].imilarities[k].setAggregator(AGGREGATORS[j]);
					evaluations[index].imilarities[k].addExample(EXAMPLES[k]);
				}
				evaluations[index].measureDescription = MEASURES[i].toString();
				evaluations[index].aggregatorDescription = AGGREGATORS[j].toString();
				evaluations[index].cpuTime = 0;
				evaluations[index].gnar = 0.0;
			}
		}
	}
	
	public int getRowCount() {
		return evaluations.length;
	}

	public int getColumnCount() {
		return 4;
	}
	
	private static final String[] COLUMN_NAMES = {
		"Measure", "Aggregator", "CPU Time", "GNAR"
	};
	
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0)
			return evaluations[rowIndex].measureDescription;
		else if (columnIndex == 1)
			return evaluations[rowIndex].aggregatorDescription;
		else if (columnIndex == 2)
			return new Long(evaluations[rowIndex].cpuTime);
		else
			return new Double(evaluations[rowIndex].gnar);
	}
	
	
	public void calculate(final int rowIndex) {
		System.out.println("calculating: '" 
				+ evaluations[rowIndex].measureDescription + "' with '" 
				+ evaluations[rowIndex].aggregatorDescription + "'");
		new Thread(new Runnable() {
			public void run() {
				try {
					calculateEvaluation(evaluations[rowIndex]);
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							EvaluationsModel.this.fireTableDataChanged();
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private synchronized static void calculateEvaluation(Evaluation evaluation) 
	throws IOException {
		double sum = 0.0;
		evaluation.cpuTime = 0;
		for (int i = 0; i < evaluation.imilarities.length; i++) {
			evaluation.imilarities[i].clearFields();
			for (int j = 1; j <= evaluation.imilarities[i].getPageCount(); j++) {
				evaluation.imilarities[i].loadPage(j);	
				evaluation.imilarities[i].reorderPage(j);
			}
			evaluation.imilarities[i].mergeReorderedPages();
			sum += evaluation.imilarities[i].getNar();
			evaluation.cpuTime += evaluation.imilarities[i].getCpuTime();
		}
		evaluation.gnar = sum / EXAMPLES.length;
	}
	
	
	private static class Evaluation {
		public EvalImilarity[] imilarities;
		public String measureDescription;
		public String aggregatorDescription;
		public long cpuTime = 0;
		public double gnar = 0.0;
	}

}
