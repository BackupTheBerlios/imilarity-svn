/*
 * Created on 6-okt-2005
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.berlios.imilarity.aggregators.*;
import de.berlios.imilarity.image.quantizers.NeuQuant;
import de.berlios.imilarity.image.quantizers.SctQuantizer;
import de.berlios.imilarity.image.quantizers.WuQuantizer;
import de.berlios.imilarity.measures.*;
import de.berlios.imilarity.scales.EdgeScale;
import de.berlios.imilarity.scales.SpatialScale;
import de.berlios.imilarity.smoothers.DefaultSmoother;
import de.berlios.imilarity.smoothers.SctSmoother;


public class SettingsDialog extends JDialog {

	private static final long serialVersionUID = 5534949425380832459L;

	private DescriptiveChooser measure, aggregator;
	private JDialog measuresDialog;
	
	private static final ImageMeasure[] MEASURES = new ImageMeasure[] {
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1a()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1b()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1c()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M2()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M3()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5c()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6c()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7c()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8c()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9c()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10c()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11c()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M12()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M13()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3()))),
		new ScalingImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3c()))),
		
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3()))),
		new ScalingImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c()))),
		
		new ScalingImageMeasure(new FuzzyImageMeasure(new M1a())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M1b())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M1c())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M2())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M3())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M5())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M5c())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M6())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M6c())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M7())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M7c())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M8())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M8c())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M9())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M9c())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M10())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M10c())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M11())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M11c())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M12())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new M13())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new MI3())),
		new ScalingImageMeasure(new FuzzyImageMeasure(new MI3c())),
		
		
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1a())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1b())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M2())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M3())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M12())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M13())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3c())), new NeuQuant(30, 8)),
		
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3())), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c())), new NeuQuant(30, 8)),
		
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M1a()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M1b()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M1c()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M2()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M3()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M5()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M5c()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M6()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M6c()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M7()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M7c()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M8()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M8c()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M9()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M9c()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M10()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M10c()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M11()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M11c()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M12()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M13()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new MI3()), new NeuQuant(30, 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new MI3c()), new NeuQuant(30, 8)),
		

		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1a())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1b())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M2())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M3())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M12())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M13())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3c())), new WuQuantizer(8)),
		
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3())), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c())), new WuQuantizer(8)),
		
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M1a()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M1b()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M1c()), new WuQuantizer( 8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M2()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M3()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M5()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M5c()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M6()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M6c()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M7()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M7c()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M8()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M8c()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M9()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M9c()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M10()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M10c()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M11()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M11c()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M12()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M13()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new MI3()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new MI3c()), new WuQuantizer(8)),
		
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3()), new Minimum()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c()), new Minimum()), new WuQuantizer(8)),
		
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3()), new Product()), new WuQuantizer(8)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c()), new Product()), new WuQuantizer(8)),
		
		
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1a()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1b()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1c()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M2()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M3()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5c()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6c()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7c()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8c()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9c()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10c()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11c()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M12()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M13()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3()))),
		new MomentsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3c()))),
		
		
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3()))),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c()))),
		
		new MomentsImageMeasure(new FuzzyImageMeasure(new M1a())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M1b())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M1c())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M2())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M3())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M5())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M5c())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M6())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M6c())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M7())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M7c())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M8())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M8c())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M9())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M9c())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M10())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M10c())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M11())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M11c())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M12())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new M13())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new MI3())),
		new MomentsImageMeasure(new FuzzyImageMeasure(new MI3c())),
		
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3()), new Minimum())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c()), new Minimum())),
		
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3()), new Product())),
		new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c()), new Product())),
		
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1a()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1b()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1c()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M2()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M3()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5c()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6c()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7c()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8c()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9c()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10c()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11c()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M12()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M13()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3()))),
		new PartsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3c()))),
		
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3()))),
		new PartsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c()))),
		
		new PartsImageMeasure(new FuzzyImageMeasure(new M1a())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M1b())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M1c())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M2())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M3())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M5())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M5c())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M6())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M6c())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M7())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M7c())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M8())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M8c())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M9())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M9c())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M10())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M10c())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M11())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M11c())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M12())),
		new PartsImageMeasure(new FuzzyImageMeasure(new M13())),
		new PartsImageMeasure(new FuzzyImageMeasure(new MI3())),
		new PartsImageMeasure(new FuzzyImageMeasure(new MI3c())),
		
		
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new int[] {16, 4, 4})),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new int[] {16, 4, 4})),
			
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new int[] {4, 8, 8})),
		new I1i2i3ImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new int[] {4, 8, 8})),
		
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new int[] {4, 8, 8})),
		new IrbImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new int[] {4, 8, 8})),	
		
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new int[] {8, 4, 8})),
		new XyzImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new int[] {8, 4, 8})),
		
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new int[] {4, 8, 8})),
		new YxyImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new int[] {4, 8, 8})),
		
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new int[] {7, 7, 7})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new int[] {4, 8, 8})),
		new LabImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new int[] {4, 8, 8})),
		
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new int[] {11})),
		new FocalImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new int[] {11})),
		
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new SctQuantizer())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new SctQuantizer())),
		
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new SpatialScale())),
				
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new SctQuantizer(16,240), 
				new DefaultSmoother(), new EdgeScale())),
		
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240))),
		
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new SpatialScale())),
		
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1a(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1b(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M1c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M2(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M3(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M5c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M6c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M7c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M8c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M9c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M10c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M11c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M12(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new M13(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		new HsvImageMeasure(new PseudoFuzzyHistogramImageMeasure(new MI3c(), new SctQuantizer(16,240), 
				new SctSmoother(16,240), new EdgeScale())),
		
		new FuzzyHistogramImageMeasure(new M1a()),
		new FuzzyHistogramImageMeasure(new M1b()),
		new FuzzyHistogramImageMeasure(new M1c()),
		new FuzzyHistogramImageMeasure(new M2()),
		new FuzzyHistogramImageMeasure(new M3()),
		new FuzzyHistogramImageMeasure(new M5()),
		new FuzzyHistogramImageMeasure(new M5c()),
		new FuzzyHistogramImageMeasure(new M6()),
		new FuzzyHistogramImageMeasure(new M6c()),
		new FuzzyHistogramImageMeasure(new M7()),
		new FuzzyHistogramImageMeasure(new M7c()),
		new FuzzyHistogramImageMeasure(new M8()),
		new FuzzyHistogramImageMeasure(new M8c()),
		new FuzzyHistogramImageMeasure(new M9()),
		new FuzzyHistogramImageMeasure(new M9c()),
		new FuzzyHistogramImageMeasure(new M10()),
		new FuzzyHistogramImageMeasure(new M10c()),
		new FuzzyHistogramImageMeasure(new M11()),
		new FuzzyHistogramImageMeasure(new M11c()),
		new FuzzyHistogramImageMeasure(new M12()),
		new FuzzyHistogramImageMeasure(new M13()),
		new FuzzyHistogramImageMeasure(new MI3()),
		new FuzzyHistogramImageMeasure(new MI3c()),		
	};
	
	private static final Aggregator[] AGGREGATORS = new Aggregator[] { 
		new ArithmeticMean(),
		new Minimum(),
		new Maximum(),
		new Product(),
		new ProbabilisticSum()
	};
	
	
	
	public SettingsDialog(Frame owner) {
		super(owner, "Settings");
		
		measuresDialog = new JDialog(this, "Measures");
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(300, 400));
		measuresDialog.setContentPane(contentPane);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		measuresDialog.getContentPane().add(scrollPane);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				measuresDialog.setVisible(false);
			}
		});
		JPanel panel = new JPanel();
		panel.add(cancel);
		measuresDialog.getContentPane().add(panel, BorderLayout.SOUTH);

		measuresDialog.pack();
		
		
		JPanel content = new JPanel(new GridBagLayout());
		content.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		
		c.insets = new Insets(0,0,10,10);
		c.gridy = 0;
		content.add(new JLabel("Measure:"), c);
		c.insets = new Insets(0,10,0,0);
		c.gridy++;
		measure = new DescriptiveChooser();
		measure.addObject(MEASURES[504]);
		//measure.setSelectedIndex(0);
		//measure.setPreferredSize(new Dimension(200, measure.getPreferredSize().height));
		content.add(measure, c);
		c.gridy++;
		c.insets = new Insets(10,0,0,0);
		c.anchor = GridBagConstraints.EAST;
		JButton addMeasure = new JButton("Add measure ...");
		content.add(addMeasure, c);
		addMeasure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				scrollPane.setViewportView(new JLabel("Loading ...", JLabel.CENTER));
				measuresDialog.setVisible(true);
				
				new Thread(new Runnable() {
					public void run() {
						final JPanel measuresPanel = new JPanel();
						measuresPanel.setLayout(new BoxLayout(measuresPanel, BoxLayout.Y_AXIS));
						for (int i = 0; i < MEASURES.length; i++) {
							if (!measure.containsObject(MEASURES[i])) {
								final JPanel measurePanel = new JPanel(new BorderLayout());
								JTextArea text = new JTextArea();
								text.setEditable(false);
								text.setLineWrap(true);
								text.setWrapStyleWord(true);
								text.setOpaque(false);
								text.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
								
								text.append(MEASURES[i].getDescription());
								measurePanel.add(text);
								measurePanel.setBorder(BorderFactory.createEmptyBorder(3,5,3,5));
								JButton add = new JButton("Add");
								final int index = i;
								add.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										measure.addObject(MEASURES[index]);
										measuresDialog.setVisible(false);
									}
								});
								measurePanel.add(add, BorderLayout.EAST);
								measuresPanel.add(measurePanel);
							}
						}
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								scrollPane.setViewportView(measuresPanel);
								scrollPane.revalidate();
							}
						});
					}
				}).start();
			}
		});
		
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
				SettingsDialog.this.measuresDialog.setVisible(false);
			}
		});
		buttons.add(close);
		getContentPane().add(buttons, BorderLayout.SOUTH);

		pack();
	}
	
	
	public ImageMeasure getMeasure() {
		return (ImageMeasure) measure.getSelectedObject();
	}
	
	public Aggregator getAggregator() {
		return (Aggregator) aggregator.getSelectedObject();
	}

	
	private static class DescriptiveChooser extends JPanel {

		private static final long serialVersionUID = 5038447430819503383L;
		
		private List elements = new ArrayList();
		private int selectedIndex;
		private JTextArea text;
		private JButton prev, next;
		
		public DescriptiveChooser() {
			super(new BorderLayout());
			text = new JTextArea(5,20);
			text.setEditable(false);
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			text.setBorder(BorderFactory.createEtchedBorder());
			add(text);
			
			prev = new JButton("<");
			prev.setMargin(new Insets(0,0,0,0));
			add(prev, BorderLayout.WEST);
			next = new JButton(">");
			next.setMargin(new Insets(0,0,0,0));
			add(next, BorderLayout.EAST);

			selectedIndex = -1;
			prev.setEnabled(false);
			next.setEnabled(false);
			
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
		
		public DescriptiveChooser(Object[] elements) {
			this();
			for (int i = 0; i < elements.length; i++)
				this.elements.add(elements[i]);
			if (elements.length > 0)
				setSelectedIndex(0);
		}
		
		public void addObject(Object obj) {
			elements.add(obj);
			if (selectedIndex < 0) setSelectedIndex(0);
			else {
				prev.setEnabled(selectedIndex > 0);
				next.setEnabled(selectedIndex < elements.size()-1);
			}
		}
		
		public boolean containsObject(Object obj) {
			return elements.contains(obj);
		}
		
		public void setSelectedIndex(int index) {
			selectedIndex = index;
			if (index >= 0 && index < elements.size())
				text.setText(elements.toArray()[index].toString());
			prev.setEnabled(selectedIndex > 0);
			next.setEnabled(selectedIndex < elements.size()-1);
		}
		
		public int getSelectedIndex() { return selectedIndex; }
		
		public Object getSelectedObject() {
			if (selectedIndex >= 0 && selectedIndex < elements.size())
				return elements.toArray()[selectedIndex]; 
			return null;
		}
		
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
