package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.xchanging.ops.dao.AbstractDao;
import com.xchanging.ops.dao.ServiceDao;
import com.xchanging.ops.model.ServiceModel;

@Service("opsService")
@Transactional
public class OpsServiceImpl implements OpsService{


	@Autowired
	private ServiceDao dao;


	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void update(ServiceModel service) {
		ServiceModel entity = dao.findById(service.getId());
		if(entity!=null){
			entity.setAccount(service.getAccount());
			entity.setName(service.getName());
			entity.setBusinessOwner(service.getBusinessOwner());
			entity.setHrsEnd(service.getHrsEnd());
			entity.setHrsStart(service.getHrsStart());
			entity.setServiceOwner(service.getServiceOwner());
			entity.setSupportBy(service.getSupportBy());
			entity.setSystemManager(service.getSystemManager());
			entity.setBrief(service.getBrief());
			//if(service.getDocId()!=null){
			entity.setDocId(service.getDocId());
			//}
			
		}
	}


	public List<ServiceModel> findAll() {
		return dao.findAll();
	}

	public List<ServiceModel> findAll(Integer ofset, Integer maxResults) {
		return dao.findAll(ofset, maxResults);
	}

	public void save(ServiceModel service) {
		dao.save(service);
		
	}


	public ServiceModel findById(Integer id) {
		
		return dao.findById(id);
	}


	public void deleteById(String id) {
		dao.deleteById(id);
		
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
		
	}


	public void deleteDocument(Integer id) {
		dao.deleteDocument(id);
		
	}


	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}




}
