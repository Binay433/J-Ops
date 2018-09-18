package com.xchanging.ops.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import com.xchanging.ops.model.KbSuggest;


@Repository("kbSugestDao")
public class KBSugestDaoImpl extends AbstractDaoImpl<Integer,KbSuggest> implements KBSugestDao{
	
	@SuppressWarnings("unchecked")
	public ModelMap doSearch(String queryString,ModelMap model,Integer offset, Integer maxResults) {
    	boolean completeText = false;
    	if(queryString!=null && queryString.startsWith("\"") && queryString.endsWith("\"")){
    		completeText=true;
    	}
    	
    	List<KbSuggest> suggestions=null;
    	try{
    	Session session = getSession();
    	FullTextSession fullTextSession = Search.getFullTextSession(session);
    	fullTextSession.createIndexer().startAndWait();
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(KbSuggest.class).get();
        org.apache.lucene.search.Query luceneQuery =null;
        if(completeText){
        	luceneQuery = queryBuilder.phrase().onField("keyword").ignoreAnalyzer().andField("description").ignoreAnalyzer().sentence(queryString).createQuery();	
        }else{
        	luceneQuery = queryBuilder.keyword().onFields("keyword","description").matching(queryString).createQuery();

        }
        
        org.hibernate.Criteria crit= fullTextSession.createCriteria(KbSuggest.class);
        //crit.add(Restrictions.eq("approved", true));
        	org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery).setCriteriaQuery(crit);
         	
        	model.addAttribute("count", fullTextQuery.list().size());
        	suggestions = fullTextQuery.setFirstResult(offset).setMaxResults(maxResults).list();
        }catch(Exception e){
        	e.printStackTrace();
        }
       
    	
    	
    	model.addAttribute("kblistsuggestions", suggestions);
    	
        return model;
    }
}
