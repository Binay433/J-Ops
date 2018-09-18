package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.Config;
import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;

@Repository("healthCheckDao")
public class HealthCheckDaoImpl extends AbstractDaoImpl<Integer, HealthCheck> implements HealthCheckDao{
	public List<HealthCheck> findByService(ServiceModel model) {
		//System.out.println("Service : "+model);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("serviceModel", model));
		@SuppressWarnings("unchecked")
		List<HealthCheck> checks = (List<HealthCheck>)crit.list();
		return checks;
	}

}
