package com.xchanging.ops.service;

import com.xchanging.ops.model.ServiceModel;

public interface OpsService extends AbstractService<Integer, ServiceModel>{

/*	List<ServiceModel> findAll();
	void save(ServiceModel service);
	ServiceModel findById(Integer id);
	void update(ServiceModel service);
	*/
	void deleteDocument(Integer id);

}
