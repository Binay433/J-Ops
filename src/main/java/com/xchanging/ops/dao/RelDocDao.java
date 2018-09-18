package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.RelDoc;

public interface RelDocDao extends AbstractDao<Integer,RelDoc>{
	public List<RelDoc> findByRelease(Integer relId);
	public List<RelDoc> findByProj(Integer projId);
	public List<RelDoc> findByRelComp(Integer projCompId);
}
