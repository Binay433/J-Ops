package com.xchanging.ops.dao;

import org.springframework.ui.ModelMap;

import com.xchanging.ops.model.KbSuggest;

public interface KBSugestDao extends AbstractDao<Integer,KbSuggest>{
	public ModelMap doSearch(String queryString,ModelMap model,Integer offset, Integer maxResults);
}
