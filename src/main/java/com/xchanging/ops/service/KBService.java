package com.xchanging.ops.service;

import org.springframework.ui.ModelMap;

import com.xchanging.ops.model.KnowledgeBase;
import com.xchanging.ops.model.User;

public interface KBService extends AbstractService<Integer, KnowledgeBase>{
	public ModelMap doSearch(String queryString,ModelMap model,Integer offset, Integer maxResults);
	public ModelMap findAllDrafts(ModelMap model);
	public ModelMap findMyKbItems(ModelMap model, User Id);
}
