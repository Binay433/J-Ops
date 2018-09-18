package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.User;


public interface UserService{
	
	
	User findByLoginId(String loginid);
	void deleteUserByLoginId(String sso);
	boolean isUserLoginIdUnique(Integer id, String sso);
	List<User> findAll();
	List<User> findAll(Integer ofset, Integer maxResults);
	void save(User user);
	User findById(Integer id);
	void update(User ser);
	void delete(String id);

}