package com.xchanging.ops.service;

import com.xchanging.ops.model.Config;

public interface ConfigService  extends AbstractService<Integer,Config>{
	public Config findByKey(String key);
}
