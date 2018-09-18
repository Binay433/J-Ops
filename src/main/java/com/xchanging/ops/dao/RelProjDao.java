package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.RelProject;

public interface RelProjDao extends AbstractDao<Integer,RelProject>{
	List<RelProject> findByRelease(Integer relId);
}
