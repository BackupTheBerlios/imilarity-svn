/*
 * Created on 26-sep-2005
 */
package de.berlios.imilarity.providers;


/**
 * @author Klaas Bosteels
 */
public abstract class ProvidorBase implements Providor {
	
	public String toString() {
		return getDescription();
	}

}
