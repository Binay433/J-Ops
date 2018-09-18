package com.xchanging.ops.dao;

import com.xchanging.ops.model.User;


public interface UserDao extends AbstractDao<Integer, User>{

	User findByLoginId(String loginId);
	

}

