package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.RelDocDao;
import com.xchanging.ops.model.RelDoc;

@Service("relDocService")
@Transactional
public class RelDocServiceImpl implements RelDocService{

	@Autowired
	RelDocDao relDocDao;
	
	
	public RelDoc findById(Integer id) {
		// TODO Auto-generated method stub
		return relDocDao.findById(id);
	}

	
	public Long countAll() {
		// TODO Auto-generated method stub
		return relDocDao.countAll();
	}

	
	public List<RelDoc> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return relDocDao.findAll();
	}

	
	public List<RelDoc> findAll() {
		// TODO Auto-generated method stub
		return relDocDao.findAll();
	}

	
	public void save(RelDoc t) {
		// TODO Auto-generated method stub
		relDocDao.save(t);
		
	}

	
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		relDocDao.deleteById(id);
	}

	
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		relDocDao.deleteById(id);
	}

	
	public void update(RelDoc t) {
		// TODO Auto-generated method stub
		relDocDao.update(t);
	}

	public List<RelDoc> findByRelease(Integer relId) {
		// TODO Auto-generated method stub
		return relDocDao.findByRelease(relId);
	}

	
	public List<RelDoc> findByProj(Integer projId) {
		// TODO Auto-generated method stub
		return relDocDao.findByProj(projId);
	}

	public List<RelDoc> findByRelComp(Integer projCompId) {
		// TODO Auto-generated method stub
		return relDocDao.findByRelComp(projCompId);
	}

}
