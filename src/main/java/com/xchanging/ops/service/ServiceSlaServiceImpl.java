package com.xchanging.ops.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.ServiceSlaDao;
import com.xchanging.ops.dao.SlaTransactionDao;
import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.model.ServiceSla;
import com.xchanging.ops.model.SlaTransaction;

@Service("serviceSlaService")
@Transactional
public class ServiceSlaServiceImpl implements ServiceSlaService{

	
	@Autowired
	private ServiceSlaDao dao;
	@Autowired
	private SlaTransactionDao transDao;
	
	public List<ServiceSla> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	public Long countAll(){
		return dao.countAll();
	}
	public List<ServiceSla> findAll(Integer ofset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(ofset, maxResults);
	}
	public List<ServiceSla> findAllOrderbyService(){
		return dao.findAllOrderbyService();
	}

	public void save(ServiceSla sla) {
		dao.save(sla);
		
	}

	public ServiceSla findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public void update(ServiceSla sla) {
		// TODO Auto-generated method stub
		ServiceSla entity = dao.findById(sla.getId());
		if(entity!=null){
			entity.setDescription(sla.getDescription());
			entity.setName(sla.getName());
			entity.setServiceModel(sla.getServiceModel());
			//entity.setServiceSlaTrackings(sla.getServiceSlaTrackings());
			if(sla.getDocId()!=null){
				entity.setDocId(sla.getDocId());				
			}
			entity.setSla_type(sla.getSla_type());
			entity.setTargetAmber(sla.getTargetAmber());
			entity.setTargetGreen(sla.getTargetGreen());
		}
		
	}
	

	public void delete(String id) {
		dao.deleteById(id);
		
	}
	
	public List<ServiceSla> findByService(ServiceModel service) {
		// TODO Auto-generated method stub
		return dao.findByService(service);
	}

	public void saveData(Date date, List<SlaTransaction> trans) {

		List<SlaTransaction> list =transDao.findByDate(date);
		
		if(list!=null && list.size() >0){
			transDao.deleteByDate(date);
		}
		for(SlaTransaction tran: trans ){
			transDao.save(tran);
		}
	}


	public List<SlaTransaction> findByWeek(String fromdate, String toate,
			ServiceSla sla) {
		// TODO Auto-generated method stub
		return transDao.findByWeek(fromdate, toate, sla);
	}
	
	public List<SlaTransaction> findByMonth(String year, String month){
		return transDao.findByMonth(year, month);
	}

	public SlaTransaction findByMonth(Date day,
			Integer service_id) {
		// TODO Auto-generated method stub
		return transDao.findByDay(day, service_id);
	}

	public List<SlaTransaction> findMonthlyCompact(String year, String month,Integer id) {
		// TODO Auto-generated method stub
		return transDao.findMonthlyCompact(year, month,id);
	}

	public String findComments(Date date, Integer slaId) {
		// TODO Auto-generated method stub
		return transDao.findComments(date, slaId);
	}

	public double findSla_averageByMonthAndId(Integer year, Integer month, Integer sla_id) {
		double slaAverage = dao.findSla_averageByMonthAndId(year,month,sla_id);
		return slaAverage;
	}

	public List<SlaTransaction> findByDate(Date date) {
		// TODO Auto-generated method stub
		return transDao.findByDate(date);
	}
	
	public Double[] findGoodDayAvg(Integer sla_id, String month, String year,
			double target) {
		// TODO Auto-generated method stub
		return transDao.findGoodDayAvg(sla_id, month, year, target);
	}
	



}
