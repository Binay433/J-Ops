package com.xchanging.ops.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.multipart.MultipartFile;

import com.xchanging.ops.dao.ChangeComponentDAO;
import com.xchanging.ops.dao.ChangeComponentDAOImpl;
import com.xchanging.ops.model.Account;
import com.xchanging.ops.model.EmergencyChange;
import com.xchanging.ops.model.OpsDocument;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.service.AccountService;
import com.xchanging.ops.service.ChangeService;
import com.xchanging.ops.service.DocumentService;
import com.xchanging.ops.service.OpsService;
import com.xchanging.ops.service.UserService;
import com.xchanging.ops.utils.CommonUtils;
import com.xchanging.ops.utils.Constants;
import com.xchanging.ops.utils.FileValidator;

@Controller
@RequestMapping("/services")
public class ServiceController {
	private static Logger logger = LoggerFactory.getLogger(ServiceController.class);
	
	@Autowired
	OpsService opsService;
	
	@Autowired
	AccountService accountService;
	
	 @Autowired  
	 FileValidator fileValidator;  
	 
	@Autowired
	UserService userService;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	ChangeService changeService;
	
	
	
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = {"/list-{offset}-{maxResult}" }, method = RequestMethod.GET)
	public String listServices(@PathVariable Integer offset, @PathVariable Integer maxResult,ModelMap model) {
		if(maxResult==null){
			maxResult=Constants.MAX_PAGE_SIZE;
		}
		if(offset==null){
			offset=Constants.DEFAULT_PAGE_OFFSET;
		}
		List<ServiceModel> services = opsService.findAll(offset,maxResult);
		List<EmergencyChange> changes = changeService.findAll();
		long count = opsService.countAll();
		Map<Integer, Integer> serviceCountMap = new HashMap<Integer, Integer>();
		
		//if(serviceId==0){		
		for(EmergencyChange change :changes){
			if(Constants.CHANGE_STATUS.equals(change.getStatus())){
				for(ServiceModel servmodel:change.getServices()){
					if(serviceCountMap.get(servmodel.getId())==null){
						serviceCountMap.put(servmodel.getId(), 1);
					}else{
						int oldValue =serviceCountMap.get(servmodel.getId());
						serviceCountMap.put(servmodel.getId(), oldValue+1);
					}
				}
			}
		}
		
		for (ServiceModel service : services){
			if(null!=serviceCountMap.get(service.getId())){
				service.setChangecount(serviceCountMap.get(service.getId()));
			}
		}
		model.addAttribute("pages",CommonUtils.pages(count, maxResult));

		
		int prev = offset-maxResult;
		int next = offset+maxResult;
		if(next >=count){
			next=0;
		}
		model.addAttribute("offset", offset);
		model.addAttribute("previous", prev);
		model.addAttribute("next", next);
		model.addAttribute("maxResult", maxResult);
		model.addAttribute("services", services);
		return "serviceslist";

	}

	/**
	 * This method will provide the medium to add a new service.
	 */
	@RequestMapping(value = { "/newservice" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String newService(ModelMap model) {
		ServiceModel service = new ServiceModel();
		model.addAttribute("service", service);
		model.addAttribute("account", new Account());
		model.addAttribute("edit", false);
		return "addServrice";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newservice" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String saveService(@Valid ServiceModel service, BindingResult result,
			ModelMap model) {

		  MultipartFile file = service.getFile();  
		  fileValidator.validate(file, result);  
		if (result.hasErrors()) {
			CommonUtils.arrangeErrors(result, model);
			model.addAttribute("service", service);
			return "addServrice";
		}
		if(service.getFile()!=null && service.getFile().getSize()!=0){
			OpsDocument document = new OpsDocument();
			try {
				document = CommonUtils.saveDocument(service.getFile(), documentService, CommonUtils.getBasicDocument("Service"));
				service.setDocId(document.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		opsService.save(service);

		model.addAttribute("success", "Service " + service.getName() + " registered successfully");
		//return "success";
		return "addServiceSuccess";
	}
	
	
	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-service-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String editService(@PathVariable Integer id, ModelMap model) {
		ServiceModel service = opsService.findById(id);
		model.addAttribute("service", service);
		model.addAttribute("account", new Account());
		model.addAttribute("edit", true);
		return "addServrice";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-service-{id}" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String updateService(@Valid ServiceModel service, BindingResult result,
			ModelMap model, @PathVariable Integer id) {

		  MultipartFile file = service.getFile();  
		  fileValidator.validate(file, result); 
		if (result.hasErrors()) {
			CommonUtils.arrangeErrors(result, model);
			model.addAttribute("service", service);
			model.addAttribute("account", new Account());
			model.addAttribute("edit", true);
			return "addServrice";
		}

		if(service.getFile()!=null && service.getFile().getSize()!=0){
			OpsDocument document = new OpsDocument();
			try {
				document = CommonUtils.saveDocument(service.getFile(), documentService, CommonUtils.getBasicDocument( "Service"));
				service.setDocId(document.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		opsService.update(service);

		model.addAttribute("success", "service " + service.getName()+ " updated successfully");
		return "addServiceSuccess";
	}
	

	/**
	 * This method will delete an user by it's ID value.
	 */
	@RequestMapping(value = { "/delete-service-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String deleteService(@PathVariable String id,ModelMap model) {
		try{
			opsService.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("error", "Cannot delete the service due to DB Constraints");
		}
		
		return "forward:/services/list--";
	}
	
	
	
	/**
	 * This method will delete an user by it's ID value.
	 */
	@RequestMapping(value = { "/delete-servicedoc-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String deleteServiceDoc(@PathVariable String id,ModelMap model) {

		if(id!=null){
			Integer serviceId = Integer.parseInt(id);
			ServiceModel service = opsService.findById(serviceId);
			if(service!=null && service.getDocId()!=null){
				try {
					documentService.deleteById(service.getDocId());
					opsService.deleteDocument(service.getId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return "redirect:/services/list-0-10";
	}
	
	/**
	 * This method will provide Account list to views
	 */
	@ModelAttribute("accounts")
	public List<Account> initializeAccounts() {
		return accountService.findAll();
	}
	
	
	

	
	
	
	
	
}
