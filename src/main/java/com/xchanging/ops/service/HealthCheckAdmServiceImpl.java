package com.xchanging.ops.service;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.HealthCheckDao;
import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.HealthCheckTransaction;
import com.xchanging.ops.model.ServiceModel;

@Service("healthService")
@Transactional
public class HealthCheckAdmServiceImpl implements HealthCheckAdmService {
	
	@Autowired
	private HealthCheckDao dao;

	public List<HealthCheck> findAll() {
		return dao.findAll();		
	}
	public List<HealthCheck> findAll(Integer ofset, Integer maxResults) {
		return dao.findAll(ofset, maxResults);		
	}

	public void save(HealthCheck check) {
		dao.save(check);
		
	}

	public HealthCheck findById(Integer id) {
		return dao.findById(id);
	}

	public List<HealthCheck> findByService(ServiceModel model) {
		return dao.findByService(model);
	}


	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}
	
	public void deleteById(String idStr) {
		Integer id = Integer.parseInt(idStr);
		deleteById(id);
		
	}

	public void update(HealthCheck check){
		dao.update(check);
	}
	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}
	
	
	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
/*	public void update(HealthCheck check) {
		HealthCheck entity = dao.findById(check.getId());
		if(entity!=null){
			entity.setCheckTime(check.getCheckTime());
			entity.setServices(check.getServices());
			entity.setDocId(check.getDocId());
			entity.setEmailCc(check.getEmailCc());
			entity.setEmailTo(check.getEmailTo());
			entity.setName(check.getName());
			entity.setSubject(check.getSubject());
			entity.setEmailEnabled(check.getEmailEnabled());

		}
	}*/



}
