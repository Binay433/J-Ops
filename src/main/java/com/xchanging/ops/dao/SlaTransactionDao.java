package com.xchanging.ops.dao;

import java.util.Date;
import java.util.List;

import com.xchanging.ops.model.ServiceSla;
import com.xchanging.ops.model.SlaTransaction;

public interface SlaTransactionDao  extends AbstractDao<Integer, SlaTransaction>{
	List<SlaTransaction> findByWeek(String fromdate,String toate,ServiceSla sla);
	public List<SlaTransaction> findByMonth(String year, String month);
	public SlaTransaction findByDay(Date day,Integer service_id);
	public List<SlaTransaction> findMonthlyCompact(String year, String month,Integer id);
	public List<SlaTransaction> findByDate(Date date);
	public void deleteByDate(Date date);
	public String findComments(Date date,Integer slaId);
	public Double[] findGoodDayAvg(Integer sla_id, String month,String year,double target);

	
}
