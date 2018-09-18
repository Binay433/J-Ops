package com.xchanging.ops.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.ServiceSla;
import com.xchanging.ops.model.SlaTransaction;
import com.xchanging.ops.utils.CommonUtils;

@Repository
public class SlaTransactionDaoImpl extends AbstractDaoImpl<Integer, SlaTransaction> implements SlaTransactionDao {

	private static final Object[] Double = null;


	public List<SlaTransaction> findByWeek(String fromdate, String todate,
			ServiceSla serviceSla) {
		 Query query =getSession().getNamedQuery("SlaTransaction.findRowsDateWise");  
		  query.setString("fromdate", fromdate); 
		  query.setString("todate",  todate); 
		  query.setInteger("serviceSla", serviceSla.getId());
		  return (List<SlaTransaction>)query.list();
	}

	public List<SlaTransaction> findByMonth(String year, String month) {
		//String query="select id , comments, date, sum(passed_record) as 'passed_record', percent, sum(total_record) as 'total_record', updated_on, sla_id, user_id from sla_transaction where year(date)=:year and month(date)=:month group by sla_id";
		String query="select t.id, t.comments, t.date, sum(t.passed_record) as 'passed_record',"+
		"t.percent, sum(t.total_record) as 'total_record', t.updated_on, t.sla_id, t.user_id "+
		"from ops.sla_transaction t,ops.service_sla s,ops.ops_service o where year(date)=:year and month(date)=:month "+
		"and t.sla_id=s.id and s.service_id=o.id group by sla_id order by o.name";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(SlaTransaction.class);
		sql.setParameter("year", year);
		sql.setParameter("month", month);
		return (List<SlaTransaction>)sql.list();
	}
	
	public SlaTransaction findByDay(Date day,Integer sla_id) {
		
		SlaTransaction result = null;
		String query="select id , comments, date, sum(passed_record) as 'passed_record', TRUNCATE (SUM(passed_record)/ SUM(total_record),4)*100 as percent, sum(total_record) as 'total_record', updated_on, sla_id, user_id from sla_transaction where date=:entrydate and sla_id=:sla_id group by sla_id";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(SlaTransaction.class);

		sql.setParameter("entrydate", CommonUtils.returnDateToString(day));
		sql.setParameter("sla_id", sla_id);
		List<SlaTransaction> trans = sql.list();
		if(trans!=null && trans.size()>0){
			result=trans.get(0);
		}
		return result;
	}

	public List<SlaTransaction> findMonthlyCompact(String year, String month,Integer sla_id) {
		String query="select id , comments, date, sum(passed_record) as 'passed_record', TRUNCATE (SUM(passed_record)/ SUM(total_record),4)*100 as percent, sum(total_record) as 'total_record', updated_on, sla_id, user_id from sla_transaction where year(date)=:year and month(date)=:month and sla_id=:sla_id group by sla_id, date";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(SlaTransaction.class);
		sql.setParameter("year", year);
		sql.setParameter("month", month);
		sql.setParameter("sla_id", sla_id);
		return (List<SlaTransaction>)sql.list();
	}

	@SuppressWarnings("unchecked")
	public List<SlaTransaction> findByDate(Date date) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("date", date));
		return (List<SlaTransaction>)crit.list();
	}

	public void deleteByDate(Date date) {
		List<SlaTransaction> list =findByDate(date);
		for(SlaTransaction trn :list){
			getSession().delete(trn);
		}
	}

	public String findComments(Date date, Integer slaId) {
		String query="SELECT * FROM ops.sla_transaction where date=:date and sla_id=:slaId";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(SlaTransaction.class);
		sql.setParameter("date", date);
		sql.setParameter("slaId", slaId);
		List<SlaTransaction> list = (List<SlaTransaction>)sql.list();
		String comments = "";
		if(list!=null){
			for(SlaTransaction trn:list){
				if(null!=trn.getComments()){
					comments=trn.getComments();
					break;
				}
			}
		}
		return comments;
	}
	
	
	public Double[] findGoodDayAvg(Integer sla_id, String month,String year,double target) {
		String query="SELECT id,sla_id,date,avg(total_record) as total_record, "
				+ "avg(passed_record) as passed_record,percent,comments,user_id,updated_on FROM ops.sla_transaction s"
				+ " where year(date)=:year and month(date)=:month and percent >=:target and sla_id=:sla_id";
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.addEntity(SlaTransaction.class);
		sql.setParameter("year", year);
		sql.setParameter("month", month);
		sql.setParameter("sla_id", sla_id);
		sql.setParameter("target", target);
		double tot =100;
		double pas =target;
		
		List<SlaTransaction> row = sql.list();
		if(row!=null){
			if(row.size()>0){
				if(row.get(0)!=null){
					tot=row.get(0).getTotalRecord();
					pas=row.get(0).getPassedRecord();								
				}
			}
		}
		 Double[] arr = new Double[2];
		 arr[0]=tot;
		 arr[1]=pas;
		return arr;
	}

	
	
	

}
