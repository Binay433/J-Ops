package com.xchanging.ops.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.xchanging.ops.model.HealthCheck;
import com.xchanging.ops.model.HealthCheckRecord;
import com.xchanging.ops.model.HealthCheckTransaction;
import com.xchanging.ops.model.OpsDocument;
import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.service.DocumentService;
import com.xchanging.ops.service.HealthCheckAdmService;
import com.xchanging.ops.service.HealthCheckTranService;
import com.xchanging.ops.service.OpsService;
import com.xchanging.ops.service.ServiceComponentService;
import com.xchanging.ops.service.UserService;
import com.xchanging.ops.utils.CommonMailUtil;
import com.xchanging.ops.utils.CommonUtils;
import com.xchanging.ops.utils.FileValidator;

@Controller
@RequestMapping("/healthcheck")
@SessionAttributes("serviceModels")
public class HealthCheckController {
	private static Logger logger = LoggerFactory.getLogger(HealthCheckController.class);
	
	@Autowired
	HealthCheckAdmService healthService;
	
	@Autowired
	HealthCheckTranService tranService;
	
	 @Autowired  
	 FileValidator fileValidator;  
	 
	@Autowired
	OpsService opservice ;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	ServiceComponentService compservice;
	
	
	@Autowired
	UserService userService;
	
/*This method will list all the components */
	
	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String listServiceComponentService(ModelMap model) {
		List<HealthCheck> checkList = healthService.findAll();
		model.addAttribute("checkList", checkList);
		return "healthCheckList";
	}
	
	@RequestMapping(value = {"/withoutkblist" }, method = RequestMethod.GET)
	public String listTransWithoutKbComponentService(ModelMap model) {
		model =tranService.findWithoutKb(model);
		return "healthTransWithoutKb";
	}
	
	/*This method will list components of particular service */
	@RequestMapping(value = { "/listbyservice-{id}" }, method = RequestMethod.GET)
	public String listByService(@PathVariable Integer id, ModelMap model) {
		ServiceModel service =opservice.findById(id);
		List<HealthCheck> list = healthService.findAll();
		List<HealthCheck> finalList = new ArrayList<HealthCheck>();
		for(HealthCheck chk:list){
			for(ServiceModel obj:chk.getServices()){
				if(obj.getId()==service.getId()){
					finalList.add(chk);
					break;
				}
			}
		}
		model.addAttribute("checkList", finalList);
		return "healthCheckList";
	}
	
	@RequestMapping(value = {"/listhealthtrans" }, method = RequestMethod.GET)
	public String listTransactions(ModelMap model) {
		List<HealthCheckTransaction> transList =   tranService.findAll();
		model.addAttribute("transList", transList);
		return "healthCheckTransactions";
	}
	
	@RequestMapping(value = {"/detail/{servid}/{date}" }, method = RequestMethod.GET)
	public String detailServiceCheck(@PathVariable Integer servid,@PathVariable String date,ModelMap model) {
		List<HealthCheckTransaction> transList =   tranService.findByDateAndService(date,servid);
		model.addAttribute("transList", transList);
		model.addAttribute("date", date);
		return "healthCheckTransactions";
	}

	@RequestMapping(value = { "/newtrans-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String newHealthTrans(@PathVariable Integer id,ModelMap model) {
		
		HealthCheckRecord recordEntry =new HealthCheckRecord();
		HealthCheck check = healthService.findById(id);
		recordEntry.setName(check.getName());
		List<HealthCheckTransaction> trans = new ArrayList<HealthCheckTransaction>();
		
		if(check!=null && (check.getServices()!=null && check.getServices().size() >0) ){
			Iterator< ServiceModel> itr=check.getServices().iterator();
			ServiceModel obj=null;
			while(itr.hasNext()){
				obj= itr.next();
				List<ServiceComponent> comps=compservice.findByService(obj);
				if(comps!=null && comps.size()>0){
						for(ServiceComponent comp: comps){
							HealthCheckTransaction tran = new HealthCheckTransaction();
							tran.setServiceModel(obj);
							tran.setComponent(comp);
							tran.setHealthCheck(check);
							trans.add(tran);
						}
					}

			}
		}
		String comment="hidden";
		recordEntry.setEntryDate(CommonUtils.getToday());
		recordEntry.setRecords(trans);
		model.addAttribute("hcId", check.getId());
		model.addAttribute("recordEntry", recordEntry);
		model.addAttribute("comment", comment);
		model.addAttribute("edit", false);
		return "addHealthCheckData";
	}
	
	@RequestMapping(value = { "/oldrecords-{hcId}-{entryid}-{currentdate}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String oldRecord(@PathVariable Integer hcId,@PathVariable Integer entryid, 
			@PathVariable String currentdate,ModelMap model) {
		Date currentDate = null;
		if (!StringUtils.isEmpty(currentdate)) {
			currentDate = CommonUtils.stringToDate(currentdate);
		} else {
			currentDate = new Date();
		}
		
		if(entryid!=null){
			HealthCheck check = healthService.findById(hcId);
			HealthCheckRecord recordEntry =new HealthCheckRecord();
			List<HealthCheckTransaction> trans = tranService.findByEntry(entryid);
			recordEntry.setEntryDate(CommonUtils.returnDateToString(currentDate));
			recordEntry.setName(check.getName()); 
			recordEntry.setRecords(trans);
			model.addAttribute("hcId", check.getId());
			model.addAttribute("recordEntry", recordEntry);
			model.addAttribute("edit", false);
			return "addHealthCheckData";
		}else{
			List<HealthCheckTransaction> list =tranService.findByDateIdAndEntry(currentDate, hcId);
			List<List<String>> uniqCheck= new ArrayList<List<String>>();
			if(list!=null && list.size()>0)
			{
				List<String> recods = null;
				List<String> tmp = new ArrayList<String>();
				for(HealthCheckTransaction trn:list){
					 recods = new ArrayList<String>();
					
					String check =trn.getHealthCheck().getName();
					String time =trn.getCheckTime()+"";
					recods.add(check);
					recods.add(time);
					recods.add(trn.getEntryId()+"");
					recods.add(trn.getHealthCheck().getId()+"");
					if(!tmp.contains(time)){
						tmp.add(time);	
						uniqCheck.add(recods);					
					}

				}
				
			}	
			model.addAttribute("displayDate",CommonUtils.getEnglishDate(currentDate));
			model.addAttribute("currentDate",CommonUtils.returnString(currentDate));
			model.addAttribute("datestr",CommonUtils.returnDateToString(currentDate));
			model.addAttribute("size",uniqCheck.size());
			model.addAttribute("hcId",hcId);
			model.addAttribute("uniqCheck",uniqCheck);
			return "healthCheckRecords";
		}
		
		

	}
	
	@RequestMapping(value = { "/oldrecords-{hcId}-{entryid}-{currentdate}" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String updateOldRecord(@Valid HealthCheckRecord record,@PathVariable Integer hcId,@PathVariable Integer entryid, 
			@PathVariable String currentdate,ModelMap model,BindingResult result) {
		return  saveHealthTrans(record, result,model, hcId);
	}
	@RequestMapping(value = { "/newtrans-{id}" }, method = RequestMethod.POST)
	public String saveHealthTrans(@Valid HealthCheckRecord record, BindingResult result,
			ModelMap model, @PathVariable Integer id) {
		if(result.getAllErrors().size() >0){
			model.addAttribute("hcId", record.getCheck());
			model.addAttribute("recordEntry", record.getRecords());
			return "addHealthCheckData";
		}
		
		 Date time = new Date();
		 List<HealthCheckTransaction> trans = new ArrayList<HealthCheckTransaction>();
		Iterator<HealthCheckTransaction > itr = record.getRecords().iterator();
		
		String checkStatus ="OK";
		
		while(itr.hasNext()){
			HealthCheckTransaction tran = itr.next();
			if(tran.getStatus()==3){
				checkStatus="Issue";
			}
			if(tran.getStatus()==1){
				tran.setComments(null);
			}
			tran.setCheckBy(CommonUtils.getCurrentUser());
			tran.setCheckTime(time);
			tran.setEntryDate(CommonUtils.stringToDate(record.getEntryDate()));
			trans.add(tran);
		}
		
/*		List<HealthCheckTransaction> existing =tranService.findByDateAndHealthCheck(CommonUtils.stringToDate(record.getEntryDate()), record.getCheck());
		if(existing.size()>0){
			tranService.update(trans,existing);
		}else{
			tranService.save(trans);
		}*/
		tranService.save(trans);
		if("submit".equals(record.getSaveOption())){
			CommonMailUtil.sendMailWithEmailTemplate(createHelthCheckMailProps(trans,record,checkStatus), generateMailContent(System.getProperty("health_template_"+record.getCheck().getEmailType()), trans,record.getCheck().getEmailType()));
		}
		
	return "redirect:/healthcheck/list";
	}

	@RequestMapping(value = { "/datewise-{week}-{currentdate}-{id}"}, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String listDateWise(@PathVariable String week, @PathVariable String currentdate,
			@PathVariable Integer id,ModelMap model) {
		Date currentDate=null;
		if(!StringUtils.isEmpty(currentdate)){
			if(currentdate.contains("month(")){
				String year=CommonUtils.getToday().substring(0,4);
				String day=CommonUtils.getToday().substring(8,10);
				String month=currentdate.substring(currentdate.indexOf("(")+1, currentdate.indexOf(")"));
				if(month.length()<2){
					month="0"+month;
				}
				currentdate=year+"-"+month+"-"+day;
			}
			currentDate=CommonUtils.stringToDate(currentdate);
		}else{
			currentDate= new Date();
		}
		
		if(null!=week && !week.equals("")){
			if("next".equals(week)){
				currentDate = CommonUtils.getCustomDate(currentDate, 7);
			}else{
				currentDate = CommonUtils.getCustomDate(currentDate, -7);
			}
		}
	
		List<String> dates = CommonUtils.getWeekDays(currentDate);
		List<List<String>> finalList =getWeeklyData(currentDate,id,dates,1);
		
		List<String> displayDates = CommonUtils.getEnglishDates(dates);
		model.addAttribute("dates", dates);
		model.addAttribute("uidates", displayDates);
		model.addAttribute("serviceid", id);
		model.addAttribute("next", "next");
		model.addAttribute("prev", "prev");
		model.addAttribute("finalList", finalList);
		model.addAttribute("currentdate", CommonUtils.returnString(currentDate));
		return "healthCheckDateWise";
	}
	
	
	@RequestMapping(value = { "/compact-{week}-{currentdate}-{id}"}, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String listCompactView(@PathVariable String week, @PathVariable String currentdate,
			@PathVariable Integer id,ModelMap model) {
		logger.info("entered");
		Date currentDate=null;
		if(!StringUtils.isEmpty(currentdate)){
			currentDate=CommonUtils.stringToDate(currentdate);
		}else{
			currentDate= new Date();
		}
		
		if(null!=week && !week.equals("")){
			if("next".equals(week)){
				currentDate = CommonUtils.getCustomDate(currentDate, 7);
			}else{
				currentDate = CommonUtils.getCustomDate(currentDate, -7);
			}
		}
		List<String> dates = CommonUtils.getWeekDays(currentDate);
		logger.info("currentDate == "+currentDate);
		List<List<String>> finalList =getWeeklyData(currentDate,id,dates,2);
		
		logger.info("finalList size == "+finalList.size());
		
		
		List<String> displayDates = CommonUtils.getEnglishDates(dates);
		model.addAttribute("dates", dates);
		model.addAttribute("uidates", displayDates);
		model.addAttribute("serviceid", id);
		model.addAttribute("next", "next");
		model.addAttribute("prev", "prev");
		model.addAttribute("finalList", finalList);
		model.addAttribute("currentdate", CommonUtils.returnString(currentDate));
		return "healthCheckCompact";
	}
	
	
	@RequestMapping(value = { "/monthly"}, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String monthlyReport(ModelMap model) {
		logger.info("entered Monthly Report");
		
		Date today =new Date();
		String year = ""+((today).getYear()+1900);
		
		List<HealthCheckTransaction> monthlyData = tranService.getMonthlyReport(year);
		List<ServiceModel> services = opservice.findAll();
		
		
		String monthStr =""; 
		String[] monthArr =CommonUtils.monthArray();
		for(String str: monthArr){
			if("DEC".equalsIgnoreCase(str)){
			monthStr+=str;
			}else{
				monthStr+=str+",";
			}
		}

		Map<String, String> map = new Hashtable<String, String>();
/*		for(HealthCheckTransaction data: monthlyData){
				String keyName= data.getServiceModel().getName();
				map.put(keyName, data.getServiceModel().getName()+","+monthStr+","+data.getServiceModel().getId());
			
		}
*/		
		List<String> nameList = new ArrayList<String>();
		for(ServiceModel service:services){
			String keyName= service.getName();
			map.put(keyName, service.getName()+","+monthStr+","+service.getId());
			nameList.add(keyName);
		}
		//Set<String> keys =map.keySet();

		
		for(String strKey: nameList){
			for(HealthCheckTransaction trn:monthlyData){
				String row =map.get(strKey);
				String month = CommonUtils.monthArray()[trn.getEntryDate().getMonth()];
				
				if(strKey.equals(trn.getServiceModel().getName())){
					if(null!=row && row.contains(month)){
						row =row.replace(month, "ISSUE");
					}else{
						row =row.replace(month, "OK");
					}
				}
				map.put(strKey, row);
			}
		}
		
		List<List<String>> finalList = new ArrayList<List<String>>();
		for(String strKey: nameList){
			String[] rowData = map.get(strKey).split(",");
			
			int currentMonth= today.getMonth();
			List<String> tmp = new ArrayList<String>();	
			for(String str:rowData){
				if(null!=str){
					for(int i=0; i<=currentMonth; i++){
						if(monthArr[i].equalsIgnoreCase(str.trim())){
							str="OK";
						}
					}
					tmp.add(str);
				}
				
			}
			finalList.add(tmp);
		}
		model.addAttribute("months", monthArr);
		model.addAttribute("finalList", finalList);
		return "healthCheckMonthly";
	}
	
	
	
	/**
	 * This method will provide the medium to add a new component.
	 */
	@RequestMapping(value = { "/newcheck" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String newHealthCheck(ModelMap model) {
		HealthCheck check = new HealthCheck();
		model.addAttribute("check", check);
		model.addAttribute("edit", false);
		return "addHealthCheck";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving form in database. It also validates the user input
	 */
	
	@RequestMapping(value = { "/newcheck" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String saveHealthCheck(@Valid HealthCheck check, BindingResult result,
			ModelMap model) {

		  MultipartFile file = check.getFile();  
		  fileValidator.validate(file, result); 
		if (result.hasErrors()) {
			CommonUtils.arrangeErrors(result, model);
			if(model.get("emailTypeErr")!=null){
				model.addAttribute("emailTypeErr", "please select email type!");
			}
			model.addAttribute("check", check);
			model.addAttribute("edit", false);
			return "addHealthCheck";
		}
	

		if(check.getFile().getSize()!=0){
			OpsDocument document = new OpsDocument();
			try {
				document = CommonUtils.saveDocument(check.getFile(), documentService, CommonUtils.getBasicDocument("Health Check"));
				check.setDocId(document.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		healthService.save(check);

		//model.addAttribute("success", "Service " + component.getName() + " registered successfully");
		//return "success";
		return "redirect:/healthcheck/list";
	}
		
	/**
	 * This method will provide the medium to update an existing comcponent.
	 */
	@RequestMapping(value = { "/edit-healthcheck-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String editHealthCheck(@PathVariable Integer id, ModelMap model) {
		HealthCheck check = healthService.findById(id);
		model.addAttribute("check", check);
		//model.addAttribute("serviceModel", new ServiceModel());
		model.addAttribute("edit", true);
		return "addHealthCheck";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating component in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-healthcheck-{id}" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String updateHealthCheck(@Valid HealthCheck check, BindingResult result,
			ModelMap model, @PathVariable Integer id) {

		  MultipartFile file = check.getFile();  
		  fileValidator.validate(file, result); 
		if (result.hasErrors()) {
			CommonUtils.arrangeErrors(result, model);
			if(model.get("emailTypeErr")!=null){
				model.addAttribute("emailTypeErr", "please select email type!");
			}
			model.addAttribute("check", check);
			model.addAttribute("edit", true);
			return "addHealthCheck";

		}
		if(check.getFile().getSize()!=0){
			OpsDocument document = new OpsDocument();
			try {
				document = CommonUtils.saveDocument(check.getFile(), documentService, CommonUtils.getBasicDocument("Service"));
				check.setDocId(document.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		healthService.update(check);

		model.addAttribute("success", "component " + check.getName()+ " updated successfully");
		return "redirect:/healthcheck/list";
	}
		
	/**
	 * This method will provide Account list to views
	 */
	@ModelAttribute("serviceModels")
	public List<ServiceModel> initializeServices() {
		return opservice.findAll();
	}
	
	@RequestMapping(value = { "/delete-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String deleteCheck(@PathVariable Integer id) {
		healthService.deleteById(id);
		return "redirect:/healthcheck/list";
	}
	
	@RequestMapping(value = { "/deletetrn/{id}/{servid}/{date}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String deleteTransaction(@PathVariable Integer id,@PathVariable Integer servid,@PathVariable String date) {
		tranService.deleteById(id);
		return "redirect:/healthcheck/detail/"+servid+"/"+date;
	}
	
	@RequestMapping(value = { "/addkb-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String addKBtoIssue(@PathVariable Integer id,ModelMap model) {
		editTransaction(id,model);
		return "addHealthCheckData";
	}
	@RequestMapping(value = { "/addkb-{id}" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String addKBtoIssue(@Valid HealthCheckRecord record, BindingResult result,ModelMap model, @PathVariable Integer id) {
		editTrans(record, result,model, id);
		return "redirect:/healthcheck/withoutkblist";
	}
	
	@RequestMapping(value = { "/edit-trn-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String editTransaction(@PathVariable Integer id,ModelMap model) {
		HealthCheckTransaction trn=	tranService.findById(id);
		HealthCheckRecord recordEntry =new HealthCheckRecord();
		List<HealthCheckTransaction> trans = new ArrayList<HealthCheckTransaction>();
		trans.add(trn);
		recordEntry.setEntryDate(CommonUtils.returnDateToString(trn.getEntryDate()));
		recordEntry.setName(trn.getHealthCheck().getName()); 
		recordEntry.setRecords(trans);
		model.addAttribute("hcId", trn.getHealthCheck().getId());
		model.addAttribute("recordEntry", recordEntry);
		model.addAttribute("edit", true);
		return "addHealthCheckData";
	}
	
	@RequestMapping(value = { "/edit-trn-{id}" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String editTrans(@Valid HealthCheckRecord record, BindingResult result,
			ModelMap model, @PathVariable Integer id) {
		if(result.getAllErrors().size() >0){
			model.addAttribute("hcId", record.getCheck());
			model.addAttribute("recordEntry", record.getRecords());
			return "addHealthCheckData";
		}
		List<HealthCheckTransaction> trans = new ArrayList<HealthCheckTransaction>();
		Iterator<HealthCheckTransaction > itr = record.getRecords().iterator();
		HealthCheckTransaction tran=record.getRecords().get(0);
		if(null!=id){
			tran.setCheckBy(CommonUtils.getCurrentUser());
			tran.setCheckTime(new Date());
			tran.setEntryDate(CommonUtils.stringToDate(record.getEntryDate()));
			tran.setId(id);
			if(tran.getStatus()==1){
				tran.setComments(null);
			}
			trans.add(tran);			
			tranService.save(trans);
		}

		return "redirect:/healthcheck/detail/"+tran.getServiceModel().getId()+"/"+record.getEntryDate();
	}
	
	
	
	
	private List<List<String>> getWeeklyData(Date currentDate,Integer id, List<String> dates,int view){
		List<String[]> rows = new ArrayList<String[]>();
		String[] row;
		List<HealthCheck> checks=healthService.findAll();
		
		if(checks!=null && checks.size()>0){
			
			Iterator<HealthCheck> itr = checks.iterator();
			while(itr.hasNext()){
				HealthCheck check = (HealthCheck)itr.next();
				if(checks!=null && (check.getServices()!=null && check.getServices().size() >0) ){
					Iterator< ServiceModel> itr2=check.getServices().iterator();
					ServiceModel obj=null;
					while(itr2.hasNext()){
						obj= itr2.next();
						if(id==null || (obj.getId().intValue()==id.intValue())){
							
						List<ServiceComponent> comps=compservice.findByService(obj);
						
						if(2==view){
							Map<String, String[]> weeklyData =fetchCompactView(check,obj,comps,dates);
							Set<String> dateKeys=weeklyData.keySet();
							for(String key:dateKeys){
								rows.add(weeklyData.get(key));
							}
							
						}else{
							if(comps!=null && comps.size()>0){
								for(ServiceComponent comp: comps){
									if(check!=null && obj!=null && comp!=null){
										List<HealthCheckTransaction> trans =tranService.findWeekTrans(dates.get(0), dates.get(6),check,obj,comp);
										for(HealthCheckTransaction tran:trans){
											row = new String[6];
											row[0]=tran.getHealthCheck().getName();
											row[1]=tran.getServiceModel().getName();
											row[2]=tran.getComponent().getName();
											row[3]=tran.getEntryDate().toString().substring(0, 10);
											row[4]=""+tran.getStatus();
											//row[5]=""+tran.getComments();
											row[5]=""+tran.getServiceModel().getId();
											//System.out.println("row == "+row[0]+" "+row[1]+" "+row[2]+" "+row[3]+" "+row[4]);
											rows.add(row);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		String strDates =""; 
		for(String str: dates){
			if(dates.get(6)==str || dates.get(5)==str){
				str="OFF";
			}
			strDates+=str+",";
		}
		
		Map<String, String> map = new Hashtable<String, String>();
		for(String[] data: rows){
			map.put(data[1]+" "+data[2], strDates+","+data[5]);
		}
		
		Set<String> keys =map.keySet();
		for(String strKey: keys){
			for(String[] data: rows){
				String makeKey =data[1]+" "+data[2];
					if(strKey.trim().equals(makeKey.trim())){
						String strRow=(String) map.get(strKey);
						if(strRow.contains(data[3])){
							strRow = strRow.replace(data[3],data[3]+"("+ data[4]+")");
							map.put(strKey, strRow);
					}
				}
			}
		}
		
		List<List<String>> finalList = new ArrayList<List<String>>();
		for(String strKey: keys){
			String[] rowData = ((String)map.get(strKey)).split(",");
			List<String> tmp = new ArrayList<String>();
			tmp.add((String)strKey);
			for(int i=0; i<rowData.length; i++){
				if(!StringUtils.isEmpty(rowData[i])){
					if(rowData[i].contains("(3)")){
						tmp.add("3");											
					}else if(rowData[i].contains("(2)")){
						tmp.add("2");
					}else if(rowData[i].contains("(1)")){
						tmp.add("1");
					}else{
						tmp.add(rowData[i]);
					}
				}

			}
			finalList.add(tmp);
		}
		
		return finalList;
	}
	
	
	
	
	private Map<String, String[]> fetchCompactView(HealthCheck check,ServiceModel obj,List<ServiceComponent> comps,
			List<String> dates){
		
		Map<String, String[]> weeklyData = new HashMap<String, String[]>();
		
		
		String[] row;
		if(comps!=null && comps.size()>0){
			for(ServiceComponent comp: comps){
					List<HealthCheckTransaction> trans =tranService.findWeekTrans(dates.get(0), dates.get(6),check,obj,comp);
					//System.out.println(dates.get(0)+" "+ dates.get(6)+" "+check.getId()+" "+obj.getId()+" "+comp.getId()+" = "+trans.size());
					for(HealthCheckTransaction tran:trans){
						row= new String[6];
						row[0]=tran.getHealthCheck().getName();
						row[1]=tran.getServiceModel().getName();
						row[2]="";
						row[3]=tran.getEntryDate().toString().substring(0, 10);
						row[4]=""+tran.getStatus();
						row[5]="-"+tran.getServiceModel().getId();
						//System.out.println("compact row == "+row[0]+" "+row[1]+" "+row[2]+" "+row[3]+" "+row[4]);
						if((weeklyData.get(row[3])==null) || tran.getStatus()==3){
							weeklyData.put(row[3],row);
						}
						
						
						
					}
				}
			}

		return weeklyData;
	}
	

	private Map<String, String> createHelthCheckMailProps(List<HealthCheckTransaction> trans,HealthCheckRecord record,String status){
		Map<String, String> map= new HashMap<String, String>();
		map.put("mailfrom", System.getProperty("mailfrom"));
		map.put("mailInCC", record.getCheck().getEmailCc());
		map.put("mailInBCC", System.getProperty("healthmailbcc"));
		map.put("mailInTo", record.getCheck().getEmailTo());
		map.put("subject", record.getCheck().getSubject()+" : "+CommonUtils.getEnglishDate(CommonUtils.stringToDate(record.getEntryDate()))+" - "+status);
		return map;
	}
	
	  public static String  generateMailContent(String mailTemplate, List<HealthCheckTransaction> trans, Short mailType){
		  
	    	logger.info("getcreateGRMailContent(Map infomap)");
	    	String template =System.getProperty(mailTemplate);
	    	
	    	if(StringUtils.isEmpty(template)){
	    		//logger.info("mailTemplate  in generateMailContent method  "+mailTemplate);
	    		template =CommonUtils.readFile(new File(mailTemplate));
	    		System.setProperty(mailTemplate+"_content",template);
	    	}
	    	logger.info("getcreateGRMailContent(Map infomap) done");
	    	return  fillUpMailTemplate(template,trans, mailType);

	  }

	  
	  public static String fillUpMailTemplate(String content, List<HealthCheckTransaction> trans,Short mailType){
			logger.info("fillUpMailTemplate()");
			Iterator<HealthCheckTransaction> entries=null;
			if(mailType!=null && mailType==2){
				entries = trans.iterator();
			}else{
			entries = getBusinessViewList(trans).iterator();
			}
			String trs="";
			int count=1;
			String issues ="";
			List<String> trList = new ArrayList<String>(); 
			while(entries.hasNext()){

	        	  HealthCheckTransaction row= entries.next();
					String serviceName=row.getServiceModel().getName();
	        	  String color="#66ff99";
	        	  String status="OK";
	        	  if(row.getStatus()==2){
	        		  color="#ffb366";
	        		  status="Warning";
	        	  }else if(row.getStatus()==3){
	        		  color="#ff8080";
	        		  status="Issue";  
	        		  if(StringUtils.isEmpty(issues)){
	        			  issues+=""+serviceName;
	        		  }else{
	        			  if(!StringUtils.contains(issues, serviceName)){
	        				  issues+=", "+serviceName; 
	        			  }
	        		  }
	        	  }
	        	  String comments=StringUtils.defaultString(row.getComments());
	        	  String component=StringUtils.defaultString(row.getComponent().getName());
	        		  serviceName=row.getServiceModel().getName(); 
	        	  if(mailType!=null && mailType==2){
		        	  String tr="<tr style=background-color:"+color+"><td>"+count+"</td><td align='left'>"+serviceName+"</td><td align='left'>"+component+"</td><td>"+status+"</td><td align='left'>"+comments+"</td></tr>";
		        	  trList.add(tr);
		        	  count++;
	        	  }else{
	        		  if("Warning".equals(status)){
	        			  status="OK"; 
	        			  color="#66ff99";
	        		  }
	        			  String tr="<tr style=background-color:"+color+"><td>"+count+"</td><td align='left'>"+serviceName+"</td><td>"+status+"</td><td align='left'>"+comments+"</td></tr>";
	        			  trList.add(tr);
	        			  count++;
	        	  }
					
	          }
			Iterator itr = trList.iterator();
			while(itr.hasNext()){
				trs+=itr.next();
			}
			
			
				if(StringUtils.isEmpty(issues)){
					issues="None";
				}
				content=content.replace("{{ref_id_ISSUES}}", issues);
				content=content.replace("{{rows}}", trs);
	          logger.info("fillUpMailTemplate() done");
	          return content;
		}
	  
	  private static List<HealthCheckTransaction> getBusinessViewList(List<HealthCheckTransaction> trans){
			logger.info("fillUpMailTemplate()");
			List<HealthCheckTransaction> result = new ArrayList<HealthCheckTransaction>();
			Iterator<HealthCheckTransaction> entries = trans.iterator();
				Map<String, HealthCheckTransaction> serviceMap = new HashMap<String, HealthCheckTransaction>();
				List<String> orderedServices = new ArrayList<String>();
			while(entries.hasNext()){
	        	  HealthCheckTransaction row= entries.next();
					String serviceName=row.getServiceModel().getName();
					if(!orderedServices.contains(serviceName)){
						orderedServices.add(serviceName);
					}
					if(serviceMap.get(serviceName)==null){
						serviceMap.put(serviceName, row);
					}else{
						HealthCheckTransaction existing =serviceMap.get(serviceName);
						if(existing.getStatus()!=3){
							existing.setStatus(row.getStatus());
						}
							String existingComments = existing.getComments()==null ? "":existing.getComments();
							if(StringUtils.isEmpty(existingComments)){
								existing.setComments(row.getComments());
							}else if(!StringUtils.isEmpty(row.getComments())){
									existing.setComments(existingComments+" , "+row.getComments());
							}
							serviceMap.put(serviceName, existing);
							
						}
					
					}
			for(String service:orderedServices){
				result.add(serviceMap.get(service));
			}
	          logger.info("fillUpMailTemplate() done");
	          return result;
		} 
}
