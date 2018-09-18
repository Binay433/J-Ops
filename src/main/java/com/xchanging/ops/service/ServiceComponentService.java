package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;



public interface ServiceComponentService {

	List<ServiceComponent> findAll();
	List<ServiceComponent> findOrderedAll();
	List<ServiceComponent> findAll(Integer ofset, Integer maxResults);
	List<ServiceComponent> findByService(ServiceModel service);
	void save(ServiceComponent service);
	ServiceComponent findById(Integer id);
	void update(ServiceComponent service);
	void delete(String id);
}
