package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.RelProject;

public interface RelProjService extends AbstractService<Integer,RelProject>{
	List<RelProject> findByRelease(Integer relId);

}
