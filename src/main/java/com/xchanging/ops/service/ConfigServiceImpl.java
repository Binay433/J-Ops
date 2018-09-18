package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.ConfigDao;
import com.xchanging.ops.dao.HealthCheckTranDao;
import com.xchanging.ops.model.Config;
import com.xchanging.ops.model.ServiceModel;

@Service("configService")
@Transactional
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private ConfigDao dao;

	public Config findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public List<Config> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	public List<Config> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(offset, maxResults);
	}

	public void save(Config t) {
		// TODO Auto-generated method stub
		dao.save(t);
	}

	public void deleteById(String id) {
		dao.deleteById(id);

	}

	public void deleteById(Integer id) {
		dao.deleteById(id);

	}

	public void update(Config t) {
		dao.update(t);

	}

	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}
	
	
	public Config findByKey(String key){
		return dao.findByKey(key);
	}

}
