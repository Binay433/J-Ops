package com.xchanging.ops.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xchanging.ops.model.EmergencyChange;
import com.xchanging.ops.model.EmergencyChangeComponent;
import com.xchanging.ops.model.Environment;
import com.xchanging.ops.model.RefData;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.service.ChangeService;
import com.xchanging.ops.service.EnvironmentService;
import com.xchanging.ops.service.OpsService;
import com.xchanging.ops.service.RefDataService;
import com.xchanging.ops.service.UserProfileService;
import com.xchanging.ops.utils.CommonUtils;
import com.xchanging.ops.utils.Constants;



@Controller
@RequestMapping("/change")
@SessionAttributes("serviceModels")
public class ChangeController {
	private static Logger logger = LoggerFactory.getLogger(ChangeController.class);
	@Autowired
	ChangeService changeService;
	
	@Autowired
	RefDataService refDataService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	OpsService opservice ;
	
	@Autowired
	EnvironmentService environmentService ;
	
	@Autowired
	MessageSource messageSource;
	
/*	@Autowired
	UserService userService;*/
	
	/**
	 * This method will list all existing Changes.
	 */
	@RequestMapping(value = {"/list-{serviceid}" }, method = RequestMethod.GET)
	public String listChanges(@PathVariable Integer serviceid, ModelMap model) {
		List<EmergencyChange> changeList = null;
		//ArrayList<Date> prodDateList;
		Date today = new Date();
		int year = 1900+today.getYear();
		if(serviceid==null){
			changeList = changeService.findAll();
		}else{
		changeList = changeService.findByServiceId(serviceid, ""+year);
		}
		//prodDateList = 
				changeService.findLatestDate(changeList);
		
		for(int i=0;i<changeList.size();i++){
			//changeList.get(i).setStatusNo(Integer.parseInt(changeList.get(i).getStatus()));
			int statusCode = Integer.parseInt(changeList.get(i).getStatus());
			//if((statusCode>0 && statusCode <5) && (null!= changeList.get(i).getLatestProdDate() && changeList.get(i).getLatestProdDate().before(new Date()))){
			if((statusCode>0 && statusCode <5) && (null!= changeList.get(i).getLatestProdDate() && CommonUtils.isBeforeToday(changeList.get(i).getLatestProdDate()))){
				changeList.get(i).setDateColor("#FFCC99");
			}
			changeList.get(i).setStatus(refDataService.getStatusMap(refDataService.findByCode("change_status")).get(changeList.get(i).getStatus()));
			
		}
		model.addAttribute("changes", changeList);
		return "changeDetails";
	}

	

	@RequestMapping(value = {"/listbystatus-{status}-{year}" }, method = RequestMethod.GET)
	public String listByStatus(@PathVariable String status,@PathVariable String year, ModelMap model) {
	
		List<EmergencyChange> changeList = changeService.findByStatusAndYear(status, ""+year);
		changeService.findLatestDate(changeList);
		for(int i=0;i<changeList.size();i++){
			int statusCode = Integer.parseInt(changeList.get(i).getStatus());
			if((statusCode>0 && statusCode <5) && (null!= changeList.get(i).getLatestProdDate() && CommonUtils.isBeforeToday(changeList.get(i).getLatestProdDate()))){
				changeList.get(i).setDateColor("#FFCC99");
			}
			changeList.get(i).setStatus(refDataService.getStatusMap(refDataService.findByCode("change_status")).get(changeList.get(i).getStatus()));
		}
		model.addAttribute("changes", changeList);
		return "changeDetails";
	}
	
	@RequestMapping(value = {"/listbymonyearserv-{month}-{year}-{service}" }, method = RequestMethod.GET)
	public String listByMonYearService(@PathVariable String month,@PathVariable String year,@PathVariable Integer service, ModelMap model) {
	
		List<EmergencyChange> changeList = changeService.findByServiceMonthYear(service, month, year);
		changeService.findLatestDate(changeList);
		for(int i=0;i<changeList.size();i++){
			int statusCode = Integer.parseInt(changeList.get(i).getStatus());
			if((statusCode>0 && statusCode <5) && (null!= changeList.get(i).getLatestProdDate() && CommonUtils.isBeforeToday(changeList.get(i).getLatestProdDate()))){
				changeList.get(i).setDateColor("#FFCC99");
			}
			changeList.get(i).setStatus(refDataService.getStatusMap(refDataService.findByCode("change_status")).get(changeList.get(i).getStatus()));
		}
		model.addAttribute("changes", changeList);
		return "changeDetails";
	}
	
	@RequestMapping(value = {"/listbymonyearstatus-{month}-{year}-{status}" }, method = RequestMethod.GET)
	public String listByMonYearStatus(@PathVariable String month,@PathVariable String year,@PathVariable String status, ModelMap model) {
	
		List<EmergencyChange> changeList = changeService.findByStatusYearMonth(status, year, month);
		changeService.findLatestDate(changeList);
		for(int i=0;i<changeList.size();i++){
			int statusCode = Integer.parseInt(changeList.get(i).getStatus());
			if((statusCode>0 && statusCode <5) && (null!= changeList.get(i).getLatestProdDate() && CommonUtils.isBeforeToday(changeList.get(i).getLatestProdDate()))){
				changeList.get(i).setDateColor("#FFCC99");
			}
			changeList.get(i).setStatus(refDataService.getStatusMap(refDataService.findByCode("change_status")).get(changeList.get(i).getStatus()));
		}
		model.addAttribute("changes", changeList);
		return "changeDetails";
	}
	
	/**
	 * This method will list all existing Changes.
	 */
	@RequestMapping(value = {"/summary-{year}" }, method = RequestMethod.GET)
	public String changesSummary(@PathVariable Integer year,ModelMap model) {
		String yearStr =""+(new Date().getYear()+1900);
		if(year!=null){
			yearStr=""+year;
		}
		List<RefData> statusList =refDataService.findByCode("change_status");
		String[] months =CommonUtils.monthArray();
		List<List<String[]>> statusRows = new ArrayList<List<String[]>>();
		List<String[]> monthRows =null;
		
		for(int i=1; i<=months.length; i++){
			monthRows= new ArrayList<String[]>();
			String[] monthName =new String[1];
			monthName[0]=months[i-1];
			monthRows.add(monthName);
			String[] monthData;
				for(RefData status:statusList){
					List<EmergencyChange> list = changeService.findByStatusYearMonth(status.getKey(), yearStr, i+"");
					//if(list.size()>0){
						monthData =new String[4];
						monthData[0]=status.getValue();
						String count ="";
						if(list.size()>0)
						{
							count=list.size()+"";
						}
						monthData[1]=count;
						monthData[2]=status.getKey();
						monthData[3]=i+"";
						monthRows.add(monthData);
					//}
			}
				statusRows.add(monthRows);
		}
		model.addAttribute("statusRows",statusRows);
		model.addAttribute("months",CommonUtils.monthArray());
		model.addAttribute("year",yearStr);
		return "changeSummary";
	}
	
	
	/**
	 * This method will list all existing Changes.
	 */
	@RequestMapping(value = {"/servicesummary-{year}" }, method = RequestMethod.GET)
	public String serviceSummary(@PathVariable Integer year,ModelMap model) {
		String yearStr =""+(new Date().getYear()+1900);
		if(year!=null){
			yearStr=""+year;
		}
		String[] months =CommonUtils.monthArray();
		List<RefData> statusList =refDataService.findByCode("change_status");
		List<ServiceModel> services =opservice.findAll();
		List<String[]> serviceRow = null;
		List<List<String[]>> serviceRows = new ArrayList<List<String[]>>();
		for(ServiceModel serviceModel:services){
			serviceRow = new ArrayList<String[]>();
			String[] service =new String[3];
			service[0]="";
			service[1]=serviceModel.getId()+"";
			service[2]=serviceModel.getName();
			serviceRow.add(service);
			int changeForYear=0;
			for(int i=1; i<=months.length; i++){
				List<EmergencyChange> changes=changeService.findByServiceMonthYear(serviceModel.getId(), ""+i, yearStr);
				int monthChanges=changes.size();
				changeForYear+=monthChanges;
				String[] serviceMonth =new String[3];
				if(monthChanges > 0){
					serviceMonth[0]=i+"";
					serviceMonth[1]=serviceModel.getId()+"";
					serviceMonth[2]=monthChanges+"";
					serviceRow.add(serviceMonth);
				}else{
					serviceRow.add(serviceMonth);
				}
			}
			if(changeForYear > 0){
				String[] serviceTot =new String[3];
				serviceTot[0]="";
				serviceTot[1]=serviceModel.getId()+"";
				serviceTot[2]=changeForYear+"";
				serviceRow.add(serviceTot);
				serviceRows.add(serviceRow);				
			}
			serviceRow=null;
		}
		
		List<String[]> statusRow = new ArrayList<String[]>();
		
		int total =0;
		String[] statusData;
		for(String s:Constants.SUMMARY_CHANGE_STATUS){
			for(RefData status:statusList){
					if(s.equals(status.getKey())){	
						statusData = new String[3];
						List<EmergencyChange> changes=changeService.findByStatusAndYear(status.getKey(), yearStr);
						if(changes.size() >0){
							statusData[0]=status.getKey();
							statusData[1]=status.getValue();
							statusData[2]=changes.size()+"";				
							statusRow.add(statusData);
							total+=changes.size();
					}
				}
			}
		}
		model.addAttribute("total",total);
		model.addAttribute("statusRow",statusRow);
		model.addAttribute("serviceRows",serviceRows);
		model.addAttribute("months",months);
		model.addAttribute("year",yearStr);
		return "changeServiceSummary";
	}
	
	/**
	 * This method will provide the medium to add a new change Request.
	 */
	@RequestMapping(value = { "/newchange-{id}" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('OPS')")
	public String newChangeRequest(@PathVariable Integer id, ModelMap model) {
			EmergencyChange change=null;
				if(id==null){
					change = new EmergencyChange();
					ArrayList<String> hourList = changeService.getHour();
					ArrayList<String> minutesList = changeService.getMinutesList();
					change.setEmailTo((String)System.getProperty("change_email_to"));
					change.setEmailCc((String)System.getProperty("change_email_cc"));
					change.setRcmMail((String)System.getProperty("change_email_rcm"));
					change.setHourList(hourList);
					change.setMinutesList(minutesList);
					change.setStatusList(refDataService.findByCode("change_status"));

				}else{
					change=findChange(id);
					change.setCrqRef(null);
					change.setId(null);
				}
				change.setSaveOnly(true);
				model.addAttribute("change", change);
				model.addAttribute("edit", false);
		return "newchangerequest";
	}

	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving form in database. It also validates the user input
	 */
	
	@RequestMapping(value = { "/newchange-{id}" }, method = RequestMethod.POST)
	public String saveEmergencyChange(@Valid EmergencyChange change, BindingResult result,
			ModelMap model,@PathVariable Integer id) {
		
		String sysComponent = StringUtils.EMPTY;
		List<EmergencyChangeComponent> compList = change.getEmergencyChangeComponent();
		for(int i=0;i<change.getEmergencyChangeComponent().size();i++){
			sysComponent = compList.get(i).getSystemComponent();
			if(sysComponent.equalsIgnoreCase(StringUtils.EMPTY)){
				change.getEmergencyChangeComponent().remove(i);
			}
		}
		
		if (result.hasErrors()) {
			change.setRequestBy(CommonUtils.getCurrentUser());
			change.setRequestDate(new Date());
			change.setChangeTime(change.getHour()+":"+change.getMinute());
			// Setting Time for different Environments
			for(EmergencyChangeComponent compChange : change.getEmergencyChangeComponent()){
				
				compChange.setTsaTime(compChange.getTsahour()+":"+compChange.getTsaminute());
				compChange.setTsbTime(compChange.getTsbhour()+":"+compChange.getTsbminute());
				compChange.setTscTime(compChange.getTschour()+":"+compChange.getTscminute());
				compChange.setTrnTime(compChange.getTrnhour()+":"+compChange.getTrnminute());
				compChange.setPrdTime(compChange.getPrdhour()+":"+compChange.getPrdminute());
				compChange.setDreTime(compChange.getDrehour()+":"+compChange.getDreminute());
				compChange.setTsdTime(compChange.getTsdhour()+":"+compChange.getTsdminute());
			}
			ArrayList<String> hourList = changeService.getHour();
			ArrayList<String> minutesList = changeService.getMinutesList();
			change.setHourList(hourList);
			change.setMinutesList(minutesList);
			change.setSaveOnly(true);
			model.addAttribute("change", change);
			return "newchangerequest";
		}
		/*if(!change.getAdditionalCc().isEmpty())
		{
			change.setEmailCc(change.getEmailCc().concat(",").concat(change.getAdditionalCc()));
		}*/
		if(change.getBloker() == null || change.getBloker().toString().isEmpty()){
			change.setBloker(0);
		}
		if(change.getCritical() == null || change.getCritical().toString().isEmpty()){
			change.setCritical(0);
		}
		change.setRequestBy(CommonUtils.getCurrentUser());
		change.setRequestDate(new Date());
		change.setChangeTime(change.getHour()+":"+change.getMinute());
		// Setting Time for different Environments
		for(EmergencyChangeComponent compChange : change.getEmergencyChangeComponent()){
			
			compChange.setTsaTime(compChange.getTsahour()+":"+compChange.getTsaminute());
			compChange.setTsbTime(compChange.getTsbhour()+":"+compChange.getTsbminute());
			compChange.setTscTime(compChange.getTschour()+":"+compChange.getTscminute());
			compChange.setTrnTime(compChange.getTrnhour()+":"+compChange.getTrnminute());
			compChange.setPrdTime(compChange.getPrdhour()+":"+compChange.getPrdminute());
			compChange.setDreTime(compChange.getDrehour()+":"+compChange.getDreminute());
			compChange.setTsdTime(compChange.getTsdhour()+":"+compChange.getTsdminute());
		}
		
		change.setEmergency_No("E001");
		//change.setStatus(refDataService.getStatusMap(refDataService.findByCode("change_status")).get(key));
		
		changeService.save(change);
		if(change.getId()!=null){
			change.setEmergency_No("E00"+change.getId());
		}
		
		//changeService.update(change);
		if(!change.isSaveOnly() && !"0".equals(change.getStatus())){
			logger.info("sending mail");
			changeService.sendMailWithEmailTemplate(change);
		}
		
		//model.addAttribute("success", "Service " + component.getName() + " registered successfully");
		//return "success";
		return "redirect:/change/list-";
	}
	
	/**
	 * This method will provide the medium to update an existing comcponent.
	 */
	@RequestMapping(value = { "/edit-changerequest-{id}" }, method = RequestMethod.GET)
	public String editChangeRequest(@PathVariable Integer id, ModelMap model) {
		
		EmergencyChange editchange =findChange(id);
		model.addAttribute("change", editchange);
		model.addAttribute("cStatus", editchange.getStatus());
		model.addAttribute("edit", true);
		return "newchangerequest";
	}
	
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating component in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-changerequest-{id}" }, method = RequestMethod.POST)
	public String updateChangeRequest(@Valid EmergencyChange change, BindingResult result,
			ModelMap model, @PathVariable Integer id) {

		if (result.hasErrors()) {
			return "newchangerequest";
		}
		
		ArrayList<String> hourList = changeService.getHour();
		ArrayList<String> minutesList = changeService.getMinutesList();
		change.setHourList(hourList);
		change.setMinutesList(minutesList);
		change.setRequestBy(CommonUtils.getCurrentUser());
		change.setRequestDate(new Date());
		change.setChangeTime(change.getHour()+":"+change.getMinute());
		
		for(EmergencyChangeComponent compChange : change.getEmergencyChangeComponent()){
			
			compChange.setTsaTime(compChange.getTsahour()+":"+compChange.getTsaminute());
			compChange.setTsbTime(compChange.getTsbhour()+":"+compChange.getTsbminute());
			compChange.setTscTime(compChange.getTschour()+":"+compChange.getTscminute());
			compChange.setTrnTime(compChange.getTrnhour()+":"+compChange.getTrnminute());
			compChange.setPrdTime(compChange.getPrdhour()+":"+compChange.getPrdminute());
			compChange.setDreTime(compChange.getDrehour()+":"+compChange.getDreminute());
			compChange.setTsdTime(compChange.getTsdhour()+":"+compChange.getTsdminute());
		}
		
		changeService.update(change);
		
		if(change.getId()!=null){
			change.setEmergency_No("E00"+change.getId());
		}
		if(!change.isSaveOnly() && !"0".equals(change.getStatus())){
			changeService.sendMailWithEmailTemplate(change);
		}
		
		//model.addAttribute("success", "component " + change.getCrqRef()+ " updated successfully");
		return "redirect:/change/list-";
	}
	

	public EmergencyChange findChange(Integer id) {
		EmergencyChange editchange = changeService.findById(id);
		editchange.setEmailTo((String)System.getProperty("change_email_to"));
		editchange.setEmailCc((String)System.getProperty("change_email_cc"));
		ArrayList<String> hourList = changeService.getHour();
		ArrayList<String> minutesList = changeService.getMinutesList();
		editchange.setHourList(hourList);
		editchange.setMinutesList(minutesList);
		ArrayList<EmergencyChangeComponent> editChangeCompList = new ArrayList<EmergencyChangeComponent>();
		editChangeCompList.addAll(changeService.findbyEmergencyChangeId(id));
		
		for(EmergencyChangeComponent compChange : editChangeCompList){
			
			compChange.setTsahour(compChange.getTsaTime().substring(0, 2));
			compChange.setTsaminute(compChange.getTsaTime().substring(3, 5).trim());
			compChange.setTsbhour(compChange.getTsbTime().substring(0, 2));
			compChange.setTsbminute(compChange.getTsbTime().substring(3, 5).trim());
			compChange.setTschour(compChange.getTscTime().substring(0, 2));
			compChange.setTscminute(compChange.getTscTime().substring(3, 5).trim());
			compChange.setTrnhour(compChange.getTrnTime().substring(0, 2));
			compChange.setTrnminute(compChange.getTrnTime().substring(3, 5).trim());
			compChange.setPrdhour(compChange.getPrdTime().substring(0, 2));
			compChange.setPrdminute(compChange.getPrdTime().substring(3, 5).trim());
			compChange.setDrehour(compChange.getDreTime().substring(0, 2));
			compChange.setDreminute(compChange.getDreTime().substring(3, 5).trim());
			compChange.setTsdhour(compChange.getTsdTime().substring(0, 2));
			compChange.setTsdminute(compChange.getTsdTime().substring(3, 5).trim());
		}
		
		editchange.setEmergencyChangeComponent(editChangeCompList);
		editchange.setHour(editchange.getChangeTime().substring(0, 2));
		editchange.setMinute(editchange.getChangeTime().substring(3, 5).trim());
		editchange.setSaveOnly(true);
		editchange.setStatusList(refDataService.findByCode("change_status"));
		return editchange;
	}
	
	@RequestMapping(value = { "/delete-change-{id}" }, method = RequestMethod.GET)
	public String deleteChangeRequest(@PathVariable Integer id) {
		changeService.deleteById(id);
		return "redirect:/change/list-";
	}
	
	@RequestMapping(value = { "/sendapprovalmail" }, method = RequestMethod.POST)
	public String sendApprovalMail(@Valid EmergencyChange sendChange, BindingResult result,
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "newchangerequest";
		}
		
		
		return "redirect:/change/list-";
	}
	
	/**
	 * This method will provide Account list to views
	 */
	@ModelAttribute("serviceModels")
	public List<ServiceModel> initializeServices() {
		return opservice.findAll();
	}
	
	
	/**
	 * This method will provide Account list to views
	 */
	@ModelAttribute("environments")
	public List<Environment> initializeEnvironments() {
		return environmentService.findAll();
	}
	

}
