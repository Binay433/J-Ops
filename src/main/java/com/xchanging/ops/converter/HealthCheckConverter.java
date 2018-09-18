package com.xchanging.ops.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.service.HealthCheckAdmService;

@Component
public class HealthCheckConverter implements Converter<Object, HealthCheck> {

	@Autowired
	HealthCheckAdmService healthAdmService;
	
	
	public HealthCheck convert(Object element) {
		Integer id =null;
		HealthCheck check = null;
		if(element instanceof HealthCheck){
			check=(HealthCheck)element;
			id =check.getId();
			
		}else{
			id = Integer.parseInt((String)element);
			check= healthAdmService.findById(id);
		}
		
		
		//System.out.println("HealthCheck : "+check);
		return check;
	}
}
