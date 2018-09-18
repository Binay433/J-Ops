package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.xchanging.ops.model.EmergencyChange;

@Repository("changeDAO")
public class ChangeDAOImpl extends AbstractDaoImpl<Integer, EmergencyChange> implements ChangeDAO{

	
/*	public Integer findTotalEmergencyChanges()
	{
		Query query =getSession().getNamedQuery("EmergencyChange.findTotalCount");  
		  return query.
	}*/

	@SuppressWarnings("unchecked")
	public List<EmergencyChange> findByServiceId(int service,String yearId) {
		
		String query = "SELECT e.* FROM ops.emergency_changes e,ops.change_services where id=change_id and service_id=:service_id and status=5 and EXTRACT(YEAR FROM request_date)=:yearId";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(EmergencyChange.class);
		sql.setParameter("service_id", service);
		sql.setParameter("yearId", yearId);
		return sql.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<EmergencyChange> findByServiceMonthYear(Integer service,String month,String year) {
		String query = "SELECT * FROM ops.emergency_changes e, ops.change_services cs "
				+ "where e.id=cs.change_id and cs.service_id=:service and year(e.request_date)=:year "
				+ "and month(e.request_date)=:month and e.status in(5,6)";			
		if(StringUtils.isEmpty(month)){
			query = "SELECT * FROM ops.emergency_changes e, ops.change_services cs "
					+ "where e.id=cs.change_id and cs.service_id=:service and year(e.request_date)=:year and e.status in(5,6)";
		}

		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(EmergencyChange.class);
		sql.setParameter("service", service);
		if(!StringUtils.isEmpty(month)){
			sql.setParameter("month", month);			
		}
		sql.setParameter("year", year);
		
		return sql.list();
	}

	@SuppressWarnings("unchecked")
	public List<EmergencyChange> findByStatusAndYear(String status, String year) {
		String query = "SELECT * FROM ops.emergency_changes e where year(e.request_date)=:year and status=:status";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(EmergencyChange.class);
		sql.setParameter("year", year);
		sql.setParameter("status", status);
		return sql.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<EmergencyChange> findByStatusYearMonth(String status, String year,String month) {
		String query = "SELECT * FROM ops.emergency_changes e where year(e.request_date)=:year and month(e.request_date)=:month and status=:status";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(EmergencyChange.class);
		sql.setParameter("year", year);
		sql.setParameter("month", month);
		sql.setParameter("status", status);
		return sql.list();
	}
}
