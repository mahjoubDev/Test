package com.proxym.jsf.converters;

import javax.annotation.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.proxym.domain.Resource;
import com.proxym.repositories.ResourceRepository;


@RestController
@Scope("view")
public class ResourceConverter implements Converter {
	
	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		Long idparsed=null;
		boolean parsed=true;
		try {
			idparsed=Long.valueOf(id);
		}catch(Exception exception){
			parsed=false;
		}
		return parsed?resourceRepository.findOne(idparsed):null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		return value!=null ? ((Resource)value).getId().toString(): null;
	}

	
}
