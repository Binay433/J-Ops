package com.xchanging.ops.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xchanging.ops.dao.ChangeComponentDAO;
import com.xchanging.ops.dao.ChangeDAO;
import com.xchanging.ops.model.EmergencyChange;
import com.xchanging.ops.model.EmergencyChangeComponent;
import com.xchanging.ops.model.Environment;
import com.xchanging.ops.utils.CommonMailUtil;
import com.xchanging.ops.utils.CommonUtils;


@Service("ChangeService")
@Transactional
public class ChangeServiceImpl implements ChangeService{
	private static Logger logger = LoggerFactory.getLogger(ChangeServiceImpl.class);
	
	@Autowired
	private ChangeDAO dao;
	
	@Autowired
	RefDataService refDataService;
	
	@Autowired
	private ChangeComponentDAO componentDao;
	
	private static CommonMailUtil cmnMailutil;

	public EmergencyChange findByCRQRef(String crqRef) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EmergencyChange> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	public List<EmergencyChange> findAll(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return dao.findAll(offset, maxResults);
	}
	public void save(EmergencyChange eChange) {
		dao.save(eChange);
		for(EmergencyChangeComponent compChange : eChange.getEmergencyChangeComponent()){
			compChange.setEmergencyChage(eChange);
			if(compChange.getSystemComponent().isEmpty() || compChange.getSystemComponent().equalsIgnoreCase(""))
			{
				System.out.println("No Details have been filled for this component row");
			}
			else
			{
				compChange.setId(null);
				componentDao.save(compChange);
			}
			
		}
		
	}
	
	public List<EmergencyChangeComponent> findbyEmergencyChangeId(Integer id) {
		// TODO Auto-generated method stub
		return componentDao.findByChangeId(id);
	}
	
/*	public Integer findTotalEmergencyChanges() {
		// TODO Auto-generated method stub
		return dao.findTotalEmergencyChanges();
	}*/
	
	public EmergencyChange findById(Integer id) {
		
		return dao.findById(id);
	}

	public void update(EmergencyChange eChange) {
		// TODO Auto-generated method stub
		EmergencyChange editchange = findById(eChange.getId());
		eChange.setRequestBy(editchange.getRequestBy());
		eChange.setRequestDate(editchange.getRequestDate());
		dao.update(eChange);
		
		Iterator<EmergencyChangeComponent> itr = eChange.getEmergencyChangeComponent().iterator();
		while(itr.hasNext()){
			EmergencyChangeComponent compChange=itr.next();
			compChange.setEmergencyChage(eChange);
			if(compChange.getSystemComponent().isEmpty() || compChange.getSystemComponent().equalsIgnoreCase(""))
			{
				logger.info("No Details have been filled for this component row");
				if(compChange.getId()!=null){
					componentDao.deleteById(compChange.getId());
				}
				itr.remove();
			}
			else
			{
				if(compChange.getId()==null){
					componentDao.save(compChange);
				}else{
				componentDao.update(compChange);
				}
			}
		}
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}
	
	public void sendMailWithEmailTemplate(EmergencyChange EChange)
	{
		HashMap<String, String> mailproperties = new HashMap<String, String>();
		HashMap<String, String> mailData = new HashMap<String, String>();
		//HashMap<String, ArrayList<String>> changeCompData = new HashMap<String, ArrayList<String>>();
		//Integer emergencyCount = findTotalEmergencyChanges();
		//String.valueOf(findTotalEmergencyChanges().intValue());
		
		//Integer count = findTotalEmergencyChanges().toString();
		//mailproperties.put("mailfrom", RefData.getAppProps().get("change_email_from").toString());
		//mailproperties.put("mailfrom", "Atul.Bhadauria@xchanging.com");
		mailproperties.put("mailfrom", "XISSupportIndia@xchanging.com");
		if(1==Integer.parseInt(EChange.getStatus())){
			mailproperties.put("mailInTo", EChange.getRcmMail());
			mailproperties.put("mailInCc", EChange.getAdditionalCc());
			mailData.put("{{address_to}}", System.getProperty("address_to_rcm"));
		}else{
			mailproperties.put("mailInTo", EChange.getEmailTo());
			mailproperties.put("mailInCc", EChange.getEmailCc().concat(",").concat(EChange.getRcmMail()));
			mailData.put("{{address_to}}", System.getProperty("address_to_heads"));
			if(!EChange.getAdditionalCc().isEmpty())
			{
				mailproperties.put("mailInCc", EChange.getEmailCc().concat(",").concat(EChange.getAdditionalCc()));
			}
		}
		mailproperties.put("mailInBCC", "");

		String statusStr=refDataService.getStatusMap(refDataService.findByCode("change_status")).get(EChange.getStatus());
		//mailproperties.put("subject", EChange.getChangeCategory()+" Emergency Release : "+EChange.getCrqRef()+","+EChange.getIncidenceRef()+"-"+statusStr);
		mailproperties.put("subject",EChange.getCrqRef()+"-"+EChange.getChangeCategory()+"- "+ EChange.getSubjectline()+" ("+EChange.getIncidenceRef()+")- "+statusStr);

		mailData.put("{{ref_change_remarks}}", EChange.getRemarks());
		mailData.put("{{ref_id_CRQ}}", EChange.getCrqRef());
		mailData.put("{{ref_id_Incident}}", EChange.getIncidenceRef());
		mailData.put("{{ref_id_RCM Lead}}", EChange.getRcmLead());
		mailData.put("{{ref_id_Requestor}}", EChange.getChangeRequestor());
		mailData.put("{{ref_id_Manager}}", EChange.getSystemManager());
		mailData.put("{{ref_id_Summary}}", EChange.getDescription());
		mailData.put("{{ref_id_Impact}}", EChange.getImpactAssesment());
		mailData.put("{{ref_id_Urgency}}", EChange.getUrgencyReason());
		mailData.put("{{ref_id_Risk}}", EChange.getRiskMitigation());
		mailData.put("{{ref_id_Customer}}", EChange.getCustomerAffected());
		mailData.put("{{ref_id_Communication}}", EChange.getCommunication());
		mailData.put("{{ref_id_Backout}}", EChange.getBackoutPlan());
		mailData.put("{{ref_change_no}}", EChange.getEmergency_No());
		//mailData.put("{{ref_change_no}}", "E0001");
		mailData.put("{{ref_change_category}}", EChange.getChangeCategory());
		mailData.put("{{ref_change_status}}", statusStr);
		mailData.put("{{align_to}}", covertArrayToString(EChange.getEnvironments()));
		mailData.put("{{ref_critical}}", EChange.getCritical().toString());
		mailData.put("{{ref_blocker}}", EChange.getBloker().toString());
		int status = Integer.parseInt(EChange.getStatus());
		String template ="change_mail_template";
		if(status>3){
			template="change_mail_template_2";
		}
		
		mailData.put("{{mail_template}}", template);
		
		

		mailData.put("{{rows}}", generateChangeComponentRows(EChange.getEmergencyChangeComponent()));
		
		try{
		cmnMailutil.sendMail(mailproperties, mailData);
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
	}
	
	public String generateChangeComponentRows(List<EmergencyChangeComponent> changeCompList){
		ArrayList<String> rows = new ArrayList<String>();
		String row="";
		String trs="";
		Iterator<EmergencyChangeComponent> entries = changeCompList.iterator();
		
		while(entries.hasNext()){
			EmergencyChangeComponent changeComp = entries.next();
			
			if(null!=changeComp && changeComp.getId()!=null && changeComp.getId()>0){
				row="<tr><td>"+WordUtils.wrap(changeComp.getSystemComponent(), 30, "<br/>\n", true)+"</td>"
						+ "<td>"+WordUtils.wrap(changeComp.getSystemPackage(), 30, "<br/>\n", true)+"</td>"
								+ "<td>"+CommonUtils.returnDateTime(changeComp.getTsaDate(), changeComp.getTsaTime())+"</td>"
									+ "<td>"+CommonUtils.returnDateTime(changeComp.getTsbDate(), changeComp.getTsbTime())+"</td>"
											+ "<td>"+CommonUtils.returnDateTime(changeComp.getTscDate(), changeComp.getTscTime())+"</td>"
													+ "<td>"+CommonUtils.returnDateTime(changeComp.getTrnDate(), changeComp.getTrnTime())+"</td>"
														+ "<td>"+CommonUtils.returnDateTime(changeComp.getPrdDate(), changeComp.getPrdTime())+"</td>"
															+ "<td>"+CommonUtils.returnDateTime(changeComp.getDreDate(), changeComp.getDreTime())+"</td>"
																	+ "<td>"+CommonUtils.returnDateTime(changeComp.getTsdDate(), changeComp.getTsdTime())+"</td></tr>";
								
			rows.add(row);
				
			}
		}
		for(String mailrow: rows){
			
			trs+=mailrow;
		}
		
		return trs;
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<String> getHour(){
		
		ArrayList<String> hourList = new ArrayList<String>();
		for(int i=0; i<24; i++){
			String strNumber = ""+i;
			if(i < 10){
				strNumber="0"+strNumber;
			}
			hourList.add(strNumber);
		}
		return hourList;
	}
	
public ArrayList<String> getMinutesList(){
		
		String s1 = "00";
		String s2 = "10";
		String s3 = "20";
		String s4 = "30";
		String s5 = "40";
		String s6 = "50";
		
		
		
		ArrayList<String> minutesList = new ArrayList<String>();
		minutesList.add(s1);
		minutesList.add(s2);
		minutesList.add(s3);
		minutesList.add(s4);
		minutesList.add(s5);
		minutesList.add(s6);
		
		
		return minutesList;
	}

public List<EmergencyChange> findByServiceId(int id,String yearId) {
	return dao.findByServiceId(id,yearId);
}


private String covertArrayToString(Set<Environment> arrayString){
	String str ="";
	if(null!=arrayString){
		for(Environment s: arrayString){
			if("".equals(str)){
				str=s.getName();
			}else{
				str+=", "+s.getName();
			}
		}		
	}
	return str;
}


public List<EmergencyChange> findByServiceMonthYear(Integer service, String month, String year) {
	// TODO Auto-generated method stub
	return dao.findByServiceMonthYear(service, month, year);
}


public List<EmergencyChange> findByStatusAndYear(String status, String year) {
	// TODO Auto-generated method stub
	return dao.findByStatusAndYear(status, year);
}


public List<EmergencyChange> findByStatusYearMonth(String status, String year,
		String month) {
	// TODO Auto-generated method stub
	return dao.findByStatusYearMonth(status, year, month);
}

public void findLatestDate(List<EmergencyChange> changeList){
/*	ArrayList<List<EmergencyChangeComponent>> changeCompList = new ArrayList<List<EmergencyChangeComponent>>();
	ArrayList<Date> changeProdDateList = new ArrayList<Date>();
	ArrayList<Date> prodDateList = new ArrayList<Date>();
*/	
	/*for(int i=0;i<changeList.size();i++){
		changeCompList.add(changeList.get(i).getEmergencyChangeComponent());
	}
	for(int j=0;j<changeCompList.size();j++){
		
		for(int k=0;k<changeCompList.get(j).size();k++){
			if(null != changeCompList.get(j).get(k).getPrdDate()){
				prodDateList.add(changeCompList.get(j).get(k).getPrdDate());
			}
		}
		if(prodDateList.size() != 0){
			Collections.sort(prodDateList);
			changeProdDateList.add(prodDateList.get(prodDateList.size()-1));
		}
		
		prodDateList.clear();
	}*/
	
	for(EmergencyChange change :changeList){
		Date prodDate = null;
		
		List<EmergencyChangeComponent> compList =findbyEmergencyChangeId(change.getId());
		for(EmergencyChangeComponent comp:compList){
			if(prodDate==null || (null!=comp.getPrdDate() && prodDate.before(comp.getPrdDate()))){
				prodDate=comp.getPrdDate();
			}
		}
		change.setLatestProdDate(prodDate);
	}

}

public Long countAll() {
	// TODO Auto-generated method stub
	return dao.countAll();
}

}
