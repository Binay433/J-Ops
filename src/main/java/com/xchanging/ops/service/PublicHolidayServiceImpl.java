package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.PublicHolidayDAO;
import com.xchanging.ops.model.PublicHoliday;

@Service("publicHolidayService")
@Transactional
public class PublicHolidayServiceImpl implements PublicHolidayService{

	@Autowired
	PublicHolidayDAO dao;
	
	public PublicHoliday findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public List<PublicHoliday> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	public List<PublicHoliday> findAll(Integer ofset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(ofset, maxResults);
	}

	public void save(PublicHoliday t) {
		// TODO Auto-generated method stub
		dao.save(t);
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
		
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	public void update(PublicHoliday t) {
		// TODO Auto-generated method stub
		dao.update(t);
	}
	
	public List<PublicHoliday> findByYear(int year){
		return dao.findByYear(year);
	}

	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}

}
