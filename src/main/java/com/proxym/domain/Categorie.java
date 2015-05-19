package com.proxym.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxym.business.CategorieInfo;
import com.proxym.service.CategorieService;

/**A Category  object is a representation of Categories data.
 * 
 * @author Nessrine.
 * @version 1.0.
 *
 */
@SuppressWarnings("serial")
@Entity
@JsonIgnoreProperties({"id","resources"})
public class Categorie implements Serializable {

	/**
	 * Primary key .
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * name of category.
	 */
	private String nameCategorie;

	/**
	 * reference to identify the category.
	 */
	private String reference ;

	/**
	 * List of resources related to this category.
	 */
	@OneToMany(mappedBy = "category",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Resource> resources = new HashSet<Resource>(0);

	/**
	 * default constructor.
	 */
	public Categorie() {
	}

	/*
	 * Getters and Setters.
	 */

	/**
	 * Returns the unique id of categories's ID.
	 * 
	 * @return A <code>Long</code> containing the id..
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique id of category's ID.
	 * 
	 * @param id A <code>Long</code> containing the id.
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
	 * Returns the list of resource related to this category.
	 * 
	 * @return A <code>List</code> containing the entire
	 * category's resources.
	 */
	public Set<Resource> getResources() {
		return resources;
	}

	/**
	 * Sets the list of resources.
	 * 
	 * @param resources A <code>List<String></code> containing 
	 * the entire category's resources.
	 */
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	
	/**
	 * gets the business object from the domain one.
	 * 
	 * @return A object representing the business object
	 * for DTO desing pattern.
	 */
	public CategorieInfo toBusiness () {
		CategorieInfo categorieInfo =new CategorieInfo();
		categorieInfo.setNameCategorie(nameCategorie);
		categorieInfo.setReference(reference);
		return categorieInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nameCategorie == null) ? 0 : nameCategorie.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nameCategorie == null) {
			if (other.nameCategorie != null)
				return false;
		} else if (!nameCategorie.equals(other.nameCategorie))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

	
	

}
