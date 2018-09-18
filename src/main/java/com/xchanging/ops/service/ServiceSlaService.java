package com.xchanging.ops.service;

import java.util.Date;
import java.util.List;

import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.model.ServiceSla;
import com.xchanging.ops.model.SlaTransaction;

public interface ServiceSlaService{
	
	List<ServiceSla> findAll();
	List<ServiceSla> findAll(Integer ofset, Integer maxResults);
	List<ServiceSla> findByService(ServiceModel service);
	public Long countAll();
	void save(ServiceSla sla);
	ServiceSla findById(Integer id);
	void update(ServiceSla sla);
	void delete(String id);
	void saveData(Date date,List<SlaTransaction> trans);
	List<SlaTransaction> findByWeek(String fromdate,String toate,ServiceSla sla);
	public List<SlaTransaction> findByMonth(String year, String month);
	public SlaTransaction findByMonth(Date day,Integer service_id);
	public List<SlaTransaction> findMonthlyCompact(String year, String month,Integer id);
	public String findComments(Date date,Integer slaId);
	public double findSla_averageByMonthAndId(Integer year,Integer month,Integer sla_id);
	public List<ServiceSla> findAllOrderbyService();
	public List<SlaTransaction> findByDate(Date date);
	public Double[] findGoodDayAvg(Integer sla_id, String month,String year,double target);
	

}
