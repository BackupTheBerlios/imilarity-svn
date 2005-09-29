/*
 * Created on 29-sep-2005
 */
package aggregators;

/**
 * @author Klaas Bosteels
 */
public interface Aggregator {

	public int aggregatedValue(int[] values);
	
	public String getDescription();
	
}
