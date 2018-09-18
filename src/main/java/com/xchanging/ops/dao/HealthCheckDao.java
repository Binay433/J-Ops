package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.ServiceModel;

public interface HealthCheckDao extends AbstractDao<Integer, HealthCheck>{
	public List<HealthCheck> findByService(ServiceModel model);

}
