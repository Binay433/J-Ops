package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.RelComp;

@Repository("relProjCompDao")
public class RelComponentDaoImpl extends AbstractDaoImpl<Integer,RelComp> implements RelComponentDao{

	@SuppressWarnings("unchecked")
	public List<RelComp> findByRelease(Integer relId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("relId", relId));
		return crit.list();
	}
	


	

	
	
}
