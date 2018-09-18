package com.xchanging.ops.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDaoImpl<PK extends Serializable, T> {
	
	private static Logger logger = LoggerFactory.getLogger(AbstractDaoImpl.class);
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDaoImpl(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		try{
			return sessionFactory.getCurrentSession();			
		}catch(Exception e){
			logger.error("Error on getSession() "+e.getStackTrace());
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public T getByKey(int id) {
		try{
			return (T) getSession().get(persistentClass, id);
		}catch(Exception e){
			logger.error("Error on getByKey("+id+") "+e.getStackTrace());
			return null;
		}

	}
	
	
	public void persist(T entity) {
		try{
			getSession().persist(entity);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error on persist("+entity.toString()+") "+e.getMessage());
		}
	}

	public void delete(T entity) {
		try{
			getSession().delete(entity);
		}catch(Exception e){
			logger.error("Error on delete("+entity.toString()+") "+e.getStackTrace());
		}
	}
	
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}
	
	
	@SuppressWarnings("unchecked")
	public T findById(int id) {
		T t = null;;
		try{
			t=getByKey(id);
		}catch(Exception e){
			logger.error("Error on findById("+id+") "+e.getStackTrace());
		}
		return t;
	}
	@SuppressWarnings("unchecked")
	public void save(T t) {
		try{
		persist(t);
		}catch(Exception e){
			logger.error("Error on save("+t.toString()+") "+e.getStackTrace());
		}
		
	}
	

	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		try{
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		
		List<T> list = (List<T>) criteria.list();
		return list;
		}catch(Exception e){
			logger.error("Error on findAll() "+e.getStackTrace());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(Integer offset, Integer maxResults) {
		try{
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		
		List<T> list = (List<T>) criteria.setFirstResult(offset!=null?offset:0).setMaxResults(maxResults!=null?maxResults:10).list();
		return list;
		}catch(Exception e){
			logger.error("Error on findAll() "+e.getStackTrace());
			return null;
		}
	}
	
	
	public void deleteById(Integer id) {
		try{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		T t = (T)crit.uniqueResult();
		delete(t);
		}catch(Exception e){
			logger.error("Error on deleteById("+id+") "+e.getStackTrace());
		}
	}
	
	public void deleteById(String id) {
		deleteById(Integer.parseInt(id));
	}
	
	public void update(T entity){
		try{
		//getSession().update(entity);
			getSession().merge(entity);
		}catch(Exception e){
			logger.error("Error on update("+entity.toString()+") "+e.getStackTrace());
			e.getStackTrace();
		}
	}
	
	public void saveOrUpdate(T entity){
		try{
		getSession().saveOrUpdate(entity);
		}catch(Exception e){
			logger.error("Error on update("+entity.toString()+") "+e.getStackTrace());
		}
	}

	
	
	 public Long countAll(){
		 Criteria criteria = createEntityCriteria();
		  return (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();

		 }
	
	
	
}
