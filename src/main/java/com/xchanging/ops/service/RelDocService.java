package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.RelDoc;

public interface RelDocService extends AbstractService<Integer,RelDoc>{
	public List<RelDoc> findByRelease(Integer relId);
	public List<RelDoc> findByProj(Integer projId);
	public List<RelDoc> findByRelComp(Integer projCompId);
}
