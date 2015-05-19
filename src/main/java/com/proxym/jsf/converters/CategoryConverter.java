package com.proxym.jsf.converters;

import javax.annotation.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.proxym.domain.Categorie;
import com.proxym.repositories.CategorieRepository;


@RestController
@Scope("view")
public class CategoryConverter implements Converter {
	
	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		Long idparsed=null;
		boolean parsed=true;
		try {
			idparsed=Long.valueOf(id);
		}catch(Exception exception){
			parsed=false;
		}
		return parsed?categorieRepository.findOne(idparsed):null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		return value!=null ? ((Categorie)value).getId().toString(): null;
	}

}
