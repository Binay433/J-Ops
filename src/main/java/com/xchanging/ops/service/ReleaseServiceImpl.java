package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.ReleaseDao;
import com.xchanging.ops.model.TranRelease;

@Service("releaseService")
@Transactional
public class ReleaseServiceImpl implements ReleaseService{

	@Autowired
	ReleaseDao releaseDao;
	
	
	public TranRelease findById(Integer id) {
		// TODO Auto-generated method stub
		return releaseDao.findById(id);
	}

	
	public Long countAll() {
		// TODO Auto-generated method stub
		return releaseDao.countAll();
	}

	
	public List<TranRelease> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return releaseDao.findAll();
	}

	
	public List<TranRelease> findAll() {
		// TODO Auto-generated method stub
		return releaseDao.findAll();
	}

	
	public void save(TranRelease t) {
		// TODO Auto-generated method stub
		releaseDao.save(t);
		
	}

	
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		releaseDao.deleteById(id);
	}

	
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		releaseDao.deleteById(id);
	}

	
	public void update(TranRelease t) {
		// TODO Auto-generated method stub
		releaseDao.update(t);
	}

}
