package com.xchanging.ops.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xchanging.ops.model.Environment;
import com.xchanging.ops.service.EnvironmentService;

@Component
public class EnvironmentConverter implements Converter<Object, Environment>{
	@Autowired
	EnvironmentService envService;
	
	
	public Environment convert(Object element) {
		Integer id = Integer.parseInt((String)element); 
		Environment environment= envService.findById(id);
		//System.out.println("Profile : "+account);
		return environment;

	}
}
