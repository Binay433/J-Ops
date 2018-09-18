package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.EmergencyChange;
import com.xchanging.ops.model.ServiceModel;

public interface ChangeDAO extends AbstractDao<Integer, EmergencyChange>{
	//public Integer findTotalEmergencyChanges();
	List<EmergencyChange> findByServiceId(int id,String yearId);
	public List<EmergencyChange> findByServiceMonthYear(Integer service,String month,String year);
	public List<EmergencyChange> findByStatusAndYear(String status, String yearStr);
	public List<EmergencyChange> findByStatusYearMonth(String status, String year,String month);

}
