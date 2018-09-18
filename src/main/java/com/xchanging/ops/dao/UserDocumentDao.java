package com.xchanging.ops.dao;

import java.util.List;

import com.xchanging.ops.model.OpsDocument;

public interface UserDocumentDao {

	List<OpsDocument> findAll();
	List<OpsDocument> findAll(Integer offset, Integer maxResults);
	Long countAll();
	OpsDocument findById(int id);
	
	void save(OpsDocument document);
	
	List<OpsDocument> findAllByUserId(int userId);
	
	void deleteById(int id);
	
	List<OpsDocument> findAllByParentId(Integer userId);
}
