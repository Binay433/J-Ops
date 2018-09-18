package com.xchanging.ops.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.service.ServiceComponentService;

@Component
public class ComponentConverter implements Converter<Object, ServiceComponent> {

	@Autowired
	ServiceComponentService compService;
	
	
	public ServiceComponent convert(Object element) {
		Integer id =null;
		ServiceComponent comp = null;
		if(element instanceof ServiceComponent){
			comp=(ServiceComponent)element;
			id =comp.getId();
			
		}else{
			id = Integer.parseInt((String)element);
			comp= compService.findById(id);
		}
		
		
		//System.out.println("ServiceComponent : "+comp);
		return comp;
	}
}
