package com.xchanging.ops.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.xchanging.ops.model.OpsDocument;
import com.xchanging.ops.model.SendMail;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.model.ServiceSla;
import com.xchanging.ops.model.SlaEntry;
import com.xchanging.ops.model.SlaTransaction;
import com.xchanging.ops.service.DocumentService;
import com.xchanging.ops.service.OpsService;
import com.xchanging.ops.service.ServiceSlaService;
import com.xchanging.ops.service.UserService;
import com.xchanging.ops.utils.CommonMailUtil;
import com.xchanging.ops.utils.CommonUtils;
import com.xchanging.ops.utils.Constants;
import com.xchanging.ops.utils.FileValidator;
import com.xchanging.ops.utils.RefDataUtil;

@Controller
@RequestMapping("/serviceSla")
public class ServiceSlaController {
	private static Logger logger = LoggerFactory.getLogger(ServiceSlaController.class);
	
	@Autowired
	ServiceSlaService serviceSlaService;

	@Autowired
	ServiceSlaService transService;

	@Autowired
	FileValidator fileValidator;

	@Autowired
	OpsService opservice;
	@Autowired
	UserService userService;

	@Autowired
	DocumentService documentService;

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/list-{offset}-{maxResult}" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') OR hasRole('OPS') OR hasRole('USER')")
	public String listServicesSla(@PathVariable Integer offset, @PathVariable Integer maxResult,ModelMap model) {
		if(maxResult==null){
			maxResult=Constants.MAX_PAGE_SIZE;
		}
		if(offset==null){
			offset=Constants.DEFAULT_PAGE_OFFSET;
		}
		List<ServiceSla> slaList = serviceSlaService.findAll(offset,maxResult);
		long count = serviceSlaService.countAll();
		model.addAttribute("pages",CommonUtils.pages(serviceSlaService.countAll(), maxResult));
		model.addAttribute("serviceSlaList", slaList);
		int prev = offset-maxResult;
		int next = offset+maxResult;
		if(next >=count){
			next=0;
		}
		model.addAttribute("offset", offset);
		model.addAttribute("previous", prev);
		model.addAttribute("next", next);
		model.addAttribute("maxResult", maxResult);
		return "serviceSlaList";
	}

	/* This method will list components of particular service */
	@RequestMapping(value = { "/listbyservice-{id}" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') OR hasRole('OPS') OR hasRole('USER')")
	public String listSlaByService(@PathVariable Integer id, ModelMap model) {
		ServiceModel service = opservice.findById(id);
		List<ServiceSla> slaList = serviceSlaService.findByService(service);
		model.addAttribute("serviceSlaList", slaList);
		return "serviceSlaList";
	}

	/**
	 * This method will provide the medium to add a new service.
	 */
	@RequestMapping(value = { "/newservicesla" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String newServiceSla(ModelMap model) {
		ServiceSla serviceSla = new ServiceSla();
		model.addAttribute("serviceSla", serviceSla);
		model.addAttribute("edit", false);
		return "addServicSla";
	}

	/**
	 * This method will provide the medium to add a new service.
	 */
	@RequestMapping(value = { "/newsladata-{date}" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('OPS')")
	public String newSlaData(ModelMap model,@PathVariable String date) {
		
		Date currentDate = null;
		if (!StringUtils.isEmpty(date)) {
			currentDate = CommonUtils.stringToDate(date);
		} else {
			currentDate = CommonUtils.stringToDate(CommonUtils.getYesterday());
		}
		
		
		List<ServiceModel> servicelist =opservice.findAll();
		//List<ServiceSla> slas = serviceSlaService.findAll();
		SlaEntry entry = new SlaEntry();
		for(ServiceModel service :servicelist){
			
			List<ServiceSla> slas = serviceSlaService.findByService(service);
			List<SlaTransaction> slaEntries =transService.findByDate(currentDate);
			Map<Integer, SlaTransaction> entryMap = new HashMap<Integer, SlaTransaction>();
			if(slaEntries!=null && slaEntries.size()>0){
				for(SlaTransaction trn:slaEntries){
					entryMap.put(trn.getServiceSla().getId(), trn);
				}
			}
			
			for (ServiceSla sla : slas) {
				SlaTransaction tran = new SlaTransaction();
				tran.setComments("");
				if(entryMap.size() > 0){
					SlaTransaction trn = entryMap.get(sla.getId());
					if(trn!=null){
						tran=trn;
						if(tran.getComments()==null || tran.getComments().length() <1){
							tran.setComments("");
						}
					}
				}
				tran.setServiceSla(sla);
				entry.addSlaTransaction(tran);
			}
		}
		entry.setEntrydate(currentDate);
		model.addAttribute("entry", entry);
		return "addSlaTracking";
	}

	@RequestMapping(value = { "/newsladata-{date}" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('OPS')")
	public String saveSlaData(@Valid SlaEntry entry, BindingResult result,
			ModelMap model,@PathVariable String date) {
		if (result.hasErrors()) {
			CommonUtils.arrangeErrors(result, model);
			model.addAttribute("commonErr", "Please correct records and retry !");
			model.addAttribute("entry", entry);
			return "addSlaTracking";
		}

		Date today = new Date();

		Iterator<SlaTransaction> itr = entry.getTransactions().iterator();

		while (itr.hasNext()) {
			SlaTransaction row = (SlaTransaction) itr.next();
			if((row.getTotalRecord() < row.getPassedRecord()) && (row.getServiceSla().getSla_type()==0)){
				model.addAttribute("commonErr", "Total records cannot be less than passed records! please check :"+row.getServiceSla().getName());
				model.addAttribute("entry", entry);
				return "addSlaTracking";
			}
			row.setDate(entry.getEntrydate());
			row.setAppUser(CommonUtils.getCurrentUser());
			row.setUpdatedOn(today);

		}
		transService.saveData(entry.getEntrydate(),entry.getTransactions());
		return "redirect:/serviceSla//datewise--";

	}

	@RequestMapping(value = { "/datewise-{week}-{currentdate}" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') OR hasRole('OPS') OR hasRole('USER')")
	public String listDateWise(@PathVariable String week,
			@PathVariable String currentdate, ModelMap model) {

		Date currentDate = null;
		if (!StringUtils.isEmpty(currentdate)) {
			currentDate = CommonUtils.stringToDate(currentdate);
		} else {
			currentDate = new Date();
		}

		if (null != week && !week.equals("-") && !week.equals("")) {
			if ("next".equals(week)) {
				currentDate = CommonUtils.getCustomDate(currentDate, 7);
			} else {
				currentDate = CommonUtils.getCustomDate(currentDate, -7);
			}
		}

		List<String> dates = CommonUtils.getWeekDays(currentDate);
		List<String> slaDates=new ArrayList<String>();
		for(String date:dates){
			slaDates.add(CommonUtils.returnString(CommonUtils.stringToDate(date)));
		}
		
		
		
		List<String> displayDates = CommonUtils.getEnglishDates(dates);
		List<String[]> rows = new ArrayList<String[]>();
		String[] row;
		List<ServiceSla> slas = serviceSlaService.findAll();
		if (slas != null && slas.size() > 0) {
			Iterator<ServiceSla> itr = slas.iterator();
			while (itr.hasNext()) {
				ServiceSla sla = (ServiceSla) itr.next();
				if (sla != null) {
					List<SlaTransaction> trans = transService.findByWeek(
							dates.get(0), dates.get(6), sla);
					for (SlaTransaction tran : trans) {
						row = new String[8];
						row[0] = tran.getServiceSla().getServiceModel()
								.getName();
						row[1] = tran.getServiceSla().getName();
						row[2] = tran.getDate().toString().substring(0, 10);
						row[3] = "" + tran.getPercent();
						row[4] = "" + sla.getTargetGreen();
						row[5] = "" + sla.getTargetAmber();
						row[6]=""+tran.getServiceSla().getId();
						row[7]=""+tran.getServiceSla().getSla_type();
						rows.add(row);
					}
				}
			}
		}
		String strDates = "";
		for (String str : dates) {
			if (dates.get(6) == str || dates.get(5) == str) {
				str = "OFF";
			}
			strDates += str + ",";
		}

		Map<String, String> map = new Hashtable<String, String>();
		for (String[] data : rows) {
			map.put(data[0] + " " + data[1], strDates + "," + data[4] + ","
					+ data[5] + "," + data[6]+ "," + data[7]);

		}
		Set<String> keys = map.keySet();
		for (String strKey : keys) {
			for (String[] data : rows) {
				if (strKey.equals(data[0] + " " + data[1])) {
					String strRow = (String) map.get(strKey);
					if (strRow.contains(data[2])) {
						strRow = strRow.replace(data[2], data[3]);
						map.put(strKey, strRow);
					}

				}
			}
		}

		Set<String> sortedKeays = new TreeSet<String>(keys);

		List<List<String>> finalList = new ArrayList<List<String>>();
		for (String strKey : sortedKeays) {
			String[] rowData = ((String) map.get(strKey)).split(",");
			List<String> tmp = new ArrayList<String>();
			tmp.add((String) strKey);
			for (int i = 0; i < rowData.length; i++) {
				if (!rowData[i].isEmpty()) {
					tmp.add(rowData[i]);
				}

			}
			finalList.add(tmp);
		}

		model.addAttribute("dates", dates);
		model.addAttribute("slaDates", slaDates);
		model.addAttribute("uidates", displayDates);
		model.addAttribute("next", "next");
		model.addAttribute("prev", "prev");
		model.addAttribute("finalList", finalList);
		model.addAttribute("currentdate", CommonUtils.returnString(currentDate));

		return "slaDateWise";
	}
	
	
	@RequestMapping(value = { "/comments-{currentdate}-{slaid}" }, method = RequestMethod.GET)
	public String viewComments(@PathVariable String currentdate, @PathVariable Integer slaid,ModelMap model) {
		Date currentDate=null;
		if(!StringUtils.isEmpty(currentdate)){
			currentDate=CommonUtils.stringToDate(currentdate);
		}
		String comment =transService.findComments(currentDate, slaid);
		model.addAttribute("comment",comment);
		return "comments";
	}
	

	@RequestMapping(value = { "/compact-{currentdate}" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') OR hasRole('OPS') OR hasRole('USER')")
	public String monthlyReport(@PathVariable String currentdate, ModelMap model) {

		Date currentDate = null;
		if (!StringUtils.isEmpty(currentdate)) {
			currentDate = CommonUtils.stringToDate(currentdate);
		} else {
			currentDate = new Date();
		}
		List<List<String>> finalList1 = null;
		List<List<String>> finalList2 = null;
		List<SlaTransaction> trans = new ArrayList<SlaTransaction>();

		if (finalList1 == null || finalList1.size() < 1) {
			finalList1 = compactRows(trans,currentDate);
			finalList2 = monthlySheet(currentDate);
			CommonUtils.createSpreadSheet(finalList1, finalList2);
			RefDataUtil.addAppProps("slaReport", finalList1);
		}
		model.addAttribute("finalList", finalList1);
		String day1 = CommonUtils.lastThreeDays()[0];
		String day2 = CommonUtils.lastThreeDays()[1];
		model.addAttribute("day1", day1.substring(5, day1.length()));
		model.addAttribute("day2", day2.substring(5, day2.length()));
		model.addAttribute("currentdate", CommonUtils.returnString(currentDate));
		
		SendMail sendMail = new SendMail();
		sendMail.setDate(CommonUtils.returnString(currentDate));
		model.addAttribute("sendMail", sendMail);
		return "slaCompact";
	}
	
	
	
	@RequestMapping(value = { "/sendmail" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') OR hasRole('OPS') OR hasRole('USER')")
	public String sendMail(@Valid SendMail mail, BindingResult result,
			ModelMap model) {
		
		@SuppressWarnings("unchecked")
		List<List<String>> finalList1 = (List<List<String>>) RefDataUtil.getAppProps().get(
					"slaReport");
			String doc_location=System.getProperty("DOC_LOCATION");
			String file_name=System.getProperty("sla_attachment");
			CommonMailUtil.sendMailWithAttach(createSlaMailProps(),generateSlaMailContent(System.getProperty("slamailtemplate"),finalList1,mail), doc_location+file_name,file_name);
			model.addAttribute("mailsent", "Mail sent Successfully");
			model.addAttribute("sent","disabled");
		return "slaCompact";
	}
	
	
	
	
	

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newservicesla" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String saveServiceSla(@Valid ServiceSla serviceSla,
			BindingResult result, ModelMap model) {

		MultipartFile file = serviceSla.getFile();
		fileValidator.validate(file, result);
		if (result.hasErrors()) {
			CommonUtils.arrangeErrors(result, model);
			model.addAttribute("serviceSla", serviceSla);
			model.addAttribute("edit", false);
			return "addServicSla";
		}

		if (serviceSla.getFile().getSize() != 0) {
			OpsDocument document = new OpsDocument();
			try {
				document = CommonUtils.saveDocument(serviceSla.getFile(),
						documentService, CommonUtils.getBasicDocument("Service-SLA"));
				serviceSla.setDocId(document.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		serviceSlaService.save(serviceSla);

		model.addAttribute("success", "Service SLA " + serviceSla.getName()
				+ " registered successfully");
		// return "success";
		return "redirect:/serviceSla/list";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-sla-{id}" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String editServiceSla(@PathVariable Integer id, ModelMap model) {
		ServiceSla sla = serviceSlaService.findById(id);
		model.addAttribute("serviceSla", sla);
		// model.addAttribute("account", new Account());
		model.addAttribute("edit", true);
		return "addServicSla";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-sla-{id}" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String updateSla(@Valid ServiceSla sla, BindingResult result,
			ModelMap model, @PathVariable Integer id) {

		MultipartFile file = sla.getFile();
		fileValidator.validate(file, result);
		if (result.hasErrors()) {
			CommonUtils.arrangeErrors(result, model);
			model.addAttribute("serviceSla", sla);
			model.addAttribute("edit", true);
			return "addServicSla";
		}
		if (sla.getFile().getSize() != 0) {
			OpsDocument document = new OpsDocument();
			try {
				document = CommonUtils.saveDocument(sla.getFile(),
						documentService, CommonUtils.getBasicDocument("Service-SLA"));
				sla.setDocId(document.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		serviceSlaService.update(sla);

		//model.addAttribute("success", "service " + sla.getName()+ " updated successfully");
		return "redirect:/serviceSla/list--";
	}

	/**
	 * This method will delete an user by it's ID value.
	 */
	@RequestMapping(value = { "/delete-sla-{id}" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteServiceSla(@PathVariable String id) {
		serviceSlaService.delete(id);
		return "redirect:/serviceSla/list--";
	}

	/**
	 * This method will provide Account list to views
	 */
	@ModelAttribute("services")
	public List<ServiceModel> initializeAccounts() {
		return opservice.findAll();
	}

	private Map<String, String> createSlaMailProps() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mailfrom", System.getProperty("mailfrom"));
		map.put("mailInCC", System.getProperty("slamailcc"));
		map.put("mailInBCC", System.getProperty("slamailbcc"));
		map.put("mailInTo", System.getProperty("slamailto"));
		map.put("subject", "SLA Tracking and forcasting");
		return map;
	}

	private String generateSlaMailContent(String mailTemplate,
			List<List<String>> trans,SendMail mail) {

		logger.info("getcreateSLAContent(Map infomap)");
		String template = System.getProperty(mailTemplate + "_content");

		if (StringUtils.isEmpty(template)) {
			// logger.info("mailTemplate  in generateMailContent method  "+mailTemplate);
			template = CommonUtils.readFile(new File(mailTemplate));
			System.setProperty(mailTemplate + "_content", template);
		}
		logger.info("getcreateSLAMailContent(Map infomap) done");
		return fillUpMailTemplate(template, trans,mail);

	}

	private String fillUpMailTemplate(String content, List<List<String>> trans,SendMail mail) {

		if(trans==null || trans.size()==0){
			return content;
		}
		logger.info("fillUpMailTemplate()");
		Iterator<List<String>> entries = trans.iterator();

		String trs = "";
		while (entries.hasNext()) {
			List<String> row = entries.next();
			String color = "W";
			if(row.size() > 8){
				if(row.get(4)!="NA"){
					color=row.get(8);
				}
				
			}
			trs+="<tr>"
					+ "<td>"+row.get(0)+"</td>"
							+ "<td>"+row.get(1)+"</td>"
									+ "<td>"+row.get(2)+"</td>"
											+ "<td>"+row.get(3)+"</td>"
											+ "<td style='background-color:"+colorCode(color)+"'>"+row.get(4)+"</td>"
					+"<td>"+row.get(5)+"</td>"
					+"<td style='background-color:"+colorCodeForForecast(row.get(7))+"'>"+row.get(6)+"</td>"
							+ "</tr>";

		}
		content = content.replace("{{rows}}", trs);
		content = content.replace("{{ops_comments}}", mail.getComments());
		return content;
	}

	
	String colorCode(String record){

		if (record.equals("A")) {
			return "#FFC733";
		} else if (record.equals("R")) {
			return "#FF0000";
		} else if (record.equals("W")) {
			return "";
		}else if (record.equals("W")) {
			return "#FBE104";
		}else {
			return "#33FF61";
		}

	}
	
	String colorCodeForForecast(String record){
		if (record.equals("A")) {
			return "#ffbb99";
		} else if (record.equals("R")) {
			return "#ff3333";
		} else if (record.equals("W")) {
			return "";
		}else if (record.equals("W")) {
			return "#FBE104";
		}else {
			return "#bbff99";
		}
	}
	
	
	
/*	  private String decideColor(SlaTransaction record,double percent){
		  String colColor="";
		  if(record!=null){
		  if(percent > record.getServiceSla().getTargetAmber() && percent < record.getServiceSla().getTargetGreen()){
				colColor ="#ffbb99";
			}else if(percent < record.getServiceSla().getTargetAmber()){
				colColor ="red";
			}else{
				colColor ="#bbff99";
			}
		  }
		  return colColor;
	  }*/
	  
	  private char decideColorForXLS(SlaTransaction record,double percent){
		  char colColor = 0;
		  if(record!=null){
			  if(record.getServiceSla().getSla_type()==1){
					  if(0.0==percent){
						  colColor ='W';
					  }else if(percent > record.getServiceSla().getTargetGreen() && record.getPassedRecord() <=record.getServiceSla().getTargetAmber()){
						  colColor ='A';
					  }else if(percent > record.getServiceSla().getTargetAmber()){
						  colColor ='R';
					  }else if(percent <=record.getServiceSla().getTargetGreen()){
						  colColor ='G';
					  }else{
						  colColor ='W'; 
					  }
			  }else{
				  if(percent > record.getServiceSla().getTargetAmber() && percent < record.getServiceSla().getTargetGreen()){
						colColor ='A';
					}else if(percent ==0.0){
						colColor ='W';
					}else if(percent < record.getServiceSla().getTargetAmber()){
						colColor ='R';
					}else{
						colColor ='G';
					}
			  }
		  }
		  return colColor;
	  }

	private List<List<String>> monthlySheet(Date currentdate) {
		Map<String, List<String>[]> map = new HashMap<String, List<String>[]>();
		//Date today = new Date();
		Date today = currentdate;
		int year = today.getYear() + 1900;
		int month = today.getMonth() + 1;
		String monthSTR = "";
		if (month < 10) {
			monthSTR = "0" + month;
		} else {
			monthSTR += month;
		}
		List<String> header1 = new ArrayList<String>();
		List<String> header2 = new ArrayList<String>();
		for (String[] dayNames : CommonUtils.monthDayNames(year,
				today.getMonth())) {
			header1.add(dayNames[1]);
			header2.add(dayNames[0]);
		}
		List<String> entries = new ArrayList<String>();
		List<String>[] headers = new ArrayList[2];
		headers[0] = header1;
		headers[1] = header2;
		map.put("headers", headers);
		entries.add("headers");

		List<ServiceSla> serviceList = serviceSlaService.findAllOrderbyService();
		for (ServiceSla sla : serviceList) {
			// List<SlaTransaction> trns
			// =transService.findMonthlyCompact(""+year, monthSTR,sla.getId());
			List<SlaTransaction> trns = transService.findMonthlyCompact(""
					+ year, monthSTR, sla.getId());
			List<String>[] records = new ArrayList[3];
			List<String> serviceD = new ArrayList<String>();
			List<String> serviceN = new ArrayList<String>();
			List<String> serviceP = new ArrayList<String>();
			for (String str : header2) {
				serviceD.add("¢" + str);
				serviceN.add("¢" + str);
				serviceP.add("¢" + str);
			}
			serviceD.add(0, sla.getName() + "-D");
			serviceN.add(0, sla.getName() + "-N");
			serviceP.add(0, sla.getName() + "-P");
			String slaName = "";
			int slaType=0;
			
			double totalMonthly =0.0;
			double passedMonthly =0.0;
			double percMonthly =0.0;
			SlaTransaction currentTrn = null;
			for (SlaTransaction trn : trns) {
				currentTrn = trn;
				slaName = trn.getServiceSla().getName();
				if(trn.getServiceSla().getSla_type()==1){
					slaType=1;
				}

				for (int i = 0; i < serviceD.size(); i++) {
					String day = "¢" + trn.getDate().getDate();
					if (serviceD.get(i).equals(day)) {
						serviceD.remove(i);
						serviceD.add(i, trn.getTotalRecord() + "");
						serviceN.remove(i);
						if(slaType==1){
							serviceN.add(i, trn.getPassedRecord() + "("+decideColorForXLS(trn,trn.getPassedRecord())+")");
						}else{
							serviceN.add(i, trn.getPassedRecord()  + "("+decideColorForXLS(trn,trn.getPercent())+")");
						}
						serviceP.remove(i);
						serviceP.add(i, trn.getPercent() + "");
					}
					totalMonthly+=trn.getTotalRecord();
					passedMonthly+=trn.getPassedRecord();
				}
			}
			percMonthly=(passedMonthly/totalMonthly)*100;
			
			//serviceP.add(1, sla.getTargetAmber()+"");
			//serviceP.add(2, sla.getTargetGreen()+"");	
			
			if(slaType==0){
				records[0] = serviceD;
				DecimalFormat df = new DecimalFormat("###.##");
				if(currentTrn!=null){
					serviceP.add(serviceP.size(),df.format(percMonthly)+"("+decideColorForXLS(currentTrn,percMonthly)+")");					
				}

				records[2] = serviceP;
			}
			records[1] = serviceN;
			

			map.put(slaName, records);
		}

		List<List<String>> finalList = new ArrayList<List<String>>();
		for(ServiceSla sla:serviceList){
			entries.add(sla.getName());
		}

		Iterator itr = entries.iterator();
		while (itr.hasNext()) {
			String keyName = (String) itr.next();
			List[] list = map.get(keyName);
			if(list!=null){
				if (!"headers".equalsIgnoreCase(keyName)) {
					if (list[0]!=null && list.length > 0) {
						finalList.add(list[0]);
					}
					if (list[1]!=null && list.length > 1) {
						finalList.add(list[1]);
					}
					if (list[2]!=null && list.length > 2) {
						finalList.add(list[2]);
					}
						
					
				} else {
					if (list!=null && list.length > 0) {

						list[0].add(0, "SLA Name");
						list[1].add(0, "");
						finalList.add(0, list[0]);
						if (list.length > 1) {
							finalList.add(1, list[1]);
						}
					}
				}
			}

		}

		for (List<String> lst : finalList) {
			if(lst!=null){
				for (int i = 0; i < lst.size(); i++) {
					if (lst.get(i).contains("¢")) {
						lst.remove(i);
						lst.add(i, "");
					}
				}
			}

		}

		return finalList;
	}

	private List<List<String>> compactRows(List<SlaTransaction> trans,Date currentdate) {

		List<List<String>> rows = new ArrayList<List<String>>();
		
		Map <String, List<String>> rowMap = new HashMap<String, List<String>>();
		
		
		
		List<String> row = null;
		Date today = currentdate;
		int year = today.getYear() + 1900;
		int month = today.getMonth() + 1;
		String monthSTR = "";
		if (month < 10) {
			monthSTR = "0" + month;
		} else {
			monthSTR += month;
		}
		trans = transService.findByMonth("" + year, monthSTR);

		Iterator<SlaTransaction> entries = trans.iterator();
		//int count = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		while (entries.hasNext()) {
			//count++;
			SlaTransaction record = entries.next();
			row = new ArrayList<String>();

				DecimalFormat df = new DecimalFormat("###.##");
				logger.debug("name " + record.getServiceSla().getName()
						+ " target =" + record.getServiceSla().getTargetGreen()
						+ " Current = " + record.getPercent());
				//row.add(count + "");
				row.add(record.getServiceSla().getServiceModel().getName()+"-"+record.getServiceSla().getName());
				row.add(record.getServiceSla().getDescription());
				row.add(CommonUtils.intToString(record.getServiceSla().getTargetGreen()));
				double passed=record.getPassedRecord();
				double total=record.getTotalRecord();	
				double dblPercent=0.0;
				if(record.getServiceSla().getSla_type()==0){
					if(total > 0.0){
						dblPercent = (passed/total)*100;
					}
					if(total==0.0){
						row.add("NA");				
					}else{
						row.add(df.format(dblPercent));
					}	
					Double[] goodDayVals =serviceSlaService.findGoodDayAvg(record.getServiceSla().getId(), ""+month, ""+year, record.getServiceSla().getTargetGreen());
					Double[] slaResults = CommonUtils.calculateSLARequired(today, total, passed, record.getServiceSla().getTargetGreen(),dblPercent,goodDayVals);					
					
					row.add(df.format(slaResults[0]));
					row.add(df.format(slaResults[1]));
					//for deciding color of sla forecast
					if(slaResults[1]<=100 && slaResults[1]>=record.getServiceSla().getTargetGreen()){
						row.add("G");
					}else if(slaResults[1]>=record.getServiceSla().getTargetAmber() && slaResults[1]<record.getServiceSla().getTargetGreen()){
						row.add("A");
					}else{
						row.add("R");
					}
					
					//for deciding color of current month sla
					
					if(dblPercent<=100 && dblPercent>=record.getServiceSla().getTargetGreen()){
						row.add("G");
					}else if(dblPercent>=record.getServiceSla().getTargetAmber() && dblPercent<record.getServiceSla().getTargetGreen()){
						row.add("A");
					}else if(dblPercent <= 0){
						row.add("W");
					}else{
						row.add("R");
					}
				}else{
					
					dblPercent = serviceSlaService.findSla_averageByMonthAndId(year, month, record.getServiceSla().getId());				
					row.add(df.format(dblPercent));
					row.add("NA");
					row.add("NA");
				}

				char col1=decideColorForXLS(record,dblPercent);
				row.add(col1+"");
				
				rowMap.put(record.getServiceSla().getServiceModel().getName()+"-"+record.getServiceSla().getName(), row);
				//rows.add(row);
		}
		
		int count = 0;
		Set<String> sortedKeays = new TreeSet<String>(rowMap.keySet());
		Iterator<String> itr = sortedKeays.iterator();
		
		while(itr.hasNext()){
			count++;
			row = new ArrayList<String>();
			row=rowMap.get(itr.next());
			row.add(0, count+"");
			rows.add(row);
		}
		return rows;
	}


}
