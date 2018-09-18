package com.xchanging.ops.dao;

import org.springframework.ui.ModelMap;

import com.xchanging.ops.model.KnowledgeBase;
import com.xchanging.ops.model.User;

public interface KBDao extends AbstractDao<Integer,KnowledgeBase>{

	public ModelMap doSearch(String queryString,ModelMap model,Integer offset, Integer maxResults);
	public ModelMap findAllDrafts(ModelMap model);
	public ModelMap findMyKbItems(ModelMap model, User id);
}
