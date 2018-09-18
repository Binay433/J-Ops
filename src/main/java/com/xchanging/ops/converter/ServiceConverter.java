package com.xchanging.ops.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xchanging.ops.model.Account;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.service.AccountService;
import com.xchanging.ops.service.OpsService;

@Component
public class ServiceConverter implements Converter<Object, ServiceModel> {

	@Autowired
	OpsService opsService;
	
	public ServiceModel convert(Object element) {
		Integer id =null;
		ServiceModel service = null;
		if(element instanceof ServiceModel){
			service=(ServiceModel)element;
			id =service.getId();
			
		}else{
			id = Integer.parseInt((String)element);
			service= opsService.findById(id);
		}
		
		
		//System.out.println("ServiceModel : "+service);
		return service;
	}

}
