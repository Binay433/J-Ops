package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.AbstractDao;
import com.xchanging.ops.dao.AccountDAO;
import com.xchanging.ops.dao.ServiceDao;
import com.xchanging.ops.model.Account;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.model.UserProfile;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDAO dao;
	
	public Account findById(Integer id) {
		return dao.findById(id);
	}

	public void update(Account obj) {
		System.out.println("update method yet to implement !");
		
	}

	public List<Account> findAll() {
		return dao.findAll();
	}
	public List<Account> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(offset, maxResults);
	}

	public void save(Account entity) {
		dao.save(entity);
		
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	public Account findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}


}
