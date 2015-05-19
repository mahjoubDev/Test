package com.proxym.business;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.proxym.domain.Resource;
import com.proxym.enums.TypeDate;
import com.proxym.serialiser.TypeDateDeserializer;

public class ResourceInfo {
	
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
	 * category which the resource belong to.
	 */
	private String referenceCategory;
	
	/**
	 * the type of date it can be hour, day or week.
	 */
	@JsonDeserialize(using=TypeDateDeserializer.class)
	private TypeDate typeDate;
	
	/**
	 * the max of duration for a given reservation.
	 */
	private Integer dureeMax;

	/**
	 * default constructor.
	 */
	public ResourceInfo() {
		
	}

	/*
	 * Getters and Setters.
	 */

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
	 * returns the resource's category.
	 * 
	 * @return A <code>String</code> containing the reference of
	 *  category object which the resource  belong to. 
	 */
	public String getReferenceCategory() {
		return referenceCategory;
	}

	/**
	 * Sets the resource's category.
	 * 
	 * @param  A <code>String</code> containing the new reference of
	 *  category object which the resource  belong to. 
	 */
	public void setReferenceCategory(String referenceCategory) {
		this.referenceCategory = referenceCategory;
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
	 * create domain object from DTO one.
	 * 
	 * @return object Resouce ready to be persisted in
	 * data base.
	 * 
	 */
	public Resource toDomain(){
		Resource resource=new Resource();
		resource.setDescription(description);
		resource.setName(name);
		resource.setReference(reference);
		resource.setTypeDate(typeDate);
		resource.setDureeMax(dureeMax);
		return resource;
	}
	
	

}
