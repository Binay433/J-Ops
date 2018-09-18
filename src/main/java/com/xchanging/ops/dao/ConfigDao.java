package com.xchanging.ops.dao;

import com.xchanging.ops.model.Config;

public interface ConfigDao extends AbstractDao<Integer, Config>{
	public Config findByKey(String key);

}
