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
			new FuzzyImageMeasure(new M1b()),
			new FuzzyImageMeasure(new M1c()),
			new FuzzyImageMeasure(new M2()),
			new FuzzyImageMeasure(new M3()),
			new FuzzyImageMeasure(new M5()),
			new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M5())),
			new FuzzyImageMeasure(new M6()),
			new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M6())),
			new FuzzyImageMeasure(new M7()),
			new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M7())),
			new FuzzyImageMeasure(new M8()),
			new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M8())),
			new FuzzyImageMeasure(new M9()),
			new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M9())),
			new FuzzyImageMeasure(new M10()),
			new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M10())),
			new FuzzyImageMeasure(new M11()),
			new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M11())),
			new FuzzyImageMeasure(new M12()),
			new FuzzyImageMeasure(new M13()),
			new FuzzyImageMeasure(new MI3()),
			new FuzzyImageMeasure(new MI3c()),
			
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M1a())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M1b())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M1c())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M2())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M3())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M5())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M6())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M7())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M8())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M9())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M10())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M11())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M12())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new M13())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new MI3())),
			new HueImageMeasure(new FuzzyHistogramImageMeasure(new MI3c())),
			
			new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a())),
			new FuzzyQuantizedImageMeasure(new PM1a(), new NeuQuant(30,6)),
			new FuzzyQuantizedImageMeasure(new PM1a(), new Wu(10)),
			new ProductImageMeasure(new FuzzyImageMeasure(new M1c()), 
					new FuzzyQuantizedImageMeasure(new PM1a(), new Wu(8)))
	};
	
	private static final Aggregator[] AGGREGATORS = new Aggregator[] {
			new ArithmeticMean()
	};
	
	private static ImageData[] EXAMPLES;
	static {
		try {
			EXAMPLES = new ImageData[] { 
					ImageData.loadFile("/home/klbostee/Images/coil3/obj3__0.png"),
					ImageData.loadFile("/home/klbostee/Images/coil3/obj12__0.png"),
					ImageData.loadFile("/home/klbostee/Images/coil3/obj16__0.png"),
					ImageData.loadFile("/home/klbostee/Images/coil3/obj38__0.png"),
					ImageData.loadFile("/home/klbostee/Images/coil3/obj42__0.png"),
					ImageData.loadFile("/home/klbostee/Images/coil3/obj43__0.png"),
					ImageData.loadFile("/home/klbostee/Images/coil3/obj45__0.png"),
					ImageData.loadFile("/home/klbostee/Images/coil3/obj51__0.png"),
					ImageData.loadFile("/home/klbostee/Images/coil3/obj59__0.png"),
					ImageData.loadFile("/home/klbostee/Images/coil3/obj78__0.png"),
					ImageData.loadFile("/home/klbostee/Images/coil3/obj81__0.png")
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
				evaluations[index].gnar = 1.0;
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
	
	public String getHtml(int rowIndex) {
		
		if (!evaluations[rowIndex].calculated)
			return "<h2>Not calculated yet</h2>";
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<html>");
		buf.append("<head>");
		buf.append("<title>Query Results</title>");
		buf.append("<style> body { text-align: center; color: #444444; "
				+ "background: #ffffff; "
				+ "font-family: trebuchet ms,luxi sans,sans-serif; font-size: 96%; } </style>");
		buf.append("</head>");
		buf.append("<body>");
		
		buf.append("<h2>Measure: " + evaluations[rowIndex].measureDescription 
				+", Aggregator: " + evaluations[rowIndex].aggregatorDescription + "</h2>");
		
		EvalImilarity[] imilarities = evaluations[rowIndex].imilarities;
		for (int i = 0; i < imilarities.length; i++) {
			buf.append("<br/> <br/> ");
			
			buf.append("<h3>Example image</h3>");
			buf.append("<table align=\"center\">");
			buf.append("<tr>");
			buf.append("<td><img src=\"" + EXAMPLES[i].getUrl() + "\"</td>");
			buf.append("</tr>");
			buf.append("</table>");
			
			buf.append("<h3>Results (NAR = " + imilarities[i].getNar() + ")</h3>");
			
			buf.append("<table align=\"center\">");
			buf.append("<tr>");
			double[] firstSimilarities = imilarities[i].getFirstSimilarities();
			String[] firstUrls = imilarities[i].getFirstUrls();
			for (int k = 0; k < firstUrls.length; k++) {
				String similarity = "" + firstSimilarities[k];
				if (similarity.length() > 8)
					similarity = similarity.substring(0,8);
				if (k % 5 == 0)
					buf.append("</tr><tr>");
				buf.append("<td style=\"font-size: 10pt; text-align: center;\">"
						+ "<img src=\"" + firstUrls[k] + "\"/><br/>" 
						+ similarity + "</td>");
			}
			buf.append("</tr>");
			buf.append("</table>");
			buf.append("<center>CPU time used: " 
					+ imilarities[i].getCpuTime() + " ms</center>");
		}
		
		buf.append("</body>");
		buf.append("</html>");
		
		return buf.toString();
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
	
	private synchronized void calculateEvaluation(Evaluation evaluation) 
	throws IOException {
		double sum = 0.0;
		evaluation.cpuTime = 0;
		for (int i = 0; i < evaluation.imilarities.length; i++) {
			evaluation.calculated = false;
			evaluation.imilarities[i].clearFields();
			for (int j = 1; j <= evaluation.imilarities[i].getPageCount(); j++) {
				evaluation.imilarities[i].loadPage(j);	
				evaluation.imilarities[i].reorderPage(j);
			}
			evaluation.imilarities[i].mergeReorderedPages();
			sum += evaluation.imilarities[i].getNar();
			evaluation.cpuTime += evaluation.imilarities[i].getCpuTime();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					EvaluationsModel.this.fireTableDataChanged();
				}
			});
		}
		evaluation.gnar = sum / EXAMPLES.length;
		evaluation.calculated = true;
	}
	
	
	private static class Evaluation {
		public EvalImilarity[] imilarities;
		public String measureDescription;
		public String aggregatorDescription;
		public long cpuTime = 0;
		public double gnar = 1.0;
		public boolean calculated = false;
	}

}
