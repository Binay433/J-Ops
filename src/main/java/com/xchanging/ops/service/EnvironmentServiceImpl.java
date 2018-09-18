package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.EnvironmentDao;
import com.xchanging.ops.model.Environment;

@Service("environmentService")
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService{
	@Autowired
	private EnvironmentDao dao;

	public Environment findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public List<Environment> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	public List<Environment> findAll(Integer ofset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(ofset,maxResults);
	}

	public void save(Environment t) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public void update(Environment t) {
		// TODO Auto-generated method stub
		
	}

	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}
}
