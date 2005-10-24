package models;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


import de.berlios.imilarity.Imilarity;
import de.berlios.imilarity.aggregators.*;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.quantizers.NeuQuant;
import de.berlios.imilarity.image.quantizers.Wu;
import de.berlios.imilarity.measures.*;
import de.berlios.imilarity.providors.Providor;

public class EvalImilarity extends Imilarity implements Comparable {
	
	private ImageCollection collection;
	
	private ImageMeasure measure;
	private Aggregator aggregator;
	
	private long cpuTime = 0;
	private double nar = 0.0;
	private double[] firstSimilarities;
	private String[] firstUrls;
	
	
	public EvalImilarity(ImageCollection collection) {
		if (collection == null)
			throw new NullPointerException("collection == null");
		this.collection = collection;
		super.setProvidor(collection);
	}
	
	public void setMeasure(ImageMeasure measure) {
		super.setMeasure(measure);
		this.measure = measure;
	}
	
	public void setAggregator(Aggregator aggregator) {
		super.setAggregator(aggregator);
		this.aggregator = aggregator;
	}
	
	public void setProvidor(Providor providor) {
		throw new UnsupportedOperationException("the collection is the providor for this object");
	}
	
	public void reorderPage(int page) {
		long millis = System.currentTimeMillis();
		super.reorderPage(page);
		cpuTime += (System.currentTimeMillis() - millis);
	}

	public void mergeReorderedPages() {
		long millis = System.currentTimeMillis();
		super.mergeReorderedPages();
		cpuTime += (System.currentTimeMillis() - millis);
		
		try {
			int rank = 0, relevantCount = 0;
			int sum = 0;
			for (int i = 1; i <= getPageCount(); i++) {
				ImageData[] page = getPage(i);
				for (int j = 0; j < page.length; j++) {
					rank++;
					if (page[j] != null && isRelevant(page[j])) {
						sum += rank;
						relevantCount++;
					}
				}
			}
			nar = (sum - (relevantCount*(relevantCount+1)/2)) * 1.0 / (rank * relevantCount);
			
			firstSimilarities = new double[getPageSize()];
			firstUrls = new String[getPageSize()];
			ImageData[] page = getPage(1);
			for (int i = 0; i < getPageSize(); i++) {
				firstSimilarities[i] = page[i].getSimilarity();
				firstUrls[i] = page[i].getUrl().toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isRelevant(ImageData image) {
		ImageData[] examples = getExamples();
		for (int i = 0; i < examples.length; i++)
			if (collection.areRelevant(examples[i], image))
				return true;
		return false;
	}
	
	public long getCpuTime() {
		return cpuTime;
	}
	
	public double getNar() {
		return nar;
	}
	
	public double[] getFirstSimilarities() {
		return firstSimilarities;
	}
	
	public String[] getFirstUrls() {
		return firstUrls;
	}
	
	public void clearFields() {
		cpuTime = 0;
		nar = 0;
		firstSimilarities = null;
		firstUrls = null;
	}

	
	public String getDescription() {
		return "Measure: " + measure.getDescription() + ", Aggregator: " + aggregator;
	}
	
	
	public int compareTo(Object arg0) {
		if (!(arg0 instanceof EvalImilarity))
			throw new IllegalArgumentException("must be an instance of Evaluator");
		return new Double(nar).compareTo(new Double(((EvalImilarity)arg0).nar));
	}
	
	
	public static void evaluateQuery
		(ImageMeasure[] measures, Aggregator[] aggregators, ImageData[] examples,
				String filename) 
		throws IOException {
		
		ImageCollection collection = new CoilImageCollection();
		List imperforates = new ArrayList();
		
		for (int i = 0; i < measures.length; i++) {
			for (int j = 0; j < aggregators.length; j++) {			
				EvalImilarity imperforate = new EvalImilarity(collection);
				imperforate.setMeasure(measures[i]);
				imperforate.setAggregator(aggregators[j]);
				
				System.out.println("Loading images.");
				System.out.print("  progress: ");
				for (int k = 1; k <= imperforate.getPageCount(); k++) {
					imperforate.loadPage(k);
					System.out.print("#");
				}
				System.out.println();
				
				System.out.println("Adding example images.");
				System.out.print("  progress: ");
				for (int k = 0; k < examples.length; k++) {
					imperforate.addExample(examples[k]);
					System.out.print("#");
				}
				System.out.println();
				
				System.out.println("Reordering using '" + measures[i].getDescription() + "' and '" 
						+ aggregators[j].getDescription() + "'.");
				System.out.print("  progress: ");
				for (int k = 1; k <= imperforate.getPageCount(); k++) {
					imperforate.reorderPage(k);
					System.out.print("#");
				}
				System.out.println();
				System.out.println("Merging reordered pages.");
				imperforate.mergeReorderedPages();
				
				imperforates.add(imperforate);
			}
		}
		
		Collections.sort(imperforates);
		
		System.out.println();
		PrintWriter output = new PrintWriter(filename);
		
		output.println("<html>");
		output.println("<head>");
		output.println("<title>Query Results</title>");
		output.println("<style> body { text-align: center; color: #444; "
				+ "background: #fff; "
				+ "font-family: trebuchet ms,luxi sans,sans-serif; font-size: 96%; } </style>");
		output.println("</head>");
		output.println("<body>");
		
		output.println("<h3>Example images</h3>");
		output.println("<table align=\"center\">");
		output.println("<tr>");
		for (int i = 0; i < examples.length; i++)
			output.println("<td><img src=\"" + examples[i].getUrl() + "\"</td>");
		output.println("</tr>");
		output.println("</table>");
		
		int counter = 0;
		Iterator it = imperforates.iterator();
		while (it.hasNext()) {
			counter++;
			EvalImilarity imilarity = (EvalImilarity) it.next();
			System.out.println("" + counter + ": " + imilarity.getDescription() 
					+ " (NAR: " + imilarity.getNar() + ")");
			
			output.println("<h3>" + imilarity.getDescription() + "</h3>");
			output.println("<h4>NAR: " + imilarity.getNar() + "</h4>");
			
			output.println("<table align=\"center\">");
			output.println("<tr>");
			double[] firstSimilarities = imilarity.getFirstSimilarities();
			String[] firstUrls = imilarity.getFirstUrls();
			for (int k = 0; k < firstUrls.length; k++) {
				String similarity = "" + firstSimilarities[k];
				if (similarity.length() > 8)
					similarity = similarity.substring(0,8);
				if (k % 5 == 0)
					output.println("</tr><tr>");
				output.println("<td style=\"font-size: 10pt; text-align: center;\">"
						+ "<img src=\"" + firstUrls[k] + "\"/><br/>" 
						+ similarity + "</td>");
			}
			output.println("</tr>");
			output.println("</table>");
			output.println("<div align=\"center\">CPU time used: " 
					+ imilarity.getCpuTime() + " ms</div>");
		}
		
		output.println("</body>");
		output.println("</html>");
		
		output.flush();
		output.close();
	}

	
	
	public static void main(String[] args) throws IOException {
		
		ImageMeasure[] measures = new ImageMeasure[] {
				new FuzzyImageMeasure(new M1a()),
				new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a())),
				new FuzzyQuantizedImageMeasure(new M1a(), new NeuQuant(30,4)),
				new FuzzyQuantizedImageMeasure(new M1a(), new Wu(8))
		};
		Aggregator[] aggregators = new Aggregator[] {
				new ArithmeticMean()
		};
		ImageData[] examples = new ImageData[] { 
			ImageData.loadFile("/home/klbostee/Images/coil3/obj59__0.png") 
		};
		
		evaluateQuery(measures, aggregators, examples, "queryResults.html");
	}
	
	
	//////////////////////////////////////
	//// VAN OUDE IMPERFORATE-KLASSE: ////
	//////////////////////////////////////
	
//	ImageMeasure[][] measures = {
//			new ImageMeasure[] {
//					new GrayscaledImageMeasure(new ScalingImageMeasure(new FuzzyImageMeasure(new M20()))),
//					new ComponentsImageMeasure(new ScalingImageMeasure(new FuzzyImageMeasure(new M20()))),
//					new GrayscaledImageMeasure(new FuzzyHistogramImageMeasure(new M20())),
//					new ComponentsImageMeasure(new FuzzyHistogramImageMeasure(new M20()))
//					//new GrayscaledImageMeasure(new FuzzyGammaImageMeasure(new GTI(0.5,0.5))),
//					//new ComponentsImageMeasure(new FuzzyGammaImageMeasure(new GTI(0.5,0.5)))
//			},
//			new ImageMeasure[] {
//					new HueImageMeasure(new FuzzyImageMeasure(new M20())),
//					new HueImageMeasure(new FuzzyHistogramImageMeasure(new M1a())),
//					new HueImageMeasure(new PartImageMeasure(new ImageMeasureFactory() {
//						public ImageMeasure createMeasure() {
//							return new HomImageMeasure(new FuzzyHistogramImageMeasure(new M1a()));
//						}
//					})),
//					new HueImageMeasure(new PartImageMeasure(new ImageMeasureFactory() {
//						public ImageMeasure createMeasure() {
//							return new HomImageMeasure(new FuzzyHistogramImageMeasure(new M2()));
//						}
//					})),
//					new HueImageMeasure(new PartImageMeasure(new ImageMeasureFactory() {
//						public ImageMeasure createMeasure() {
//							return new HomImageMeasure(new FuzzyHistogramImageMeasure(new M3()));
//						}
//					})),
//					new HueImageMeasure(new PartImageMeasure(new ImageMeasureFactory() {
//						public ImageMeasure createMeasure() {
//							return new HomImageMeasure(new FuzzyHistogramImageMeasure(new M20()));
//						}
//					})),
//					new HueImageMeasure(new PartImageMeasure(new ImageMeasureFactory() {
//						public ImageMeasure createMeasure() {
//							return new HomImageMeasure(new FuzzyHistogramImageMeasure(new M20c()));
//						}
//					}))
//			},
//			new ImageMeasure[] {
//					new GrayscaledImageMeasure(new ScalingImageMeasure(
//							new PartImageMeasure(new ImageMeasureFactory() {
//								public ImageMeasure createMeasure() {
//									return new ProductImageMeasure(
//											new FuzzyHistogramImageMeasure(new M2()), 
//											new HomImageMeasure(new FuzzyImageMeasure(new M2())));
//								}
//							}))),
//					new ComponentsImageMeasure(new ScalingImageMeasure(
//							new PartImageMeasure(new ImageMeasureFactory() {
//								public ImageMeasure createMeasure() {
//									return new ProductImageMeasure(
//											new FuzzyHistogramImageMeasure(new M2()), 
//											new HomImageMeasure(new FuzzyImageMeasure(new M2())));
//								}
//							}))),
//					// TODO: histogram voor kleurbeelden:
////					new ScalingImageMeasure(
////							new PartImageMeasure(new StagedImageMeasureFactory() {
////								public StagedImageMeasure createMeasure() {
////									return new ProductImageMeasure(
////											new FuzzyHistogramImageMeasure(new M2()), 
////											new HomImageMeasure(new FuzzyImageMeasure(new M2())));
////									}
////							}))
//			},
//			new ImageMeasure[] {
//				new FuzzyQuantizedImageMeasure(new M1a(), new NeuQuant(30,4)),
//				new FuzzyQuantizedImageMeasure(new M1a(), new Wu(4)),
//				new FuzzyQuantizedImageMeasure(new M1a(), new NeuQuant(30,4)),
//				new FuzzyQuantizedImageMeasure(new M1a(), new Wu(4))
////				new ProductImageMeasure(
////					//new FuzzyQuantizedImageMeasure(new PM1a()),
////				    new FuzzyImageMeasure(new M2()),
////					new FuzzyImageMeasure(new M1a())
////				),
////				new ProductImageMeasure2(
////					//new FuzzyQuantizedImageMeasure(new PM1a()),
////					new FuzzyImageMeasure(new M2()),
////					new FuzzyImageMeasure(new M1a())
////				)
//			},
//			new ImageMeasure[] {
////					new ComponentsImageMeasure(new ScalingImageMeasure(
////							new PartImageMeasure(new ImageMeasureFactory() {
////								public ImageMeasure createMeasure() {
////									return new ProductImageMeasure(
////											new FuzzyHistogramImageMeasure(new M2()), 
////											new HomImageMeasure(new FuzzyImageMeasure(new M2())));
////								}
////							}))),
//					new ComponentsImageMeasure(new FuzzyHistogramImageMeasure(new M2())),
//					new FuzzyHistogramImageMeasure(new M2())
//			}
//		};
	
}
