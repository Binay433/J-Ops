package com.xchanging.ops.service;

import java.util.ArrayList;
import java.util.List;

import com.xchanging.ops.model.EmergencyChange;
import com.xchanging.ops.model.EmergencyChangeComponent;
import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.ServiceModel;


public interface ChangeService extends AbstractService<Integer,EmergencyChange> {
	
	EmergencyChange findByCRQRef(String crqRef);
	//void deleteUserByLoginId(String sso);
	//boolean isUserLoginIdUnique(Integer id, String sso);
	List<EmergencyChange> findAll(Integer offset, Integer maxResults);
	void save(EmergencyChange EChange);
	EmergencyChange findById(Integer id);
	void update(EmergencyChange EChange);
	
	void deleteById(Integer id);
	void sendMailWithEmailTemplate(EmergencyChange EChange);
	List<EmergencyChangeComponent> findbyEmergencyChangeId(Integer id);
	ArrayList<String> getHour();
	ArrayList<String> getMinutesList();
	//Integer findTotalEmergencyChanges();
	List<EmergencyChange> findByServiceId(int id,String yearId);
	public List<EmergencyChange> findByServiceMonthYear(Integer service, String month, String year);
	public List<EmergencyChange> findByStatusAndYear(String status, String year);
	public List<EmergencyChange> findByStatusYearMonth(String status, String year,String month);
	void findLatestDate(List<EmergencyChange> changeList);
}
