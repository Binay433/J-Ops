package com.xchanging.ops.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NamedQuery;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.HealthCheckTransaction;
import com.xchanging.ops.model.KnowledgeBase;
import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.utils.CommonUtils;

@Repository("healthCheckTranDao")
public class HealthCheckTranDaoImpl extends AbstractDaoImpl<Integer, HealthCheckTransaction> implements HealthCheckTranDao{

	@SuppressWarnings("unchecked")
	public List<HealthCheckTransaction> findByDateAndHealthCheck(Date date,HealthCheck hcId) {
		 Query query =getSession().getNamedQuery("HealthCheckTransaction.findByHcIdAndDate");  
		  query.setString("entryDate", CommonUtils.getYesterday()); 
		  query.setInteger("healthCheck", hcId.getId()); 
		
		return query.list();
		
	}
	
	public List<HealthCheckTransaction> findByDateIdAndEntry(Date currentDate, Integer hcId){
		 Query query =getSession().getNamedQuery("HealthCheckTransaction.findByDateIdAndEntry");  
		  query.setString("entryDate", CommonUtils.returnDateToString(currentDate)); 
		  query.setInteger("healthCheck", hcId); 
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<HealthCheckTransaction> findByDateAndService(String date,Integer servId) {
		 Query query =getSession().getNamedQuery("HealthCheckTransaction.findByServiceAndDate");  
		  query.setString("entryDate", date); 
		  query.setInteger("serviceModel", servId); 
		
		return query.list();
		
	}
	
	
	

	@SuppressWarnings("unchecked")
	public List<HealthCheckTransaction> findWeekTrans(String fromdate, String todate,HealthCheck hcId,
			ServiceModel model, ServiceComponent comp) {
		 Query query =getSession().getNamedQuery("HealthCheckTransaction.findRowsDateWise");  
		  query.setString("fromDate", fromdate); 
		  query.setString("toDate",  todate); 
		  query.setInteger("healthCheck", hcId.getId());
		  query.setInteger("serviceModel",model.getId());
		  query.setInteger("component", comp.getId());
		  return (List<HealthCheckTransaction>)query.list();

	}
	
	
	

	@SuppressWarnings("unchecked")
	public List<HealthCheckTransaction> getMonthlyReport(String year){
	String query="SELECT * FROM Health_Check_Transaction h where h.status=3 and year(h.entryDate)=:year group by year(h.entryDate),month(h.entryDate),service_id,component_id";
	SQLQuery sql = getSession().createSQLQuery(query);
	sql.addEntity(HealthCheckTransaction.class);
	sql.setParameter("year", year);
	
	return (List<HealthCheckTransaction>)sql.list();
	}

	public List<HealthCheckTransaction> findByEntry(Integer entryId) {
		String query="SELECT * FROM Health_Check_Transaction h where h.entry_id=:entryId";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(HealthCheckTransaction.class);
		sql.setParameter("entryId", entryId);
		
		return (List<HealthCheckTransaction>)sql.list();
	}

	public Integer nextEntryId() {
		String query="SELECT * FROM ops.Health_Check_Transaction where entry_id in(SELECT MAX(entry_id) from ops.Health_Check_Transaction) ";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(HealthCheckTransaction.class);
		List<HealthCheckTransaction> list = sql.list();
		Integer nextCount = 1;
		if(list!=null && list.size() > 0 && list.get(0)!=null){
			nextCount=list.get(0).getEntryId();
		}
		return nextCount+1;
	}


	public ModelMap findWithoutKb(ModelMap model) {
		org.hibernate.Criteria crit = createEntityCriteria();
		crit.add(Restrictions.isNull("kbId"));
		crit.add(Restrictions.between("status", 2, 3));
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<HealthCheckTransaction> list = crit.list();
		model.addAttribute("count", list.size());
		model.addAttribute("list", list);
		return model;
	}
	

}
