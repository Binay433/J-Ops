package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.HealthCheckTransaction;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.model.UserProfile;





@Repository("serviceDao")
public class ServiceDaoImpl  extends AbstractDaoImpl<Integer, ServiceModel> implements ServiceDao {

	@SuppressWarnings("unchecked")
	public List<ServiceModel> findAll(){
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("name"));
		return (List<ServiceModel>)crit.list();
	}

	public void deleteDocument(Integer id) {
		String query="update ops.ops_service set DOC_ID = NULL where ID="+id;
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(HealthCheckTransaction.class);
		sql.executeUpdate();
	}
}
