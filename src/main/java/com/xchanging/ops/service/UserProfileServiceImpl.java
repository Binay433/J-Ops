package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.UserProfileDao;
import com.xchanging.ops.model.UserProfile;


@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	UserProfileDao dao;
	
	public UserProfile findById(int id) {
		return dao.findById(id);
	}

	public UserProfile findByType(String type){
		return dao.findByType(type);
	}

	public List<UserProfile> findAll(Integer ofset, Integer maxResults) {
		return dao.findAll(ofset, maxResults);
	}
	
	public List<UserProfile> findAll() {
		return dao.findAll();
	}

	public UserProfile findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
}
