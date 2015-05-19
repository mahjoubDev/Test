package com.proxym.business;


import java.io.Serializable;

import com.proxym.domain.Categorie;

/**
 * 
 * @author Nessrine
 *
 */
public class CategorieInfo implements Serializable{

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 7270711425759845852L;

	/**
	 * name of category.
	 */
	private String nameCategorie;
	
	/**
	 * reference to identify the category.
	 */
	private String reference ;

	/**
	 * default constructor.
	 */
	public CategorieInfo() {
	}


	/**
	 * Constructor using all fields.
	 * 
	 * @param nameCategorie
	 */
	public CategorieInfo(String nameCategorie) {
		super();
		this.nameCategorie = nameCategorie;
	}



	/*
	 * Getters and Setters.
	 */

	/**
	 * Returns the  category's name.
	 * 
	 * @return A <code>String</code> containing the category's name.
	 */
	public String getNameCategorie() {
		return nameCategorie;
	}

	/**
	 * Sets the category's name.
	 * 
	 * @param nameCategorie A <code>String</code> containing the category's
	 *  name.
	 */
	public void setNameCategorie(String nameCategorie) {
		this.nameCategorie = nameCategorie;
	}
	
	/**
	 * Returns the category's reference.
	 * 
	 * @return  A <code>String</code> containing the category's reference.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the category's reference.
	 * 
	 * @param reference A <code>String</code> containing the category's
	 *  reference.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Create domain object from business one.
	 * 
	 * @return
	 */
	public Categorie toDomain(){
		Categorie categorie=new Categorie();
		categorie.setNameCategorie(nameCategorie);
		categorie.setReference(reference);
		return categorie;
	}


}
