package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.RelDoc;

@Repository("relDocDao")
public class RelDocDaoImpl extends AbstractDaoImpl<Integer,RelDoc> implements RelDocDao{

	@SuppressWarnings("unchecked")
	public List<RelDoc> findByRelease(Integer relId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("parentId", relId));
		crit.add(Restrictions.eq("type", 1));
		return (List<RelDoc>) crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<RelDoc> findByProj(Integer projId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("parentId", projId));
		crit.add(Restrictions.eq("type", 2));
		return (List<RelDoc>) crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<RelDoc> findByRelComp(Integer projCompId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("parentId", projCompId));
		crit.add(Restrictions.eq("type", 3));
		return (List<RelDoc>) crit.list();
	}
}
