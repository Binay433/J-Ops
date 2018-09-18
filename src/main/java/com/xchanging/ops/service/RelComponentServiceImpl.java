package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.RelComponentDao;
import com.xchanging.ops.model.RelComp;

@Service("relProjCompService")
@Transactional
public class RelComponentServiceImpl implements RelComponentService{

	@Autowired
	RelComponentDao relProjCompDao;
	
	

	public RelComp findById(Integer id) {
		// TODO Auto-generated method stub
		return relProjCompDao.findById(id);
	}


	public Long countAll() {
		// TODO Auto-generated method stub
		return relProjCompDao.countAll();
	}


	public List<RelComp> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return relProjCompDao.findAll();
	}


	public List<RelComp> findAll() {
		// TODO Auto-generated method stub
		return relProjCompDao.findAll();
	}
	
	
	public List<RelComp> findByRelease(Integer id) {
		// TODO Auto-generated method stub
		return relProjCompDao.findByRelease(id);
	}

	
	public void save(RelComp t) {
		// TODO Auto-generated method stub
		 relProjCompDao.save(t);
	}

	
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		 relProjCompDao.deleteById(id);
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		 relProjCompDao.deleteById(id);
	}

	
	public void update(RelComp t) {
		// TODO Auto-generated method stub
		 relProjCompDao.update(t);
	}


	
	

}
