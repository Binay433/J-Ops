package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.model.User;

@Repository("compDao")
public class ServiceComponentDaoImpl extends AbstractDaoImpl<Integer, ServiceComponent> implements ServiceComponentDao{

	
	public List<ServiceComponent> findByService(ServiceModel service) {
		//System.out.println("Service : "+service);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("serviceModel", service));
		crit.addOrder(Order.asc("name"));
		@SuppressWarnings("unchecked")
		List<ServiceComponent> comps = (List<ServiceComponent>)crit.list();
		return comps;
	}


	public List<ServiceComponent> findOrderedAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("name"));
		@SuppressWarnings("unchecked")
		List<ServiceComponent> comps = (List<ServiceComponent>)crit.list();
		return comps;
	}
}
