package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.PublicHoliday;
import com.xchanging.ops.utils.CommonUtils;

@Repository("PublicHolidayDao")
public class PublicHolidayDaoImpl extends AbstractDaoImpl<Integer, PublicHoliday> implements PublicHolidayDAO{

	@SuppressWarnings("unchecked")
	public List<PublicHoliday> findByYear(int year){
		 Query query =getSession().createQuery("SELECT p FROM PublicHoliday p where p.year=:year");
		  query.setInteger("year", year); 
		return query.list();
	}
	
}
