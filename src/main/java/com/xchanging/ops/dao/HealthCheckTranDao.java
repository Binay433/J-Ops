package com.xchanging.ops.dao;

import java.util.Date;
import java.util.List;

import org.springframework.ui.ModelMap;

import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.HealthCheckTransaction;
import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;

public interface HealthCheckTranDao extends AbstractDao<Integer,HealthCheckTransaction>{
	public List<HealthCheckTransaction> findByDateAndHealthCheck(Date date,HealthCheck hcId);
	public List<HealthCheckTransaction> findByDateAndService(String date,Integer servId) ;
	public List<HealthCheckTransaction> findWeekTrans(String fromdate,String toate,HealthCheck hcId,
			ServiceModel model, ServiceComponent comp);
	public List<HealthCheckTransaction> findByDateIdAndEntry(Date currentDate, Integer hcId);
	public List<HealthCheckTransaction> getMonthlyReport(String year);
	public List<HealthCheckTransaction> findByEntry(Integer entryId);
	public Integer nextEntryId();
	public ModelMap findWithoutKb(ModelMap model);
}
