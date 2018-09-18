package com.xchanging.ops.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.ServicePointsDAO;
import com.xchanging.ops.model.ServicePoint;
import com.xchanging.ops.model.User;
import com.xchanging.ops.utils.CommonMailUtil;

@Service("servicePointsService")
@Transactional
public class ServicePointsServiceImpl implements ServicePointsService{
	
	 private static Logger logger = LoggerFactory.getLogger(ServicePointsServiceImpl.class);

	@Autowired
	ServicePointsDAO dao;
	
	public ServicePoint findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public Long countAll() {
		// TODO Auto-generated method stub
		return dao.countAll();
	}

	public List<ServicePoint> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(offset, maxResults);
	}

	public List<ServicePoint> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	public void save(ServicePoint t) {
		// TODO Auto-generated method stub
		if(null==t.getId()){
			dao.save(t);	
		}else{
			update(t);
		}
		
		
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	public void update(ServicePoint t) {
		// TODO Auto-generated method stub
		dao.update(t);
	}

	
	public Integer findUserServicePoints(User user) {
		// TODO Auto-generated method stub
		return dao.findUserServicePoints(user);
	}
	public List<ServicePoint> findAllUserEarnedPoints(){
		return dao.findAllUserEarnedPoints();
	}

	
	public void appendServicePoints() {
		// TODO Auto-generated method stub
		
		List<ServicePoint> spList = dao.findAllUserEarnedPoints();
		
		dao.appendAllServicePoints();
		
		for(ServicePoint sp:spList){
			Map<String,String> mailProps = new HashMap<String, String>();
        	Map<String,String> transData = new HashMap<String, String>();
        	transData.put("{{points}}", sp.getPoint()+"");
        	transData.put("{{user}}", sp.getUser().getFirstName());
        	mailProps.put("mailInTo",sp.getUser().getEmail());
        	mailProps.put("subject","you have earned service points");
        	transData.put("{{mail_template}}","service_point_mail");
        	try{
        	CommonMailUtil.sendMail(mailProps, transData);
        	}catch(Exception e){
        		logger.error(e.getStackTrace().toString());
        	}
			
			
			
			
			
			
			
		}
	}

	
	public List<ServicePoint> findDraftPoints() {
		// TODO Auto-generated method stub
		return dao.findDraftPoints();
	}

	
	public void updateAllServicePoints() {
		
		List<ServicePoint> draftPoints = findDraftPoints();
		
		if(draftPoints!=null && draftPoints.size() > 0){
			Map<String,String> mailProps = new HashMap<String, String>();
        	Map<String,String> transData = new HashMap<String, String>();
        	mailProps.put("subject","Congratulations,you have earned service points");
        	transData.put("{{mail_template}}","service_point_mail");
			for(ServicePoint points :draftPoints){
	        	transData.put("{{points}}", points.getPoint()+"");
	        	transData.put("{{user}}", points.getUser().getFirstName());
	        	mailProps.put("mailInTo",points.getUser().getEmail());
	        	try {
					CommonMailUtil.sendMail(mailProps, transData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.info("Error occured while sending mail to "+points.getUser().getFirstName());
					logger.error(e.toString());
				}
			}
		}
		dao.updateAllServicePoints();
		
	}

	
	public List<ServicePoint> findAllUserSPList() {
		// TODO Auto-generated method stub
		return dao.findAllUserSPList();
	}

	
	public void kbApproved(Integer id) {
		dao.kbApproved(id);
	}

	
	public List<ServicePoint> findByUserId(User user) {
		// TODO Auto-generated method stub
		return dao.findByUserId(user);
	}

}
