package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.UserDocumentDao;
import com.xchanging.ops.model.OpsDocument;

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService{

	@Autowired
	UserDocumentDao dao;

	public OpsDocument findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public List<OpsDocument> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	public List<OpsDocument> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(offset, maxResults);
	}

	public List<OpsDocument> findAllByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(OpsDocument document) {
		dao.save(document);
		
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}



	public void update(OpsDocument t) {
		// TODO Auto-generated method stub
		
	}

	public List<OpsDocument> findAllByParentId(Integer id) {
		// TODO Auto-generated method stub
		return dao.findAllByParentId(id);
	}

	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}


	
}
