package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.ServiceModel;


public interface ServiceDao extends AbstractDao<Integer, ServiceModel>{
	public List<ServiceModel> findAll();
	void deleteDocument(Integer id);
}

