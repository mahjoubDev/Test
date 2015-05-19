package com.proxym.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxym.business.ResourceInfo;
import com.proxym.enums.TypeDate;



/** Resource  object is a representation of Resource data.
 * 
 * @author Nessrine
 * @version 1.0.
 *
 */
@JsonIgnoreProperties({"reservations"})
@SuppressWarnings("serial")
@Entity
public class Resource implements Serializable {
	
	/**
	 * Primary key.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * Reference of the resource.
	 */
	private String reference;
	
	/**
	 * Name of the resource.
	 */
	private String name;
	
	/**
	 * dDescription of the resource.
	 */
	private String description;
	
	/**
	 * the type of date it can be hour, day or week.
	 */
	@Enumerated(EnumType.STRING)
	private TypeDate typeDate;
	
	/**
	 * the max of duration for a given reservation.
	 */
	private Integer dureeMax;
	
	/**
	 * category which the resource belong to.
	 */
	@ManyToOne()
	@JoinColumn(name = "category")
	private Categorie category;
	
	/**
	 * List of resources related to this category.
	 */
	@OneToMany(mappedBy = "resource",fetch=FetchType.EAGER)
	private Set<Reservation> reservations = new HashSet<Reservation>(0);

	/**
	 * default constructor.
	 */
	public Resource() {
		
	}

	/*
	 * Getters and Setters.
	 */
	
	/**
	 * Returns the resource's ID.
	 * 
	 * @return A <code>Long</code> containing the 
	 * resource's ID.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the resource's ID.
	 * 
	 * @param id  A <code>Long</code> containing the 
	 * new resource's ID.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the resource's reference.
	 * 
	 * @return A <code>String</code> containing the
	 * reference of resource.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the resource's reference.
	 * 
	 * @param reference A <code>String</code> containing the
	 * new  reference of resource.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Returns the resource's name.
	 * 
	 * @return A <code>String</code> containing the
	 *   name of resource.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the resource's name.
	 * 
	 * @param name A <code>String</code> containing the
	 * new  name of resource.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * Returns the resource's description.
	 * 
	 * @return  A <code>String</code> containing the
	 *   description of resource.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the resource's description.
	 * 
	 * @param description A <code>String</code> containing the
	 *  new  description of resource.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the type of date.
	 * 
	 * @return A object representing the type of date.
	 */
	public TypeDate getTypeDate() {
		return typeDate;
	}

	/**
	 * Sets  the type of date.
	 * 
	 * @param typeDate A object representing the new 
	 * type of date.
	 */
	public void setTypeDate(TypeDate typeDate) {
		this.typeDate = typeDate;
	}
	
	/**
	 * the max of duration.
	 * 
	 * @return A <code>integer</code> containing the max 
	 * of duration.
	 */
	public Integer getDureeMax() {
		return dureeMax;
	}

	/**
	 * The max of duration.
	 * 
	 * @param dureeMax A <code>integer</code> containing the max 
	 * of duration.
	 */
	public void setDureeMax(Integer dureeMax) {
		this.dureeMax = dureeMax;
	}

	/**
	 * returns the resource's category.
	 * 
	 * @return The category object which the resource 
	 * belong to. 
	 */
	public Categorie getCategory() {
		return category;
	}

	/**
	 * Sets the resource's category.
	 * 
	 * @param categorie The new category object which the resource 
	 * belong to.
	 */
	public void setCategory(Categorie category) {
		this.category = category;
	}
	

	/**
	 * 
	 * @return
	 */
	public Set<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * 
	 * @param reservations
	 */
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	/**
	 * Gets the business from domain.
	 * 
	 * @return An object {@link ResourceInfo}}
	 */
	public ResourceInfo toBusiness () {
		ResourceInfo resourceInfo =new ResourceInfo();
		resourceInfo.setDescription(description);
		resourceInfo.setDureeMax(dureeMax);
		resourceInfo.setName(name);
		resourceInfo.setReference(reference);
		resourceInfo.setTypeDate(typeDate);
		resourceInfo.setReferenceCategory(category!=null?category.getReference():null);
		return resourceInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dureeMax == null) ? 0 : dureeMax.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((typeDate == null) ? 0 : typeDate.hashCode());
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
		Resource other = (Resource) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dureeMax == null) {
			if (other.dureeMax != null)
				return false;
		} else if (!dureeMax.equals(other.dureeMax))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (typeDate != other.typeDate)
			return false;
		return true;
	}
	
	
	
}
