package com.xchanging.ops.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xchanging.ops.model.Account;
import com.xchanging.ops.service.AccountService;

@Component
public class AccountConverter implements Converter<Object, Account>{

	
	@Autowired
	AccountService accountService;
	
	
	public Account convert(Object element) {
		Integer id = Integer.parseInt((String)element); 
		Account account= accountService.findById(id);
		//System.out.println("Profile : "+account);
		return account;

	}

}
