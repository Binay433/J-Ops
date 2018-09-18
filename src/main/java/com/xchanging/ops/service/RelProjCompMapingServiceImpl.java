package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.ProjCompMapingDao;
import com.xchanging.ops.model.RelProjComp;

@Service("projCompMapingService")
@Transactional
public class RelProjCompMapingServiceImpl implements RelProjCompMapingService{

	@Autowired
	public ProjCompMapingDao dao;
	
	
	public RelProjComp findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	
	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}

	
	public List<RelProjComp> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	
	public List<RelProjComp> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	
	public void save(RelProjComp t) {
		dao.save(t);
		
	}

	
	public void deleteById(String id) {
		dao.deleteById(id);
		
	}

	
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	
	public void update(RelProjComp t) {
		dao.update(t);
		
	}

	
	public List<RelProjComp> findByProject(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByProject(id);
	}

	
	public List<RelProjComp> findByComponent(Integer id) {
		return dao.findByComponent(id);
	}

	
	public RelProjComp findByProjAndComp(Integer proj, Integer comp) {
		// TODO Auto-generated method stub
		return dao.findByProjAndComp(proj,comp);
	}

}
