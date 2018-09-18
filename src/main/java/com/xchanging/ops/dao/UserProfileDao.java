package com.xchanging.ops.dao;

import com.xchanging.ops.model.UserProfile;
public interface UserProfileDao extends AbstractDao<Integer, UserProfile>{

	UserProfile findByType(String type);
	

}
