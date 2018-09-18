package com.xchanging.ops.service;

import java.util.Date;
import java.util.List;

import org.springframework.ui.ModelMap;

import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.HealthCheckTransaction;
import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;

public interface HealthCheckTranService extends AbstractService<Integer,HealthCheckTransaction>{
	public void save(List<HealthCheckTransaction> trs);
	public void update(List<HealthCheckTransaction> trs,List<HealthCheckTransaction> existing);
	public List<HealthCheckTransaction> findByDateAndHealthCheck(Date date,HealthCheck hcId);
	public List<HealthCheckTransaction> findWeekTrans(String fromdate,String toate,HealthCheck hcId,
			ServiceModel model, ServiceComponent comp);
	public List<HealthCheckTransaction> getMonthlyReport(String year);
	public List<HealthCheckTransaction> findByDateAndService(String date,Integer servId);
	public List<HealthCheckTransaction> findByDateIdAndEntry(Date currentDate, Integer hcId);
	public List<HealthCheckTransaction> findByEntry(Integer entryId);
	public ModelMap findWithoutKb(ModelMap model);

}
