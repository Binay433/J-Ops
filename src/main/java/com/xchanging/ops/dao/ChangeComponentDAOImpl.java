package com.xchanging.ops.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.EmergencyChangeComponent;
import com.xchanging.ops.model.ServiceModel;


@Repository("ChangeComponentDAO")
public class ChangeComponentDAOImpl extends AbstractDaoImpl<Integer, EmergencyChangeComponent> implements ChangeComponentDAO{
	
	@SuppressWarnings("unchecked")
	public List<EmergencyChangeComponent> findByChangeId(Integer id) {
		 Query query =getSession().getNamedQuery("EmergencyChangeComponent.findbyEmergencyChangeId");  
		  
		  query.setInteger("emergencyId", id);
		  return (ArrayList<EmergencyChangeComponent>)query.list();
	}
	



}
