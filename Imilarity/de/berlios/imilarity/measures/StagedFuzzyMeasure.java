package de.berlios.imilarity.measures;

public interface StagedFuzzyMeasure extends FuzzyMeasure {
	
	public void compare(int element);
	public double combine();
	public void reset();

}
