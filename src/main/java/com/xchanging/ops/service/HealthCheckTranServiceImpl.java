package com.xchanging.ops.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.rowset.spi.SyncResolver;
import javax.transaction.Synchronization;

import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.xchanging.ops.dao.HealthCheckTranDao;
import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.HealthCheckTransaction;
import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.utils.CommonUtils;

@Service("healthTranService")
@Transactional
public class HealthCheckTranServiceImpl implements HealthCheckTranService{

	@Autowired
	private HealthCheckTranDao dao;
	
	public HealthCheckTransaction findById(Integer id) {
		return dao.findById(id);
	}
	public List<HealthCheckTransaction> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	public List<HealthCheckTransaction> findAll(Integer ofset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(ofset, maxResults);
	}

	public void save(HealthCheckTransaction t) {
		dao.save(t);
		
	}
	
	
	public void save(List<HealthCheckTransaction> trs) {

		synchronized(this){
			Integer nextEntry = dao.nextEntryId();
			for(HealthCheckTransaction tran:trs){
				if(tran.getId()==null){
					tran.setEntryId(nextEntry);
					save(tran);
				}else{
					update(tran);
				}
			}			
		}
	}

	public void deleteById(String id) {
		dao.deleteById(id);
		
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	public void update(HealthCheckTransaction t) {
		dao.update(t);
		
	}

	public List<HealthCheckTransaction> findByDateAndHealthCheck(Date date,HealthCheck chek){
		// TODO Auto-generated method stub
		return dao.findByDateAndHealthCheck(date, chek);
	}

	
	public List<HealthCheckTransaction> findByDateIdAndEntry(Date currentDate, Integer hcId){
		return dao.findByDateIdAndEntry(currentDate, hcId);
	}
	
	
	
	public void update(List<HealthCheckTransaction> trs,List<HealthCheckTransaction> existing) {
		for(HealthCheckTransaction trn: existing){
			dao.deleteById(trn.getId());
		}
		for(HealthCheckTransaction trn: trs){
			save(trn);
		}
		
	}

	public List<HealthCheckTransaction> findWeekTrans(String fromdate, String toate,HealthCheck hcId,
			ServiceModel model, ServiceComponent comp) {
		
		return dao.findWeekTrans(fromdate, toate,hcId,model,comp);
	}

	public List<HealthCheckTransaction> getMonthlyReport(String year) {
		// TODO Auto-generated method stub
		return dao.getMonthlyReport(year);
	}

	public List<HealthCheckTransaction> findByDateAndService(String date,Integer servId) {
		return dao.findByDateAndService(date, servId);
	}

	public List<HealthCheckTransaction> findByEntry(Integer entryId) {
		
		return dao.findByEntry(entryId);
	}
	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}
	public ModelMap findWithoutKb(ModelMap model) {
		// TODO Auto-generated method stub
		return dao.findWithoutKb(model);
	}

}
