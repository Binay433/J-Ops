package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.RelProjDao;
import com.xchanging.ops.model.RelProject;
import com.xchanging.ops.model.TranRelease;

@Service("relProjService")
@Transactional
public class RelProjServiceImpl implements RelProjService{

	@Autowired
	RelProjDao relProjDao;
	
	
	public RelProject findById(Integer id) {
		// TODO Auto-generated method stub
		return relProjDao.findById(id);
	}

	
	public Long countAll() {
		// TODO Auto-generated method stub
		return relProjDao.countAll();
	}

	
	public List<RelProject> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return relProjDao.findAll();
	}

	
	public List<RelProject> findAll() {
		// TODO Auto-generated method stub
		return relProjDao.findAll();
	}

	
	public void save(RelProject t) {
		// TODO Auto-generated method stub
		relProjDao.save(t);
		
	}

	
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		relProjDao.deleteById(id);
	}

	
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		relProjDao.deleteById(id);
	}

	
	public void update(RelProject t) {
		// TODO Auto-generated method stub
		relProjDao.update(t);
	}

	
	public List<RelProject> findByRelease(Integer relId) {
		// TODO Auto-generated method stub
		return relProjDao.findByRelease(relId);
	}

}
