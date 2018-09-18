package com.xchanging.ops.dao;

import java.util.Date;
import java.util.List;

import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.model.ServiceSla;

public interface ServiceSlaDao extends AbstractDao<Integer, ServiceSla>{
	List<ServiceSla> findByService(ServiceModel service);
	public double findSla_averageByMonthAndId(Integer year,Integer month,Integer sla_id);
	public List<ServiceSla> findAllOrderbyService();

}
