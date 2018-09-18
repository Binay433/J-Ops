package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.Config;
import com.xchanging.ops.model.ServicePoint;
import com.xchanging.ops.model.UserProfile;

@Repository("ConfigDao")
public class ConfigDaoImpl extends AbstractDaoImpl<Integer, Config> implements ConfigDao{

	
	public Config findByKey(String key) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("code", key));
		return (Config) crit.uniqueResult();
	}







}
