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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.berlios.imilarity.aggregators.Aggregator;
import de.berlios.imilarity.aggregators.ArithmeticMean;
import de.berlios.imilarity.measures.*;
import de.berlios.imilarity.smoothers.NhSmoother;


public class SettingsDialog extends JDialog {

	private static final long serialVersionUID = 5534949425380832459L;

	private DescriptiveChooser measure, aggregator;

	
	private static final ImageMeasure[] MEASURES = new ImageMeasure[] {		
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1a()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1b()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1c()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M2()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M3()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M5())))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M6())))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M7())))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M8())))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M9())))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M10())))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M11())))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M12()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M13()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3()))),
		new MultiresImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3c()))),
		
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M5())))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M6())))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M7())))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M8())))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M9())))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M10())))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M11())))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3()))),
		new MultiresImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c()))),
		
		new MultiresImageMeasure(new FuzzyImageMeasure(new M1a())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M1b())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M1c())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M2())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M3())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M5())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M5()))),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M6())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M6()))),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M7())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M7()))),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M8())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M8()))),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M9())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M9()))),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M10())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M10()))),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M11())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new ComplementFuzzyMeasure(new M11()))),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M12())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new M13())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new MI3())),
		new MultiresImageMeasure(new FuzzyImageMeasure(new MI3c())),
			
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M1a(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M1b(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M1c(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M2(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M3(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M5(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M5()), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M6(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M6()), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M7(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M7()), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M8(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M8()), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M9(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M9()), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M10(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M10()), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M11(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M12(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new M13(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new MI3(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new FuzzyHistogramImageMeasure(new MI3c(), new int[] {16, 4, 4})),
			
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M1a(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M1b(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M1c(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M2(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M3(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M5(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M5()), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M6(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M6()), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M7(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M7()), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M8(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M8()), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M9(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M9()), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M10(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M10()), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M11(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M12(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new M13(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new MI3(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new FuzzyHistogramImageMeasure(new MI3c(), new int[] {4, 8, 8})),
		
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M1a(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M1b(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M1c(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M2(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M3(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M5(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M5()), new int[] {8, 4, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M6(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M6()), new int[] {8, 4, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M7(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M7()), new int[] {8, 4, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M8(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M8()), new int[] {8, 4, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M9(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M9()), new int[] {8, 4, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M10(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M10()), new int[] {8, 4, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M11(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {8, 4, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M12(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new M13(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new MI3(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new FuzzyHistogramImageMeasure(new MI3c(), new int[] {4, 8, 8})),
			
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M1a(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M1b(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M1c(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M1d(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M2(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M3(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M5(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M5()), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M6(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M6()), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M7(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M7()), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M8(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M8()), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M9(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M9()), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M10(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M10()), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M11(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M12(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new M13(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new MI3(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new FuzzyHistogramImageMeasure(new MI3c(), new int[] {8, 4, 8})),
		
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M1a(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M1b(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M1c(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M2(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M3(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M5(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M5()), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M6(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M6()), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M7(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M7()), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M8(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M8()), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M9(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M9()), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M10(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M10()), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M11(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M12(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new M13(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new MI3(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new FuzzyHistogramImageMeasure(new MI3c(), new int[] {4, 8, 8})),
		
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M1a(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M1b(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M1c(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M2(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M3(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M5(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M5()), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M6(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M6()), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M7(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M7()), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M8(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M8()), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M9(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M9()), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M10(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M10()), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M11(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M12(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new M13(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new MI3(), new int[] {7, 7, 7})),
		new LabImageMeasure(new FuzzyHistogramImageMeasure(new MI3c(), new int[] {7, 7, 7})),
		
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M1a(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M1b(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M1c(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M2(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M3(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M5(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M5()), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M6(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M6()), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M7(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M7()), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M8(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M8()), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M9(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M9()), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M10(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M11(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M12(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new M13(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new MI3(), new int[] {11})),
		new FocalImageMeasure(new FuzzyHistogramImageMeasure(new MI3c(), new int[] {11})),
		
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M1a(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M1b(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M1c(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M2(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M3(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M5(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M5()), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M6(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M6()), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M7(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M7()), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M8(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M8()), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M9(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M9()), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M10(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M11(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M12(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M13(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new MI3(), new int[] {256})),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new MI3c(), new int[] {256})),
		
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M1a(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M1b(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M1c(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M2(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M3(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M5(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M5()), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M6(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M6()), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M7(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M7()), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M8(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M8()), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M9(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M9()), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M10(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M11(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new ComplementFuzzyMeasure(new M11()), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M12(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new M13(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new MI3(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240),
		new NhImageMeasure(new FuzzyHistogramImageMeasure(new MI3c(), new int[] {256}, 
				new NhSmoother(16,240)), 16, 240)
	};
	
	private static final Aggregator[] AGGREGATORS = new Aggregator[] { 
		new ArithmeticMean()
	};
	
	
	
	public SettingsDialog(Frame owner) {
		super(owner, "Settings");
		
		JPanel content = new JPanel(new GridBagLayout());
		content.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		
		c.insets = new Insets(0,0,10,10);
		c.gridy = 0;
		content.add(new JLabel("Measure:"), c);
		c.insets = new Insets(0,10,0,0);
		c.gridy++;
		measure = new DescriptiveChooser(MEASURES);
		measure.setSelectedIndex(136);
		//measure.setPreferredSize(new Dimension(200, measure.getPreferredSize().height));
		content.add(measure, c);
		
		c.insets = new Insets(15,0,10,10);
		c.gridy++;
		c.fill = GridBagConstraints.HORIZONTAL;
		content.add(new JLabel("Aggregator:"), c);
		c.insets = new Insets(0,10,0,0);
		c.gridy++;
		aggregator = new DescriptiveChooser(AGGREGATORS);
		//aggregator.setPreferredSize(new Dimension(200, aggregator.getPreferredSize().height));
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
	
	
	public ImageMeasure getMeasure() {
		return MEASURES[measure.getSelectedIndex()];
	}
	
	public Aggregator getAggregator() {
		return AGGREGATORS[measure.getSelectedIndex()];
	}
	
	
	private static class DescriptiveChooser extends JPanel {

		private static final long serialVersionUID = 5038447430819503383L;
		
		private Object[] elements;
		private int selectedIndex;
		private JTextArea text;
		private JButton prev, next;
		
		public DescriptiveChooser(Object[] elements) {
			super(new BorderLayout());
			this.elements = elements;
			text = new JTextArea(5,20);
			text.setEditable(false);
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			add(new JScrollPane(text));
			
			prev = new JButton("<");
			prev.setMargin(new Insets(0,0,0,0));
			add(prev, BorderLayout.WEST);
			next = new JButton(">");
			next.setMargin(new Insets(0,0,0,0));
			add(next, BorderLayout.EAST);
			
			setSelectedIndex(0);
			
			prev.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setSelectedIndex(selectedIndex-1);
				}
			});
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setSelectedIndex(selectedIndex+1);
				}
			});
		}
		
		public void setSelectedIndex(int index) {
			selectedIndex = index;
			text.setText(elements[index].toString());
			prev.setEnabled(selectedIndex > 0);
			next.setEnabled(selectedIndex < elements.length-1);
		}
		
		public int getSelectedIndex() { return selectedIndex; }
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
