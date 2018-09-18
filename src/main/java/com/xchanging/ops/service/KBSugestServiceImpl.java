package com.xchanging.ops.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.xchanging.ops.dao.KBSugestDao;
import com.xchanging.ops.model.KbSuggest;
import com.xchanging.ops.model.User;

@Service("kbSugest")
@Transactional
public class KBSugestServiceImpl implements KBSugestService{

	
	
	@Autowired
	KBSugestDao dao;
	
	public KbSuggest findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}


	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}




	public List<KbSuggest> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}


	public void save(KbSuggest t) {
		// TODO Auto-generated method stub
		try{
		dao.save(t);
		}catch(Exception e){
			e.printStackTrace();
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


	public void update(KbSuggest t) {
		// TODO Auto-generated method stub
	dao.update(t);	
	}


	public List<KbSuggest> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return null;
	}


	public ModelMap doSearch(String queryString, ModelMap model, Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.doSearch(queryString,model,offset, maxResults);
	}


	public ModelMap findAllDrafts(ModelMap model) {
		// TODO Auto-generated method stub
		return null;
	}


	public ModelMap findMyKbItems(ModelMap model, User Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
