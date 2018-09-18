package com.xchanging.ops.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao <PK extends Serializable, T>{
	T findById(int id);
	List<T> findAll(Integer offset, Integer maxResults);
	List<T> findAll();
	Long countAll();
	void save(T t);
	public void deleteById(String id);
	public void deleteById(Integer id);
	void update(T t);
	void saveOrUpdate(T t);
	
}
