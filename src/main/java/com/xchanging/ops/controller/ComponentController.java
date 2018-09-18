package com.xchanging.ops.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.xchanging.ops.model.Account;
import com.xchanging.ops.model.OpsDocument;
import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.ServiceModel;
import com.xchanging.ops.service.DocumentService;
import com.xchanging.ops.service.OpsService;
import com.xchanging.ops.service.ServiceComponentService;
import com.xchanging.ops.service.UserService;
import com.xchanging.ops.utils.CommonUtils;
import com.xchanging.ops.utils.FileValidator;

@Controller
@RequestMapping("/compservices")
public class ComponentController {
	private static Logger logger = LoggerFactory.getLogger(ComponentController.class);
	@Autowired
	ServiceComponentService compService;
	
	@Autowired
	OpsService opservice ;
	
	@Autowired
	DocumentService documentService;
	
	 @Autowired  
	 FileValidator fileValidator;  
	 
/*	@Autowired
	UserService userService;*/
	
/*This method will list all the components */
	
	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String listServiceComponent(ModelMap model) {

/*		List<ServiceComponent> compList = compService.findAll();
		model.addAttribute("compList", compList);*/
		List<ServiceComponent> compList = new ArrayList<ServiceComponent>();
		List<ServiceModel> services = opservice.findAll();
		for(ServiceModel serv :services){
			List<ServiceComponent> tmpList = compService.findByService(serv);
			compList.addAll(tmpList);
		}
		model.addAttribute("compList", compList);
		return "componentlist";
	}
	
	
/*This method will list components of particular service */
	@RequestMapping(value = { "/listbyservice-{id}" }, method = RequestMethod.GET)
	public String listComponentByService(@PathVariable Integer id, ModelMap model) {
		ServiceModel service =opservice.findById(id);
		List<ServiceComponent> compList = compService.findByService(service);
		model.addAttribute("serviceid", id);
		model.addAttribute("compList", compList);
		return "componentlist";
	}
	
	
	
	
	/**
	 * This method will provide the medium to add a new component.
	 */
	@RequestMapping(value = { "/newcomponent-{serviceid}" }, method = RequestMethod.GET)
	public String newService(ModelMap model,@PathVariable Integer serviceid) {
		ServiceComponent component = new ServiceComponent();
		if(serviceid!=null){
			ServiceModel opsService = opservice.findById(serviceid);
			component.setServiceModel(opsService);			
		}

		model.addAttribute("component", component);
		model.addAttribute("serviceModel", new ServiceModel());
		model.addAttribute("edit", false);
		return "addcomponent";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving form in database. It also validates the user input
	 */
	
	@RequestMapping(value = { "/newcomponent-{serviceid}" }, method = RequestMethod.POST)
	public String saveService(@Valid ServiceComponent component,@PathVariable Integer serviceid, BindingResult result,
			ModelMap model) {

		  MultipartFile file = component.getFile();  
		  fileValidator.validate(file, result);  
		if (result.hasErrors()) {
			CommonUtils.arrangeErrors(result, model);
			model.addAttribute("component", component);
			model.addAttribute("serviceModel", new ServiceModel());
			model.addAttribute("edit", false);
			return "addcomponent";
		}
	

		if(component.getFile().getSize()!=0){
			OpsDocument document = new OpsDocument();
			try {
				document = CommonUtils.saveDocument(component.getFile(), documentService, CommonUtils.getBasicDocument("Component"));
				component.setDocId(document.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		compService.save(component);

		//model.addAttribute("success", "Service " + component.getName() + " registered successfully");
		//return "success";
		if(serviceid!=null){
			return "redirect:/compservices/listbyservice-"+serviceid;
		}else{
			return "redirect:/compservices/list";			
		}

	}
		
	/**
	 * This method will provide the medium to update an existing comcponent.
	 */
	@RequestMapping(value = { "/edit-component-{id}-{serviceid}" }, method = RequestMethod.GET)
	public String editComponent(@PathVariable Integer id,@PathVariable Integer serviceid, ModelMap model) {
		ServiceComponent component = compService.findById(id);
		model.addAttribute("component", component);
		model.addAttribute("serviceModel", new ServiceModel());
		model.addAttribute("edit", true);
		return "addcomponent";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating component in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-component-{id}-{serviceid}" }, method = RequestMethod.POST)
	public String updateComponent(@Valid ServiceComponent component,@PathVariable Integer serviceid, BindingResult result,
			ModelMap model, @PathVariable Integer id) {

		  MultipartFile file = component.getFile();  
		  fileValidator.validate(file, result);  
		if (result.hasErrors()) {
			CommonUtils.arrangeErrors(result, model);
			model.addAttribute("component", component);
			model.addAttribute("serviceModel", new ServiceModel());
			model.addAttribute("edit", true);
			return "addcomponent";
		}
		if(component.getFile().getSize()!=0){
			OpsDocument document = new OpsDocument();
			try {
				document = CommonUtils.saveDocument(component.getFile(), documentService, CommonUtils.getBasicDocument("Service"));
				component.setDocId(document.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		compService.update(component);

		model.addAttribute("success", "component " + component.getName()+ " updated successfully");
		if(serviceid!=null){
			return "redirect:/compservices/listbyservice-"+serviceid;
		}else{
		return "redirect:/compservices/list";
		}
	}
		
	/**
	 * This method will provide Account list to views
	 */
	@ModelAttribute("services")
	public List<ServiceModel> initializeAccounts() {
		return opservice.findAll();
	}
	
	@RequestMapping(value = { "/delete-component-{id}-{serviceid}" }, method = RequestMethod.GET)
	public String deleteComponent(@PathVariable String id,@PathVariable Integer serviceid) {
		compService.delete(id);
		if(serviceid==null){
		return "redirect:/compservices/list";
		}else{
		return "redirect:/compservices/listbyservice-"+serviceid;
		}
	}
	
}
