package com.xchanging.ops.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.model.ServiceSla;
import com.xchanging.ops.service.OpsService;
import com.xchanging.ops.service.ServiceSlaService;

@Component
public class ServiceSlaConverter implements Converter<Object, ServiceSla> {
	@Autowired
	ServiceSlaService service;
	
	public ServiceSla convert(Object element) {
		Integer id =null;
		ServiceSla sla = null;
		if(element instanceof ServiceSla){
			sla=(ServiceSla)element;
			id =sla.getId();
			
		}else{
			id = Integer.parseInt((String)element);
			sla= service.findById(id);
		}
		
		
		//System.out.println("ServiceSLA : "+sla);
		return sla;
	}
}
