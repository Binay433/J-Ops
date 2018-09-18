package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.ServiceComponentDao;
import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;

@Service("compService")
@Transactional
public class ServiceComponentServiceImpl implements ServiceComponentService {
	
	@Autowired
	private ServiceComponentDao dao;

	public List<ServiceComponent> findAll() {
		return dao.findAll();		
	}
	public List<ServiceComponent> findAll(Integer ofset, Integer maxResults) {
		return dao.findAll(ofset, maxResults);		
	}
	public void save(ServiceComponent service) {
		dao.save(service);
		
	}

	public ServiceComponent findById(Integer id) {
		return dao.findById(id);
	}

	public void update(ServiceComponent component) {
		ServiceComponent entity = dao.findById(component.getId());
		if(entity!=null){
			entity.setServiceModel((component.getServiceModel()));
			entity.setName(component.getName());
			entity.setComponentDescription(component.getComponentDescription());
			if(component.getDocId()!=null){
				entity.setDocId(component.getDocId());
			}

			entity.setCurrentVersion(component.getCurrentVersion());

			
		}
		
	}

	public void delete(String id) {
		dao.deleteById(id);
		
	}

	public List<ServiceComponent> findByService(ServiceModel service) {
		// TODO Auto-generated method stub
		List<ServiceComponent> result=null;
		try{
			result= dao.findByService(service);
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<ServiceComponent> findOrderedAll() {
		// TODO Auto-generated method stub
		return dao.findOrderedAll();
	}

}
