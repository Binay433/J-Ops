package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.RelProjComp;

public interface ProjCompMapingDao extends AbstractDao<Integer,RelProjComp>{
	
	public List<RelProjComp> findByProject(Integer id);
	public List<RelProjComp> findByComponent(Integer id);
	public RelProjComp findByProjAndComp(Integer proj,Integer comp);

}
