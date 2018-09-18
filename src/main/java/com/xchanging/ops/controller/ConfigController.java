package com.xchanging.ops.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xchanging.ops.model.Config;
import com.xchanging.ops.service.ConfigService;
import com.xchanging.ops.utils.RefDataUtil;


@Controller
@RequestMapping("config")
public class ConfigController {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ComponentController.class);
	@Autowired
	ConfigService service;
	

	
	@RequestMapping(value = {"/load" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		RefDataUtil.readProperties(service);
		return "loadConfig";
	}
	
	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String listRefData(ModelMap model) {

		List<Config> connfigData = service.findAll();
		model.addAttribute("connfigData", connfigData);
		return "configurations";
	}
	
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String saveConfig(@Valid Config connfigData, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "redirect:/config/list";
		}		
		service.save(connfigData);

		model.addAttribute("success");
		return "redirect:/config/list";
	}
	
	@RequestMapping(value = { "/editconfig-{id}" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String editConfigData(@PathVariable Integer id, ModelMap model){
		
		Config connfigData = service.findById(id);
		model.addAttribute("connfigData", connfigData);
		model.addAttribute("edit", true);
		return "addconfigrecord";
	}
	
	
	@RequestMapping(value = { "/editconfig-{id}" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String editConfigData(@Valid Config confiData, BindingResult result,
			ModelMap model, @PathVariable Integer id){
		
		service.update(confiData);
		RefDataUtil.readProperties(service);
		return "success";
	}
	
	@RequestMapping(value = { "/addNew" }, method = RequestMethod.GET)
	public String newService(ModelMap model) {
		Config connfigData = new Config();
		model.addAttribute("connfigData", connfigData);
		model.addAttribute("edit", false);
		return "addconfigrecord";
	}
	
	@RequestMapping(value = { "/addNew" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String newConfigData(@Valid Config confiData, BindingResult result,
			ModelMap model){
		
		service.save(confiData);
		RefDataUtil.readProperties(service);
		return "success";
	}
	
	@RequestMapping(value = { "/delete-config-{id}" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteConfigData(@Valid Config confiData, BindingResult result,
			ModelMap model, @PathVariable Integer id){
		try{
		service.deleteById(id);
		RefDataUtil.readProperties(service);
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		return "redirect:/config/list";
	}
	


}
