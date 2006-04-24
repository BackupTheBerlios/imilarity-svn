/*
 * Created on 26-sep-2005
 */
package de.berlios.imilarity.providers;


/**
 * @author Klaas Bosteels
 */
public abstract class ProviderBase implements Provider {
	
	public String toString() {
		return getDescription();
	}

}
