package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.OpsDocument;

@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDaoImpl<Integer, OpsDocument> implements UserDocumentDao{

	@SuppressWarnings("unchecked")
	public List<OpsDocument> findAll(Integer offset, Integer maxResults) {
		Criteria crit = createEntityCriteria();
		return (List<OpsDocument>)crit.setFirstResult(offset).setMaxResults(maxResults).list();
	}

	public void save(OpsDocument document) {
		persist(document);
	}

	
	public OpsDocument findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<OpsDocument> findAllByUserId(int userId){
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("user");
		userCriteria.add(Restrictions.eq("id", userId));
		return (List<OpsDocument>)crit.list();
	}

	
	public void deleteById(int id) {
		OpsDocument document =  getByKey(id);
		delete(document);
	}

	@SuppressWarnings("unchecked")
	public List<OpsDocument> findAllByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("parentId", parentId));
		return (List<OpsDocument>)crit.list();
	}

}
