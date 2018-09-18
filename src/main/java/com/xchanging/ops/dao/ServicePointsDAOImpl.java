package com.xchanging.ops.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.ServicePoint;
import com.xchanging.ops.model.User;
import com.xchanging.ops.utils.CommonUtils;

@Repository("servicePointsDAO")
public class ServicePointsDAOImpl extends AbstractDaoImpl<Integer, ServicePoint>  implements ServicePointsDAO{


	public Integer findUserServicePoints(User user) {
		 Query query =getSession().getNamedQuery("ServicePoint.findServicePoint");  
		 query.setEntity("user", user);
		 query.setBoolean("credited", true);
		 Long val =(Long)query.uniqueResult();
		 Integer intVal =null;
		 if(null!=val){
			 intVal=val.intValue();
		 }
		 return intVal;
	}


	@SuppressWarnings("unchecked")
	public List<ServicePoint> findAllUserEarnedPoints() {
			String query="SELECT s.id, s.user, sum(s.point) as point, s.description, s.credited, s.created,s.createdby, s.updated, s.updatedby , s.type, s.kbid, s.kbapproved FROM ops.service_points s"
		 		+ "  where s.credited =0 and s.type=0 and kbapproved=1 group by s.user";
			SQLQuery sql = getSession().createSQLQuery(query);
			sql.addEntity(ServicePoint.class);
		 return (List<ServicePoint>)sql.list();

	}
	
	@SuppressWarnings("unchecked")
	public List<ServicePoint> findAllUserSPList() {
			String query="SELECT s.id, s.user, sum(s.point) as point, s.description, s.credited, s.created,s.createdby, s.updated, s.updatedby , s.type , s.kbid, s.kbapproved "
					+ "FROM ops.service_points s where s.credited =1 group by s.user";
			SQLQuery sql = getSession().createSQLQuery(query);
			sql.addEntity(ServicePoint.class);
		 return (List<ServicePoint>)sql.list();

	}


	public void updateServicePoints(User user) {
		String UPDATE_SERVICE_POINT_BY_USER = "update ops.service_points s set s.updated=:updated, s.updatedby=:updatedby,"
				+ "s.credited=:credited where s.user=:user";
		SQLQuery query = getSession().createSQLQuery(UPDATE_SERVICE_POINT_BY_USER);  
		 query.setDate("updated", new Date());
		 query.setEntity("updatedby", CommonUtils.getCurrentUser());
		 query.setBoolean("credited", true);
		 query.setEntity("user", user);
		 query.executeUpdate();
	}
	

	public void appendAllServicePoints() {
		String UPDATE_SERVICE_POINT_BY_USER = "update ops.service_points s set s.updated=:updated, s.updatedby=:updatedby,"
				+ "s.credited=:credited where s.credited =0 and s.type=0";
		SQLQuery query = getSession().createSQLQuery(UPDATE_SERVICE_POINT_BY_USER);  
		 query.setDate("updated", new Date());
		 query.setEntity("updatedby", CommonUtils.getCurrentUser());
		 query.setBoolean("credited", true);
		 query.executeUpdate();
	}

	
	@SuppressWarnings("unchecked")
	public List<ServicePoint> findDraftPoints() {
			String query="SELECT s.id, s.user, s.point, s.description, s.credited, s.createdby, s.created, s.updated, s.updatedby, s.type, s.kbid, s.kbapproved FROM ops.service_points s"
		 		+ "  where s.credited =0 and s.type=1";
			SQLQuery sql = getSession().createSQLQuery(query);
			sql.addEntity(ServicePoint.class);
		 return (List<ServicePoint>)sql.list();

	}
	
	

	public void updateAllServicePoints() {
		String UPDATE_SERVICE_POINT = "update ops.service_points s set s.updated=:updated, s.updatedby=:updatedby,"
				+ "s.credited=:credited where s.type=1";
		SQLQuery query = getSession().createSQLQuery(UPDATE_SERVICE_POINT);  
		 query.setDate("updated", new Date());
		 query.setEntity("updatedby", CommonUtils.getCurrentUser());
		 query.setBoolean("credited", true);
		 query.executeUpdate();
	}
	
	public void kbApproved(Integer id){
		String UPDATE_SQL = "update ops.service_points s set s.kbapproved=:kbapproved, s.updated=:updated, s.updatedby=:updatedby "
				+ "where s.kbid=:kbid";
		SQLQuery query = getSession().createSQLQuery(UPDATE_SQL);  
		 query.setDate("updated", new Date());
		 query.setEntity("updatedby", CommonUtils.getCurrentUser());
		 query.setBoolean("kbapproved", true);
		 query.setInteger("kbid", id);
		 query.executeUpdate();
	}
	
	public List<ServicePoint> findByUserId(User user){
		org.hibernate.Criteria crit = createEntityCriteria();
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("user", user));
		crit.add(Restrictions.eq("credited", true));
		return crit.list();
	}
	
}
