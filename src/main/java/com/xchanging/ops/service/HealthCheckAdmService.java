package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.ServiceModel;



public interface HealthCheckAdmService extends AbstractService<Integer,HealthCheck>{
	public List<HealthCheck> findByService(ServiceModel model);
}
