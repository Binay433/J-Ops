package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.RelComp;

public interface RelComponentDao extends AbstractDao<Integer,RelComp>{
	public List<RelComp> findByRelease(Integer id);
}
