package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;

public abstract class GrayscaleImageMeasureBase extends ImageMeasureBase {

	public void setQuery(Image query) {
		if (query.getColorComponentsCount() != 1)
			throw new IllegalArgumentException("query must have 1 color component");
		super.setQuery(query);
	}
	
	public void setTarget(Image target) {
		if (target.getColorComponentsCount() != 1)
			throw new IllegalArgumentException("target must have 1 color component");
		super.setTarget(target);
	}

}
