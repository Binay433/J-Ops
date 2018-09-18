package com.xchanging.ops.dao;


import java.util.List;

import com.xchanging.ops.model.EmergencyChangeComponent;
import com.xchanging.ops.model.ServiceModel;

public interface ChangeComponentDAO extends AbstractDao<Integer, EmergencyChangeComponent>{
	public List<EmergencyChangeComponent> findByChangeId(Integer id);

	
}
