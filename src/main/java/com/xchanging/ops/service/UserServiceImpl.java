package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.AbstractDao;
import com.xchanging.ops.dao.UserDao;
import com.xchanging.ops.model.Account;
import com.xchanging.ops.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{


	@Autowired
	private UserDao dao;
	

	public User findByLoginId(String loginId) {
		User user = dao.findByLoginId(loginId);
		return user;
	}


	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void update(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setLoginId(user.getLoginId());
			entity.setPassword(user.getPassword());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
			//entity.setServicepoint(user.getServicepoint());
		}
	}

	
	public void deleteUserByLoginId(String sso) {
		dao.deleteById(sso);
	}



	public boolean isUserLoginIdUnique(Integer id, String sso) {
		User user = findByLoginId(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}


	public List<User> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	public List<User> findAll(Integer ofset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(ofset, maxResults);
	}

	public void save(User user) {
		dao.save(user);
		
	}


	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}


	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	
}
