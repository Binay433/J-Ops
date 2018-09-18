package com.xchanging.ops.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.User;



@Repository("userDao")
public class UserDaoImpl extends AbstractDaoImpl<Integer, User> implements UserDao {



	public User findByLoginId(String loginid) {
		//System.out.println("loginid : "+loginid);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("loginId", loginid));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}





}
