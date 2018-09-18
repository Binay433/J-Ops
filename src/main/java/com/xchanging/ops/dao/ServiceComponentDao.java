package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;

public interface ServiceComponentDao extends AbstractDao<Integer, ServiceComponent>{

	List<ServiceComponent> findByService(ServiceModel service);
	List<ServiceComponent> findOrderedAll();
}
