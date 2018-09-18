package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.RelComp;

public interface RelComponentService extends AbstractService<Integer,RelComp>{
	public List<RelComp> findByRelease(Integer id);
}
