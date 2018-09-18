package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.RelProjComp;

@Repository("relProjCompMappingDao")
public class ProjCompMapingDaoImpl extends AbstractDaoImpl<Integer,RelProjComp> implements ProjCompMapingDao{

	@SuppressWarnings("unchecked")
	public List<RelProjComp> findByProject(Integer id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("proj", id));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<RelProjComp> findByComponent(Integer id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("comp", id));
		return crit.list();
	}


	public RelProjComp findByProjAndComp(Integer proj, Integer comp) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("proj", proj));
		crit.add(Restrictions.eq("comp", comp));
		return (RelProjComp) crit.uniqueResult();

	}

}
