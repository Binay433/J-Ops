package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.RelProjComp;

public interface RelProjCompMapingService extends AbstractService<Integer,RelProjComp>{
	public List<RelProjComp> findByProject(Integer id);
	public List<RelProjComp> findByComponent(Integer id);
	public RelProjComp findByProjAndComp(Integer proj,Integer comp);
}
