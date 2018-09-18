package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.model.ServiceSla;

@Repository("serviceSlaDao")
public class ServiceSlaDaoImpl extends AbstractDaoImpl<Integer, ServiceSla> implements ServiceSlaDao {
	
	public List<ServiceSla> findByService(ServiceModel service) {
		//System.out.println("Service : "+service);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("serviceModel", service));
		@SuppressWarnings("unchecked")
		List<ServiceSla> slas = (List<ServiceSla>)crit.list();
		return slas;
	}
	
	
	public List<ServiceSla> findAllOrderbyService() {
		String query="SELECT s.id,service_id,s.name,s.description,s.doc_id,s.target_green,s.target_amber,s.sla_type "+
					 "FROM ops.service_sla s, ops.ops_service o where s.service_id=o.id group by id order by o.name, s.name";
				SQLQuery sql = getSession().createSQLQuery(query);
				sql.addEntity(ServiceSla.class);
				return (List<ServiceSla>)sql.list();

	}

	public double findSla_averageByMonthAndId(Integer year, Integer month, Integer sla_id) {
		// TODO Auto-generated method stub
		String query = "select avg(passed_record) from ops.sla_transaction where year(date)=:year and month(date)=:month and sla_id=:sla_id";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("year", year);
		sql.setParameter("month", month);
		sql.setParameter("sla_id", sla_id);		
		
		Double tempAvegSla = (Double) sql.uniqueResult();
		
		return tempAvegSla;
	}
}
