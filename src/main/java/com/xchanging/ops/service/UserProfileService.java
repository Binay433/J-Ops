package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.UserProfile;


public interface UserProfileService {
	UserProfile findByType(String type);
	UserProfile findById(Integer id);
	public List<UserProfile> findAll();
	public List<UserProfile> findAll(Integer ofset, Integer maxResults);
}
