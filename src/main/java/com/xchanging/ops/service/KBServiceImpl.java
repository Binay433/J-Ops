package com.xchanging.ops.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.xchanging.ops.dao.KBDao;
import com.xchanging.ops.model.KnowledgeBase;
import com.xchanging.ops.model.User;
import com.xchanging.ops.utils.CommonUtils;

@Service("kbService")
@Transactional
public class KBServiceImpl implements KBService{

	@Autowired
	KBDao dao;
	
	public KnowledgeBase findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public List<KnowledgeBase> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	public List<KnowledgeBase> findAll(Integer ofset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(ofset, maxResults);
	}

	public void save(KnowledgeBase t) {
		// TODO Auto-generated method stub
		if(null==t.getId()){
			t.setCreatedby(CommonUtils.getCurrentUser());
			t.setCreated(new Date());
			dao.save(t);
		}else{
			KnowledgeBase kb = findById(t.getId());
			t.setCreated(kb.getCreated());
			t.setCreatedby(kb.getCreatedby());
			dao.update(t);
		}
		
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	public void update(KnowledgeBase t) {
		// TODO Auto-generated method stub
		dao.update(t);
	}

	public ModelMap doSearch(String queryString,ModelMap model,Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.doSearch(queryString,model,offset, maxResults);
	}

	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}

	
	public ModelMap findAllDrafts(ModelMap model) {
		// TODO Auto-generated method stub
		return dao.findAllDrafts(model);
	}
	
	public ModelMap findMyKbItems(ModelMap model, User id) {
		// TODO Auto-generated method stub
		return dao.findMyKbItems(model, id);
	}

}
