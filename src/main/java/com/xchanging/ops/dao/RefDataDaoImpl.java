package com.xchanging.ops.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.RefData;

@Repository("RefDataDao")
public class RefDataDaoImpl extends AbstractDaoImpl<Integer, RefData> implements RefDataDao{

	@Cacheable(value="statusCode",key="#code")
	public ArrayList<RefData> findByCode(String code) {
		 Query query =getSession().getNamedQuery("RefData.findByCode");  
		  
		  query.setString("code", code);
		  return (ArrayList<RefData>)query.list();
	}

	


}
