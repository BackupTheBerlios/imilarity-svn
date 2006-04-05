package models;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.table.AbstractTableModel;


import de.berlios.imilarity.aggregators.*;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.quantizers.*;
import de.berlios.imilarity.measures.*;
import de.berlios.imilarity.scales.EdgeScale;
import de.berlios.imilarity.scales.SpatialScale;
import de.berlios.imilarity.smoothers.DefaultSmoother;
import de.berlios.imilarity.smoothers.SctSmoother;

public class EvaluationsModel extends AbstractTableModel {

	private static final long serialVersionUID = 2285465758067991988L;

	private static final ImageMeasure[] MEASURES = new ImageMeasure[] {
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1a())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1b())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1c())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M2())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M3())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5c())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6c())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7c())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8c())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9c())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10c())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11c())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M12())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new M13())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3())),
		new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3c())),
		
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M2())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M3())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M5())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M6())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M7())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M8())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M9())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M10())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M11())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M12())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new M13())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3())),
		new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c())),
		
		
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
		
		
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1a())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1b())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M1c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M2())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M3())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M5c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M6c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M7c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M8c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M9c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M10c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M11c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M12())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new M13())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new GrayscaledImageMeasure(new FuzzyImageMeasure(new MI3c())), new NeuQuant(30, 16)),
		
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3())), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c())), new NeuQuant(30, 16)),
		
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M1a()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M1b()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M1c()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M2()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M3()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M5()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M5c()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M6()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M6c()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M7()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M7c()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M8()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M8c()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M9()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M9c()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M10()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M10c()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M11()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M11c()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M12()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new M13()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new MI3()), new NeuQuant(30, 16)),
		new DominantColorsImageMeasure(new FuzzyImageMeasure(new MI3c()), new NeuQuant(30, 16)),
		
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
		
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3())))),
		new HsvImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c())))),
		
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1a())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1b())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M1c())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M2())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M3())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M5c())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M6c())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M7c())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M8c())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M9c())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M10c())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M11c())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M12())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new M13())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3())))),
		new I1i2i3ImageMeasure(new MomentsImageMeasure(new ComponentsImageMeasure(new FuzzyImageMeasure(new MI3c())))),
		
		
		
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
	
	private ImageCollection collection = new CoilImageCollection();
	private Evaluation[] evaluations; 
	
	public EvaluationsModel() {
		super();
		evaluations = new Evaluation[MEASURES.length];
		ImageData[] examples = collection.getExamples();
		for (int i = 0; i < MEASURES.length; i++) {
			evaluations[i] = new Evaluation();
			evaluations[i].imilarities = new EvalImilarity[examples.length];
			for (int j = 0; j < examples.length; j++) {
				evaluations[i].imilarities[j] = new EvalImilarity(collection);
				evaluations[i].imilarities[j].setMeasure(MEASURES[i]);
				evaluations[i].imilarities[j].setAggregator(new ArithmeticMean());
				evaluations[i].imilarities[j].addExample(examples[j]);
			}
			evaluations[i].description = MEASURES[i].toString();
			evaluations[i].cpuTime = 0;
			evaluations[i].gnar = 1.0;
		}
	}
	
	public int getRowCount() {
		return evaluations.length;
	}

	public int getColumnCount() {
		return 3;
	}
	
	private static final String[] COLUMN_NAMES = {
		"Measure", "CPU Time", "GNAR"
	};
	
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0)
			return evaluations[rowIndex].description;
		else if (columnIndex == 1)
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
		
		buf.append("<h2>" + evaluations[rowIndex].description + "</h2>");
		
		EvalImilarity[] imilarities = evaluations[rowIndex].imilarities;
		for (int i = 0; i < imilarities.length; i++) {
			buf.append("<br/> <br/> ");
			
			//buf.append("<h3>Example image</h3>");
			//buf.append("<table align=\"center\">");
			//buf.append("<tr>");
			//buf.append("<td><img src=\"" + EXAMPLES[i].getUrl() + "\"</td>");
			//buf.append("</tr>");
			//buf.append("</table>");
			
			buf.append("<h3>Query " + (i+1) + " (NAR = " + imilarities[i].getNar() + ")</h3>");
			
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
	
	public String[][] getSortedFirstUrls(int rowIndex) {
		if (!evaluations[rowIndex].calculated)
			return null;
		EvalImilarity[] imilarities = createSortedImilaritiesArray(rowIndex);
		String[][] result = new String[imilarities.length][];
		for (int i = 0; i < imilarities.length; i++)
			result[i] = imilarities[i].getFirstUrls();
		return result;
	}
	
	public double[] getSortedNars(int rowIndex) {
		if (!evaluations[rowIndex].calculated)
			return null;
		EvalImilarity[] imilarities = createSortedImilaritiesArray(rowIndex);
		double[] result = new double[imilarities.length];
		for (int i = 0; i < imilarities.length; i++)
			result[i] = imilarities[i].getNar();
		return result;
	}
	
	
	public void calculate(int rowIndex) {
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
	
	
	
	private void calculateEvaluation(Evaluation evaluation) 
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
		evaluation.gnar = sum / collection.getExamples().length;
		evaluation.calculated = true;
	}
	
	
	private EvalImilarity[] createSortedImilaritiesArray(int rowIndex) {
		EvalImilarity[] result = new EvalImilarity[evaluations[rowIndex].imilarities.length];
		for (int i = 0; i < result.length; i++)
			result[i] = evaluations[rowIndex].imilarities[i];
		Arrays.sort(result, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				EvalImilarity im1 = (EvalImilarity) arg0;
				EvalImilarity im2 = (EvalImilarity) arg1;
				return (new Double(im1.getNar())).compareTo(new Double(im2.getNar()));
			}
		});
		return result;
	}
	
	private static class Evaluation {
		public EvalImilarity[] imilarities;
		public String description;
		public long cpuTime = 0;
		public double gnar = 1.0;
		public boolean calculated = false;
	}

}
