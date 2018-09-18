package com.xchanging.ops.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.RefDataDao;
import com.xchanging.ops.model.RefData;

@Service("refDataService")
@Transactional
public class RefDataServiceImpl implements RefDataService{

	
	@Autowired
	private RefDataDao dao;
	
	public RefData findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public List<RefData> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	public List<RefData> findAll(Integer ofset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(ofset, maxResults);
	}

	public void save(RefData t) {
		// TODO Auto-generated method stub
		dao.save(t);
	}

	public void deleteById(String id) {
		dao.deleteById(id);
		
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	public void update(RefData t) {
		dao.update(t);
		
	}
	public ArrayList<RefData> findByCode(String code){
		
		return dao.findByCode(code);
	}
	
	
	
	
	public HashMap<String,String> getStatusMap(ArrayList<RefData> refDataList){
		HashMap<String,String> statusMap = new HashMap<String,String>();
		
		Iterator<RefData> statusIterator = refDataList.iterator();
		while (statusIterator.hasNext()) {
			RefData  refData =statusIterator.next();
			
			
			statusMap.put(refData.getKey(),refData.getValue());
		}
		
		return statusMap;
	}

	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}

}
