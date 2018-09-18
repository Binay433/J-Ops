package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import com.xchanging.ops.model.KnowledgeBase;
import com.xchanging.ops.model.User;


@Repository("kbDao")
public class KBDaoImpl extends AbstractDaoImpl<Integer,KnowledgeBase> implements KBDao{

	
    @SuppressWarnings("unchecked")
	public ModelMap doSearch(String queryString,ModelMap model,Integer offset, Integer maxResults) {
    	boolean completeText = false;
    	if(queryString!=null && queryString.startsWith("\"") && queryString.endsWith("\"")){
    		completeText=true;
    	}
    	
    	List<KnowledgeBase> kblist=null;
    	try{
    	Session session = getSession();
    	FullTextSession fullTextSession = Search.getFullTextSession(session);
    	fullTextSession.createIndexer().startAndWait();
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(KnowledgeBase.class).get();
        org.apache.lucene.search.Query luceneQuery =null;
        if(completeText){
        	luceneQuery = queryBuilder.phrase().onField("keywords").ignoreAnalyzer().andField("description").ignoreAnalyzer().sentence(queryString).createQuery();	
        }else{
        	luceneQuery = queryBuilder.keyword().onFields("keywords","description").matching(queryString).createQuery();

        }
        
        org.hibernate.Criteria crit= fullTextSession.createCriteria(KnowledgeBase.class);
        crit.add(Restrictions.eq("approved", true));
       
        
        
       
        	// wrap Lucene query in a javax.persistence.Query
        	org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery).setCriteriaQuery(crit);
        	//org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, KnowledgeBase.class);
         	
        	model.addAttribute("count", fullTextQuery.list().size());
        	kblist = fullTextQuery.setFirstResult(offset).setMaxResults(maxResults).list();
        }catch(Exception e){
        	e.printStackTrace();
        }
       
    	
    	
    	model.addAttribute("kblist", kblist);
    	
        return model;
    }
    

	public ModelMap findAllDrafts(ModelMap model) {
		org.hibernate.Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("approved", false));
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<KnowledgeBase> kbList = crit.list();
		model.addAttribute("count", kbList.size());
		model.addAttribute("kbList", kbList);
		return model;
	}


	public ModelMap findMyKbItems(ModelMap model, User id) {
		org.hibernate.Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("createdby", id));
		@SuppressWarnings("unchecked")
		List<KnowledgeBase> kbList = crit.list();
		model.addAttribute("count", kbList.size());
		model.addAttribute("kbList", kbList);
		return model;
	}
    
    
}
