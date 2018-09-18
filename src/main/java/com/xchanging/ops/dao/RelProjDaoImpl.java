package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.RelProject;

@Repository("relProjDao")
public class RelProjDaoImpl extends AbstractDaoImpl<Integer,RelProject> implements RelProjDao{

	@SuppressWarnings("unchecked")
	public List<RelProject> findByRelease(Integer relId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("relId", relId));
		return crit.list();
	}
	


}
