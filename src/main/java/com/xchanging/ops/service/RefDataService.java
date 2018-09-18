package com.xchanging.ops.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xchanging.ops.model.RefData;

public interface RefDataService extends AbstractService<Integer,RefData>{
	List<RefData> findAll(Integer ofset, Integer maxResults);
	void save(RefData refData);
	RefData findById(Integer id);
	void update(RefData refData);
	
	void deleteById(Integer id);
	ArrayList<RefData> findByCode(String code);
	HashMap<String,String> getStatusMap(ArrayList<RefData> refDataList);
}
