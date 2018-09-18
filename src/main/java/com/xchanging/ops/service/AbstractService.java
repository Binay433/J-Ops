package com.xchanging.ops.service;

import java.io.Serializable;
import java.util.List;

public interface AbstractService <PK extends Serializable, T>{
	T findById(Integer id);
	Long countAll();
	List<T> findAll(Integer offset, Integer maxResults);
	List<T> findAll();
	void save(T t);
	public void deleteById(String id);
	public void deleteById(Integer id);
	void update(T t);
}
