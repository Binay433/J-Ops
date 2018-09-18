package com.xchanging.ops.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.xchanging.ops.model.KbSuggest;
import com.xchanging.ops.model.User;

public interface KBSugestService extends AbstractService<Integer,KbSuggest>{

	public ModelMap doSearch(String queryString,ModelMap model,Integer offset, Integer maxResults);
	public ModelMap findAllDrafts(ModelMap model);
	public ModelMap findMyKbItems(ModelMap model, User Id);

}
