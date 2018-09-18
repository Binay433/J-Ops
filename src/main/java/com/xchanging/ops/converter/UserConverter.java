package com.xchanging.ops.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.User;
import com.xchanging.ops.service.UserService;

@Component
public class UserConverter implements Converter<Object, User>{

	@Autowired
	UserService userService;


	public User convert(Object element) {
		Integer id =null;
		User user = null;
		if(element instanceof User){
			user=(User)element;
			id =user.getId();
			
		}else{
			id = Integer.parseInt((String)element);
			user= userService.findById(id);
		}
		return user;
	}
}
