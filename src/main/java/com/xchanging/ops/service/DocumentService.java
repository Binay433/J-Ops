package com.xchanging.ops.service;

import java.util.List;

import com.xchanging.ops.model.OpsDocument;



public interface DocumentService extends AbstractService<Integer,OpsDocument>{

	
	//List<OpsDocument> findAllByUserId(int id);
	//public OpsDocument findById(int id);
	//public List<OpsDocument> findAll();
	//void deleteById(int id);
	//public void saveDocument(OpsDocument document);
	public List<OpsDocument> findAllByParentId(Integer id);
}
