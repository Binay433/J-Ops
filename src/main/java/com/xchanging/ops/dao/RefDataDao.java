package com.xchanging.ops.dao;

import java.util.ArrayList;
import java.util.List;
import com.xchanging.ops.model.RefData;

public interface RefDataDao extends AbstractDao<Integer, RefData>{
	public ArrayList<RefData> findByCode(String code);

}
